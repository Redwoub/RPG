package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.RpgLevelManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LevelUP implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager rpgLevelManager = new RpgLevelManager(accounts);
            rpgLevelManager.levelUp();
        });
        return false;
    }
}
