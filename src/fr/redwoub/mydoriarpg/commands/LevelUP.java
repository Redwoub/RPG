package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.RpgLevelManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelUP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        if(args.length < 1){
            Player player = (Player) sender;
            Main.getInstance().getAccount(player).ifPresent(account -> {
                RpgLevelManager rpgLevelManager = new RpgLevelManager(account);
                rpgLevelManager.levelUp();
            });
        } else if(args.length == 1){
            if(Bukkit.getPlayer(args[0]) != null){
                Player player = Bukkit.getPlayer(args[0]);
                Main.getInstance().getAccount(player).ifPresent(account -> {
                    RpgLevelManager rpgLevelManager = new RpgLevelManager(account);
                    rpgLevelManager.levelUp();
                });
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§e" + player.getName() + " §aa bien levelup");
            } else {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §cCe joueur n'existe pas");
                return false;
            }
        }

        return false;
    }
}
