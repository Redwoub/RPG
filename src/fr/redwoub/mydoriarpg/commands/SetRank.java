package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.RankUnit;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class SetRank implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.sytem-prefix"));
    private void sendHelp(CommandSender sender){
        sender.sendMessage(prefix + "§cErreur §8: §e/setrank <joueur> <grade>");
        sender.sendMessage(prefix + "§7Voici la liste des grades : Joueur, Vip, Mvp, Support, Builder, Modérateur, Développeur et Administrateur");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length != 2){
            sendHelp(sender);
            return false;
        }

        if(Bukkit.getPlayer(args[0]) != null){
            Player player = Bukkit.getPlayer(args[0]);
            Main.getInstance().getAccount(player).ifPresent(accounts -> {
                accounts.getDataRank().setRank(RankUnit.getByName(args[1]));
                sender.sendMessage(prefix + "§aLe grade de " + player.getDisplayName() + " §aa bien été modifié !");
            });
        } else {
            if(Main.getInstance().getPseudoLinkedUUID().get(args[0].toLowerCase()) != null){
                UUID uuid = UUID.fromString(Main.getInstance().getPseudoLinkedUUID().get(args[0].toLowerCase()));
                File account = new File(Main.getInstance().getDataFolder(), "accounts/" + uuid + "/Account.yml");
                YamlConfiguration cAccount = YamlConfiguration.loadConfiguration(account);
                cAccount.set("grade", RankUnit.getByName(args[1]).getName());
                FileUtils.save(cAccount, account);
                sender.sendMessage(prefix + "§aLe grade de " + args[0] + " §aa bien été modifié !");
            } else {
                sender.sendMessage(prefix + "§cCe joueur n'existe pas !");
            }
        }
        return false;
    }
}
