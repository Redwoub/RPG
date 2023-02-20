package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.inventory.InventoryList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Skill implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        ((Player) sender).openInventory(new InventoryList().skillMenu((Player) sender));
        return false;
    }
}
