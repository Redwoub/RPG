package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.FriendsManager;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            FriendsManager friendsManager = new FriendsManager(accounts);
            friendsManager.sendLeftNotification();
            Main.getInstance().getScoreboardManager().onLogout(player);
            accounts.onLogout();
        });

        event.setQuitMessage("§8[§c-§8] " + player.getDisplayName());
    }
}
