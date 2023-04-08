package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity().getPlayer();
        Main.getInstance().getAccount(player).ifPresent(Accounts::onLogout);
        player.kickPlayer("§cVous etes mort ! Votre compte a été réinitialisé.");
        File playerData = new File(Main.getInstance().getConfig().getString("config.player-data-world-path"), player.getUniqueId() + ".dat");
        File file = new File(Main.getInstance().getDataFolder(), "/accounts/" + player.getUniqueId() + "/");
        File account = new File(file, "Account.yml");
        File friends = new File(file, "Friends.yml");
        File optionFriends = new File(file, "OptionFriends.yml");
        File deletedFile = new File(Main.getInstance().getSaveDeleteAccount(), file.getName());
        File deletedPlayerDaya = new File(deletedFile, playerData.getName());
        File deletedAccount = new File(deletedFile, account.getName());
        File deletedFriend = new File(deletedFile, friends.getName());
        File deletedOptionFriend = new File(deletedFile, optionFriends.getName());


        if(deletedFile.exists()){
            FileUtils.deleteDirectory(deletedFile);
            deletedFile.delete();
            try {
                Files.copy(file.toPath(), deletedFile.toPath());
                Files.copy(account.toPath(), deletedAccount.toPath());
                Files.copy(friends.toPath(), deletedFriend.toPath());
                Files.copy(optionFriends.toPath(), deletedOptionFriend.toPath());
                Files.copy(playerData.toPath(), deletedPlayerDaya.toPath());
                account.delete();
                friends.delete();
                optionFriends.delete();
                file.delete();
                playerData.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                Files.copy(file.toPath(), deletedFile.toPath());
                Files.copy(account.toPath(), deletedAccount.toPath());
                Files.copy(friends.toPath(), deletedFriend.toPath());
                Files.copy(optionFriends.toPath(), deletedOptionFriend.toPath());
                Files.copy(playerData.toPath(), deletedPlayerDaya.toPath());
                account.delete();
                friends.delete();
                optionFriends.delete();
                file.delete();
                playerData.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
