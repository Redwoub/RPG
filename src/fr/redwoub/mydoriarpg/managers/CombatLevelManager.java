package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.accounts.CombatLevel;
import fr.redwoub.mydoriarpg.accounts.RpgLevel;
import fr.redwoub.mydoriarpg.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.Arrays;

public class CombatLevelManager {

    private final Accounts accounts;
    public CombatLevelManager(Accounts accounts){
        this.accounts = accounts;
    }

    public boolean levelIsExceed(int lvl){
        if(accounts.getDataLvl().getCombatLvl() >= lvl) return true;
        return false;
    }

    public ItemStack createGlass(int lvl){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 5).setName("§aNiveau " + lvl).setLore("§a§lDébloqué").toItemStack();
        } else if((accounts.getDataLvl().getCombatLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingCombatXp()))).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore("§c§lBloqué").toItemStack();
    }

    public ItemStack createGlass(int lvl, String lore){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE,1, (byte) 5).setName("§aNiveau " + lvl).setLore(Arrays.asList("§a§lDébloqueé", lore)).toItemStack();
        } else if((accounts.getDataLvl().getCombatLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingCombatXp()), lore)).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore(Arrays.asList("§c§lBloqué", lore)).toItemStack();
    }

    public ItemStack createGlass(int lvl, String lore, String lore2){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE,1, (byte) 5).setName("§aNiveau " + lvl).setLore(Arrays.asList("§a§lDébloqueé", lore, lore2)).toItemStack();
        } else if((accounts.getDataLvl().getCombatLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingCombatXp()), lore, lore2)).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore(Arrays.asList("§c§lBloqué", lore, lore2)).toItemStack();
    }

    public ItemStack createGlass(int lvl, String lore, String lore2, String lore3){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE,1, (byte) 5).setName("§aNiveau " + lvl).setLore(Arrays.asList("§a§lDébloqueé", lore, lore2, lore3)).toItemStack();
        } else if((accounts.getDataLvl().getCombatLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingCombatXp()), lore, lore2, lore3)).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore(Arrays.asList("§c§lBloqué", lore, lore2, lore3)).toItemStack();
    }

    public ItemStack createGlass(int lvl, String lore, String lore2, String lore3, String lore4){
        DecimalFormat df = new DecimalFormat("###.#");
        if(levelIsExceed(lvl)){
            return new ItemBuilder(Material.STAINED_GLASS_PANE,1, (byte) 5).setName("§aNiveau " + lvl).setLore(Arrays.asList("§a§lDébloqueé", lore, lore2, lore3, lore4)).toItemStack();
        } else if((accounts.getDataLvl().getCombatLvl() + 1) == lvl){
            return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 4).setName("§eNiveau " + lvl).setLore(Arrays.asList("§7Progression : §e" + df.format(progression()) + "%", generateProgressionBar(progression(), lvl, accounts.getDataLvl().getMissingCombatXp()), lore, lore2, lore3, lore4)).toItemStack();
        }

        return new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName("§cNiveau " + lvl).setLore(Arrays.asList("§c§lBloqué", lore, lore2, lore3, lore4)).toItemStack();
    }

    public double progression(){
        if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel()){
            return 110;
        } else {
            double difference = CombatLevel.needingxp(accounts.getDataLvl().getCombatLvl() + 1) - accounts.getDataLvl().getMissingCombatXp();
            double prog = (difference / CombatLevel.needingxp(accounts.getDataLvl().getCombatLvl() + 1)) * 100;
            return prog;
        }
    }

    public void addXp(long xp){
        accounts.getDataLvl().addCombatXp(xp);
        if(accounts.getDataLvl().getMissingCombatXp() <= 0){
            levelUp();
        }
    }

    public void levelUp(){
        String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.system-prefix"));
        if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel()) return;
        accounts.getDataLvl().setCombatLvl(accounts.getDataLvl().getNextCombatLvl());
        accounts.getDataLvl().setMissingCombatXp(RpgLevel.needingxp(accounts.getDataLvl().getCombatLvl()));
        Player player = Bukkit.getPlayer(accounts.getUUID());
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
        player.sendMessage("§7------------------------------------");
        player.sendMessage(prefix + "§aCombat Level UP §8: " + ChatColor.DARK_AQUA + (accounts.getDataLvl().getCombatLvl() - 1) + " §7 -> " + ChatColor.DARK_AQUA + accounts.getDataLvl().getCombatLvl());
        player.sendMessage("§7------------------------------------");

    }

    public String generateProgressionBar(double pourcentage, int lvl, double missingxp){
        if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel()){
            return "§a--------------------";
        } else {
            double needxp = CombatLevel.needingxp(lvl) - missingxp;

            if(pourcentage <= 0){
                return "§f-------------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 5 && pourcentage < 10){
                return "§a-§f------------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 10 && pourcentage < 15){
                return "§a--§f------------------ §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 15 && pourcentage < 20){
                return "§a---§f----------------- §e §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 20 && pourcentage < 25){
                return "§a----§f---------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 25 && pourcentage < 30){
                return "§a-----§f--------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 30 && pourcentage < 35){
                return "§a------§f-------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 35 && pourcentage < 40){
                return "§a-------§f------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 40 && pourcentage < 45){
                return "§a--------§f------------ §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 45 && pourcentage < 50){
                return "§a---------§f----------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 50 && pourcentage < 55){
                return "§a----------§f---------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 55 && pourcentage < 60){
                return "§a-----------§f--------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 60 && pourcentage < 65){
                return "§a------------§f-------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 65 && pourcentage < 70){
                return "§a-------------§f------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 70 && pourcentage < 75){
                return "§a--------------§f------ §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 75 && pourcentage < 80){
                return "§a---------------§f----- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 80 && pourcentage < 85){
                return "§a----------------§f---- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 85 && pourcentage < 90){
                return "§a-----------------§f--- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 90 && pourcentage < 95){
                return "§a------------------§f-- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else if(pourcentage >= 95 && pourcentage < 100){
                return "§a-------------------§f- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            } else {
                return "§f-------------------- §e " + needxp + "§6/§e" + CombatLevel.needingxp(lvl);
            }
        }
    }
}
