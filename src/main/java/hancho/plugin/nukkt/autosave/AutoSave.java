package hancho.plugin.nukkt.autosave;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.scheduler.AsyncTask;

import static hancho.plugin.nukkt.autosave.InfoTask.MINE_COIN;

public class AutoSave extends PluginBase {
    public static final int period = 20 * 60 * 60 * 2;

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
        }, 20 * 60 * 60 * 2, 20 * 60 * 2);
    }

    public void schedule(){
        this.getServer().getScheduler().scheduleDelayedTask(this, new InfoTask(), period - (20 * 10));
        this.getServer().getScheduler().scheduleDelayedTask(this, new SaveTask(this), period, true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("서버저장")){
            this.getServer().broadcastMessage("§l§o§c===========§f잠시만요!§c===========");
            this.getServer().broadcastMessage("§l" + MINE_COIN + " 서버가 데이터 저장 작업을 실행합니다.");
            this.getServer().broadcastMessage("§l" + MINE_COIN + " 약간의 서버 렉이 발생할 수 있습니다.");
            this.getServer().broadcastMessage("§l§o§c===========§f이 작업은 서버 재부팅을 대신합니다§c===========");
            Save save = new Save(this);
            save.start();
        }else if(command.getName().equals("월드에딧리로드")){
            this.getServer().shutdown();
            Plugin p = this.getServer().getPluginManager().getPlugin("FastAsyncWorldEdit");
            this.getServer().getPluginManager().disablePlugin(p);
            Plugin newPlugin = this.getServer().getPluginManager().loadPlugin("/root/season3/plugins/FastAsyncWorldEdit.jar");
            this.getServer().getPluginManager().enablePlugin(newPlugin);
        }
        return true;
    }
}
