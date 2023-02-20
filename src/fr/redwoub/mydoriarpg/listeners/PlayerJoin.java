package fr.redwoub.mydoriarpg.listeners;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.FriendsManager;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Accounts accounts = new Accounts(player.getUniqueId());
        FriendsManager friendsManager = new FriendsManager(accounts);
        accounts.onLogin();
        StatistiqueManager statistiqueManager = new StatistiqueManager(player);
        Main.getInstance().statsBonusForEachPlayer.put(player, statistiqueManager);
        statistiqueManager.runTaskTimer(Main.getInstance(), 20L, 40L);
        friendsManager.sendJoinNotification();
        player.setDisplayName(getByString(accounts.getDataStatistique().getColorName()) + player.getName());
        player.setMaxHealth(accounts.getDataStatistique().getMaxVie());
        player.setHealth(accounts.getDataStatistique().getVie());
        player.setWalkSpeed(PlayerUtils.generateSpeed(accounts));
        Main.getInstance().getScoreboardManager().onLogin(player);
        event.setJoinMessage("§8[§a+§8] " + player.getDisplayName());
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
        } else if(color.equalsIgnoreCase("dark_red")){
            return ChatColor.DARK_RED;
        } else if(color.equalsIgnoreCase("noir")){
            return ChatColor.BLACK;
        }

        return ChatColor.WHITE;
    }
}
