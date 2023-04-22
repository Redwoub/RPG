package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.inventory.InventoryList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ColorChat implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.sytem-prefix"));
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(Main.getInstance().getAccount(player).isPresent()){
            if(Main.getInstance().getAccount(player).get().getDataLvl().getRpgLvl() >= 250){
                player.openInventory(new InventoryList().colorChat(player));
            } else {
                player.sendMessage(prefix + "§cVous n'avez pas le niveau requis pour executer cette commande !");
                player.sendMessage(prefix + "§cElle se débloque au niveau §bRPG §c250");
            }
        }

        return false;
    }
}
