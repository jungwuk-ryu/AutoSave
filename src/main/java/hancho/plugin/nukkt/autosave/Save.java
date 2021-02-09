package hancho.plugin.nukkt.autosave;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

public class Save {
    private Server server;
    private final boolean debugMode;

    public Save(){
        this.debugMode = AutoSave.DEBUG_MODE;
        this.server = Server.getInstance();
    }

    public void debug(String msg){
        if(!debugMode) return;
        this.server.getLogger().info("§7§o " + msg);
    }

    public void start() {
        Map<String, Plugin> plugins = this.server.getPluginManager().getPlugins();
        Set<String> keySet = plugins.keySet();
        int pluginCount = plugins.size() + 1;

        for(String name : keySet){
            Plugin plugin = plugins.get(name);
            boolean isSaveable = false;
            pluginCount --;

            for(Player player : this.server.getOnlinePlayers().values()){
                player.sendTip("§l§o저장 §b" + pluginCount + "§f개 남음");
            }

            if(AutoSave.INVOKE_ON_DISABLE.contains(plugin.getName())){  // onDisable 메소드 실행
                try {
                    this.debug(plugin.getName() + " : onDisable");
                    plugin.getClass().getDeclaredMethod("onDisable").invoke(plugin);
                } catch (Exception e) {
                    this.server.getLogger().error("", e);
                }
                return;
            }

            for (Method method : plugin.getClass().getDeclaredMethods()) {
                if(method.getName().equals("save")) {
                    Class<?>[] paramTypes = method.getParameterTypes();

                    try {
                        if (method.getParameterCount() == 1 && (paramTypes[0].equals(boolean.class) || paramTypes[0].equals(Boolean.class))) {
                            this.debug(plugin.getName() + " : with param (false)");

                            method.invoke(plugin, false);
                            isSaveable = true;
                        }else if (method.getParameterCount() == 0) {
                            this.debug(plugin.getName() + " : without param");

                            method.invoke(plugin);
                            isSaveable = true;
                        }
                    }  catch (Exception e) {
                        this.server.getLogger().error("", e);
                    }
                }
            }
            if(!isSaveable) this.debug(plugin.getName() + " : §c저장되지 않음");
        }

        this.server.getLogger().warning("서버 저장 완료");
    }
}
