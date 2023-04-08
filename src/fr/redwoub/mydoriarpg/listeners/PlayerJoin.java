package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.FriendsManager;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Accounts accounts = new Accounts(player.getUniqueId());
        FriendsManager friendsManager = new FriendsManager(accounts);
        YamlConfiguration config = YamlConfiguration.loadConfiguration(Main.getInstance().getPseudoWithUUID());
        accounts.onLogin();
        friendsManager.sendJoinNotification();
        player.setDisplayName(PlayerUtils.getByString(accounts.getDataStatistique().getColorName()) + player.getName());
        player.setWalkSpeed(PlayerUtils.generateSpeed(accounts));
        Main.getInstance().getScoreboardManager().onLogin(player);

        config.set(player.getUniqueId().toString(), player.getName());
        FileUtils.save(config, Main.getInstance().getPseudoWithUUID());

        event.setJoinMessage("§8[§a+§8] " + player.getDisplayName());
    }
}
