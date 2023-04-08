package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Nick implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;

        Player player = (Player) sender;
        if(args.length != 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §e/nick <nickname>");
            return false;
        }

        Main.getInstance().getAccount(player).ifPresent(account -> {
            account.getDataStatistique().setNickname(args[0]);
            player.sendMessage("§aVotre nouveau pseudo est §7: " + PlayerUtils.getByString(account.getDataStatistique().getColorName()) + account.getDataStatistique().getNickname());
            player.setDisplayName(PlayerUtils.getByString(account.getDataStatistique().getColorName()) + account.getDataStatistique().getNickname());
        });

        YamlConfiguration config = YamlConfiguration.loadConfiguration(Main.getInstance().getNicknameWithUUID());
        config.set(player.getUniqueId().toString(), args[0].toLowerCase());
        FileUtils.save(config, Main.getInstance().getNicknameWithUUID());

        return false;
    }
}
