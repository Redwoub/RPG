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
            event.setFormat("§8§l[" + accounts.getDataRank().getRank().getPrefix() + "§8§l] §8§l[§aGUILDE§8§l]§r %1$s §7>" + PlayerUtils.getByString(accounts.getDataStatistique().getColorChat()) + " %2$s");
        });
    }

}
