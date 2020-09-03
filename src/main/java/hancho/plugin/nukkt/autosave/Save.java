package hancho.plugin.nukkt.autosave;

import banMaster.BanMaster;
import bookLibrary.BookLibrary;
import com.hancho.blocknametag.BlockNameTagManager;
import com.hancho.simplegambling.SimpleGambling;
import cn.nukkit.Player;
import cn.nukkit.Server;
import co.hancho.customResourcePack.CustomResourcePack;
import hFriend.HFriend;
import hancho.Auction.Auction;
import hancho.ChestLog.ChestLog;
import hancho.Hshop.Hshop;
import hancho.nukkit.plugin.commandsign.commandsign;
import hancho.plugin.keyWords.KeyWords;
import hancho.plugin.nukkit.KkutuInNukkit.KkutuInNukkit;
import hancho.plugin.nukkit.announcer.Announcer;
import hancho.plugin.nukkit.autocrop.AutoCrop;
import hancho.plugin.nukkit.check.check;
import hancho.plugin.nukkit.commandshortcut.CommandShortcut;
import hancho.plugin.nukkit.connectionTime.ConnectionTime;
import hancho.plugin.nukkit.dashblock.DashBlock;
import hancho.plugin.nukkit.hwarp.hwarp;
import hancho.plugin.nukkit.mailbox.mailbox;
import hancho.plugin.nukkit.particlemanager.ParticleManager;
import hancho.plugin.nukkit.rewardSign.RewardSign;
import hancho.plugin.nukkit.showhealth.showhealth;
import hancho.plugin.nukkit.sign.Sign;
import hancho.plugin.nukkit.simpleController.simpleController;
import hancho.plugin.nukkit.soundManager.SoundManager;
import hancho.todayDB.TodayDB;
import hcDiscordBot.HcDiscordBot;
import logmaster.LogMaster;
import org.hancho.nukkit.plugins.SizeShop.SizeShop;
import org.hancho.plugin.nukkit.doorlock.DoorLock;
import org.hancho.plugin.nukkit.informations.Informations;
import popularity.Popularity;
import rankManager.RankManager;
import solo.sololand.Main;
import statusMessage.StatusMessage;

public class Save {
    public static final int COUNT = 39;
    public AutoSave autoSave;
    private Server server;
    private int needSaveCount = COUNT;

    public Save(AutoSave autoSave){
        this.autoSave = autoSave;
        this.server = Server.getInstance();
    }

    public void broadcast(){
        //server.getLogger().info("§l§o저장 §b" + (this.needSaveCount--) + "§f개 남음");
        for(Player player : this.server.getOnlinePlayers().values()){
            player.sendTip("§l§o저장 §b" + (this.needSaveCount --) + "§f개 남음");
        }
    }

