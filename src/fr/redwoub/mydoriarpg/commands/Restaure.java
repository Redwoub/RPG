package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

public class Restaure implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix"));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length != 1){
            sender.sendMessage(prefix + "§cErreur §8: §e/restaure <pseudo>");
            return false;
        }

        if(Main.getInstance().getPseudoLinkedUUID().get(args[0].toLowerCase()) == null){
            sender.sendMessage(prefix + "§cErreur §8: §cLe joueur §b" + args[0] + " §cn'existe pas !");
            return false;
        }


        if(!PlayerUtils.hasDeletedAccount(UUID.fromString(Main.getInstance().getPseudoLinkedUUID().get(args[0].toLowerCase())))){
            sender.sendMessage(prefix + "§cErreur §8: §cLe joueur §b" + args[0] + " §cn'a pas de compte supprimé");
            return false;
        }

        if(Bukkit.getPlayer(args[0]) != null){
            UUID uuid = Bukkit.getPlayer(args[0]).getUniqueId();
            File oldAccount = Main.getInstance().getAccount(Bukkit.getPlayer(uuid)).get().getSaveDir();
            File oldPlayerData = new File(Main.getInstance().getConfig().getString("config.player-data-world-path"), uuid + ".dat");
            String path = oldAccount.getPath();
            Bukkit.getPlayer(args[0]).kickPlayer(prefix + "\n\n§cVous avez été expulsé car un modérateur à restauré votre ancien compte");

            FileUtils.deleteDirectory(oldAccount);
            oldAccount.delete();
            oldPlayerData.delete();

            File account = new File(Main.getInstance().getDeletedAccounts(), uuid + "/");
            File playerData = new File(account, uuid + ".dat");

            try {
                Files.copy(account.toPath(), Paths.get(path));
                Files.copy(playerData.toPath(), Paths.get(Main.getInstance().getConfig().getString("config.player-data-world-path")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FileUtils.deleteDirectory(account);
            account.delete();
            playerData.delete();
        } else {
            UUID uuid = UUID.fromString(Main.getInstance().getPseudoLinkedUUID().get(args[0].toLowerCase()));
            File oldAccount = new File(Main.getInstance().getDataFolder(), "accounts/" + uuid + "/");
            File oldPlayerData = new File(Main.getInstance().getConfig().getString("config.player-data-world-path"), uuid + ".dat");
            String path = oldAccount.getPath();

            FileUtils.deleteDirectory(oldAccount);
            oldAccount.delete();
            oldPlayerData.delete();

            File account = new File(Main.getInstance().getDeletedAccounts(), uuid + "/");
            File playerData = new File(Main.getInstance().getConfig().getString("config.player-data-world-path"), uuid + ".dat");

            try {
                Files.copy(account.toPath(), Paths.get(path));
                Files.copy(playerData.toPath(), Paths.get(Main.getInstance().getConfig().getString("config.player-data-world-path")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            FileUtils.deleteDirectory(account);
            account.delete();
            playerData.delete();

        }

        return false;
    }
}
