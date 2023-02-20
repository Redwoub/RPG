package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.RankUnit;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetRank implements CommandExecutor {

    private void sendHelp(CommandSender sender){
        sender.sendMessage("§cErreur §8: §e/setrank <joueur> <grade>");
        sender.sendMessage("§7Voici la liste des grades : Joueur, Vip, Mvp, Support, Builder, Modérateur, Développeur et Administrateur");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length != 2){
            sendHelp(sender);
            return false;
        }

        if(Bukkit.getPlayer(args[0]) == null){
            sender.sendMessage("§c§cErreur §8: §7Ce joueur n'existe pas");
            return false;
        }

        Player player = Bukkit.getPlayer(args[0]);
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RankUnit rank = RankUnit.getByName(args[1]);
            accounts.getDataRank().setRank(rank);
            sender.sendMessage("§aLe grade de " + player.getDisplayName() + " §aa bien été modifié !");
        });
        return false;
    }
}
