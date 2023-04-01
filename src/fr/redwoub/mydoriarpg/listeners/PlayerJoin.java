package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.FriendsManager;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Accounts accounts = new Accounts(player.getUniqueId());
        FriendsManager friendsManager = new FriendsManager(accounts);
        accounts.onLogin();
        StatistiqueManager statistiqueManager = new StatistiqueManager(player);
        Main.getInstance().statsBonusForEachPlayer.put(player, statistiqueManager);
        statistiqueManager.runTaskTimer(Main.getInstance(), 20L, 40L);
        friendsManager.sendJoinNotification();
        player.setDisplayName(PlayerUtils.getByString(accounts.getDataStatistique().getColorName()) + player.getName());
        player.setMaxHealth(accounts.getDataStatistique().getMaxVie());
        player.setHealth(accounts.getDataStatistique().getVie());
        player.setWalkSpeed(PlayerUtils.generateSpeed(accounts));
        Main.getInstance().getScoreboardManager().onLogin(player);
        event.setJoinMessage("§8[§a+§8] " + player.getDisplayName());
    }

}
