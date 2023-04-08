package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

public class RealName implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length != 1){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §e/realname <nickname>");
            return false;
        }

        YamlConfiguration config = YamlConfiguration.loadConfiguration(Main.getInstance().getNicknameWithUUID());
        if(Main.getInstance().getUuidLinkedToNickname().get(args[0].toLowerCase()) != null){
            YamlConfiguration cfg = YamlConfiguration.loadConfiguration(Main.getInstance().getPseudoWithUUID());
            String pseudo = cfg.getString(Main.getInstance().getUuidLinkedToNickname().get(args[0].toLowerCase()));
            sender.sendMessage("§7Le joueur ayant le nickname : §e" + args[0] + " §7est : §e" + pseudo);
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §cAucun joueur ne porte ce nickname");
        }
        return false;
    }
}
