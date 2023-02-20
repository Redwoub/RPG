package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.inventory.InventoryList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorName implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmb, String label, String[] args) {
        if((!(sender instanceof Player))) return false;

        Player player = (Player) sender;
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            if(accounts.getDataLvl().getRpgLvl() >= 250){
                player.openInventory(new InventoryList().colorName(player));
            } else {
                player.sendMessage("§cVous n'avez pas le niveau requis pour executer cette commande !");
                player.sendMessage("§cElle se débloque au niveau §bRPG §c250");
            }
        });
        return false;
    }
}
