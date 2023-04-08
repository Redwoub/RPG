package fr.redwoub.mydoriarpg.inventory;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.CombatLevel;
import fr.redwoub.mydoriarpg.accounts.DonjonLevel;
import fr.redwoub.mydoriarpg.accounts.ForgeronLevel;
import fr.redwoub.mydoriarpg.accounts.RpgLevel;
import fr.redwoub.mydoriarpg.managers.*;
import fr.redwoub.mydoriarpg.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.DecimalFormat;
import java.util.Arrays;

public class InventoryList {

    public Inventory mainMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 5*9, "§9Menu Principal");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager rpgLevelManager = new RpgLevelManager(accounts);
            DecimalFormat df = new DecimalFormat("###.#");
            if(accounts.getDataLvl().getRpgLvl() == RpgLevel.getMaxLevel()){
                inventory.setItem(31, new ItemBuilder(Material.NETHER_STAR).setName("§aNiveau §6" + accounts.getDataLvl().getRpgLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", rpgLevelManager.generateProgressionBar(rpgLevelManager.progression(), accounts.getDataLvl().getNexpRpgLvl(), accounts.getDataLvl().getMissingRpgXp()))).toItemStack());
                inventory.setItem(10, new ItemBuilder(Material.RAW_FISH, 1, (byte) 2).setName("§aAmis").toItemStack());
                inventory.setItem(16, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 3).setName("§aGuilde").toItemStack());
                inventory.setItem(20, new ItemBuilder(Material.GOLD_SWORD).setName("§aCompétences").setLore(Arrays.asList("§7Ouvrir le menu de vos compétences")).toItemStack());
                inventory.setItem(24, new ItemBuilder(Material.PAINTING).setName("§aSkill").setLore(Arrays.asList("§7Ouvrir le menu de vos skills", "§7Qui sont combat, donjon et forgeron")).toItemStack());
                inventory.setItem(28, new ItemBuilder(Material.ENDER_CHEST).setName("§aInventaire").toItemStack());
                inventory.setItem(34, new ItemBuilder(Material.ARMOR_STAND).setName("§aWardrobe").setLore("§7Ouvrir la wardrobe").toItemStack());
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(player.getName()).setName("§aStatistique").setLore("§7Ouvrir le menu des statistiques").toItemStack());


                ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
                inventory.setItem(17, glass);
                inventory.setItem(18, glass);
                inventory.setItem(27, glass);
                inventory.setItem(26, glass);
                for(int i = 0; i < 10; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 35; i < 45; i++){
                    inventory.setItem(i, glass);
                }
            } else {
                inventory.setItem(31, new ItemBuilder(Material.NETHER_STAR).setName("§aNiveau " + accounts.getDataLvl().getRpgLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(rpgLevelManager.progression()) + "%", rpgLevelManager.generateProgressionBar(rpgLevelManager.progression(), accounts.getDataLvl().getNexpRpgLvl(), accounts.getDataLvl().getMissingRpgXp()))).toItemStack());
                inventory.setItem(10, new ItemBuilder(Material.RAW_FISH, 1, (byte) 2).setName("§aAmis").toItemStack());
                inventory.setItem(16, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 3).setName("§aGuilde").toItemStack());
                inventory.setItem(20, new ItemBuilder(Material.GOLD_SWORD).setName("§aCompétences").setLore(Arrays.asList("§7Ouvrir le menu de vos compétences")).toItemStack());
                inventory.setItem(24, new ItemBuilder(Material.PAINTING).setName("§aSkill").setLore(Arrays.asList("§7Ouvrir le menu de vos skills", "§7Qui sont combat, donjon et forgeron")).toItemStack());
                inventory.setItem(28, new ItemBuilder(Material.ENDER_CHEST).setName("§aInventaire").toItemStack());
                inventory.setItem(34, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").setLore(Arrays.asList("§7Ouvrir le menu des compétences spéciales.", "§7Elles n'appartiennent à aucune classe, elles ne sont donc", "§7Pas affecter par votre classe !")).toItemStack());
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(player.getName()).setName("§aStatistique").setLore("§7Ouvrir le menu des statistiques").toItemStack());


                ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
                inventory.setItem(17, glass);
                inventory.setItem(18, glass);
                inventory.setItem(27, glass);
                inventory.setItem(26, glass);
                for(int i = 0; i < 10; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 35; i < 45; i++){
                    inventory.setItem(i, glass);
                }
            }

        });
        return inventory;
    }

    public Inventory statistiqueMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§eStatistiques");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
            inventory.setItem(2, glass);
            inventory.setItem(3, glass);
            inventory.setItem(5, glass);
            inventory.setItem(6, glass);
            inventory.setItem(8, glass);
            inventory.setItem(9, glass);
            inventory.setItem(11, glass);
            inventory.setItem(12, glass);
            inventory.setItem(14, glass);
            inventory.setItem(15, glass);
            inventory.setItem(17, glass);
            inventory.setItem(20, glass);
            inventory.setItem(21, glass);
            inventory.setItem(23, glass);
            inventory.setItem(24, glass);
            inventory.setItem(0, new ItemBuilder(Material.PAINTING).setName("§7Player Type : " + accounts.getDataStatistique().getPlayerType().getPrefix()).setLore("§7Le player type est votre type d'items/capacité", "§7qui vous infligeron le plus de dégats.", "§7Mais vos attaques de ce type seront plus puissante.").toItemStack());
            inventory.setItem(1, new ItemBuilder(Material.APPLE).setName("§cVie §7: §f" + accounts.getDataStatistique().getMaxVie()).toItemStack());
            inventory.setItem(7, new ItemBuilder(Material.BLAZE_POWDER).setName("§cForce §7: §f" + accounts.getDataStatistique().getForce()).toItemStack());
            inventory.setItem(19, new ItemBuilder(Material.ENCHANTED_BOOK).setName("§bMana §7: §f" + accounts.getDataStatistique().getMaxMana()).toItemStack());
            inventory.setItem(4, new HeadsManager().generateCustomHead("§9Dégats Critique §7: §f" + accounts.getDataStatistique().getDegatCritique() + "%", 1, Main.getInstance().getConfig().getString("heads.wither-boss")));
            inventory.setItem(13, new ItemBuilder(Material.IRON_CHESTPLATE).setName("§aDéfense §7: §f" + accounts.getDataStatistique().getDefense()).toItemStack());
            inventory.setItem(16, new ItemBuilder(Material.SUGAR).setName("§fVitesse §7: §f" + accounts.getDataStatistique().getSpeed()).toItemStack());
            inventory.setItem(25, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 1).setName("§9Taux Critique §7: §f" + accounts.getDataStatistique().getTauxCritique() + "%").toItemStack());
            inventory.setItem(10, new ItemBuilder(Material.IRON_SWORD).setName("§eVitesse d'Attaque §7: §f" + accounts.getDataStatistique().getAttaqueSpeed() + "%").setLore("§7Cette statisque réduit le temps d'attente entre chaque flèche").toItemStack());
            inventory.setItem(18, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(22, new ItemBuilder(Material.WATCH).setName("§eNiveau de Puissance §7: §f" + PuissanceGenerator.generatePuissance(accounts)).setLore(Arrays.asList("§7Le niveau de puissance est une indication de votre force.", "§7Plus votre niveau de puissance est élevé, plus vous êtes fort")).toItemStack());
            inventory.setItem(26, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
        });
        return inventory;
    }

    public Inventory skillMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§eSkill Menu");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DecimalFormat df = new DecimalFormat("###.#");
            CombatLevelManager combatLevelManager = new CombatLevelManager(accounts);
            DonjonLevelManager donjonLevelManager = new DonjonLevelManager(accounts);
            ForgeronLevelManager forgeronLevelManager = new ForgeronLevelManager(accounts);
            ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();

            if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel() && accounts.getDataLvl().getForgeronLvl() == ForgeronLevel.getMaxLevel() && accounts.getDataLvl().getDonjonLvl() == DonjonLevel.getMaxLevel()){
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel() && accounts.getDataLvl().getDonjonLvl() == DonjonLevel.getMaxLevel()){
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(forgeronLevelManager.progression()) + "%", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel() && accounts.getDataLvl().getForgeronLvl() == ForgeronLevel.getMaxLevel()) {
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(donjonLevelManager.progression()) + "%", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getForgeronLvl() == ForgeronLevel.getMaxLevel() && accounts.getDataLvl().getDonjonLvl() == DonjonLevel.getMaxLevel()) {
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(combatLevelManager.progression()) + "%", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getCombatLvl() == CombatLevel.getMaxLevel()) {
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(donjonLevelManager.progression()) + "%", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(forgeronLevelManager.progression()) + "%", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getDonjonLvl() == DonjonLevel.getMaxLevel()){
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(combatLevelManager.progression()), combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(forgeronLevelManager.progression()) + "%", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else if(accounts.getDataLvl().getForgeronLvl() == ForgeronLevel.getMaxLevel()){
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(combatLevelManager.progression()), combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(donjonLevelManager.progression()), donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §bMAX", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            } else {
                for(int i = 0; i < 9; i++){
                    inventory.setItem(i, glass);
                }

                for(int i = 18; i < 27; i++){
                    inventory.setItem(i, glass);
                }

                inventory.setItem(9, glass);
                inventory.setItem(10, new ItemBuilder(Material.DIAMOND_SWORD).setName("§aCombat niveau §7: §a" + accounts.getDataLvl().getCombatLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(combatLevelManager.progression()) + "%", combatLevelManager.generateProgressionBar(combatLevelManager.progression(), accounts.getDataLvl().getNextCombatLvl(), accounts.getDataLvl().getMissingCombatXp()))).toItemStack());
                inventory.setItem(11, glass);
                inventory.setItem(12, glass);
                inventory.setItem(13, new ItemBuilder(Material.SKULL_ITEM,1, (byte) 1).setName("§aDonjon niveau §7: §a" + accounts.getDataLvl().getDonjonLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(donjonLevelManager.progression()) + "%", donjonLevelManager.generateProgressionBar(donjonLevelManager.progression(), accounts.getDataLvl().getNextDonjonLvl(), accounts.getDataLvl().getMissingDonjonXp()))).toItemStack());
                inventory.setItem(14, glass);
                inventory.setItem(15, glass);
                inventory.setItem(16, new ItemBuilder(Material.FURNACE).setName("§aForgeron niveau §7: §a" + accounts.getDataLvl().getForgeronLvl()).setLore(Arrays.asList("§7Progression pour le niveau suivant : §e" + df.format(forgeronLevelManager.progression()) + "%", forgeronLevelManager.generateProgressionBar(forgeronLevelManager.progression(), accounts.getDataLvl().getNextForgeronLvl(), accounts.getDataLvl().getMissingForgeronXp()))).toItemStack());
                inventory.setItem(17, glass);
            }
        });
        return inventory;
    }

    public Inventory colorChat(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9Color Chat");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
            inventory.setItem(0, new ItemBuilder(Material.INK_SACK).setName("§0Noir").toItemStack());
            inventory.setItem(1, glass);
            inventory.setItem(2, new ItemBuilder(Material.INK_SACK, 1, (byte) 1).setName("§cRouge").toItemStack());
            inventory.setItem(3, glass);
            inventory.setItem(4, new ItemBuilder(Material.INK_SACK, 1, (byte) 2).setName("§2Vert Sombre").toItemStack());
            inventory.setItem(5, glass);
            inventory.setItem(6, new ItemBuilder(Material.INK_SACK, 1, (byte) 4).setName("§9Bleu").toItemStack());
            inventory.setItem(7, glass);
            inventory.setItem(8, new ItemBuilder(Material.INK_SACK, 1, (byte) 6).setName("§3Cyan Clair").toItemStack());
            for(int i = 9; i < 19; i++){
                inventory.setItem(i, glass);
            }
            inventory.setItem(19, new ItemBuilder(Material.INK_SACK, 1, (byte) 7).setName("§7Gris Clair").toItemStack());
            inventory.setItem(20, glass);
            inventory.setItem(21, new ItemBuilder(Material.INK_SACK, 1, (byte) 8).setName("§8Gris Sombre").toItemStack());
            inventory.setItem(22, glass);
            inventory.setItem(23, new ItemBuilder(Material.INK_SACK, 1, (byte) 9).setName("§dRose").toItemStack());
            inventory.setItem(24, glass);
            inventory.setItem(25, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§aVert Clair").toItemStack());
            for(int i = 26; i < 36; i++){
                inventory.setItem(i, glass);
            }
            inventory.setItem(36, new ItemBuilder(Material.INK_SACK, 1, (byte) 11).setName("§eJaune").toItemStack());
            inventory.setItem(37, glass);
            inventory.setItem(38, new ItemBuilder(Material.INK_SACK, 1, (byte) 12).setName("§bCyan").toItemStack());
            inventory.setItem(49, glass);
            inventory.setItem(40, new ItemBuilder(Material.INK_SACK, 1, (byte) 13).setName("§5Rose Sombre").toItemStack());
            inventory.setItem(41, glass);
            inventory.setItem(42, new ItemBuilder(Material.INK_SACK, 1, (byte) 14).setName("§6Dorée").toItemStack());
            inventory.setItem(43, glass);
            inventory.setItem(44, new ItemBuilder(Material.INK_SACK, 1, (byte) 15).setName("§fBlanc").toItemStack());
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(53, new ItemBuilder(Material.BARRIER).setName("§cFermer le menu").toItemStack());

            for(int i = 46; i < 53; i++){
                inventory.setItem(i, glass);
            }
        });
        return inventory;
    }

    public Inventory combatLevel1(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            CombatLevelManager levelManager = new CombatLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(1));
            inventory.setItem(9, levelManager.createGlass(2));
            inventory.setItem(18, levelManager.createGlass(3));
            inventory.setItem(27, levelManager.createGlass(4));
            inventory.setItem(36, levelManager.createGlass(5));
            inventory.setItem(37, levelManager.createGlass(6));
            inventory.setItem(38, levelManager.createGlass(7));
            inventory.setItem(29, levelManager.createGlass(8));
            inventory.setItem(20, levelManager.createGlass(9));
            inventory.setItem(11, levelManager.createGlass(10));
            inventory.setItem(2, levelManager.createGlass(11));
            inventory.setItem(3, levelManager.createGlass(12));
            inventory.setItem(4, levelManager.createGlass(13));
            inventory.setItem(13, levelManager.createGlass(14));
            inventory.setItem(22, levelManager.createGlass(15));
            inventory.setItem(31, levelManager.createGlass(16));
            inventory.setItem(40, levelManager.createGlass(17));
            inventory.setItem(41, levelManager.createGlass(18));
            inventory.setItem(42, levelManager.createGlass(19));
            inventory.setItem(33, levelManager.createGlass(20));
            inventory.setItem(24, levelManager.createGlass(21));
            inventory.setItem(15, levelManager.createGlass(22));
            inventory.setItem(6, levelManager.createGlass(23));
            inventory.setItem(7, levelManager.createGlass(24));
            inventory.setItem(8, levelManager.createGlass(25));
            inventory.setItem(17, levelManager.createGlass(26));
            inventory.setItem(26, levelManager.createGlass(27));
            inventory.setItem(35, levelManager.createGlass(28));
            inventory.setItem(44, levelManager.createGlass(29));
            inventory.setItem(53, levelManager.createGlass(30));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Voir votre progression", "§7du niveau 31 à 60")).toItemStack());
        });

        return inventory;
    }

    public Inventory combatLevel2(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            CombatLevelManager levelManager = new CombatLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(31));
            inventory.setItem(9, levelManager.createGlass(32));
            inventory.setItem(18, levelManager.createGlass(33));
            inventory.setItem(27, levelManager.createGlass(34));
            inventory.setItem(36, levelManager.createGlass(35));
            inventory.setItem(37, levelManager.createGlass(36));
            inventory.setItem(38, levelManager.createGlass(37));
            inventory.setItem(29, levelManager.createGlass(38));
            inventory.setItem(20, levelManager.createGlass(39));
            inventory.setItem(11, levelManager.createGlass(40));
            inventory.setItem(2, levelManager.createGlass(41));
            inventory.setItem(3, levelManager.createGlass(42));
            inventory.setItem(4, levelManager.createGlass(43));
            inventory.setItem(13, levelManager.createGlass(44));
            inventory.setItem(22, levelManager.createGlass(45));
            inventory.setItem(31, levelManager.createGlass(46));
            inventory.setItem(40, levelManager.createGlass(47));
            inventory.setItem(41, levelManager.createGlass(48));
            inventory.setItem(42, levelManager.createGlass(49));
            inventory.setItem(33, levelManager.createGlass(50));
            inventory.setItem(24, levelManager.createGlass(51));
            inventory.setItem(15, levelManager.createGlass(52));
            inventory.setItem(6, levelManager.createGlass(53));
            inventory.setItem(7, levelManager.createGlass(54));
            inventory.setItem(8, levelManager.createGlass(55));
            inventory.setItem(17, levelManager.createGlass(56));
            inventory.setItem(26, levelManager.createGlass(57));
            inventory.setItem(35, levelManager.createGlass(58));
            inventory.setItem(44, levelManager.createGlass(59));
            inventory.setItem(53, levelManager.createGlass(60));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 1 à 30")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 61 à 90")).toItemStack());
        });

        return inventory;
    }

    public Inventory combatLevel3(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            CombatLevelManager levelManager = new CombatLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(61));
            inventory.setItem(9, levelManager.createGlass(62));
            inventory.setItem(18, levelManager.createGlass(63));
            inventory.setItem(27, levelManager.createGlass(64));
            inventory.setItem(36, levelManager.createGlass(65));
            inventory.setItem(37, levelManager.createGlass(66));
            inventory.setItem(38, levelManager.createGlass(67));
            inventory.setItem(29, levelManager.createGlass(68));
            inventory.setItem(20, levelManager.createGlass(69));
            inventory.setItem(11, levelManager.createGlass(70));
            inventory.setItem(2, levelManager.createGlass(71));
            inventory.setItem(3, levelManager.createGlass(72));
            inventory.setItem(4, levelManager.createGlass(73));
            inventory.setItem(13, levelManager.createGlass(74));
            inventory.setItem(22, levelManager.createGlass(75));
            inventory.setItem(31, levelManager.createGlass(76));
            inventory.setItem(40, levelManager.createGlass(77));
            inventory.setItem(41, levelManager.createGlass(78));
            inventory.setItem(42, levelManager.createGlass(79));
            inventory.setItem(33, levelManager.createGlass(80));
            inventory.setItem(24, levelManager.createGlass(81));
            inventory.setItem(15, levelManager.createGlass(82));
            inventory.setItem(6, levelManager.createGlass(83));
            inventory.setItem(7, levelManager.createGlass(84));
            inventory.setItem(8, levelManager.createGlass(85));
            inventory.setItem(17, levelManager.createGlass(86));
            inventory.setItem(26, levelManager.createGlass(87));
            inventory.setItem(35, levelManager.createGlass(88));
            inventory.setItem(44, levelManager.createGlass(89));
            inventory.setItem(53, levelManager.createGlass(90));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 31 à 60")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            });
        return inventory;
    }

    public Inventory donjonLevel1(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(1));
            inventory.setItem(9, levelManager.createGlass(2, null, "§7Donjon items boosté de §c1%"));
            inventory.setItem(18, levelManager.createGlass(3, null, "§7Donjon items boosté de §c2%"));
            inventory.setItem(27, levelManager.createGlass(4, null, "§7Donjon items boosté de §c3%"));
            inventory.setItem(36, levelManager.createGlass(5, null, "§7Donjon items boosté de §c4%"));
            inventory.setItem(37, levelManager.createGlass(6, null, "§7Donjon items boosté de §c5%"));
            inventory.setItem(38, levelManager.createGlass(7, null, "§7Donjon items boosté de §c6%"));
            inventory.setItem(29, levelManager.createGlass(8, null, "§7Donjon items boosté de §c7%"));
            inventory.setItem(20, levelManager.createGlass(9, null, "§7Donjon items boosté de §c8%"));
            inventory.setItem(11, levelManager.createGlass(10, null, "§7Donjon items boosté de §c9%"));
            inventory.setItem(2, levelManager.createGlass(11, null, "§7Donjon items boosté de §c10%"));
            inventory.setItem(3, levelManager.createGlass(12, null, "§7Donjon items boosté de §c11%"));
            inventory.setItem(4, levelManager.createGlass(13, null, "§7Donjon items boosté de §c12%"));
            inventory.setItem(13, levelManager.createGlass(14, null, "§7Donjon items boosté de §c13%"));
            inventory.setItem(22, levelManager.createGlass(15, null, "§7Donjon items boosté de §c14%"));
            inventory.setItem(31, levelManager.createGlass(16, null, "§7Donjon items boosté de §c15%"));
            inventory.setItem(40, levelManager.createGlass(17, null, "§7Donjon items boosté de §c16%"));
            inventory.setItem(41, levelManager.createGlass(18, null, "§7Donjon items boosté de §c17%"));
            inventory.setItem(42, levelManager.createGlass(19, null, "§7Donjon items boosté de §c18%"));
            inventory.setItem(33, levelManager.createGlass(20, null, "§7Donjon items boosté de §c19%", "§7Accès au donjon tier 2"));
            inventory.setItem(24, levelManager.createGlass(21, null, "§7Donjon items boosté de §c20%"));
            inventory.setItem(15, levelManager.createGlass(22, null, "§7Donjon items boosté de §c21%"));
            inventory.setItem(6, levelManager.createGlass(23, null, "§7Donjon items boosté de §c22%"));
            inventory.setItem(7, levelManager.createGlass(24, null, "§7Donjon items boosté de §c23%"));
            inventory.setItem(8, levelManager.createGlass(25, null, "§7Donjon items boosté de §c24%"));
            inventory.setItem(17, levelManager.createGlass(26, null, "§7Donjon items boosté de §c25%"));
            inventory.setItem(26, levelManager.createGlass(27, null, "§7Donjon items boosté de §c26%"));
            inventory.setItem(35, levelManager.createGlass(28, null, "§7Donjon items boosté de §c27%"));
            inventory.setItem(44, levelManager.createGlass(29, null, "§7Donjon items boosté de §c28%"));
            inventory.setItem(53, levelManager.createGlass(30, null, "§7Donjon items boosté de §c29%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Voir votre progression", "§7du niveau 31 à 60")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel2(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(31, null, "§7Donjon items boosté de §c30%"));
            inventory.setItem(9, levelManager.createGlass(32, null, "§7Donjon items boosté de §c31%"));
            inventory.setItem(18, levelManager.createGlass(33, null, "§7Donjon items boosté de §c32%"));
            inventory.setItem(27, levelManager.createGlass(34, null, "§7Donjon items boosté de §c33%"));
            inventory.setItem(36, levelManager.createGlass(35, null, "§7Donjon items boosté de §c34%", "§7Accès au donjon tier 3"));
            inventory.setItem(37, levelManager.createGlass(36, null, "§7Donjon items boosté de §c35%"));
            inventory.setItem(38, levelManager.createGlass(37, null, "§7Donjon items boosté de §c36%"));
            inventory.setItem(29, levelManager.createGlass(38, null, "§7Donjon items boosté de §c37%"));
            inventory.setItem(20, levelManager.createGlass(39, null, "§7Donjon items boosté de §c38%"));
            inventory.setItem(11, levelManager.createGlass(40, null, "§7Donjon items boosté de §c39%"));
            inventory.setItem(2, levelManager.createGlass(41, null, "§7Donjon items boosté de §c40%"));
            inventory.setItem(3, levelManager.createGlass(42, null, "§7Donjon items boosté de §c41%"));
            inventory.setItem(4, levelManager.createGlass(43, null, "§7Donjon items boosté de §c42%"));
            inventory.setItem(13, levelManager.createGlass(44, null, "§7Donjon items boosté de §c43%"));
            inventory.setItem(22, levelManager.createGlass(45, null, "§7Donjon items boosté de §c44%"));
            inventory.setItem(31, levelManager.createGlass(46, null, "§7Donjon items boosté de §c45%"));
            inventory.setItem(40, levelManager.createGlass(47, null, "§7Donjon items boosté de §c46%"));
            inventory.setItem(41, levelManager.createGlass(48, null, "§7Donjon items boosté de §c47%"));
            inventory.setItem(42, levelManager.createGlass(49, null, "§7Donjon items boosté de §c48%"));
            inventory.setItem(33, levelManager.createGlass(50, null, "§7Donjon items boosté de §c49%", "§7Accès au donjon tier 4"));
            inventory.setItem(24, levelManager.createGlass(51, null, "§7Donjon items boosté de §c50%"));
            inventory.setItem(15, levelManager.createGlass(52, null, "§7Donjon items boosté de §c51%"));
            inventory.setItem(6, levelManager.createGlass(53, null, "§7Donjon items boosté de §c52%"));
            inventory.setItem(7, levelManager.createGlass(54, null, "§7Donjon items boosté de §c53%"));
            inventory.setItem(8, levelManager.createGlass(55, null, "§7Donjon items boosté de §c54%"));
            inventory.setItem(17, levelManager.createGlass(56, null, "§7Donjon items boosté de §c55%"));
            inventory.setItem(26, levelManager.createGlass(57, null, "§7Donjon items boosté de §c56%"));
            inventory.setItem(35, levelManager.createGlass(58, null, "§7Donjon items boosté de §c57%"));
            inventory.setItem(44, levelManager.createGlass(59, null, "§7Donjon items boosté de §c58%"));
            inventory.setItem(53, levelManager.createGlass(60, null, "§7Donjon items boosté de §c59%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 1 à 30")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 61 à 91")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel3(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(61, null,"§7Donjon items boosté de §c60%"));
            inventory.setItem(9, levelManager.createGlass(62, null,"§7Donjon items boosté de §c61%"));
            inventory.setItem(18, levelManager.createGlass(63, null,"§7Donjon items boosté de §c62%"));
            inventory.setItem(27, levelManager.createGlass(64, null,"§7Donjon items boosté de §c63%"));
            inventory.setItem(36, levelManager.createGlass(65, null,"§7Donjon items boosté de §c64%"));
            inventory.setItem(37, levelManager.createGlass(66, null,"§7Donjon items boosté de §c65%"));
            inventory.setItem(38, levelManager.createGlass(67, null,"§7Donjon items boosté de §c66%"));
            inventory.setItem(29, levelManager.createGlass(68, null,"§7Donjon items boosté de §c67%"));
            inventory.setItem(20, levelManager.createGlass(69, null,"§7Donjon items boosté de §c68%"));
            inventory.setItem(11, levelManager.createGlass(70, null,"§7Donjon items boosté de §c69%"));
            inventory.setItem(2, levelManager.createGlass(71, null,"§7Donjon items boosté de §c70%"));
            inventory.setItem(3, levelManager.createGlass(72, null,"§7Donjon items boosté de §c71%"));
            inventory.setItem(4, levelManager.createGlass(73, null,"§7Donjon items boosté de §c72%"));
            inventory.setItem(13, levelManager.createGlass(74, null,"§7Donjon items boosté de §c73%"));
            inventory.setItem(22, levelManager.createGlass(75, null,"§7Donjon items boosté de §c74%", "§7Accès au donjon tier 5"));
            inventory.setItem(31, levelManager.createGlass(76, null,"§7Donjon items boosté de §c75%"));
            inventory.setItem(40, levelManager.createGlass(77, null,"§7Donjon items boosté de §c76%"));
            inventory.setItem(41, levelManager.createGlass(78, null,"§7Donjon items boosté de §c77%"));
            inventory.setItem(42, levelManager.createGlass(79, null,"§7Donjon items boosté de §c78%"));
            inventory.setItem(33, levelManager.createGlass(80, null,"§7Donjon items boosté de §c79%"));
            inventory.setItem(24, levelManager.createGlass(81, null,"§7Donjon items boosté de §c80%"));
            inventory.setItem(15, levelManager.createGlass(82, null,"§7Donjon items boosté de §c81%"));
            inventory.setItem(6, levelManager.createGlass(83, null,"§7Donjon items boosté de §c82%"));
            inventory.setItem(7, levelManager.createGlass(84, null,"§7Donjon items boosté de §c83%"));
            inventory.setItem(8, levelManager.createGlass(85, null,"§7Donjon items boosté de §c84%"));
            inventory.setItem(17, levelManager.createGlass(86, null,"§7Donjon items boosté de §c85%"));
            inventory.setItem(26, levelManager.createGlass(87, null,"§7Donjon items boosté de §c86%"));
            inventory.setItem(35, levelManager.createGlass(88, null,"§7Donjon items boosté de §c87%"));
            inventory.setItem(44, levelManager.createGlass(89, null,"§7Donjon items boosté de §c88%"));
            inventory.setItem(53, levelManager.createGlass(90, null,"§7Donjon items boosté de §c89%", "§7Accès au donjon tier 6"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 31 à 60")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 91 à 120")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel4(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(91, null,"§7Donjon items boosté de §c90%"));
            inventory.setItem(9, levelManager.createGlass(92, null,"§7Donjon items boosté de §c91%"));
            inventory.setItem(18, levelManager.createGlass(93, null,"§7Donjon items boosté de §c92%"));
            inventory.setItem(27, levelManager.createGlass(94, null,"§7Donjon items boosté de §c93%"));
            inventory.setItem(36, levelManager.createGlass(95, null,"§7Donjon items boosté de §c94%"));
            inventory.setItem(37, levelManager.createGlass(96, null,"§7Donjon items boosté de §c95%"));
            inventory.setItem(38, levelManager.createGlass(97, null,"§7Donjon items boosté de §c96%"));
            inventory.setItem(29, levelManager.createGlass(98, null,"§7Donjon items boosté de §c97%"));
            inventory.setItem(20, levelManager.createGlass(99, null,"§7Donjon items boosté de §c98%"));
            inventory.setItem(11, levelManager.createGlass(100, null,"§7Donjon items boosté de §c99%"));
            inventory.setItem(2, levelManager.createGlass(101, null,"§7Donjon items boosté de §c100%"));
            inventory.setItem(3, levelManager.createGlass(102, null,"§7Donjon items boosté de §c101%"));
            inventory.setItem(4, levelManager.createGlass(103, null,"§7Donjon items boosté de §c102%"));
            inventory.setItem(13, levelManager.createGlass(104, null,"§7Donjon items boosté de §c103%"));
            inventory.setItem(22, levelManager.createGlass(105, null,"§7Donjon items boosté de §c104%", "§7Accès au donjon tier 7"));
            inventory.setItem(31, levelManager.createGlass(106, null,"§7Donjon items boosté de §c105%"));
            inventory.setItem(40, levelManager.createGlass(107, null,"§7Donjon items boosté de §c106%"));
            inventory.setItem(41, levelManager.createGlass(108, null,"§7Donjon items boosté de §c107%"));
            inventory.setItem(42, levelManager.createGlass(109, null,"§7Donjon items boosté de §c108%"));
            inventory.setItem(33, levelManager.createGlass(110, null,"§7Donjon items boosté de §c109%"));
            inventory.setItem(24, levelManager.createGlass(111, null,"§7Donjon items boosté de §c110%"));
            inventory.setItem(15, levelManager.createGlass(112, null,"§7Donjon items boosté de §c111%"));
            inventory.setItem(6, levelManager.createGlass(113, null,"§7Donjon items boosté de §c112%"));
            inventory.setItem(7, levelManager.createGlass(114, null,"§7Donjon items boosté de §c113%"));
            inventory.setItem(8, levelManager.createGlass(115, null,"§7Donjon items boosté de §c114%"));
            inventory.setItem(17, levelManager.createGlass(116, null,"§7Donjon items boosté de §c115%"));
            inventory.setItem(26, levelManager.createGlass(117, null,"§7Donjon items boosté de §c116%"));
            inventory.setItem(35, levelManager.createGlass(118, null,"§7Donjon items boosté de §c117%"));
            inventory.setItem(44, levelManager.createGlass(119, null,"§7Donjon items boosté de §c118%"));
            inventory.setItem(53, levelManager.createGlass(120, null,"§7Donjon items boosté de §c119%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 61 à 90")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 121 à 150")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel5(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(121, null,"§7Donjon items boosté de §c120%"));
            inventory.setItem(9, levelManager.createGlass(122, null,"§7Donjon items boosté de §c121%"));
            inventory.setItem(18, levelManager.createGlass(123, null,"§7Donjon items boosté de §c122%"));
            inventory.setItem(27, levelManager.createGlass(124, null,"§7Donjon items boosté de §c123%"));
            inventory.setItem(36, levelManager.createGlass(125, null,"§7Donjon items boosté de §c124%"));
            inventory.setItem(37, levelManager.createGlass(126, null,"§7Donjon items boosté de §c125%"));
            inventory.setItem(38, levelManager.createGlass(127, null,"§7Donjon items boosté de §c126%"));
            inventory.setItem(29, levelManager.createGlass(128, null,"§7Donjon items boosté de §c127%"));
            inventory.setItem(20, levelManager.createGlass(129, null,"§7Donjon items boosté de §c128%"));
            inventory.setItem(11, levelManager.createGlass(130, null,"§7Donjon items boosté de §c129%"));
            inventory.setItem(2, levelManager.createGlass(131, null,"§7Donjon items boosté de §c130%"));
            inventory.setItem(3, levelManager.createGlass(132, null,"§7Donjon items boosté de §c131%"));
            inventory.setItem(4, levelManager.createGlass(133, null,"§7Donjon items boosté de §c132%"));
            inventory.setItem(13, levelManager.createGlass(134, null,"§7Donjon items boosté de §c133%"));
            inventory.setItem(22, levelManager.createGlass(135, null,"§7Donjon items boosté de §c134%"));
            inventory.setItem(31, levelManager.createGlass(136, null,"§7Donjon items boosté de §c135%"));
            inventory.setItem(40, levelManager.createGlass(137, null,"§7Donjon items boosté de §c136%"));
            inventory.setItem(41, levelManager.createGlass(138, null,"§7Donjon items boosté de §c137%"));
            inventory.setItem(42, levelManager.createGlass(139, null,"§7Donjon items boosté de §c138%"));
            inventory.setItem(33, levelManager.createGlass(140, null,"§7Donjon items boosté de §c139%"));
            inventory.setItem(24, levelManager.createGlass(141, null,"§7Donjon items boosté de §c140%"));
            inventory.setItem(15, levelManager.createGlass(142, null,"§7Donjon items boosté de §c141%"));
            inventory.setItem(6, levelManager.createGlass(143, null,"§7Donjon items boosté de §c142%"));
            inventory.setItem(7, levelManager.createGlass(144, null,"§7Donjon items boosté de §c143%"));
            inventory.setItem(8, levelManager.createGlass(145, null,"§7Donjon items boosté de §c144%"));
            inventory.setItem(17, levelManager.createGlass(146, null,"§7Donjon items boosté de §c145%"));
            inventory.setItem(26, levelManager.createGlass(147, null,"§7Donjon items boosté de §c146%"));
            inventory.setItem(35, levelManager.createGlass(148, null,"§7Donjon items boosté de §c147%"));
            inventory.setItem(44, levelManager.createGlass(149, null,"§7Donjon items boosté de §c148%"));
            inventory.setItem(53, levelManager.createGlass(150, null,"§7Donjon items boosté de §c149%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 91 à 120")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 151 à 180")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel6(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(151, null,"§7Donjon items boosté de §c150%"));
            inventory.setItem(9, levelManager.createGlass(152, null,"§7Donjon items boosté de §c151%"));
            inventory.setItem(18, levelManager.createGlass(153, null,"§7Donjon items boosté de §c152%"));
            inventory.setItem(27, levelManager.createGlass(154, null,"§7Donjon items boosté de §c153%"));
            inventory.setItem(36, levelManager.createGlass(155, null,"§7Donjon items boosté de §c154%"));
            inventory.setItem(37, levelManager.createGlass(156, null,"§7Donjon items boosté de §c155%"));
            inventory.setItem(38, levelManager.createGlass(157, null,"§7Donjon items boosté de §c156%"));
            inventory.setItem(29, levelManager.createGlass(158, null,"§7Donjon items boosté de §c157%"));
            inventory.setItem(20, levelManager.createGlass(159, null,"§7Donjon items boosté de §c158%"));
            inventory.setItem(11, levelManager.createGlass(160, null,"§7Donjon items boosté de §c159%"));
            inventory.setItem(2, levelManager.createGlass(161, null,"§7Donjon items boosté de §c160%"));
            inventory.setItem(3, levelManager.createGlass(162, null,"§7Donjon items boosté de §c161%"));
            inventory.setItem(4, levelManager.createGlass(163, null,"§7Donjon items boosté de §c162%"));
            inventory.setItem(13, levelManager.createGlass(164, null,"§7Donjon items boosté de §c163%"));
            inventory.setItem(22, levelManager.createGlass(165, null,"§7Donjon items boosté de §c164%"));
            inventory.setItem(31, levelManager.createGlass(166, null,"§7Donjon items boosté de §c165%"));
            inventory.setItem(40, levelManager.createGlass(167, null,"§7Donjon items boosté de §c166%"));
            inventory.setItem(41, levelManager.createGlass(168, null,"§7Donjon items boosté de §c167%"));
            inventory.setItem(42, levelManager.createGlass(169, null,"§7Donjon items boosté de §c168%"));
            inventory.setItem(33, levelManager.createGlass(170, null,"§7Donjon items boosté de §c169%"));
            inventory.setItem(24, levelManager.createGlass(171, null,"§7Donjon items boosté de §c170%"));
            inventory.setItem(15, levelManager.createGlass(172, null,"§7Donjon items boosté de §c171%"));
            inventory.setItem(6, levelManager.createGlass(173, null,"§7Donjon items boosté de §c172%"));
            inventory.setItem(7, levelManager.createGlass(174, null,"§7Donjon items boosté de §c173%"));
            inventory.setItem(8, levelManager.createGlass(175, null,"§7Donjon items boosté de §c174%"));
            inventory.setItem(17, levelManager.createGlass(176, null,"§7Donjon items boosté de §c175%"));
            inventory.setItem(26, levelManager.createGlass(177, null,"§7Donjon items boosté de §c176%"));
            inventory.setItem(35, levelManager.createGlass(178, null,"§7Donjon items boosté de §c177%"));
            inventory.setItem(44, levelManager.createGlass(179, null,"§7Donjon items boosté de §c178%"));
            inventory.setItem(53, levelManager.createGlass(180, null,"§7Donjon items boosté de §c179%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 121 à 150")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 181 à 210")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel7(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(181, null,"§7Donjon items boosté de §c180%"));
            inventory.setItem(9, levelManager.createGlass(182, null,"§7Donjon items boosté de §c181%"));
            inventory.setItem(18, levelManager.createGlass(183, null,"§7Donjon items boosté de §c182%"));
            inventory.setItem(27, levelManager.createGlass(184, null,"§7Donjon items boosté de §c183%"));
            inventory.setItem(36, levelManager.createGlass(185, null,"§7Donjon items boosté de §c184%"));
            inventory.setItem(37, levelManager.createGlass(186, null,"§7Donjon items boosté de §c185%"));
            inventory.setItem(38, levelManager.createGlass(187, null,"§7Donjon items boosté de §c186%"));
            inventory.setItem(29, levelManager.createGlass(188, null,"§7Donjon items boosté de §c187%"));
            inventory.setItem(20, levelManager.createGlass(189, null,"§7Donjon items boosté de §c188%"));
            inventory.setItem(11, levelManager.createGlass(190, null,"§7Donjon items boosté de §c189%"));
            inventory.setItem(2, levelManager.createGlass(191, null,"§7Donjon items boosté de §c190%"));
            inventory.setItem(3, levelManager.createGlass(192, null,"§7Donjon items boosté de §c191%"));
            inventory.setItem(4, levelManager.createGlass(193, null,"§7Donjon items boosté de §c192%"));
            inventory.setItem(13, levelManager.createGlass(194, null,"§7Donjon items boosté de §c193%"));
            inventory.setItem(22, levelManager.createGlass(195, null,"§7Donjon items boosté de §c194%"));
            inventory.setItem(31, levelManager.createGlass(196, null,"§7Donjon items boosté de §c195%"));
            inventory.setItem(40, levelManager.createGlass(197, null,"§7Donjon items boosté de §c196%"));
            inventory.setItem(41, levelManager.createGlass(198, null,"§7Donjon items boosté de §c197%"));
            inventory.setItem(42, levelManager.createGlass(199, null,"§7Donjon items boosté de §c198%"));
            inventory.setItem(33, levelManager.createGlass(200, null,"§7Donjon items boosté de §c199%"));
            inventory.setItem(24, levelManager.createGlass(201, null,"§7Donjon items boosté de §c200%"));
            inventory.setItem(15, levelManager.createGlass(202, null,"§7Donjon items boosté de §c201%"));
            inventory.setItem(6, levelManager.createGlass(203, null,"§7Donjon items boosté de §c202%"));
            inventory.setItem(7, levelManager.createGlass(204, null,"§7Donjon items boosté de §c203%"));
            inventory.setItem(8, levelManager.createGlass(205, null,"§7Donjon items boosté de §c204%"));
            inventory.setItem(17, levelManager.createGlass(206, null,"§7Donjon items boosté de §c205%"));
            inventory.setItem(26, levelManager.createGlass(207, null,"§7Donjon items boosté de §c206%"));
            inventory.setItem(35, levelManager.createGlass(208, null,"§7Donjon items boosté de §c207%"));
            inventory.setItem(44, levelManager.createGlass(209, null,"§7Donjon items boosté de §c208%"));
            inventory.setItem(53, levelManager.createGlass(210, null,"§7Donjon items boosté de §c209%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 151 à 180")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 211 à 240")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel8(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(211, null,"§7Donjon items boosté de §c210%"));
            inventory.setItem(9, levelManager.createGlass(212, null,"§7Donjon items boosté de §c211%"));
            inventory.setItem(18, levelManager.createGlass(213, null,"§7Donjon items boosté de §c212%"));
            inventory.setItem(27, levelManager.createGlass(214, null,"§7Donjon items boosté de §c213.%"));
            inventory.setItem(36, levelManager.createGlass(215, null,"§7Donjon items boosté de §c214%"));
            inventory.setItem(37, levelManager.createGlass(216, null,"§7Donjon items boosté de §c215%"));
            inventory.setItem(38, levelManager.createGlass(217, null,"§7Donjon items boosté de §c216%"));
            inventory.setItem(29, levelManager.createGlass(218, null,"§7Donjon items boosté de §c217%"));
            inventory.setItem(20, levelManager.createGlass(219, null,"§7Donjon items boosté de §c218%"));
            inventory.setItem(11, levelManager.createGlass(220, null,"§7Donjon items boosté de §c219%"));
            inventory.setItem(2, levelManager.createGlass(221, null,"§7Donjon items boosté de §c220%"));
            inventory.setItem(3, levelManager.createGlass(222, null,"§7Donjon items boosté de §c221%"));
            inventory.setItem(4, levelManager.createGlass(223, null,"§7Donjon items boosté de §c222%"));
            inventory.setItem(13, levelManager.createGlass(224, null,"§7Donjon items boosté de §c223%"));
            inventory.setItem(22, levelManager.createGlass(225, null,"§7Donjon items boosté de §c224%"));
            inventory.setItem(31, levelManager.createGlass(226, null,"§7Donjon items boosté de §c225%"));
            inventory.setItem(40, levelManager.createGlass(227, null,"§7Donjon items boosté de §c226%"));
            inventory.setItem(41, levelManager.createGlass(228, null,"§7Donjon items boosté de §c227%"));
            inventory.setItem(42, levelManager.createGlass(229, null,"§7Donjon items boosté de §c228%"));
            inventory.setItem(33, levelManager.createGlass(230, null,"§7Donjon items boosté de §c229%"));
            inventory.setItem(24, levelManager.createGlass(231, null,"§7Donjon items boosté de §c230%"));
            inventory.setItem(15, levelManager.createGlass(232, null,"§7Donjon items boosté de §c231%"));
            inventory.setItem(6, levelManager.createGlass(233, null,"§7Donjon items boosté de §c232%"));
            inventory.setItem(7, levelManager.createGlass(234, null,"§7Donjon items boosté de §c233%"));
            inventory.setItem(8, levelManager.createGlass(235, null,"§7Donjon items boosté de §c234%"));
            inventory.setItem(17, levelManager.createGlass(236, null,"§7Donjon items boosté de §c235%"));
            inventory.setItem(26, levelManager.createGlass(237, null,"§7Donjon items boosté de §c236%"));
            inventory.setItem(35, levelManager.createGlass(238, null,"§7Donjon items boosté de §c237%"));
            inventory.setItem(44, levelManager.createGlass(239, null,"§7Donjon items boosté de §c238%"));
            inventory.setItem(53, levelManager.createGlass(240, null,"§7Donjon items boosté de §c239%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 181 à 210")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel9(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(241, null,"§7Donjon items boosté de §c240%"));
            inventory.setItem(9, levelManager.createGlass(242, null,"§7Donjon items boosté de §c241%"));
            inventory.setItem(18, levelManager.createGlass(243, null,"§7Donjon items boosté de §c242%"));
            inventory.setItem(27, levelManager.createGlass(244, null,"§7Donjon items boosté de §c243%"));
            inventory.setItem(36, levelManager.createGlass(245, null,"§7Donjon items boosté de §c244%"));
            inventory.setItem(37, levelManager.createGlass(246, null,"§7Donjon items boosté de §c245%"));
            inventory.setItem(38, levelManager.createGlass(247, null,"§7Donjon items boosté de §c246%"));
            inventory.setItem(29, levelManager.createGlass(248, null,"§7Donjon items boosté de §c247%"));
            inventory.setItem(20, levelManager.createGlass(249, null,"§7Donjon items boosté de §c248%"));
            inventory.setItem(11, levelManager.createGlass(250, null,"§7Donjon items boosté de §c249%"));
            inventory.setItem(2, levelManager.createGlass(251, null,"§7Donjon items boosté de §c250%"));
            inventory.setItem(3, levelManager.createGlass(252, null,"§7Donjon items boosté de §c251%"));
            inventory.setItem(4, levelManager.createGlass(253, null,"§7Donjon items boosté de §c252%"));
            inventory.setItem(13, levelManager.createGlass(254, null,"§7Donjon items boosté de §c253%"));
            inventory.setItem(22, levelManager.createGlass(255, null,"§7Donjon items boosté de §c254%"));
            inventory.setItem(31, levelManager.createGlass(256, null,"§7Donjon items boosté de §c255%"));
            inventory.setItem(40, levelManager.createGlass(257, null,"§7Donjon items boosté de §c256%"));
            inventory.setItem(41, levelManager.createGlass(258, null,"§7Donjon items boosté de §c257%"));
            inventory.setItem(42, levelManager.createGlass(259, null,"§7Donjon items boosté de §c258%"));
            inventory.setItem(33, levelManager.createGlass(260, null,"§7Donjon items boosté de §c259%"));
            inventory.setItem(24, levelManager.createGlass(261, null,"§7Donjon items boosté de §c260%"));
            inventory.setItem(15, levelManager.createGlass(262, null,"§7Donjon items boosté de §c261%"));
            inventory.setItem(6, levelManager.createGlass(263, null,"§7Donjon items boosté de §c262%"));
            inventory.setItem(7, levelManager.createGlass(264, null,"§7Donjon items boosté de §c263%"));
            inventory.setItem(8, levelManager.createGlass(265, null,"§7Donjon items boosté de §c264%"));
            inventory.setItem(17, levelManager.createGlass(266, null,"§7Donjon items boosté de §c265%"));
            inventory.setItem(26, levelManager.createGlass(267, null,"§7Donjon items boosté de §c266%"));
            inventory.setItem(35, levelManager.createGlass(268, null,"§7Donjon items boosté de §c267%"));
            inventory.setItem(44, levelManager.createGlass(269, null,"§7Donjon items boosté de §c268%"));
            inventory.setItem(53, levelManager.createGlass(270, null,"§7Donjon items boosté de §c269%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 211 à 240")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 271 à 300")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel10(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(271, null,"§7Donjon items boosté de §c270%"));
            inventory.setItem(9, levelManager.createGlass(272, null,"§7Donjon items boosté de §c271%"));
            inventory.setItem(18, levelManager.createGlass(273, null,"§7Donjon items boosté de §c272%"));
            inventory.setItem(27, levelManager.createGlass(274, null,"§7Donjon items boosté de §c273%"));
            inventory.setItem(36, levelManager.createGlass(275, null,"§7Donjon items boosté de §c274%"));
            inventory.setItem(37, levelManager.createGlass(276, null,"§7Donjon items boosté de §c275%"));
            inventory.setItem(38, levelManager.createGlass(277, null,"§7Donjon items boosté de §c276%"));
            inventory.setItem(29, levelManager.createGlass(278, null,"§7Donjon items boosté de §c277%"));
            inventory.setItem(20, levelManager.createGlass(279, null,"§7Donjon items boosté de §c278%"));
            inventory.setItem(11, levelManager.createGlass(280, null,"§7Donjon items boosté de §c279%"));
            inventory.setItem(2, levelManager.createGlass(281, null,"§7Donjon items boosté de §c280%"));
            inventory.setItem(3, levelManager.createGlass(282, null,"§7Donjon items boosté de §c281%"));
            inventory.setItem(4, levelManager.createGlass(283, null,"§7Donjon items boosté de §c282%"));
            inventory.setItem(13, levelManager.createGlass(284, null,"§7Donjon items boosté de §c283%"));
            inventory.setItem(22, levelManager.createGlass(285, null,"§7Donjon items boosté de §c284%"));
            inventory.setItem(31, levelManager.createGlass(286, null,"§7Donjon items boosté de §c285%"));
            inventory.setItem(40, levelManager.createGlass(287, null,"§7Donjon items boosté de §c286%"));
            inventory.setItem(41, levelManager.createGlass(288, null,"§7Donjon items boosté de §c287%"));
            inventory.setItem(42, levelManager.createGlass(289, null,"§7Donjon items boosté de §c288%"));
            inventory.setItem(33, levelManager.createGlass(290, null,"§7Donjon items boosté de §c289%"));
            inventory.setItem(24, levelManager.createGlass(291, null,"§7Donjon items boosté de §c290%"));
            inventory.setItem(15, levelManager.createGlass(292, null,"§7Donjon items boosté de §c291%"));
            inventory.setItem(6, levelManager.createGlass(293, null,"§7Donjon items boosté de §c292%"));
            inventory.setItem(7, levelManager.createGlass(294, null,"§7Donjon items boosté de §c293%"));
            inventory.setItem(8, levelManager.createGlass(295, null,"§7Donjon items boosté de §c294%"));
            inventory.setItem(17, levelManager.createGlass(296, null,"§7Donjon items boosté de §c295%"));
            inventory.setItem(26, levelManager.createGlass(297, null,"§7Donjon items boosté de §c296%"));
            inventory.setItem(35, levelManager.createGlass(298, null,"§7Donjon items boosté de §c297%"));
            inventory.setItem(44, levelManager.createGlass(299, null,"§7Donjon items boosté de §c298%"));
            inventory.setItem(53, levelManager.createGlass(300, null,"§7Donjon items boosté de §c299%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 301 à 330")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel11(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(301, null,"§7Donjon items boosté de §c300%"));
            inventory.setItem(9, levelManager.createGlass(302, null,"§7Donjon items boosté de §c301%"));
            inventory.setItem(18, levelManager.createGlass(303, null,"§7Donjon items boosté de §c302%"));
            inventory.setItem(27, levelManager.createGlass(304, null,"§7Donjon items boosté de §c303%"));
            inventory.setItem(36, levelManager.createGlass(305, null,"§7Donjon items boosté de §c304%"));
            inventory.setItem(37, levelManager.createGlass(306, null,"§7Donjon items boosté de §c305%"));
            inventory.setItem(38, levelManager.createGlass(307, null,"§7Donjon items boosté de §c306%"));
            inventory.setItem(29, levelManager.createGlass(308, null,"§7Donjon items boosté de §c307%"));
            inventory.setItem(20, levelManager.createGlass(309, null,"§7Donjon items boosté de §c308%"));
            inventory.setItem(11, levelManager.createGlass(310, null,"§7Donjon items boosté de §c309%"));
            inventory.setItem(2, levelManager.createGlass(311, null,"§7Donjon items boosté de §c310%"));
            inventory.setItem(3, levelManager.createGlass(312, null,"§7Donjon items boosté de §c311%"));
            inventory.setItem(4, levelManager.createGlass(313, null,"§7Donjon items boosté de §c312%"));
            inventory.setItem(13, levelManager.createGlass(314, null,"§7Donjon items boosté de §c313%"));
            inventory.setItem(22, levelManager.createGlass(315, null,"§7Donjon items boosté de §c314%"));
            inventory.setItem(31, levelManager.createGlass(316, null,"§7Donjon items boosté de §c315%"));
            inventory.setItem(40, levelManager.createGlass(317, null,"§7Donjon items boosté de §c316%"));
            inventory.setItem(41, levelManager.createGlass(318, null,"§7Donjon items boosté de §c317%"));
            inventory.setItem(42, levelManager.createGlass(319, null,"§7Donjon items boosté de §c318%"));
            inventory.setItem(33, levelManager.createGlass(320, null,"§7Donjon items boosté de §c319%"));
            inventory.setItem(24, levelManager.createGlass(321, null,"§7Donjon items boosté de §c320%"));
            inventory.setItem(15, levelManager.createGlass(322, null,"§7Donjon items boosté de §c321%"));
            inventory.setItem(6, levelManager.createGlass(323, null,"§7Donjon items boosté de §c322%"));
            inventory.setItem(7, levelManager.createGlass(324, null,"§7Donjon items boosté de §c323%"));
            inventory.setItem(8, levelManager.createGlass(325, null,"§7Donjon items boosté de §c324%"));
            inventory.setItem(17, levelManager.createGlass(326, null,"§7Donjon items boosté de §c325%"));
            inventory.setItem(26, levelManager.createGlass(327, null,"§7Donjon items boosté de §c326%"));
            inventory.setItem(35, levelManager.createGlass(328, null,"§7Donjon items boosté de §c327%"));
            inventory.setItem(44, levelManager.createGlass(329, null,"§7Donjon items boosté de §c328%"));
            inventory.setItem(53, levelManager.createGlass(330, null,"§7Donjon items boosté de §c329%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 271 à 300")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 331 à 360")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel12(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(331, null,"§7Donjon items boosté de §c330%"));
            inventory.setItem(9, levelManager.createGlass(332, null,"§7Donjon items boosté de §c331%"));
            inventory.setItem(18, levelManager.createGlass(333, null,"§7Donjon items boosté de §c332%"));
            inventory.setItem(27, levelManager.createGlass(334, null,"§7Donjon items boosté de §c333%"));
            inventory.setItem(36, levelManager.createGlass(335, null,"§7Donjon items boosté de §c334%"));
            inventory.setItem(37, levelManager.createGlass(336, null,"§7Donjon items boosté de §c335%"));
            inventory.setItem(38, levelManager.createGlass(337, null,"§7Donjon items boosté de §c336%"));
            inventory.setItem(29, levelManager.createGlass(338, null,"§7Donjon items boosté de §c337%"));
            inventory.setItem(20, levelManager.createGlass(339, null,"§7Donjon items boosté de §c338%"));
            inventory.setItem(11, levelManager.createGlass(340, null,"§7Donjon items boosté de §c339%"));
            inventory.setItem(2, levelManager.createGlass(341, null,"§7Donjon items boosté de §c340%"));
            inventory.setItem(3, levelManager.createGlass(342, null,"§7Donjon items boosté de §c341%"));
            inventory.setItem(4, levelManager.createGlass(343, null,"§7Donjon items boosté de §c342%"));
            inventory.setItem(13, levelManager.createGlass(344, null,"§7Donjon items boosté de §c343%"));
            inventory.setItem(22, levelManager.createGlass(345, null,"§7Donjon items boosté de §c344%"));
            inventory.setItem(31, levelManager.createGlass(346, null,"§7Donjon items boosté de §c345%"));
            inventory.setItem(40, levelManager.createGlass(347, null,"§7Donjon items boosté de §c346%"));
            inventory.setItem(41, levelManager.createGlass(348, null,"§7Donjon items boosté de §c347%"));
            inventory.setItem(42, levelManager.createGlass(349, null,"§7Donjon items boosté de §c348%"));
            inventory.setItem(33, levelManager.createGlass(350, null,"§7Donjon items boosté de §c349%"));
            inventory.setItem(24, levelManager.createGlass(351, null,"§7Donjon items boosté de §c350%"));
            inventory.setItem(15, levelManager.createGlass(352, null,"§7Donjon items boosté de §c351%"));
            inventory.setItem(6, levelManager.createGlass(353, null,"§7Donjon items boosté de §c352%"));
            inventory.setItem(7, levelManager.createGlass(354, null,"§7Donjon items boosté de §c353%"));
            inventory.setItem(8, levelManager.createGlass(355, null,"§7Donjon items boosté de §c354%"));
            inventory.setItem(17, levelManager.createGlass(356, null,"§7Donjon items boosté de §c355%"));
            inventory.setItem(26, levelManager.createGlass(357, null,"§7Donjon items boosté de §c356%"));
            inventory.setItem(35, levelManager.createGlass(358, null,"§7Donjon items boosté de §c357%"));
            inventory.setItem(44, levelManager.createGlass(359, null,"§7Donjon items boosté de §c358%"));
            inventory.setItem(53, levelManager.createGlass(360, null,"§7Donjon items boosté de §c359%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 301 à 330")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 361 à 390")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel13(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(361, null,"§7Donjon items boosté de §c360%"));
            inventory.setItem(9, levelManager.createGlass(362, null,"§7Donjon items boosté de §c361%"));
            inventory.setItem(18, levelManager.createGlass(363, null,"§7Donjon items boosté de §c362%"));
            inventory.setItem(27, levelManager.createGlass(364, null,"§7Donjon items boosté de §c363%"));
            inventory.setItem(36, levelManager.createGlass(365, null,"§7Donjon items boosté de §c364%"));
            inventory.setItem(37, levelManager.createGlass(366, null,"§7Donjon items boosté de §c365%"));
            inventory.setItem(38, levelManager.createGlass(367, null,"§7Donjon items boosté de §c366%"));
            inventory.setItem(29, levelManager.createGlass(368, null,"§7Donjon items boosté de §c367%"));
            inventory.setItem(20, levelManager.createGlass(369, null,"§7Donjon items boosté de §c368%"));
            inventory.setItem(11, levelManager.createGlass(370, null,"§7Donjon items boosté de §c369%"));
            inventory.setItem(2, levelManager.createGlass(371, null,"§7Donjon items boosté de §c370%"));
            inventory.setItem(3, levelManager.createGlass(372, null,"§7Donjon items boosté de §c371%"));
            inventory.setItem(4, levelManager.createGlass(373, null,"§7Donjon items boosté de §c372%"));
            inventory.setItem(13, levelManager.createGlass(374, null,"§7Donjon items boosté de §c373%"));
            inventory.setItem(22, levelManager.createGlass(375, null,"§7Donjon items boosté de §c374%"));
            inventory.setItem(31, levelManager.createGlass(376, null,"§7Donjon items boosté de §c375%"));
            inventory.setItem(40, levelManager.createGlass(377, null,"§7Donjon items boosté de §c376%"));
            inventory.setItem(41, levelManager.createGlass(378, null,"§7Donjon items boosté de §c377%"));
            inventory.setItem(42, levelManager.createGlass(379, null,"§7Donjon items boosté de §c378%"));
            inventory.setItem(33, levelManager.createGlass(380, null,"§7Donjon items boosté de §c379%"));
            inventory.setItem(24, levelManager.createGlass(381, null,"§7Donjon items boosté de §c380%"));
            inventory.setItem(15, levelManager.createGlass(382, null,"§7Donjon items boosté de §c381%"));
            inventory.setItem(6, levelManager.createGlass(383, null,"§7Donjon items boosté de §c382%"));
            inventory.setItem(7, levelManager.createGlass(384, null,"§7Donjon items boosté de §c383%"));
            inventory.setItem(8, levelManager.createGlass(385, null,"§7Donjon items boosté de §c384%"));
            inventory.setItem(17, levelManager.createGlass(386, null,"§7Donjon items boosté de §c385%"));
            inventory.setItem(26, levelManager.createGlass(387, null,"§7Donjon items boosté de §c386%"));
            inventory.setItem(35, levelManager.createGlass(388, null,"§7Donjon items boosté de §c387%"));
            inventory.setItem(44, levelManager.createGlass(389, null,"§7Donjon items boosté de §c388%"));
            inventory.setItem(53, levelManager.createGlass(390, null,"§7Donjon items boosté de §c389%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 331 à 360")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 391 à 420")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel14(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(391, null,"§7Donjon items boosté de §c390%"));
            inventory.setItem(9, levelManager.createGlass(392, null,"§7Donjon items boosté de §c391%"));
            inventory.setItem(18, levelManager.createGlass(393, null,"§7Donjon items boosté de §c392%"));
            inventory.setItem(27, levelManager.createGlass(394, null,"§7Donjon items boosté de §c393%"));
            inventory.setItem(36, levelManager.createGlass(395, null,"§7Donjon items boosté de §c394%"));
            inventory.setItem(37, levelManager.createGlass(396, null,"§7Donjon items boosté de §c395%"));
            inventory.setItem(38, levelManager.createGlass(397, null,"§7Donjon items boosté de §c396%"));
            inventory.setItem(29, levelManager.createGlass(398, null,"§7Donjon items boosté de §c397%"));
            inventory.setItem(20, levelManager.createGlass(399, null,"§7Donjon items boosté de §c398%"));
            inventory.setItem(11, levelManager.createGlass(400, null,"§7Donjon items boosté de §c399%"));
            inventory.setItem(2, levelManager.createGlass(401, null,"§7Donjon items boosté de §c400%"));
            inventory.setItem(3, levelManager.createGlass(402, null,"§7Donjon items boosté de §c401%"));
            inventory.setItem(4, levelManager.createGlass(403, null,"§7Donjon items boosté de §c402%"));
            inventory.setItem(13, levelManager.createGlass(404, null,"§7Donjon items boosté de §c403%"));
            inventory.setItem(22, levelManager.createGlass(405, null,"§7Donjon items boosté de §c404%"));
            inventory.setItem(31, levelManager.createGlass(406, null,"§7Donjon items boosté de §c405%"));
            inventory.setItem(40, levelManager.createGlass(407, null,"§7Donjon items boosté de §c406%"));
            inventory.setItem(41, levelManager.createGlass(408, null,"§7Donjon items boosté de §c407%"));
            inventory.setItem(42, levelManager.createGlass(409, null,"§7Donjon items boosté de §c408%"));
            inventory.setItem(33, levelManager.createGlass(410, null,"§7Donjon items boosté de §c409%"));
            inventory.setItem(24, levelManager.createGlass(411, null,"§7Donjon items boosté de §c410%"));
            inventory.setItem(15, levelManager.createGlass(412, null,"§7Donjon items boosté de §c411%"));
            inventory.setItem(6, levelManager.createGlass(413, null,"§7Donjon items boosté de §c412%"));
            inventory.setItem(7, levelManager.createGlass(414, null,"§7Donjon items boosté de §c413%"));
            inventory.setItem(8, levelManager.createGlass(415, null,"§7Donjon items boosté de §c414%"));
            inventory.setItem(17, levelManager.createGlass(416, null,"§7Donjon items boosté de §c415%"));
            inventory.setItem(26, levelManager.createGlass(417, null,"§7Donjon items boosté de §c416%"));
            inventory.setItem(35, levelManager.createGlass(418, null,"§7Donjon items boosté de §c417%"));
            inventory.setItem(44, levelManager.createGlass(419, null,"§7Donjon items boosté de §c418%"));
            inventory.setItem(53, levelManager.createGlass(420, null,"§7Donjon items boosté de §c419%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 361 à 390")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 421 à 450")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel15(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(421, null,"§7Donjon items boosté de §c420%"));
            inventory.setItem(9, levelManager.createGlass(422, null,"§7Donjon items boosté de §c421%"));
            inventory.setItem(18, levelManager.createGlass(423, null,"§7Donjon items boosté de §c422%"));
            inventory.setItem(27, levelManager.createGlass(424, null,"§7Donjon items boosté de §c423%"));
            inventory.setItem(36, levelManager.createGlass(425, null,"§7Donjon items boosté de §c424%"));
            inventory.setItem(37, levelManager.createGlass(426, null,"§7Donjon items boosté de §c425%"));
            inventory.setItem(38, levelManager.createGlass(427, null,"§7Donjon items boosté de §c426%"));
            inventory.setItem(29, levelManager.createGlass(428, null,"§7Donjon items boosté de §c427%"));
            inventory.setItem(20, levelManager.createGlass(429, null,"§7Donjon items boosté de §c428%"));
            inventory.setItem(11, levelManager.createGlass(430, null,"§7Donjon items boosté de §c429%"));
            inventory.setItem(2, levelManager.createGlass(431, null,"§7Donjon items boosté de §c430%"));
            inventory.setItem(3, levelManager.createGlass(432, null,"§7Donjon items boosté de §c431%"));
            inventory.setItem(4, levelManager.createGlass(433, null,"§7Donjon items boosté de §c432%"));
            inventory.setItem(13, levelManager.createGlass(434, null,"§7Donjon items boosté de §c433%"));
            inventory.setItem(22, levelManager.createGlass(435, null,"§7Donjon items boosté de §c434%"));
            inventory.setItem(31, levelManager.createGlass(436, null,"§7Donjon items boosté de §c435%"));
            inventory.setItem(40, levelManager.createGlass(437, null,"§7Donjon items boosté de §c436%"));
            inventory.setItem(41, levelManager.createGlass(438, null,"§7Donjon items boosté de §c437%"));
            inventory.setItem(42, levelManager.createGlass(439, null,"§7Donjon items boosté de §c438%"));
            inventory.setItem(33, levelManager.createGlass(440, null,"§7Donjon items boosté de §c439%"));
            inventory.setItem(24, levelManager.createGlass(441, null,"§7Donjon items boosté de §c440%"));
            inventory.setItem(15, levelManager.createGlass(442, null,"§7Donjon items boosté de §c441%"));
            inventory.setItem(6, levelManager.createGlass(443, null,"§7Donjon items boosté de §c442%"));
            inventory.setItem(7, levelManager.createGlass(444, null,"§7Donjon items boosté de §c443%"));
            inventory.setItem(8, levelManager.createGlass(445, null,"§7Donjon items boosté de §c444%"));
            inventory.setItem(17, levelManager.createGlass(446, null,"§7Donjon items boosté de §c445%"));
            inventory.setItem(26, levelManager.createGlass(447, null,"§7Donjon items boosté de §c446%"));
            inventory.setItem(35, levelManager.createGlass(448, null,"§7Donjon items boosté de §c447%"));
            inventory.setItem(44, levelManager.createGlass(449, null,"§7Donjon items boosté de §c448%"));
            inventory.setItem(53, levelManager.createGlass(450, null,"§7Donjon items boosté de §c449%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 391 à 420")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 451 à 480")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel16(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(451, null,"§7Donjon items boosté de §c450%"));
            inventory.setItem(9, levelManager.createGlass(452, null,"§7Donjon items boosté de §c451%"));
            inventory.setItem(18, levelManager.createGlass(453, null,"§7Donjon items boosté de §c452%"));
            inventory.setItem(27, levelManager.createGlass(454, null,"§7Donjon items boosté de §c453%"));
            inventory.setItem(36, levelManager.createGlass(455, null,"§7Donjon items boosté de §c454%"));
            inventory.setItem(37, levelManager.createGlass(456, null,"§7Donjon items boosté de §c455%"));
            inventory.setItem(38, levelManager.createGlass(457, null,"§7Donjon items boosté de §c456%"));
            inventory.setItem(29, levelManager.createGlass(458, null,"§7Donjon items boosté de §c457%"));
            inventory.setItem(20, levelManager.createGlass(459, null,"§7Donjon items boosté de §c458%"));
            inventory.setItem(11, levelManager.createGlass(460, null,"§7Donjon items boosté de §c459%"));
            inventory.setItem(2, levelManager.createGlass(461, null,"§7Donjon items boosté de §c460%"));
            inventory.setItem(3, levelManager.createGlass(462, null,"§7Donjon items boosté de §c461%"));
            inventory.setItem(4, levelManager.createGlass(463, null,"§7Donjon items boosté de §c462%"));
            inventory.setItem(13, levelManager.createGlass(464, null,"§7Donjon items boosté de §c463%"));
            inventory.setItem(22, levelManager.createGlass(465, null,"§7Donjon items boosté de §c464%"));
            inventory.setItem(31, levelManager.createGlass(466, null,"§7Donjon items boosté de §c465%"));
            inventory.setItem(40, levelManager.createGlass(467, null,"§7Donjon items boosté de §c466%"));
            inventory.setItem(41, levelManager.createGlass(468, null,"§7Donjon items boosté de §c467%"));
            inventory.setItem(42, levelManager.createGlass(469, null,"§7Donjon items boosté de §c468%"));
            inventory.setItem(33, levelManager.createGlass(470, null,"§7Donjon items boosté de §c469%"));
            inventory.setItem(24, levelManager.createGlass(471, null,"§7Donjon items boosté de §c470%"));
            inventory.setItem(15, levelManager.createGlass(472, null,"§7Donjon items boosté de §c471%"));
            inventory.setItem(6, levelManager.createGlass(473, null,"§7Donjon items boosté de §c472%"));
            inventory.setItem(7, levelManager.createGlass(474, null,"§7Donjon items boosté de §c473%"));
            inventory.setItem(8, levelManager.createGlass(475, null,"§7Donjon items boosté de §c474%"));
            inventory.setItem(17, levelManager.createGlass(476, null,"§7Donjon items boosté de §c475%"));
            inventory.setItem(26, levelManager.createGlass(477, null,"§7Donjon items boosté de §c476%"));
            inventory.setItem(35, levelManager.createGlass(478, null,"§7Donjon items boosté de §c477%"));
            inventory.setItem(44, levelManager.createGlass(479, null,"§7Donjon items boosté de §c478%"));
            inventory.setItem(53, levelManager.createGlass(480, null,"§7Donjon items boosté de §c479%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 421 à 450")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 481 à 500")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel17(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(481, null,"§7Donjon items boosté de §c480%"));
            inventory.setItem(9, levelManager.createGlass(482, null,"§7Donjon items boosté de §c481%"));
            inventory.setItem(18, levelManager.createGlass(483, null,"§7Donjon items boosté de §c482%"));
            inventory.setItem(27, levelManager.createGlass(484, null,"§7Donjon items boosté de §c483%"));
            inventory.setItem(36, levelManager.createGlass(485, null,"§7Donjon items boosté de §c484%"));
            inventory.setItem(37, levelManager.createGlass(486, null,"§7Donjon items boosté de §c485%"));
            inventory.setItem(38, levelManager.createGlass(487, null,"§7Donjon items boosté de §c486%"));
            inventory.setItem(29, levelManager.createGlass(488, null,"§7Donjon items boosté de §c487%"));
            inventory.setItem(20, levelManager.createGlass(489, null,"§7Donjon items boosté de §c488%"));
            inventory.setItem(11, levelManager.createGlass(490, null,"§7Donjon items boosté de §c489%"));
            inventory.setItem(2, levelManager.createGlass(491, null,"§7Donjon items boosté de §c490%"));
            inventory.setItem(3, levelManager.createGlass(492, null,"§7Donjon items boosté de §c491%"));
            inventory.setItem(4, levelManager.createGlass(493, null,"§7Donjon items boosté de §c492%"));
            inventory.setItem(13, levelManager.createGlass(494, null,"§7Donjon items boosté de §c493%"));
            inventory.setItem(22, levelManager.createGlass(495, null,"§7Donjon items boosté de §c494%"));
            inventory.setItem(31, levelManager.createGlass(496, null,"§7Donjon items boosté de §c495%"));
            inventory.setItem(40, levelManager.createGlass(497, null,"§7Donjon items boosté de §c496%"));
            inventory.setItem(41, levelManager.createGlass(498, null,"§7Donjon items boosté de §c497%"));
            inventory.setItem(42, levelManager.createGlass(499, null,"§7Donjon items boosté de §c498%"));
            inventory.setItem(33, levelManager.createGlass(500, null,"§7Donjon items boosté de §c500%"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 451 à 480")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel1(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(1));
            inventory.setItem(9, levelManager.createGlass(2));
            inventory.setItem(18, levelManager.createGlass(3));
            inventory.setItem(27, levelManager.createGlass(4));
            inventory.setItem(36, levelManager.createGlass(5));
            inventory.setItem(37, levelManager.createGlass(6));
            inventory.setItem(38, levelManager.createGlass(7));
            inventory.setItem(29, levelManager.createGlass(8));
            inventory.setItem(20, levelManager.createGlass(9));
            inventory.setItem(11, levelManager.createGlass(10));
            inventory.setItem(2, levelManager.createGlass(11));
            inventory.setItem(3, levelManager.createGlass(12));
            inventory.setItem(4, levelManager.createGlass(13));
            inventory.setItem(13, levelManager.createGlass(14));
            inventory.setItem(22, levelManager.createGlass(15));
            inventory.setItem(31, levelManager.createGlass(16));
            inventory.setItem(40, levelManager.createGlass(17));
            inventory.setItem(41, levelManager.createGlass(18));
            inventory.setItem(42, levelManager.createGlass(19));
            inventory.setItem(33, levelManager.createGlass(20));
            inventory.setItem(24, levelManager.createGlass(21));
            inventory.setItem(15, levelManager.createGlass(22));
            inventory.setItem(6, levelManager.createGlass(23));
            inventory.setItem(7, levelManager.createGlass(24));
            inventory.setItem(8, levelManager.createGlass(25));
            inventory.setItem(17, levelManager.createGlass(26));
            inventory.setItem(26, levelManager.createGlass(27));
            inventory.setItem(35, levelManager.createGlass(28));
            inventory.setItem(44, levelManager.createGlass(29));
            inventory.setItem(53, levelManager.createGlass(30));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Voir votre progression", "§7du niveau 31 à 60")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel2(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(31));
            inventory.setItem(9, levelManager.createGlass(32));
            inventory.setItem(18, levelManager.createGlass(33));
            inventory.setItem(27, levelManager.createGlass(34));
            inventory.setItem(36, levelManager.createGlass(35));
            inventory.setItem(37, levelManager.createGlass(36));
            inventory.setItem(38, levelManager.createGlass(37));
            inventory.setItem(29, levelManager.createGlass(38));
            inventory.setItem(20, levelManager.createGlass(39));
            inventory.setItem(11, levelManager.createGlass(40));
            inventory.setItem(2, levelManager.createGlass(41));
            inventory.setItem(3, levelManager.createGlass(42));
            inventory.setItem(4, levelManager.createGlass(43));
            inventory.setItem(13, levelManager.createGlass(44));
            inventory.setItem(22, levelManager.createGlass(45));
            inventory.setItem(31, levelManager.createGlass(46));
            inventory.setItem(40, levelManager.createGlass(47));
            inventory.setItem(41, levelManager.createGlass(48));
            inventory.setItem(42, levelManager.createGlass(49));
            inventory.setItem(33, levelManager.createGlass(50));
            inventory.setItem(24, levelManager.createGlass(51));
            inventory.setItem(15, levelManager.createGlass(52));
            inventory.setItem(6, levelManager.createGlass(53));
            inventory.setItem(7, levelManager.createGlass(54));
            inventory.setItem(8, levelManager.createGlass(55));
            inventory.setItem(17, levelManager.createGlass(56));
            inventory.setItem(26, levelManager.createGlass(57));
            inventory.setItem(35, levelManager.createGlass(58));
            inventory.setItem(44, levelManager.createGlass(59));
            inventory.setItem(53, levelManager.createGlass(60));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 1 à 30")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 61 à 90")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel3(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(61));
            inventory.setItem(9, levelManager.createGlass(62));
            inventory.setItem(18, levelManager.createGlass(63));
            inventory.setItem(27, levelManager.createGlass(64));
            inventory.setItem(36, levelManager.createGlass(65));
            inventory.setItem(37, levelManager.createGlass(66));
            inventory.setItem(38, levelManager.createGlass(67));
            inventory.setItem(29, levelManager.createGlass(68));
            inventory.setItem(20, levelManager.createGlass(69));
            inventory.setItem(11, levelManager.createGlass(70));
            inventory.setItem(2, levelManager.createGlass(71));
            inventory.setItem(3, levelManager.createGlass(72));
            inventory.setItem(4, levelManager.createGlass(73));
            inventory.setItem(13, levelManager.createGlass(74));
            inventory.setItem(22, levelManager.createGlass(75));
            inventory.setItem(31, levelManager.createGlass(76));
            inventory.setItem(40, levelManager.createGlass(77));
            inventory.setItem(41, levelManager.createGlass(78));
            inventory.setItem(42, levelManager.createGlass(79));
            inventory.setItem(33, levelManager.createGlass(80));
            inventory.setItem(24, levelManager.createGlass(81));
            inventory.setItem(15, levelManager.createGlass(82));
            inventory.setItem(6, levelManager.createGlass(83));
            inventory.setItem(7, levelManager.createGlass(84));
            inventory.setItem(8, levelManager.createGlass(85));
            inventory.setItem(17, levelManager.createGlass(86));
            inventory.setItem(26, levelManager.createGlass(87));
            inventory.setItem(35, levelManager.createGlass(88));
            inventory.setItem(44, levelManager.createGlass(89));
            inventory.setItem(53, levelManager.createGlass(90));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 31 à 60")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 91 à 120")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel4(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(91));
            inventory.setItem(9, levelManager.createGlass(92));
            inventory.setItem(18, levelManager.createGlass(93));
            inventory.setItem(27, levelManager.createGlass(94));
            inventory.setItem(36, levelManager.createGlass(95));
            inventory.setItem(37, levelManager.createGlass(96));
            inventory.setItem(38, levelManager.createGlass(97));
            inventory.setItem(29, levelManager.createGlass(98));
            inventory.setItem(20, levelManager.createGlass(99));
            inventory.setItem(11, levelManager.createGlass(100));
            inventory.setItem(2, levelManager.createGlass(101));
            inventory.setItem(3, levelManager.createGlass(102));
            inventory.setItem(4, levelManager.createGlass(103));
            inventory.setItem(13, levelManager.createGlass(104));
            inventory.setItem(22, levelManager.createGlass(105));
            inventory.setItem(31, levelManager.createGlass(106));
            inventory.setItem(40, levelManager.createGlass(107));
            inventory.setItem(41, levelManager.createGlass(108));
            inventory.setItem(42, levelManager.createGlass(109));
            inventory.setItem(33, levelManager.createGlass(110));
            inventory.setItem(24, levelManager.createGlass(111));
            inventory.setItem(15, levelManager.createGlass(112));
            inventory.setItem(6, levelManager.createGlass(113));
            inventory.setItem(7, levelManager.createGlass(114));
            inventory.setItem(8, levelManager.createGlass(115));
            inventory.setItem(17, levelManager.createGlass(116));
            inventory.setItem(26, levelManager.createGlass(117));
            inventory.setItem(35, levelManager.createGlass(118));
            inventory.setItem(44, levelManager.createGlass(119));
            inventory.setItem(53, levelManager.createGlass(120));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 61 à 90")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 121 à 150")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel5(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(121));
            inventory.setItem(9, levelManager.createGlass(122));
            inventory.setItem(18, levelManager.createGlass(123));
            inventory.setItem(27, levelManager.createGlass(124));
            inventory.setItem(36, levelManager.createGlass(125));
            inventory.setItem(37, levelManager.createGlass(126));
            inventory.setItem(38, levelManager.createGlass(127));
            inventory.setItem(29, levelManager.createGlass(128));
            inventory.setItem(20, levelManager.createGlass(129));
            inventory.setItem(11, levelManager.createGlass(130));
            inventory.setItem(2, levelManager.createGlass(131));
            inventory.setItem(3, levelManager.createGlass(132));
            inventory.setItem(4, levelManager.createGlass(133));
            inventory.setItem(13, levelManager.createGlass(134));
            inventory.setItem(22, levelManager.createGlass(135));
            inventory.setItem(31, levelManager.createGlass(136));
            inventory.setItem(40, levelManager.createGlass(137));
            inventory.setItem(41, levelManager.createGlass(138));
            inventory.setItem(42, levelManager.createGlass(139));
            inventory.setItem(33, levelManager.createGlass(140));
            inventory.setItem(24, levelManager.createGlass(141));
            inventory.setItem(15, levelManager.createGlass(142));
            inventory.setItem(6, levelManager.createGlass(143));
            inventory.setItem(7, levelManager.createGlass(144));
            inventory.setItem(8, levelManager.createGlass(145));
            inventory.setItem(17, levelManager.createGlass(146));
            inventory.setItem(26, levelManager.createGlass(147));
            inventory.setItem(35, levelManager.createGlass(148));
            inventory.setItem(44, levelManager.createGlass(149));
            inventory.setItem(53, levelManager.createGlass(150));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 91 à 120")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 151 à 180")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel6(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(151));
            inventory.setItem(9, levelManager.createGlass(152));
            inventory.setItem(18, levelManager.createGlass(153));
            inventory.setItem(27, levelManager.createGlass(154));
            inventory.setItem(36, levelManager.createGlass(155));
            inventory.setItem(37, levelManager.createGlass(156));
            inventory.setItem(38, levelManager.createGlass(157));
            inventory.setItem(29, levelManager.createGlass(158));
            inventory.setItem(20, levelManager.createGlass(159));
            inventory.setItem(11, levelManager.createGlass(160));
            inventory.setItem(2, levelManager.createGlass(161));
            inventory.setItem(3, levelManager.createGlass(162));
            inventory.setItem(4, levelManager.createGlass(163));
            inventory.setItem(13, levelManager.createGlass(164));
            inventory.setItem(22, levelManager.createGlass(165));
            inventory.setItem(31, levelManager.createGlass(166));
            inventory.setItem(40, levelManager.createGlass(167));
            inventory.setItem(41, levelManager.createGlass(168));
            inventory.setItem(42, levelManager.createGlass(169));
            inventory.setItem(33, levelManager.createGlass(170));
            inventory.setItem(24, levelManager.createGlass(171));
            inventory.setItem(15, levelManager.createGlass(172));
            inventory.setItem(6, levelManager.createGlass(173));
            inventory.setItem(7, levelManager.createGlass(174));
            inventory.setItem(8, levelManager.createGlass(175));
            inventory.setItem(17, levelManager.createGlass(176));
            inventory.setItem(26, levelManager.createGlass(177));
            inventory.setItem(35, levelManager.createGlass(178));
            inventory.setItem(44, levelManager.createGlass(179));
            inventory.setItem(53, levelManager.createGlass(180));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 121 à 150")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 181 à 210")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel7(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(181));
            inventory.setItem(9, levelManager.createGlass(182));
            inventory.setItem(18, levelManager.createGlass(183));
            inventory.setItem(27, levelManager.createGlass(184));
            inventory.setItem(36, levelManager.createGlass(185));
            inventory.setItem(37, levelManager.createGlass(186));
            inventory.setItem(38, levelManager.createGlass(187));
            inventory.setItem(29, levelManager.createGlass(188));
            inventory.setItem(20, levelManager.createGlass(189));
            inventory.setItem(11, levelManager.createGlass(190));
            inventory.setItem(2, levelManager.createGlass(191));
            inventory.setItem(3, levelManager.createGlass(192));
            inventory.setItem(4, levelManager.createGlass(193));
            inventory.setItem(13, levelManager.createGlass(194));
            inventory.setItem(22, levelManager.createGlass(195));
            inventory.setItem(31, levelManager.createGlass(196));
            inventory.setItem(40, levelManager.createGlass(197));
            inventory.setItem(41, levelManager.createGlass(198));
            inventory.setItem(42, levelManager.createGlass(199));
            inventory.setItem(33, levelManager.createGlass(200));
            inventory.setItem(24, levelManager.createGlass(201));
            inventory.setItem(15, levelManager.createGlass(202));
            inventory.setItem(6, levelManager.createGlass(203));
            inventory.setItem(7, levelManager.createGlass(204));
            inventory.setItem(8, levelManager.createGlass(205));
            inventory.setItem(17, levelManager.createGlass(206));
            inventory.setItem(26, levelManager.createGlass(207));
            inventory.setItem(35, levelManager.createGlass(208));
            inventory.setItem(44, levelManager.createGlass(209));
            inventory.setItem(53, levelManager.createGlass(210));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 151 à 180")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 211 à 240")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel8(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(211));
            inventory.setItem(9, levelManager.createGlass(212));
            inventory.setItem(18, levelManager.createGlass(213));
            inventory.setItem(27, levelManager.createGlass(214));
            inventory.setItem(36, levelManager.createGlass(215));
            inventory.setItem(37, levelManager.createGlass(216));
            inventory.setItem(38, levelManager.createGlass(217));
            inventory.setItem(29, levelManager.createGlass(218));
            inventory.setItem(20, levelManager.createGlass(219));
            inventory.setItem(11, levelManager.createGlass(220));
            inventory.setItem(2, levelManager.createGlass(221));
            inventory.setItem(3, levelManager.createGlass(222));
            inventory.setItem(4, levelManager.createGlass(223));
            inventory.setItem(13, levelManager.createGlass(224));
            inventory.setItem(22, levelManager.createGlass(225));
            inventory.setItem(31, levelManager.createGlass(226));
            inventory.setItem(40, levelManager.createGlass(227));
            inventory.setItem(41, levelManager.createGlass(228));
            inventory.setItem(42, levelManager.createGlass(229));
            inventory.setItem(33, levelManager.createGlass(230));
            inventory.setItem(24, levelManager.createGlass(231));
            inventory.setItem(15, levelManager.createGlass(232));
            inventory.setItem(6, levelManager.createGlass(233));
            inventory.setItem(7, levelManager.createGlass(234));
            inventory.setItem(8, levelManager.createGlass(235));
            inventory.setItem(17, levelManager.createGlass(236));
            inventory.setItem(26, levelManager.createGlass(237));
            inventory.setItem(35, levelManager.createGlass(238));
            inventory.setItem(44, levelManager.createGlass(239));
            inventory.setItem(53, levelManager.createGlass(240));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 181 à 210")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel9(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(241));
            inventory.setItem(9, levelManager.createGlass(242));
            inventory.setItem(18, levelManager.createGlass(243));
            inventory.setItem(27, levelManager.createGlass(244));
            inventory.setItem(36, levelManager.createGlass(245));
            inventory.setItem(37, levelManager.createGlass(246));
            inventory.setItem(38, levelManager.createGlass(247));
            inventory.setItem(29, levelManager.createGlass(248));
            inventory.setItem(20, levelManager.createGlass(249));
            inventory.setItem(11, levelManager.createGlass(250));
            inventory.setItem(2, levelManager.createGlass(251));
            inventory.setItem(3, levelManager.createGlass(252));
            inventory.setItem(4, levelManager.createGlass(253));
            inventory.setItem(13, levelManager.createGlass(254));
            inventory.setItem(22, levelManager.createGlass(255));
            inventory.setItem(31, levelManager.createGlass(256));
            inventory.setItem(40, levelManager.createGlass(257));
            inventory.setItem(41, levelManager.createGlass(258));
            inventory.setItem(42, levelManager.createGlass(259));
            inventory.setItem(33, levelManager.createGlass(260));
            inventory.setItem(24, levelManager.createGlass(261));
            inventory.setItem(15, levelManager.createGlass(262));
            inventory.setItem(6, levelManager.createGlass(263));
            inventory.setItem(7, levelManager.createGlass(264));
            inventory.setItem(8, levelManager.createGlass(265));
            inventory.setItem(17, levelManager.createGlass(266));
            inventory.setItem(26, levelManager.createGlass(267));
            inventory.setItem(35, levelManager.createGlass(268));
            inventory.setItem(44, levelManager.createGlass(269));
            inventory.setItem(53, levelManager.createGlass(270));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 211 à 240")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 271 à 300")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel10(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(271));
            inventory.setItem(9, levelManager.createGlass(272));
            inventory.setItem(18, levelManager.createGlass(273));
            inventory.setItem(27, levelManager.createGlass(274));
            inventory.setItem(36, levelManager.createGlass(275));
            inventory.setItem(37, levelManager.createGlass(276));
            inventory.setItem(38, levelManager.createGlass(277));
            inventory.setItem(29, levelManager.createGlass(278));
            inventory.setItem(20, levelManager.createGlass(279));
            inventory.setItem(11, levelManager.createGlass(280));
            inventory.setItem(2, levelManager.createGlass(281));
            inventory.setItem(3, levelManager.createGlass(282));
            inventory.setItem(4, levelManager.createGlass(283));
            inventory.setItem(13, levelManager.createGlass(284));
            inventory.setItem(22, levelManager.createGlass(285));
            inventory.setItem(31, levelManager.createGlass(286));
            inventory.setItem(40, levelManager.createGlass(287));
            inventory.setItem(41, levelManager.createGlass(288));
            inventory.setItem(42, levelManager.createGlass(289));
            inventory.setItem(33, levelManager.createGlass(290));
            inventory.setItem(24, levelManager.createGlass(291));
            inventory.setItem(15, levelManager.createGlass(292));
            inventory.setItem(6, levelManager.createGlass(293));
            inventory.setItem(7, levelManager.createGlass(294));
            inventory.setItem(8, levelManager.createGlass(295));
            inventory.setItem(17, levelManager.createGlass(296));
            inventory.setItem(26, levelManager.createGlass(297));
            inventory.setItem(35, levelManager.createGlass(298));
            inventory.setItem(44, levelManager.createGlass(299));
            inventory.setItem(53, levelManager.createGlass(300));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 301 à 330")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel11(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(301));
            inventory.setItem(9, levelManager.createGlass(302));
            inventory.setItem(18, levelManager.createGlass(303));
            inventory.setItem(27, levelManager.createGlass(304));
            inventory.setItem(36, levelManager.createGlass(305));
            inventory.setItem(37, levelManager.createGlass(306));
            inventory.setItem(38, levelManager.createGlass(307));
            inventory.setItem(29, levelManager.createGlass(308));
            inventory.setItem(20, levelManager.createGlass(309));
            inventory.setItem(11, levelManager.createGlass(310));
            inventory.setItem(2, levelManager.createGlass(311));
            inventory.setItem(3, levelManager.createGlass(312));
            inventory.setItem(4, levelManager.createGlass(313));
            inventory.setItem(13, levelManager.createGlass(314));
            inventory.setItem(22, levelManager.createGlass(315));
            inventory.setItem(31, levelManager.createGlass(316));
            inventory.setItem(40, levelManager.createGlass(317));
            inventory.setItem(41, levelManager.createGlass(318));
            inventory.setItem(42, levelManager.createGlass(319));
            inventory.setItem(33, levelManager.createGlass(320));
            inventory.setItem(24, levelManager.createGlass(321));
            inventory.setItem(15, levelManager.createGlass(322));
            inventory.setItem(6, levelManager.createGlass(323));
            inventory.setItem(7, levelManager.createGlass(324));
            inventory.setItem(8, levelManager.createGlass(325));
            inventory.setItem(17, levelManager.createGlass(326));
            inventory.setItem(26, levelManager.createGlass(327));
            inventory.setItem(35, levelManager.createGlass(328));
            inventory.setItem(44, levelManager.createGlass(329));
            inventory.setItem(53, levelManager.createGlass(330));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 271 à 300")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 331 à 360")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel12(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(331));
            inventory.setItem(9, levelManager.createGlass(332));
            inventory.setItem(18, levelManager.createGlass(333));
            inventory.setItem(27, levelManager.createGlass(334));
            inventory.setItem(36, levelManager.createGlass(335));
            inventory.setItem(37, levelManager.createGlass(336));
            inventory.setItem(38, levelManager.createGlass(337));
            inventory.setItem(29, levelManager.createGlass(338));
            inventory.setItem(20, levelManager.createGlass(339));
            inventory.setItem(11, levelManager.createGlass(340));
            inventory.setItem(2, levelManager.createGlass(341));
            inventory.setItem(3, levelManager.createGlass(342));
            inventory.setItem(4, levelManager.createGlass(343));
            inventory.setItem(13, levelManager.createGlass(344));
            inventory.setItem(22, levelManager.createGlass(345));
            inventory.setItem(31, levelManager.createGlass(346));
            inventory.setItem(40, levelManager.createGlass(347));
            inventory.setItem(41, levelManager.createGlass(348));
            inventory.setItem(42, levelManager.createGlass(349));
            inventory.setItem(33, levelManager.createGlass(350));
            inventory.setItem(24, levelManager.createGlass(351));
            inventory.setItem(15, levelManager.createGlass(352));
            inventory.setItem(6, levelManager.createGlass(353));
            inventory.setItem(7, levelManager.createGlass(354));
            inventory.setItem(8, levelManager.createGlass(355));
            inventory.setItem(17, levelManager.createGlass(356));
            inventory.setItem(26, levelManager.createGlass(357));
            inventory.setItem(35, levelManager.createGlass(358));
            inventory.setItem(44, levelManager.createGlass(359));
            inventory.setItem(53, levelManager.createGlass(360));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 301 à 330")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 361 à 390")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel13(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(361));
            inventory.setItem(9, levelManager.createGlass(362));
            inventory.setItem(18, levelManager.createGlass(363));
            inventory.setItem(27, levelManager.createGlass(364));
            inventory.setItem(36, levelManager.createGlass(365));
            inventory.setItem(37, levelManager.createGlass(366));
            inventory.setItem(38, levelManager.createGlass(367));
            inventory.setItem(29, levelManager.createGlass(368));
            inventory.setItem(20, levelManager.createGlass(369));
            inventory.setItem(11, levelManager.createGlass(370));
            inventory.setItem(2, levelManager.createGlass(371));
            inventory.setItem(3, levelManager.createGlass(372));
            inventory.setItem(4, levelManager.createGlass(373));
            inventory.setItem(13, levelManager.createGlass(374));
            inventory.setItem(22, levelManager.createGlass(375));
            inventory.setItem(31, levelManager.createGlass(376));
            inventory.setItem(40, levelManager.createGlass(377));
            inventory.setItem(41, levelManager.createGlass(378));
            inventory.setItem(42, levelManager.createGlass(379));
            inventory.setItem(33, levelManager.createGlass(380));
            inventory.setItem(24, levelManager.createGlass(381));
            inventory.setItem(15, levelManager.createGlass(382));
            inventory.setItem(6, levelManager.createGlass(383));
            inventory.setItem(7, levelManager.createGlass(384));
            inventory.setItem(8, levelManager.createGlass(385));
            inventory.setItem(17, levelManager.createGlass(386));
            inventory.setItem(26, levelManager.createGlass(387));
            inventory.setItem(35, levelManager.createGlass(388));
            inventory.setItem(44, levelManager.createGlass(389));
            inventory.setItem(53, levelManager.createGlass(390));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 331 à 360")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 391 à 420")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel14(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(391));
            inventory.setItem(9, levelManager.createGlass(392));
            inventory.setItem(18, levelManager.createGlass(393));
            inventory.setItem(27, levelManager.createGlass(394));
            inventory.setItem(36, levelManager.createGlass(395));
            inventory.setItem(37, levelManager.createGlass(396));
            inventory.setItem(38, levelManager.createGlass(397));
            inventory.setItem(29, levelManager.createGlass(398));
            inventory.setItem(20, levelManager.createGlass(399));
            inventory.setItem(11, levelManager.createGlass(400));
            inventory.setItem(2, levelManager.createGlass(401));
            inventory.setItem(3, levelManager.createGlass(402));
            inventory.setItem(4, levelManager.createGlass(403));
            inventory.setItem(13, levelManager.createGlass(404));
            inventory.setItem(22, levelManager.createGlass(405));
            inventory.setItem(31, levelManager.createGlass(406));
            inventory.setItem(40, levelManager.createGlass(407));
            inventory.setItem(41, levelManager.createGlass(408));
            inventory.setItem(42, levelManager.createGlass(409));
            inventory.setItem(33, levelManager.createGlass(410));
            inventory.setItem(24, levelManager.createGlass(411));
            inventory.setItem(15, levelManager.createGlass(412));
            inventory.setItem(6, levelManager.createGlass(413));
            inventory.setItem(7, levelManager.createGlass(414));
            inventory.setItem(8, levelManager.createGlass(415));
            inventory.setItem(17, levelManager.createGlass(416));
            inventory.setItem(26, levelManager.createGlass(417));
            inventory.setItem(35, levelManager.createGlass(418));
            inventory.setItem(44, levelManager.createGlass(419));
            inventory.setItem(53, levelManager.createGlass(420));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 361 à 390")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 421 à 450")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel15(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(421));
            inventory.setItem(9, levelManager.createGlass(422));
            inventory.setItem(18, levelManager.createGlass(423));
            inventory.setItem(27, levelManager.createGlass(424));
            inventory.setItem(36, levelManager.createGlass(425));
            inventory.setItem(37, levelManager.createGlass(426));
            inventory.setItem(38, levelManager.createGlass(427));
            inventory.setItem(29, levelManager.createGlass(428));
            inventory.setItem(20, levelManager.createGlass(429));
            inventory.setItem(11, levelManager.createGlass(430));
            inventory.setItem(2, levelManager.createGlass(431));
            inventory.setItem(3, levelManager.createGlass(432));
            inventory.setItem(4, levelManager.createGlass(433));
            inventory.setItem(13, levelManager.createGlass(434));
            inventory.setItem(22, levelManager.createGlass(435));
            inventory.setItem(31, levelManager.createGlass(436));
            inventory.setItem(40, levelManager.createGlass(437));
            inventory.setItem(41, levelManager.createGlass(438));
            inventory.setItem(42, levelManager.createGlass(439));
            inventory.setItem(33, levelManager.createGlass(440));
            inventory.setItem(24, levelManager.createGlass(441));
            inventory.setItem(15, levelManager.createGlass(442));
            inventory.setItem(6, levelManager.createGlass(443));
            inventory.setItem(7, levelManager.createGlass(444));
            inventory.setItem(8, levelManager.createGlass(445));
            inventory.setItem(17, levelManager.createGlass(446));
            inventory.setItem(26, levelManager.createGlass(447));
            inventory.setItem(35, levelManager.createGlass(448));
            inventory.setItem(44, levelManager.createGlass(449));
            inventory.setItem(53, levelManager.createGlass(450));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 391 à 420")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 451 à 480")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel16(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(451));
            inventory.setItem(9, levelManager.createGlass(452));
            inventory.setItem(18, levelManager.createGlass(453));
            inventory.setItem(27, levelManager.createGlass(454));
            inventory.setItem(36, levelManager.createGlass(455));
            inventory.setItem(37, levelManager.createGlass(456));
            inventory.setItem(38, levelManager.createGlass(457));
            inventory.setItem(29, levelManager.createGlass(458));
            inventory.setItem(20, levelManager.createGlass(459));
            inventory.setItem(11, levelManager.createGlass(460));
            inventory.setItem(2, levelManager.createGlass(461));
            inventory.setItem(3, levelManager.createGlass(462));
            inventory.setItem(4, levelManager.createGlass(463));
            inventory.setItem(13, levelManager.createGlass(464));
            inventory.setItem(22, levelManager.createGlass(465));
            inventory.setItem(31, levelManager.createGlass(466));
            inventory.setItem(40, levelManager.createGlass(467));
            inventory.setItem(41, levelManager.createGlass(468));
            inventory.setItem(42, levelManager.createGlass(469));
            inventory.setItem(33, levelManager.createGlass(470));
            inventory.setItem(24, levelManager.createGlass(471));
            inventory.setItem(15, levelManager.createGlass(472));
            inventory.setItem(6, levelManager.createGlass(473));
            inventory.setItem(7, levelManager.createGlass(474));
            inventory.setItem(8, levelManager.createGlass(475));
            inventory.setItem(17, levelManager.createGlass(476));
            inventory.setItem(26, levelManager.createGlass(477));
            inventory.setItem(35, levelManager.createGlass(478));
            inventory.setItem(44, levelManager.createGlass(479));
            inventory.setItem(53, levelManager.createGlass(480));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 421 à 450")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 481 à 500")).toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel17(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Level");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ForgeronLevelManager levelManager = new ForgeronLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(481));
            inventory.setItem(9, levelManager.createGlass(482));
            inventory.setItem(18, levelManager.createGlass(483));
            inventory.setItem(27, levelManager.createGlass(484));
            inventory.setItem(36, levelManager.createGlass(485));
            inventory.setItem(37, levelManager.createGlass(486));
            inventory.setItem(38, levelManager.createGlass(487));
            inventory.setItem(29, levelManager.createGlass(488));
            inventory.setItem(20, levelManager.createGlass(489));
            inventory.setItem(11, levelManager.createGlass(490));
            inventory.setItem(2, levelManager.createGlass(491));
            inventory.setItem(3, levelManager.createGlass(492));
            inventory.setItem(4, levelManager.createGlass(493));
            inventory.setItem(13, levelManager.createGlass(494));
            inventory.setItem(22, levelManager.createGlass(495));
            inventory.setItem(31, levelManager.createGlass(496));
            inventory.setItem(40, levelManager.createGlass(497));
            inventory.setItem(41, levelManager.createGlass(498));
            inventory.setItem(42, levelManager.createGlass(499));
            inventory.setItem(33, levelManager.createGlass(500));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 451 à 480")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            });

        return inventory;
    }

    public Inventory friendsMenu(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9Vos amis");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            FriendsManager friendsManager = new FriendsManager(accounts);
            int i = 0;
            for(ItemStack itemStack : friendsManager.generateFriendsHead()){
                inventory.setItem(i, itemStack);
                i++;
            }
        });

        inventory.setItem(53, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
        inventory.setItem(52, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").toItemStack());
        inventory.setItem(51, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").toItemStack());
        inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
        inventory.setItem(46, new ItemBuilder(Material.REDSTONE_COMPARATOR).setName(ChatColor.DARK_AQUA + "Options").toItemStack());
        return inventory;
    }

    public Inventory optionFriends(Player player){
        Inventory inventory = Bukkit.createInventory(null, 9, "§3Options");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            FriendsManager friendsManager = new FriendsManager(accounts);
            YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());
            ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
            inventory.setItem(0, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(1, glass);
            inventory.setItem(2, new ItemBuilder(Material.PAPER).setName("§6Recevoir Des Messages").setLore(ChatColor.GRAY + "Actuellement : " + friendsManager.generateColorForFriendsOption(data.getString("reciveMessages")) + data.getString("reciveMessages")).toItemStack());
            inventory.setItem(3, glass);
            inventory.setItem(4, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(player.getName()).setName(ChatColor.GOLD + "Recevoir Des Demandes d'Amis").setLore(ChatColor.GRAY + "Actuellement : " + friendsManager.generateColorForFriendsOption(data.getString("reciveRequest")) + data.getString("reciveRequest")).toItemStack());
            inventory.setItem(5, glass);
            inventory.setItem(6, new ItemBuilder(Material.BOOK).setName("§6Recevoir Notifications Connection/Déconnection").setLore(ChatColor.GRAY + "Actuellement : " + friendsManager.generateColorForFriendsOption(data.getString("reciveJoinNotification")) + data.getString("reciveJoinNotification"), " ", ChatColor.GRAY + "Cette option vous permet d'activer ou non", ChatColor.GRAY + "les notifcations lorsqu'un de vos ami ce connecte ou ce déconnecte").toItemStack());
            inventory.setItem(7, glass);
            inventory.setItem(8, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            });
        return inventory;
    }

    public Inventory colorName(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9Color Name");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
            inventory.setItem(0, new ItemBuilder(Material.INK_SACK).setName("§0Noir").toItemStack());
            inventory.setItem(1, glass);
            inventory.setItem(2, new ItemBuilder(Material.INK_SACK, 1, (byte) 1).setName("§cRouge").toItemStack());
            inventory.setItem(3, glass);
            inventory.setItem(4, new ItemBuilder(Material.INK_SACK, 1, (byte) 2).setName("§2Vert Sombre").toItemStack());
            inventory.setItem(5, glass);
            inventory.setItem(6, new ItemBuilder(Material.INK_SACK, 1, (byte) 4).setName("§9Bleu").toItemStack());
            inventory.setItem(7, glass);
            inventory.setItem(8, new ItemBuilder(Material.INK_SACK, 1, (byte) 6).setName("§3Cyan Clair").toItemStack());
            for(int i = 9; i < 19; i++){
                inventory.setItem(i, glass);
            }
            inventory.setItem(19, new ItemBuilder(Material.INK_SACK, 1, (byte) 7).setName("§7Gris Clair").toItemStack());
            inventory.setItem(20, glass);
            inventory.setItem(21, new ItemBuilder(Material.INK_SACK, 1, (byte) 8).setName("§8Gris Sombre").toItemStack());
            inventory.setItem(22, glass);
            inventory.setItem(23, new ItemBuilder(Material.INK_SACK, 1, (byte) 9).setName("§dRose").toItemStack());
            inventory.setItem(24, glass);
            inventory.setItem(25, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setName("§aVert Clair").toItemStack());
            for(int i = 26; i < 36; i++){
                inventory.setItem(i, glass);
            }
            inventory.setItem(36, new ItemBuilder(Material.INK_SACK, 1, (byte) 11).setName("§eJaune").toItemStack());
            inventory.setItem(37, glass);
            inventory.setItem(38, new ItemBuilder(Material.INK_SACK, 1, (byte) 12).setName("§bCyan").toItemStack());
            inventory.setItem(49, glass);
            inventory.setItem(40, new ItemBuilder(Material.INK_SACK, 1, (byte) 13).setName("§5Rose Sombre").toItemStack());
            inventory.setItem(41, glass);
            inventory.setItem(42, new ItemBuilder(Material.INK_SACK, 1, (byte) 14).setName("§6Dorée").toItemStack());
            inventory.setItem(43, glass);
            inventory.setItem(44, new ItemBuilder(Material.INK_SACK, 1, (byte) 15).setName("§fBlanc").toItemStack());
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(53, new ItemBuilder(Material.BARRIER).setName("§cFermer le menu").toItemStack());

            for(int i = 46; i < 53; i++){
                inventory.setItem(i, glass);
            }
        });
        return inventory;
    }

    public Inventory optionForFriends(Player player, Player friend){
        Inventory inventory = Bukkit.createInventory(null, 9, friend.getDisplayName() + " §7Option");
        ItemStack glass = new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (byte) 15).setName(" ").toItemStack();
        inventory.setItem(0, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
        inventory.setItem(1, glass);
        inventory.setItem(2, new ItemBuilder(Material.FEATHER).setName("§aInviter " + friend.getDisplayName() + " §aà rejoindre votre partie").toItemStack());
        inventory.setItem(3, glass);
        inventory.setItem(4, new HeadsManager().generateCustomHead("§aInviter " + friend.getDisplayName() + " §aà rejoindre votre guilde", 1, Main.getInstance().getConfig().getString("heads.earth")));
        inventory.setItem(5, glass);
        inventory.setItem(6, new ItemBuilder(Material.BLAZE_POWDER).setName("§cSupprimer " + friend.getDisplayName() + " §cde vos amis").toItemStack());
        inventory.setItem(7, glass);
        inventory.setItem(8, new ItemBuilder(Material.BARRIER).setName("§cFermer ce Menu").toItemStack());
        return inventory;
    }
}
