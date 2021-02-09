package hancho.plugin.nukkt.autosave;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.scheduler.TaskHandler;
import cn.nukkit.utils.Config;

import java.util.ArrayList;
import java.util.HashSet;

public class AutoSave extends PluginBase {
    public static final String PREFIX = "§7§o";
    public static int SAVE_PERIOD = Integer.MAX_VALUE;
    public static int SHUTDOWN_DELAY = Integer.MAX_VALUE;
    public static HashSet<String> INVOKE_ON_DISABLE = new HashSet<>();
    public static boolean DEBUG_MODE = false;
    public AsyncTask saveTask;

    private TaskHandler shutdownTaskHandler;
    private TaskHandler saveTaskHandler;

    @Override
    public void onEnable() {
        reload();
    }

    public void reload(){
        this.reloadConfig();
        Config config = this.getConfig();
        if(!config.exists("invoke_OnDisable")) config.set("invoke_OnDisable", new ArrayList<>());
        if(!config.exists("debug")) config.set("debug", false);
        if(!config.exists("save_period")) config.set("save_period", 20 * 60 * 10);
        if(!config.exists("shutdown_delay")) config.set("shutdown_delay", 20 * 60 * 10);
        config.save();

        ArrayList<String> targetPlugins = (ArrayList<String>) config.get("invoke_OnDisable");
        INVOKE_ON_DISABLE.clear();
        INVOKE_ON_DISABLE.addAll(targetPlugins);
        DEBUG_MODE = (boolean) config.get("debug");
        SAVE_PERIOD = (int) config.get("save_period");
        SHUTDOWN_DELAY = (int) config.get("shutdown_delay");

        saveTask = new AsyncTask() {
            @Override
            public void onRun() {
                new Save().start();
            }
        };

        this.schedule();
    }

    public void schedule(){
        if(this.shutdownTaskHandler != null) this.shutdownTaskHandler.cancel();
        if(this.saveTaskHandler != null) this.saveTaskHandler.cancel();

        this.shutdownTaskHandler =
                this.getServer().getScheduler()
                        .scheduleDelayedRepeatingTask(this, new AsyncTask() {
                            @Override
                            public void onRun() {
                                if(getServer().getOnlinePlayers().size() < 1){
                                    getServer().shutdown();
                                }
                            }
                        }, SHUTDOWN_DELAY, 20 * 60 * 10);

        this.saveTaskHandler =
                this.getServer().getScheduler()
                        .scheduleDelayedRepeatingTask(this, saveTask, SAVE_PERIOD, SAVE_PERIOD, true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            sender.sendMessage(PREFIX + "as 저장\n" + PREFIX + "/as reload");
        }else if(args[0].equals("저장")){
            sender.sendMessage(PREFIX + "비동기에서 저장 시작");
            this.getServer().getScheduler().scheduleAsyncTask(this, saveTask);
        }else if(args[0].equals("reload")){
            sender.sendMessage(PREFIX + "비동기에서 콘피그 새로고침 하는 중");
            this.getServer().getScheduler().scheduleAsyncTask(this, new AsyncTask() {
                @Override
                public void onRun() {
                    reload();
                }
            });
        }
        return true;
    }
}
