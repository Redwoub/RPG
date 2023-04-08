package fr.redwoub.mydoriarpg.inventory;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.PlayerType;
import fr.redwoub.mydoriarpg.accounts.RpgLevel;
import fr.redwoub.mydoriarpg.managers.*;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEvent implements Listener {

    @EventHandler
    public void mainMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();
        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equalsIgnoreCase("§9Menu Principal")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case RAW_FISH:
                    player.openInventory(new InventoryList().friendsMenu(player));
                    break;
                case SKULL_ITEM:
                    if (current.getItemMeta().getDisplayName().equals("§aGuilde")) {

                    } else if (current.getItemMeta().getDisplayName().equals("§aStatistique")) {
                        player.openInventory(new InventoryList().statistiqueMenu(player));
                    }
                    break;
                case DIAMOND_SWORD:
                    //todo
                    break;
                case PAINTING:
                    player.openInventory(new InventoryList().skillMenu(player));
                    break;
                case ENDER_CHEST:
                    //todo
                    break;
                case ARMOR_STAND:
                    //todo
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void statistiqueMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();
        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§eStatistiques")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    if(current.getItemMeta().getDisplayName().equals("§cRetour")){
                        player.openInventory(new InventoryList().mainMenu(player));
                    }
                    break;
                case BARRIER:
                    if(current.getItemMeta().getDisplayName().equals("§cFermer ce menu")) player.closeInventory();
                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void skillMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§eSkill Menu")){
            event.setCancelled(true);
            switch (current.getType()){
                case DIAMOND_SWORD:
                    player.openInventory(new InventoryList().combatLevel1(player));
                    break;
                case SKULL_ITEM:
                    player.openInventory(new InventoryList().donjonLevel1(player));
                    break;
                case FURNACE:
                    player.openInventory(new InventoryList().forgeronLevel1(player));
                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void colorChatMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();
        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§9Color Chat")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    player.openInventory(new InventoryList().mainMenu(player));
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case INK_SACK:
                    Main.getInstance().getAccount(player).ifPresent(accounts -> {
                        if(current.getDurability() == 0){
                            if(accounts.getDataStatistique().getPlayerType() == PlayerType.DEMONIAQUE){
                                accounts.getDataStatistique().setColorChat("noir");
                            } else {
                                player.sendMessage("§cVous n'avez pas les prérequis pour choisir cette couleur");
                                return;
                            }
                        } else if(current.getDurability() == 1) {
                            accounts.getDataStatistique().setColorChat("rouge");
                        } else if(current.getDurability() == 2) {
                            accounts.getDataStatistique().setColorChat("Dark_Green");
                        } else if(current.getDurability() == 4) {
                            accounts.getDataStatistique().setColorChat("bleu");
                        } else if(current.getDurability() == 6){
                            accounts.getDataStatistique().setColorChat("cyan");
                        } else if(current.getDurability() == 7){
                            accounts.getDataStatistique().setColorChat("gris");
                        } else if(current.getDurability() == 8){
                            accounts.getDataStatistique().setColorChat("dark_gris");
                        } else if(current.getDurability() == 9){
                            accounts.getDataStatistique().setColorChat("rose");
                        } else if(current.getDurability() == 10){
                            accounts.getDataStatistique().setColorChat("vert");
                        } else if(current.getDurability() == 11){
                            accounts.getDataStatistique().setColorChat("jaune");
                        } else if(current.getDurability() == 12){
                            accounts.getDataStatistique().setColorChat("bleu_claire");
                        } else if(current.getDurability() == 13){
                            accounts.getDataStatistique().setColorChat("violet");
                        } else if(current.getDurability() == 14){
                            if(accounts.getDataLvl().getRpgLvl() >= 750){
                                accounts.getDataStatistique().setColorChat("dore");
                            } else {
                             player.sendMessage("§cCette couleur est réservé au joueur ayant un niveau de §bRPG §cde §6" + 750);
                             return;
                            }
                        } else if(current.getDurability() == 15){
                            accounts.getDataStatistique().setColorChat("blanc");
                        } else {
                            accounts.getDataStatistique().setColorChat("blanc");
                        }
                        player.sendMessage("§aVotre coleur a été modifié");
                        player.closeInventory();
                    });

                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void colorNameMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();
        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§9Color Name")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    player.openInventory(new InventoryList().mainMenu(player));
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                case INK_SACK:
                    Main.getInstance().getAccount(player).ifPresent(accounts -> {
                        if(current.getDurability() == 0){
                            if(accounts.getDataStatistique().getPlayerType() == PlayerType.DEMONIAQUE){
                                accounts.getDataStatistique().setColorName("noir");
                            } else {
                                player.sendMessage("§cVous n'avez pas les prérequis pour choisir cette couleur");
                                return;
                            }
                        } else if(current.getDurability() == 1) {
                            accounts.getDataStatistique().setColorName("rouge");
                        } else if(current.getDurability() == 2) {
                            accounts.getDataStatistique().setColorName("Dark_Green");
                        } else if(current.getDurability() == 4) {
                            accounts.getDataStatistique().setColorName("bleu");
                        } else if(current.getDurability() == 6){
                            accounts.getDataStatistique().setColorName("cyan");
                        } else if(current.getDurability() == 7){
                            accounts.getDataStatistique().setColorName("gris");
                        } else if(current.getDurability() == 8){
                            accounts.getDataStatistique().setColorName("dark_gris");
                        } else if(current.getDurability() == 9){
                            accounts.getDataStatistique().setColorName("rose");
                        } else if(current.getDurability() == 10){
                            accounts.getDataStatistique().setColorName("vert");
                        } else if(current.getDurability() == 11){
                            accounts.getDataStatistique().setColorName("jaune");
                        } else if(current.getDurability() == 12){
                            accounts.getDataStatistique().setColorName("bleu_claire");
                        } else if(current.getDurability() == 13){
                            accounts.getDataStatistique().setColorName("violet");
                        } else if(current.getDurability() == 14){
                            if(accounts.getDataLvl().getRpgLvl() >= 750){
                                accounts.getDataStatistique().setColorName("dore");
                            } else {
                                player.sendMessage("§cCette couleur est réservé au joueur ayant un niveau de §bRPG §cde §6" + 750);
                                return;
                            }
                        } else if(current.getDurability() == 15){
                            accounts.getDataStatistique().setColorName("blanc");
                        } else {
                            accounts.getDataStatistique().setColorName("blanc");
                        }
                        player.sendMessage("§aVotre coleur a été modifié");
                        player.setDisplayName(PlayerUtils.getByString(accounts.getDataStatistique().getColorName()) + accounts.getDataStatistique().getNickname());
                        player.closeInventory();
                    });

                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void combatMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§2Combat Level")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    if(current.getItemMeta().getDisplayName().equals("§aPage Précédente")){
                        if(inventory.contains(new CombatLevelManager(Main.getInstance().getAccount(player).get()).createGlass(61))){
                            player.openInventory(new InventoryList().combatLevel2(player));
                        } else if(inventory.contains(new CombatLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31))){
                            player.openInventory(new InventoryList().combatLevel1(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§aPage Suivante")){
                        if(inventory.contains(new CombatLevelManager(Main.getInstance().getAccount(player).get()).createGlass(1))){
                            player.openInventory(new InventoryList().combatLevel2(player));
                        } else if(inventory.contains(new CombatLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31))){
                            player.openInventory(new InventoryList().combatLevel3(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§cRetour")){
                        player.openInventory(new InventoryList().skillMenu(player));
                    }
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void forgeronSkillClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if (current == null) return;
        if (!current.hasItemMeta()) return;

        if (inventory.getName().equals("§2Forgeron Level")) {
            event.setCancelled(true);
            switch (current.getType()) {
                case ARROW:
                    if (current.getItemMeta().getDisplayName().equals("§aPage Précédente")) {
                        if (inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31))) {
                            player.openInventory(new InventoryList().forgeronLevel1(player));
                        } else if (inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(61))) {
                            player.openInventory(new InventoryList().forgeronLevel2(player));
                        } else if (inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(91))) {
                            player.openInventory(new InventoryList().forgeronLevel3(player));
                        } else if (inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(121))) {
                            player.openInventory(new InventoryList().forgeronLevel4(player));
                        } else if (inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(151))) {
                            player.openInventory(new InventoryList().forgeronLevel5(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(181))){
                            player.openInventory(new InventoryList().forgeronLevel6(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(211))){
                            player.openInventory(new InventoryList().forgeronLevel7(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(241))){
                            player.openInventory(new InventoryList().forgeronLevel8(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(271))){
                            player.openInventory(new InventoryList().forgeronLevel9(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(301))){
                            player.openInventory(new InventoryList().forgeronLevel10(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(331))){
                            player.openInventory(new InventoryList().forgeronLevel11(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(361))){
                            player.openInventory(new InventoryList().forgeronLevel12(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(391))){
                            player.openInventory(new InventoryList().forgeronLevel13(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(421))){
                            player.openInventory(new InventoryList().forgeronLevel14(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(451))){
                            player.openInventory(new InventoryList().forgeronLevel15(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(481))){
                            player.openInventory(new InventoryList().forgeronLevel16(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§aPage Suivante")){
                        if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(1))){
                            player.openInventory(new InventoryList().forgeronLevel2(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31))){
                            player.openInventory(new InventoryList().forgeronLevel3(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(61))){
                            player.openInventory(new InventoryList().forgeronLevel4(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(91))){
                            player.openInventory(new InventoryList().forgeronLevel5(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(121))){
                            player.openInventory(new InventoryList().forgeronLevel6(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(151))){
                            player.openInventory(new InventoryList().forgeronLevel7(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(181))){
                            player.openInventory(new InventoryList().forgeronLevel8(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(211))){
                            player.openInventory(new InventoryList().forgeronLevel9(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(241))){
                            player.openInventory(new InventoryList().forgeronLevel10(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(271))){
                            player.openInventory(new InventoryList().forgeronLevel11(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(301))){
                            player.openInventory(new InventoryList().forgeronLevel12(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(331))){
                            player.openInventory(new InventoryList().forgeronLevel13(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(361))){
                            player.openInventory(new InventoryList().forgeronLevel14(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(391))){
                            player.openInventory(new InventoryList().forgeronLevel15(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(421))){
                            player.openInventory(new InventoryList().forgeronLevel16(player));
                        } else if(inventory.contains(new ForgeronLevelManager(Main.getInstance().getAccount(player).get()).createGlass(451))){
                            player.openInventory(new InventoryList().forgeronLevel17(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§cRetour")){
                        player.openInventory(new InventoryList().skillMenu(player));
                    }
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                default: break;
            }
        }
    }
    @EventHandler
    public void DonjonSkillClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§2Donjon Level")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    if (current.getItemMeta().getDisplayName().equals("§aPage Précédente")) {
                        if (inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31, null,"§7Donjon items boosté de §c30%"))) {
                            player.openInventory(new InventoryList().donjonLevel1(player));
                        } else if (inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(61, null,"§7Donjon items boosté de §c60%"))) {
                            player.openInventory(new InventoryList().donjonLevel2(player));
                        } else if (inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(91, null,"§7Donjon items boosté de §c90%"))) {
                            player.openInventory(new InventoryList().donjonLevel3(player));
                        } else if (inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(121, null,"§7Donjon items boosté de §c120%"))) {
                            player.openInventory(new InventoryList().donjonLevel4(player));
                        } else if (inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(151, null,"§7Donjon items boosté de §c150%"))) {
                            player.openInventory(new InventoryList().donjonLevel5(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(181, null,"§7Donjon items boosté de §c180%"))){
                            player.openInventory(new InventoryList().donjonLevel6(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(211, null,"§7Donjon items boosté de §c210%"))){
                            player.openInventory(new InventoryList().donjonLevel7(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(241, null,"§7Donjon items boosté de §c240%"))){
                            player.openInventory(new InventoryList().donjonLevel8(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(271, null,"§7Donjon items boosté de §c270%"))){
                            player.openInventory(new InventoryList().donjonLevel9(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(301, null,"§7Donjon items boosté de §c300%"))){
                            player.openInventory(new InventoryList().donjonLevel10(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(331, null,"§7Donjon items boosté de §c330%"))){
                            player.openInventory(new InventoryList().donjonLevel11(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(361, null,"§7Donjon items boosté de §c360%"))){
                            player.openInventory(new InventoryList().donjonLevel12(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(391, null,"§7Donjon items boosté de §c390%"))){
                            player.openInventory(new InventoryList().donjonLevel13(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(421, null,"§7Donjon items boosté de §c420%"))){
                            player.openInventory(new InventoryList().donjonLevel14(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(451, null,"§7Donjon items boosté de §c450%"))){
                            player.openInventory(new InventoryList().donjonLevel15(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(481, null,"§7Donjon items boosté de §c480%"))){
                            player.openInventory(new InventoryList().donjonLevel16(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§aPage Suivante")){
                        if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(1))){
                            player.openInventory(new InventoryList().donjonLevel2(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(31, null,"§7Donjon items boosté de §c30%"))){
                            player.openInventory(new InventoryList().donjonLevel3(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(61, null,"§7Donjon items boosté de §c60%"))){
                            player.openInventory(new InventoryList().donjonLevel4(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(91, null,"§7Donjon items boosté de §c90%"))){
                            player.openInventory(new InventoryList().donjonLevel5(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(121, null,"§7Donjon items boosté de §c120%"))){
                            player.openInventory(new InventoryList().donjonLevel6(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(151, null,"§7Donjon items boosté de §c150%"))){
                            player.openInventory(new InventoryList().donjonLevel7(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(181, null,"§7Donjon items boosté de §c180%"))){
                            player.openInventory(new InventoryList().donjonLevel8(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(211, null,"§7Donjon items boosté de §c210%"))){
                            player.openInventory(new InventoryList().donjonLevel9(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(241, null,"§7Donjon items boosté de §c240%"))){
                            player.openInventory(new InventoryList().donjonLevel10(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(271, null,"§7Donjon items boosté de §c270%"))){
                            player.openInventory(new InventoryList().donjonLevel11(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(301, null,"§7Donjon items boosté de §c300%"))){
                            player.openInventory(new InventoryList().donjonLevel12(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(331, null,"§7Donjon items boosté de §c330%"))){
                            player.openInventory(new InventoryList().donjonLevel13(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(361, null,"§7Donjon items boosté de §c360%"))){
                            player.openInventory(new InventoryList().donjonLevel14(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(391, null,"§7Donjon items boosté de §c390%"))){
                            player.openInventory(new InventoryList().donjonLevel15(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(421, null,"§7Donjon items boosté de §c420%"))){
                            player.openInventory(new InventoryList().donjonLevel16(player));
                        } else if(inventory.contains(new DonjonLevelManager(Main.getInstance().getAccount(player).get()).createGlass(451, null,"§7Donjon items boosté de §c450%"))){
                            player.openInventory(new InventoryList().donjonLevel17(player));
                        }
                    } else if(current.getItemMeta().getDisplayName().equals("§cRetour")){
                        player.openInventory(new InventoryList().skillMenu(player));
                    }
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void friendsMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equalsIgnoreCase("§9Vos amis")){
            event.setCancelled(true);
            switch (current.getType()){
                case BARRIER:
                    player.closeInventory();
                    break;
                case ARROW:
                    if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§aPage Suivante")){
                        //todo
                    } else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§aPage Précédente")){
                        //todo
                    } else if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§cRetour")){
                        player.openInventory(new InventoryList().mainMenu(player));
                    }
                    break;
                case REDSTONE_COMPARATOR:
                    if(current.getItemMeta().getDisplayName().equalsIgnoreCase("§3Options")){
                        player.openInventory(new InventoryList().optionFriends(player));
                        break;
                    }
                    break;
                case SKULL_ITEM:
                    if(Bukkit.getPlayer(ChatColor.stripColor(current.getItemMeta().getDisplayName())) != null){
                        player.openInventory(new InventoryList().optionForFriends(player, Bukkit.getPlayer(ChatColor.stripColor(current.getItemMeta().getDisplayName()))));
                    }
                    break;
                default: break;
            }
        }
    }

    @EventHandler
    public void friendsOptionMenuClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().equals("§3Options")){
            Main.getInstance().getAccount(player).ifPresent(accounts -> {
                YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());
                FriendsManager friendsManager = new FriendsManager(accounts);
                switch (current.getType()){
                    case SKULL_ITEM:
                        if(current.getItemMeta().getDisplayName().equals("§6Recevoir Des Demandes d'Amis")){
                            if(data.getString("reciveRequest").equalsIgnoreCase("on")){
                                data.set("reciveRequest", "OFF");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            } else {
                                data.set("reciveRequest", "ON");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            }
                        }
                        break;
                    case PAPER:
                        if(current.getItemMeta().getDisplayName().equals("§6Recevoir Des Messages")){
                            if(data.getString("reciveMessages").equalsIgnoreCase("on")){
                                data.set("reciveMessages", "OFF");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            } else {
                                data.set("reciveMessages", "ON");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            }
                        }
                        break;
                    case BOOK:
                        if(current.getItemMeta().getDisplayName().equals("§6Recevoir Notifications Connection/Déconnection")){
                            if(data.getString("reciveJoinNotification").equals("ON")){
                                data.set("reciveJoinNotification", "OFF");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            } else {
                                data.set("reciveJoinNotification", "ON");
                                FileUtils.save(data, accounts.getOptionFriends());
                                player.openInventory(new InventoryList().optionFriends(player));
                            }
                        }
                        break;
                    case BARRIER:
                        if(current.getItemMeta().getDisplayName().equals("§cFermer ce menu")){
                            player.closeInventory();
                        }
                        break;
                    case ARROW:
                        if(current.getItemMeta().getDisplayName().equals("§cRetour")){
                            player.openInventory(new InventoryList().friendsMenu(player));
                        }
                    default: break;
                }
            });
        }

    }

    @EventHandler
    public void optionsForFriendsMenuClick(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();

        if(current == null) return;
        if(!current.hasItemMeta()) return;

        if(inventory.getName().contains("§7Option")){
            event.setCancelled(true);
            switch (current.getType()){
                case ARROW:
                    player.openInventory(new InventoryList().friendsMenu(player));
                    break;
                case FEATHER:
                    player.sendMessage("§cJ'ai pas fait :)");
                    //todo
                    break;
                case SKULL_ITEM:
                    if(current.getItemMeta().getDisplayName().startsWith("§aInviter")){
                        player.sendMessage("§cJ'ai pas fait ca aussi :)");
                        //todo
                    }
                    break;
                case BLAZE_POWDER:
                    Main.getInstance().getAccount(player).ifPresent(accounts -> {
                        FriendsManager friendsManager = new FriendsManager(accounts);
                        String[] tableau;
                        tableau = inventory.getName().split(" ");
                        friendsManager.removeFriend(ChatColor.stripColor(tableau[0]));
                    });
                    break;
                case BARRIER:
                    player.closeInventory();
                    break;
            }
        }
    }
}
