package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.inventory.InventoryList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(Main.getInstance().getAccount(player).isPresent()){
            if(Main.getInstance().getAccount(player).get().getDataLvl().getRpgLvl() >= 250){
                player.openInventory(new InventoryList().colorChat(player));
            } else {
                player.sendMessage("§cVous n'avez pas le niveau requis pour executer cette commande !");
                player.sendMessage("§cElle se débloque au niveau §bRPG §c250");
            }
        }

        return false;
    }
}
