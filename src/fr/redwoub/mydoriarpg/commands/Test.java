package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.items.swords.DevSword;
import fr.redwoub.mydoriarpg.managers.ItemManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        player.getInventory().addItem(DevSword.getDevSword());
        return false;
    }
}