    public void start() {
        this.needSaveCount = COUNT;

        /*Popularity*/
        Popularity plugin = (Popularity) this.server.getPluginManager().getPlugin("Popularity");
        if(plugin != null){
            this.server.getLogger().warning("Popularity");
            plugin.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("Popularity 누락");
        }

        /*Sponge Mine*/
        simpleController simpleController = (simpleController) this.server.getPluginManager().getPlugin("simpleController");
        if(simpleController != null){
            this.server.getLogger().warning("simpleController");
            simpleController.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("simpleController 누락");
        }

        /*showhealth*/
        showhealth sh = (showhealth) this.server.getPluginManager().getPlugin("showhealth");
        if(sh != null){
            this.server.getLogger().warning("showhealth");
            sh.save();
            sh.prepare();
            this.broadcast();
        }else{
            this.server.getLogger().warning("showhealth 누락");
        }

        /*ss*/
        DoorLock doorLock = (DoorLock) this.server.getPluginManager().getPlugin("DoorLock");
        if(doorLock != null){
            this.server.getLogger().warning("DoorLock");
            doorLock.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("DoorLock 누락");
        }
        /*ss*/
        Main sololand = (Main) this.server.getPluginManager().getPlugin("SOLOLand");
        if(sololand != null){
            this.server.getLogger().warning("SOLOLand");
            sololand.saveAllWorlds(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("SOLOLand 누락");
        }
        /*ss*/
        commandsign cs = (commandsign) this.server.getPluginManager().getPlugin("commandsign");
        if(cs != null){
            this.server.getLogger().warning("commandsign");
            cs.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("commandsign 누락");
        }
        /*ss*/
        mailbox mb = (mailbox) this.server.getPluginManager().getPlugin("mailbox");
        if(mb != null){
            this.server.getLogger().warning("mailbox");
            mb.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("mailbox 누락");
        }
        /*ss*/
        hwarp warp = (hwarp) this.server.getPluginManager().getPlugin("hwarp");
        if(warp != null){
            this.server.getLogger().warning("hwarp");
            warp.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("hwarp 누락");
        }
        /*ss*/
        KkutuInNukkit kkutu = (KkutuInNukkit) this.server.getPluginManager().getPlugin("KkutuInNukkit");
        if(kkutu != null){
            this.server.getLogger().warning("KkutuInNukkit");
            kkutu.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("KkutuInNukkit 누락");
        }
        /*ss*/
        kdr.Main kdr = (kdr.Main) this.server.getPluginManager().getPlugin("KDR");
        if(kdr != null){
            this.server.getLogger().warning("KDR");
            kdr.onDisable();
            this.broadcast();
        }else{
            this.server.getLogger().warning("KDR 누락");
        }
        /*ss*/
        TodayDB todayDB = (TodayDB) this.server.getPluginManager().getPlugin("TodayDB");
        if(todayDB != null){
            this.server.getLogger().warning("TodayDB");
            todayDB.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("TodayDB 누락");
        }
        /*ss*/
        LogMaster log = (LogMaster) this.server.getPluginManager().getPlugin("LogMaster");
        if(log != null){
            this.server.getLogger().warning("logmaster");
            log.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("logmaster 누락");
        }
        /*ss*/
        Auction auction = (Auction) this.server.getPluginManager().getPlugin("Auction");
        if(auction != null){
            this.server.getLogger().warning("Auction");
            auction.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("Auction 누락");
        }
        /*ss*/
        BanMaster banMaster = (BanMaster) this.server.getPluginManager().getPlugin("banMaster");
        if(banMaster != null){
            this.server.getLogger().warning("BanMaster");
            banMaster.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("BanMaster 누락");
        }
        /*ss*/
        Hshop hshop = (Hshop) this.server.getPluginManager().getPlugin("Hshop");
        if(hshop != null){
            this.server.getLogger().warning("hshop");
            hshop.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("hshop 누락");
        }

        /*ss*/
        StatusMessage statusMessage = (StatusMessage) this.server.getPluginManager().getPlugin("StatusMessage");
        if(statusMessage != null){
            this.server.getLogger().warning("StatusMessage");
            statusMessage.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("StatusMessage 누락");
        }

        /*ss*/
        Announcer announcer = (Announcer) this.server.getPluginManager().getPlugin("Announcer");
        if(announcer != null){
            this.server.getLogger().warning("announcer");
            announcer.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("announcer 누락");
        }

        /*ss*/
        check ck = (check) this.server.getPluginManager().getPlugin("check");
        if(ck != null){
            this.server.getLogger().warning("check");
            ck.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("check 누락");
        }

        /*ss*/
        solo.solonotification.Main noti = (solo.solonotification.Main) this.server.getPluginManager().getPlugin("SOLONotification");
        if(noti != null){
            this.server.getLogger().warning("solonotification");
            noti.onDisable();
            this.broadcast();
        }else{
            this.server.getLogger().warning("solonotification 누락");
        }


        /*ss*/
        HFriend friend = (HFriend) this.server.getPluginManager().getPlugin("HFriend");
        if(friend != null){
            this.server.getLogger().warning("HFriend");
            friend.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("HFriend 누락");
        }


        /*ss*/
        HcDiscordBot discordBot = (HcDiscordBot) this.server.getPluginManager().getPlugin("HcDiscordBot");
        if(discordBot != null){
            this.server.getLogger().warning("HcDiscordBot");
            discordBot.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("HcDiscordBot 누락");
        }


        /*ss*/
        CommandShortcut shortcut = (CommandShortcut) this.server.getPluginManager().getPlugin("CommandShortcut");
        if(shortcut != null){
            this.server.getLogger().warning("CommandShortcut");
            shortcut.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("CommandShortcut 누락");
        }


        /*ss*/
        Sign sign = (Sign) this.server.getPluginManager().getPlugin("Sign");
        if(sign != null){
            this.server.getLogger().warning("sign");
            sign.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("sign 누락");
        }


        /*ss*/
        RewardSign rewardSign = (RewardSign) this.server.getPluginManager().getPlugin("RewardSign");
        if(rewardSign != null){
            this.server.getLogger().warning("rewardSign");
            rewardSign.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("rewardSign 누락");
        }


        /*ss*/
        RankManager rankManager = (RankManager) this.server.getPluginManager().getPlugin("RankManager");
        if(rankManager != null){
            this.server.getLogger().warning("rankManager");
            rankManager.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("rankManager 누락");
        }


        /*ss*/
        solo.soloportal.Main portal = (solo.soloportal.Main) this.server.getPluginManager().getPlugin("SOLOPortal");
        if(portal != null){
            this.server.getLogger().warning("soloportal");
            portal.onDisable();
            this.broadcast();
        }else{
            this.server.getLogger().warning("soloportal 누락");
        }


        /*ss*/
        ParticleManager pm = (ParticleManager) this.server.getPluginManager().getPlugin("ParticleManager");
        if(pm != null){
            this.server.getLogger().warning("particleManager");
            pm.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("particleManager 누락");
        }


        /*ss*/
        ChestLog chestLog = (ChestLog) this.server.getPluginManager().getPlugin("ChestLog");
        if(chestLog != null){
            this.server.getLogger().warning("chestLog");
            chestLog.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("chestLog 누락");
        }


        /*ss*/
        DashBlock db = (DashBlock) this.server.getPluginManager().getPlugin("DashBlock");
        if(db != null){
            this.server.getLogger().warning("dashblock");
            db.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("dashblock 누락");
        }


        /*ss*/
        AutoCrop autoCrop = (AutoCrop) this.server.getPluginManager().getPlugin("AutoCrop");
        if(autoCrop != null){
            this.server.getLogger().warning("autoCrop");
            autoCrop.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("autoCrop 누락");
        }


        /*ss*/
        BookLibrary bookLibrary = (BookLibrary) this.server.getPluginManager().getPlugin("BookLibrary");
        if(bookLibrary != null){
            this.server.getLogger().warning("bookLibrary");
            bookLibrary.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("bookLibrary 누락");
        }


        /*ss*/
        Informations informations = (Informations) this.server.getPluginManager().getPlugin("Informations");
        if(informations != null){
            this.server.getLogger().warning("informations");
            informations.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("informations 누락");
        }


        /*ss*/
        SizeShop sizeShop = (SizeShop) this.server.getPluginManager().getPlugin("SizeShop");
        if(sizeShop != null){
            this.server.getLogger().warning("sizeShop");
            sizeShop.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("sizeShop 누락");
        }

        /*ss*/
        ConnectionTime connectionTime = (ConnectionTime) this.server.getPluginManager().getPlugin("ConnectionTime");
        if(connectionTime != null){
            this.server.getLogger().warning("ConnectionTime");
            connectionTime.save(false);
            this.broadcast();
        }else{
            this.server.getLogger().warning("ConnectionTime 누락");
        }

        /*ss*/
        CustomResourcePack customResourcePack = (CustomResourcePack) this.server.getPluginManager().getPlugin("CustomResourcePack");
        if(customResourcePack != null){
            this.server.getLogger().warning("CustomResourcePack");
            customResourcePack.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("CustomResourcePack 누락");
        }

        /*ss*/
        KeyWords keyWords = (KeyWords) this.server.getPluginManager().getPlugin("keyWords");
        if(keyWords != null){
            this.server.getLogger().warning("keyWords");
            keyWords.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("keyWords 누락");
        }

        SimpleGambling simpleGambling = (SimpleGambling) this.server.getPluginManager().getPlugin("SimpleGambling");
        if(simpleGambling != null){
            this.server.getLogger().warning("simpleGambling");
            simpleGambling.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("simpleGambling 누락");
        }

        BlockNameTagManager blockNameTagManager = (BlockNameTagManager) this.server.getPluginManager().getPlugin("BlockNameTagManager");
        if(blockNameTagManager != null){
            this.server.getLogger().warning("BlockNameTagManager");
            blockNameTagManager.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("blockNameTagManager 누락");
        }

        SoundManager soundManager = (SoundManager) this.server.getPluginManager().getPlugin("SoundManager");
        if(soundManager != null){
            this.server.getLogger().warning("soundManager");
            soundManager.save();
            this.broadcast();
        }else{
            this.server.getLogger().warning("soundManager 누락");
        }

        this.server.broadcastMessage("§o§3서버 저장 완료");
    }
}
