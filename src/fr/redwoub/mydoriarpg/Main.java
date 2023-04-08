package fr.redwoub.mydoriarpg;

import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.commands.*;
import fr.redwoub.mydoriarpg.inventory.InventoryEvent;
import fr.redwoub.mydoriarpg.listeners.PlayerChat;
import fr.redwoub.mydoriarpg.listeners.PlayerDeath;
import fr.redwoub.mydoriarpg.listeners.PlayerJoin;
import fr.redwoub.mydoriarpg.listeners.PlayerQuit;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import fr.redwoub.mydoriarpg.scoreboard.ScoreboardManager;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Main extends JavaPlugin {
    private static Main instance;
    private List<Accounts> accounts;
    private Map<Player, Player> friendsRequest;
    private Map<String, String> pseudoLinkedUUID;
    private File saveDeleteAccount;
    private File pseudoWithUUID;
    private ScoreboardManager scoreboardManager;
    private ScheduledExecutorService executorMonoThread;
    private ScheduledExecutorService scheduledExecutorService;
    private BukkitTask statistiqueManager;



    private void register(){
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(), this);
        getServer().getPluginManager().registerEvents(new PlayerChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new InventoryEvent(), this);

        getCommand("colorchat").setExecutor(new ColorChat());
        getCommand("colorname").setExecutor(new ColorName());
        getCommand("menu").setExecutor(new Menu());
        getCommand("statistique").setExecutor(new Statistique());
        getCommand("setrank").setExecutor(new SetRank());
        getCommand("skill").setExecutor(new Skill());
        getCommand("friends").setExecutor(new Friends());
        getCommand("test").setExecutor(new Test());
        getCommand("levelup").setExecutor(new LevelUP());
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        instance = this;
        register();
        saveDeleteAccount = new File(getDataFolder(), "/delete_accounts/");
        pseudoWithUUID = new File(getDataFolder(), "PseudoLinkToUUID.yml");
        accounts = new ArrayList<>();
        friendsRequest = new WeakHashMap<>();
        pseudoLinkedUUID = new HashMap<>();

        scheduledExecutorService = Executors.newScheduledThreadPool(16);
        executorMonoThread = Executors.newScheduledThreadPool(1);
        scoreboardManager = new ScoreboardManager();
        if(!getDataFolder().exists())
            getDataFolder().mkdir();

        if(!saveDeleteAccount.exists())
            saveDeleteAccount.mkdir();

        FileUtils.createFile(pseudoWithUUID);

        statistiqueManager = new StatistiqueManager().runTaskTimer(this, 20L, 60L);
    }

    @Override
    public void onDisable() {
        if(!Bukkit.getOnlinePlayers().isEmpty()){
            for(Player player : Bukkit.getOnlinePlayers()){
                getAccount(player).ifPresent(Accounts::onLogout);
                player.kickPlayer("§cUne erreur est survenue. \n\n§cPour evité tout bug avec votre compte, nous avons préféré vous expulsé du serveur.\n\n§cDésolé pour la gêne occasionné");
            }
        }

        statistiqueManager.cancel();
    }

    public static Main getInstance() {
        return instance;
    }

    public List<Accounts> getAccounts() {
        return accounts;
    }

    public Optional<Accounts> getAccount(Player player){
        return new ArrayList<>(accounts).stream().filter(account -> account.getUUID().toString().equals(player.getUniqueId().toString())).findFirst();
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public ScheduledExecutorService getExecutorMonoThread() {
        return executorMonoThread;
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return scheduledExecutorService;
    }

    public Map<Player, Player> getFriendsRequest() {
        return friendsRequest;
    }
    public Map<String, String> getPseudoLinkedUUID() {
        return pseudoLinkedUUID;
    }

    public File getSaveDeleteAccount() {
        return saveDeleteAccount;
    }

    public File getPseudoWithUUID() {
        return pseudoWithUUID;
    }
}
