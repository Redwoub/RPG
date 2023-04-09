package fr.redwoub.mydoriarpg.commands;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
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
        if(Main.getInstance().getAccount(player).isPresent()){
            Accounts accounts = Main.getInstance().getAccount(player).get();
            if(accounts.getDataLvl().getRpgLvl() < 1000){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §cIl faut être rpgLevel 1000 pour pouvoir utilisé cette commande");
                return false;
            }
        }
        if(args.length == 0){
            Main.getInstance().getAccount(player).ifPresent(account -> {
                account.getDataStatistique().setNickname(player.getName());
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§aVotre nickname a été réinitialisé");
                player.setDisplayName(PlayerUtils.getByString(account.getDataStatistique().getColorName()) + account.getDataStatistique().getNickname());
            });
            YamlConfiguration config = YamlConfiguration.loadConfiguration(Main.getInstance().getNicknameWithUUID());
            config.set(player.getUniqueId().toString(), player.getName());
            FileUtils.save(config, Main.getInstance().getNicknameWithUUID());
            if(!Main.getInstance().getUuidLinkedToNickname().isEmpty())
                Main.getInstance().getUuidLinkedToNickname().clear();
            FileUtils.fillMapWithAllLineOfFile(Main.getInstance().getNicknameWithUUID(), Main.getInstance().getUuidLinkedToNickname());
            return false;
        } else if(args.length != 1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§cErreur §8: §e/nick <nickname>");
            return false;
        }


        Main.getInstance().getAccount(player).ifPresent(account -> {
            account.getDataStatistique().setNickname(args[0]);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix")) + "§aVotre nouveau pseudo est §7: " + PlayerUtils.getByString(account.getDataStatistique().getColorName()) + account.getDataStatistique().getNickname());
            player.setDisplayName(PlayerUtils.getByString(account.getDataStatistique().getColorName()) + account.getDataStatistique().getNickname());
        });

        YamlConfiguration config = YamlConfiguration.loadConfiguration(Main.getInstance().getNicknameWithUUID());
        config.set(player.getUniqueId().toString(), args[0].toLowerCase());
        FileUtils.save(config, Main.getInstance().getNicknameWithUUID());
        if(!Main.getInstance().getUuidLinkedToNickname().isEmpty())
            Main.getInstance().getUuidLinkedToNickname().clear();
        FileUtils.fillMapWithAllLineOfFile(Main.getInstance().getNicknameWithUUID(), Main.getInstance().getUuidLinkedToNickname());

        return false;
    }
}
