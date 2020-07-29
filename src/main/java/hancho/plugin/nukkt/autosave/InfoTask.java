package hancho.plugin.nukkt.autosave;

import cn.nukkit.Server;
import cn.nukkit.scheduler.Task;

public class InfoTask extends Task {
    public static char MINE_COIN = '\uE102';
    public void onRun(int i) {
        Server server = Server.getInstance();
        server.broadcastMessage("§l§o§c===========§f잠시만요!§c===========");
        server.broadcastMessage("§l" + MINE_COIN + " 서버가 10초 후 데이터 저장 작업을 실행합니다.");
        server.broadcastMessage("§l" + MINE_COIN + " 약간의 서버 렉이 발생할 수 있습니다.");
        server.broadcastMessage("§l§o§c===========§f이 작업은 서버 재부팅을 대신합니다§c===========");
    }
}
