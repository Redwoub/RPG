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
import java.util.Map;
import java.util.WeakHashMap;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Accounts accounts = new Accounts(player.getUniqueId());
        FriendsManager friendsManager = new FriendsManager(accounts);
        Main main = Main.getInstance();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(main.getPseudoWithUUID());
        accounts.onLogin();
        friendsManager.sendJoinNotification();
        player.setDisplayName(PlayerUtils.getByString(accounts.getDataStatistique().getColorName()) + player.getName());
        player.setWalkSpeed(PlayerUtils.generateSpeed(accounts));
        main.getScoreboardManager().onLogin(player);

        if(!main.getPseudoLinkedUUID().isEmpty())
            main.getPseudoLinkedUUID().clear();

        config.set(player.getUniqueId().toString(), player.getName().toLowerCase());
        FileUtils.fillMapWithAllLineOfFile(main.getPseudoWithUUID(), main.getPseudoLinkedUUID());
        FileUtils.save(config, main.getPseudoWithUUID());

        event.setJoinMessage("§8[§a+§8] " + player.getDisplayName());
    }
}
