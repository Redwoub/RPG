package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.accounts.RpgLevel;
import fr.redwoub.mydoriarpg.utils.ItemBuilder;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.DecimalFormat;
import java.util.Arrays;

public class RpgLevelManager {

    private final Accounts accounts;
    public RpgLevelManager(Accounts accounts){
        this.accounts = accounts;
    }

    public boolean levelIsExceed(int lvl){
        if(accounts.getDataLvl().getRpgLvl() >= lvl) return true;
        return false;
    }

    public ItemStack createGlass(int lvl){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setName("§aNiveau " + lvl).setLore("§a§lDébloqué").toItemStack();
        } else if((accounts.getDataLvl().getRpgLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingRpgXp()))).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore("§c§lBloqué").toItemStack();
    }

    public ItemStack createGlass(int lvl, String lore, String lore2, String lore3){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE,1, (byte) 5).setName("§aNiveau " + lvl).setLore(Arrays.asList("§a§lDébloqueé", lore, lore2, lore3)).toItemStack();
        } else if((accounts.getDataLvl().getRpgLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingRpgXp()), lore, lore2, lore3)).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore(Arrays.asList("§c§lBloqué", lore, lore2, lore3)).toItemStack();
    }
    public double progression(){
        if(accounts.getDataLvl().getRpgLvl() == RpgLevel.getMaxLevel()){
            return 110;
        } else {
            double difference = RpgLevel.needingxp(accounts.getDataLvl().getRpgLvl() + 1) - accounts.getDataLvl().getMissingRpgXp();
            double prog = (difference / RpgLevel.needingxp(accounts.getDataLvl().getRpgLvl() + 1)) * 100;
            return prog;
        }
    }

    public void addXp(long xp){
        accounts.getDataLvl().addRpgXp(xp);
        if(accounts.getDataLvl().getMissingRpgXp() <= 0){
            levelUp();
        }
    }

    public void levelUp(){

        accounts.getDataLvl().setRpgLvl(accounts.getDataLvl().getNexpRpgLvl());
        accounts.getDataLvl().setMissingRpgXp(RpgLevel.needingxp(accounts.getDataLvl().getRpgLvl()));
        if(accounts.getDataLvl().getRpgLvl() == RpgLevel.getMaxLevel()){
            Player player = Bukkit.getPlayer(accounts.getUUID());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage("§7------------------------------------");
            player.sendMessage("§aRPG Level UP §8: " + ChatColor.DARK_AQUA + (accounts.getDataLvl().getRpgLvl() - 1) + " §7 -> " + ChatColor.DARK_AQUA + accounts.getDataLvl().getRpgLvl());
            player.sendMessage("§aBravo ! Vous êtes niveau maximum !");
            player.sendMessage("§6Une annonce va être faite pour te félicité !");
            player.sendMessage("§7------------------------------------");
            new BukkitRunnable(){

                @Override
                public void run() {
                    for(Player players : Bukkit.getOnlinePlayers()){
                        players.playSound(players.getLocation(), Sound.ENTITY_ENDERDRAGON_DEATH, 2f, 1f);
                        players.sendTitle("§cGG à " + "§6" + player.getName(), "", 5, 60,5);
                    }
                    new BukkitRunnable() {

                        @Override
                        public void run() {
                            Bukkit.broadcastMessage(PlayerUtils.generateColoredNameWithoutRPG(accounts)+ player.getName() + " §bvient de passer niveau §c280§7, §ail est donc niveau maximum. Bravo à lui !!");
                        }

                    }.runTaskLater(Main.getInstance(), 20* 3L);
                }
            }.runTaskLater(Main.getInstance(), 20*20L);

        } else {
            Player player = Bukkit.getPlayer(accounts.getUUID());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            player.sendMessage("§7------------------------------------");
            player.sendMessage("§aRPG Level UP §8: " + ChatColor.DARK_AQUA + (accounts.getDataLvl().getRpgLvl() - 1) + " §7 -> " + ChatColor.DARK_AQUA + accounts.getDataLvl().getRpgLvl());
            player.sendMessage("§7------------------------------------");
        }
    }

    public String generateProgressionBar(double pourcentage, int lvl, double missingxp){
        if(accounts.getDataLvl().getRpgLvl() == RpgLevel.getMaxLevel()){
            return "§a--------------------";
        } else {
            double needxp = RpgLevel.needingxp(lvl) - missingxp;

            if(pourcentage <= 0){
                return "§f-------------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 5 && pourcentage < 10){
                return "§a-§f------------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 10 && pourcentage < 15){
                return "§a--§f------------------ §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 15 && pourcentage < 20){
                return "§a---§f----------------- §e §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 20 && pourcentage < 25){
                return "§a----§f---------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 25 && pourcentage < 30){
                return "§a-----§f--------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 30 && pourcentage < 35){
                return "§a------§f-------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 35 && pourcentage < 40){
                return "§a-------§f------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 40 && pourcentage < 45){
                return "§a--------§f------------ §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 45 && pourcentage < 50){
                return "§a---------§f----------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 50 && pourcentage < 55){
                return "§a----------§f---------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 55 && pourcentage < 60){
                return "§a-----------§f--------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 60 && pourcentage < 65){
                return "§a------------§f-------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 65 && pourcentage < 70){
                return "§a-------------§f------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 70 && pourcentage < 75){
                return "§a--------------§f------ §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 75 && pourcentage < 80){
                return "§a---------------§f----- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 80 && pourcentage < 85){
                return "§a----------------§f---- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 85 && pourcentage < 90){
                return "§a-----------------§f--- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 90 && pourcentage < 95){
                return "§a------------------§f-- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else if(pourcentage >= 95 && pourcentage < 100){
                return "§a-------------------§f- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            } else {
                return "§f-------------------- §e " + needxp + "§6/§e" + RpgLevel.needingxp(lvl);
            }
        }
    }
}
