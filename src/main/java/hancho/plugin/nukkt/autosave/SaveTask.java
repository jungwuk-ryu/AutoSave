package hancho.plugin.nukkt.autosave;

import cn.nukkit.scheduler.AsyncTask;

public class SaveTask extends AsyncTask {
    AutoSave autoSave;

    public SaveTask(AutoSave autoSave){
        this.autoSave = autoSave;
    }

    public void onRun() {
        Save save = new Save(this.autoSave);
        save.start();
        this.autoSave.schedule();
    }
}
