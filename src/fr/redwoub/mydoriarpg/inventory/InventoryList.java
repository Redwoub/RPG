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

    public Inventory rpgLevel1(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel2(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel3(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel4(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 6 * 9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel5(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel6(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel7(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel8(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);

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

    public Inventory rpgLevel9(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);
            inventory.setItem(0, levelManager.createGlass(241));
            inventory.setItem(9, levelManager.createGlass(242));
            inventory.setItem(18, levelManager.createGlass(243));
            inventory.setItem(27, levelManager.createGlass(244));
            inventory.setItem(36, levelManager.createGlass(245));
            inventory.setItem(37, levelManager.createGlass(246));
            inventory.setItem(38, levelManager.createGlass(247));
            inventory.setItem(29, levelManager.createGlass(248));
            inventory.setItem(20, levelManager.createGlass(249));
            inventory.setItem(11, levelManager.createGlass(250,null, "§7Débloque le §e/colorchat §7❘§e /colorname", null));
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
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 271 à 280")).toItemStack());
        });
        return inventory;
    }

    public Inventory rpgLevel10(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§9RPG Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            RpgLevelManager levelManager = new RpgLevelManager(accounts);
            if(accounts.getDataLvl().getRpgLvl() == 280){
                inventory.setItem(0, levelManager.createGlass(271));
                inventory.setItem(9, levelManager.createGlass(272));
                inventory.setItem(18, levelManager.createGlass(273));
                inventory.setItem(27, levelManager.createGlass(274));
                inventory.setItem(36, levelManager.createGlass(275));
                inventory.setItem(37, levelManager.createGlass(276));
                inventory.setItem(38, levelManager.createGlass(277));
                inventory.setItem(29, levelManager.createGlass(278));
                inventory.setItem(20, levelManager.createGlass(279));
                inventory.setItem(11, levelManager.createGlass(280,"" ,"§7Débloque la couleur §6dore","§7dans le §e/colorchat §7❘ §e/colorname"));
                inventory.setItem(2, new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(player.getName()).setName("§6" + player.getName()).setLore(Arrays.asList("§7Bien joué, vous êtes maintenant", "§7au niveau maximum du jeu !")).toItemStack());
                inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
                inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
                inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            } else {
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
                inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
                inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 241 à 270")).toItemStack());
                inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
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
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Niveau");
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
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Niveau");
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
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Combat Niveau");
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
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(1));
            inventory.setItem(9, levelManager.createGlass(2, null, "§7Donjon items boosté de §c5%", null));
            inventory.setItem(18, levelManager.createGlass(3, null, "§7Donjon items boosté de §c10%", null));
            inventory.setItem(27, levelManager.createGlass(4, null, "§7Donjon items boosté de §c15%", null));
            inventory.setItem(36, levelManager.createGlass(5, null, "§7Donjon items boosté de §c20%", null));
            inventory.setItem(37, levelManager.createGlass(6, null, "§7Donjon items boosté de §c25%", null));
            inventory.setItem(38, levelManager.createGlass(7, null, "§7Donjon items boosté de §c30%", null));
            inventory.setItem(29, levelManager.createGlass(8, null, "§7Donjon items boosté de §c35%", null));
            inventory.setItem(20, levelManager.createGlass(9, null, "§7Donjon items boosté de §c40%", null));
            inventory.setItem(11, levelManager.createGlass(10, null, "§7Donjon items boosté de §c45%", "§7Accès au donjon tier 2"));
            inventory.setItem(2, levelManager.createGlass(11, null, "§7Donjon items boosté de §c50%", null));
            inventory.setItem(3, levelManager.createGlass(12, null, "§7Donjon items boosté de §c55%", null));
            inventory.setItem(4, levelManager.createGlass(13, null, "§7Donjon items boosté de §c60%", null));
            inventory.setItem(13, levelManager.createGlass(14, null, "§7Donjon items boosté de §c65%", null));
            inventory.setItem(22, levelManager.createGlass(15, null, "§7Donjon items boosté de §c70%", null));
            inventory.setItem(31, levelManager.createGlass(16, null, "§7Donjon items boosté de §c75%", null));
            inventory.setItem(40, levelManager.createGlass(17, null, "§7Donjon items boosté de §c80%", null));
            inventory.setItem(41, levelManager.createGlass(18, null, "§7Donjon items boosté de §c85%", null));
            inventory.setItem(42, levelManager.createGlass(19, null, "§7Donjon items boosté de §c90%", null));
            inventory.setItem(33, levelManager.createGlass(20, null, "§7Donjon items boosté de §c95%", "§7Accès au donjon tier 3"));
            inventory.setItem(24, levelManager.createGlass(21, null, "§7Donjon items boosté de §c100%", null));
            inventory.setItem(15, levelManager.createGlass(22, null, "§7Donjon items boosté de §c105%", null));
            inventory.setItem(6, levelManager.createGlass(23, null, "§7Donjon items boosté de §c110%", null));
            inventory.setItem(7, levelManager.createGlass(24, null, "§7Donjon items boosté de §c115%", null));
            inventory.setItem(8, levelManager.createGlass(25, null, "§7Donjon items boosté de §c120%", null));
            inventory.setItem(17, levelManager.createGlass(26, null, "§7Donjon items boosté de §c125%", null));
            inventory.setItem(26, levelManager.createGlass(27, null, "§7Donjon items boosté de §c130%", null));
            inventory.setItem(35, levelManager.createGlass(28, null, "§7Donjon items boosté de §c135%", null));
            inventory.setItem(44, levelManager.createGlass(29, null, "§7Donjon items boosté de §c140%", null));
            inventory.setItem(53, levelManager.createGlass(30, null, "§7Donjon items boosté de §c145%", "§7Accès au donjon tier 4"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
            inventory.setItem(50, new ItemBuilder(Material.ARROW).setName("§aPage Suivante").setLore(Arrays.asList("§7Voir votre progression", "§7du niveau 31 à 60")).toItemStack());
        });

        return inventory;
    }

    public Inventory donjonLevel2(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Donjon Niveau");
        Main.getInstance().getAccount(player).ifPresent(accounts -> {
            DonjonLevelManager levelManager = new DonjonLevelManager(accounts);

            inventory.setItem(0, levelManager.createGlass(31, null, "§7Donjon items boosté de §c150%", null));
            inventory.setItem(9, levelManager.createGlass(32, null, "§7Donjon items boosté de §c155%", null));
            inventory.setItem(18, levelManager.createGlass(33, null, "§7Donjon items boosté de §c160%", null));
            inventory.setItem(27, levelManager.createGlass(34, null, "§7Donjon items boosté de §c165%", null));
            inventory.setItem(36, levelManager.createGlass(35, null, "§7Donjon items boosté de §c170%", null));
            inventory.setItem(37, levelManager.createGlass(36, null, "§7Donjon items boosté de §c175%", null));
            inventory.setItem(38, levelManager.createGlass(37, null, "§7Donjon items boosté de §c180%", null));
            inventory.setItem(29, levelManager.createGlass(38, null, "§7Donjon items boosté de §c185%", null));
            inventory.setItem(20, levelManager.createGlass(39, null, "§7Donjon items boosté de §c190%", null));
            inventory.setItem(11, levelManager.createGlass(40, null, "§7Donjon items boosté de §c195%", "§7Accès au donjon tier 5"));
            inventory.setItem(2, levelManager.createGlass(41, null, "§7Donjon items boosté de §c200%", null));
            inventory.setItem(3, levelManager.createGlass(42, null, "§7Donjon items boosté de §c205%", null));
            inventory.setItem(4, levelManager.createGlass(43, null, "§7Donjon items boosté de §c210%", null));
            inventory.setItem(13, levelManager.createGlass(44, null, "§7Donjon items boosté de §c215%", null));
            inventory.setItem(22, levelManager.createGlass(45, null, "§7Donjon items boosté de §c220%", null));
            inventory.setItem(31, levelManager.createGlass(46, null, "§7Donjon items boosté de §c225%", null));
            inventory.setItem(40, levelManager.createGlass(47, null, "§7Donjon items boosté de §c230%", null));
            inventory.setItem(41, levelManager.createGlass(48, null, "§7Donjon items boosté de §c235%", null));
            inventory.setItem(42, levelManager.createGlass(49, null, "§7Donjon items boosté de §c240%", null));
            inventory.setItem(33, levelManager.createGlass(50, null, "§7Donjon items boosté de §c245%", "§7Accès au donjon tier 6"));
            inventory.setItem(24, levelManager.createGlass(51, null, "§7Donjon items boosté de §c250%", null));
            inventory.setItem(15, levelManager.createGlass(52, null, "§7Donjon items boosté de §c255%", null));
            inventory.setItem(6, levelManager.createGlass(53, null, "§7Donjon items boosté de §c260%", null));
            inventory.setItem(7, levelManager.createGlass(54, null, "§7Donjon items boosté de §c265%", null));
            inventory.setItem(8, levelManager.createGlass(55, null, "§7Donjon items boosté de §c270%", null));
            inventory.setItem(17, levelManager.createGlass(56, null, "§7Donjon items boosté de §c275%", null));
            inventory.setItem(26, levelManager.createGlass(57, null, "§7Donjon items boosté de §c280%", null));
            inventory.setItem(35, levelManager.createGlass(58, null, "§7Donjon items boosté de §c285%", null));
            inventory.setItem(44, levelManager.createGlass(59, null, "§7Donjon items boosté de §c290%", null));
            inventory.setItem(53, levelManager.createGlass(60, null, "§7Donjon items boosté de §c295%", "§7Accès au donjon tier 7"));
            inventory.setItem(45, new ItemBuilder(Material.ARROW).setName("§cRetour").toItemStack());
            inventory.setItem(48, new ItemBuilder(Material.ARROW).setName("§aPage Précédente").setLore(Arrays.asList("§7Clique pour voir ta progression", "§7du niveau 1 à 30")).toItemStack());
            inventory.setItem(49, new ItemBuilder(Material.BARRIER).setName("§cFermer ce menu").toItemStack());
        });

        return inventory;
    }

    public Inventory forgeronLevel1(Player player){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Niveau");
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
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§2Forgeron Niveau");
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
