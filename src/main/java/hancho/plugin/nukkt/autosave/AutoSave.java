package hancho.plugin.nukkt.autosave;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;

public class AutoSave extends PluginBase {
    public static final int period = 20 * 60 * 10;

    @Override
    public void onEnable() {
        this.schedule();
        this.getServer().getScheduler().scheduleDelayedRepeatingTask(this, new AsyncTask() {
            @Override
            public void onRun() {
                if(getServer().getOnlinePlayers().size() < 1){
                    getServer().shutdown();
                }
            }
        }, 20 * 60 * 60 * 2, 20 * 60 * 10);
    }

    public void schedule(){
        this.getServer().getScheduler().scheduleDelayedTask(this, new SaveTask(this), period, true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("서버저장")){
            Save save = new Save(this);
            save.start();
        }
        return true;
    }
}
