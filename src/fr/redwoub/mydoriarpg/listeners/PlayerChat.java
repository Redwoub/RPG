package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import fr.redwoub.mydoriarpg.accounts.RankUnit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    @EventHandler
    public void playerChat(AsyncPlayerChatEvent event){
        Main.getInstance().getAccount(event.getPlayer()).ifPresent(accounts -> {
            event.setFormat("§8§l[" + accounts.getDataRank().getRank().getPrefix() + "§8§l]§r %1$s §7>" + getByString(accounts.getDataStatistique().getColorChat()) + " %2$s");
        });
    }

    private ChatColor getByString(String color){

        if(color.equalsIgnoreCase("Rouge")) {
            return ChatColor.RED;
        } else if(color.equalsIgnoreCase("Dore")) {
            return ChatColor.GOLD;
        } else if(color.equalsIgnoreCase("Jaune")){
            return ChatColor.YELLOW;
        } else if(color.equalsIgnoreCase("Dark_Green")) {
            return ChatColor.DARK_GREEN;
        } else if(color.equalsIgnoreCase("Vert")){
            return ChatColor.GREEN;
        } else if(color.equalsIgnoreCase("Bleu_claire")) {
            return ChatColor.AQUA;
        } else if(color.equalsIgnoreCase("Cyan")) {
            return ChatColor.DARK_AQUA;
        } else if(color.equalsIgnoreCase("Bleu_fonce")) {
            return ChatColor.DARK_BLUE;
        } else if(color.equalsIgnoreCase("Bleu")) {
            return ChatColor.BLUE;
        } else if(color.equalsIgnoreCase("Rose")) {
            return ChatColor.LIGHT_PURPLE;
        } else if(color.equalsIgnoreCase("Violet")) {
            return ChatColor.DARK_PURPLE;
        } else if(color.equalsIgnoreCase("Blanc")) {
            return ChatColor.WHITE;
        } else if(color.equalsIgnoreCase("Gris")) {
            return ChatColor.GRAY;
        } else if(color.equalsIgnoreCase("dark_gris")){
            return ChatColor.DARK_GRAY;
        } else if(color.equalsIgnoreCase("noir")){
            return ChatColor.BLACK;
        } else if(color.equalsIgnoreCase("dark_red")){
            return ChatColor.DARK_RED;
        }

        return ChatColor.WHITE;
    }
}
