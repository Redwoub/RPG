package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.utils.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemManager {

    public static int getDamage(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("damage"), 0);
        }

        return 0;
    }

    public static int getVie(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("vie"), 0);
        }

        return 0;
    }

    public static int getAttackSpeed(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("attackspeed"), 0);
        }

        return 0;
    }

    public static int getSpeed(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("speed"), 0);
        }

        return 0;
    }

    public static int getDegatCrit(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("degat_critique"), 0);
        }

        return 0;
    }

    public static int getTauxCrit(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("taux_critique"), 0);
        }
        return 0;
    }

    public static int getForce(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("force"), 0);
        }
        return 0;
    }

    public static int getMana(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("mana"), 0);
        }

        return 0;
    }

    public static int getDefense(ItemStack itemStack){
        if(itemStack == null) return 0;
        if(!itemStack.hasItemMeta()) return 0;
        if(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())).exists()){
            YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(ChatColor.stripColor(itemStack.getItemMeta().getDisplayName())));
            return Math.max(data.getInt("defense"), 0);
        }
        return 0;
    }


    public static ChatColor colorByRarity(String rarity){
        if(rarity.equalsIgnoreCase("common"))
            return ChatColor.WHITE;
        else if(rarity.equalsIgnoreCase("uncommon"))
            return ChatColor.GREEN;
        else if(rarity.equalsIgnoreCase("rare"))
            return ChatColor.BLUE;
        else if(rarity.equalsIgnoreCase("epic") || rarity.equalsIgnoreCase("épic"))
            return ChatColor.DARK_PURPLE;
        else if(rarity.equalsIgnoreCase("legendaire") || rarity.equalsIgnoreCase("légendaire"))
            return ChatColor.GOLD;
        else if(rarity.equalsIgnoreCase("mythique"))
            return ChatColor.LIGHT_PURPLE;
        else if(rarity.equalsIgnoreCase("supreme") || rarity.equalsIgnoreCase("suprême"))
            return ChatColor.RED;
        else if(rarity.equalsIgnoreCase("colossale"))
            return ChatColor.DARK_RED;
        else if(rarity.equalsIgnoreCase("divin"))
            return ChatColor.AQUA;
        else if(rarity.equalsIgnoreCase("inconnu"))
            return ChatColor.GRAY;
        else if(rarity.equalsIgnoreCase("demoniaque") || rarity.equalsIgnoreCase("démoniaque"))
            return ChatColor.DARK_GRAY;

        return ChatColor.WHITE;
    }

    public static int getNumberForRarity(String rarity){
        if(rarity.equalsIgnoreCase("common"))
            return 1;
        else if(rarity.equalsIgnoreCase("uncommon"))
            return 2;
        else if(rarity.equalsIgnoreCase("rare"))
            return 3;
        else if(rarity.equalsIgnoreCase("epic") || rarity.equalsIgnoreCase("épic"))
            return 4;
        else if(rarity.equalsIgnoreCase("legendaire") || rarity.equalsIgnoreCase("légendaire"))
            return 5;
        else if(rarity.equalsIgnoreCase("mythique"))
            return 6;
        else if(rarity.equalsIgnoreCase("supreme") || rarity.equalsIgnoreCase("suprême"))
            return 7;
        else if(rarity.equalsIgnoreCase("colossale"))
            return 8;
        else if(rarity.equalsIgnoreCase("divin"))
            return 9;
        else if(rarity.equalsIgnoreCase("inconnu"))
            return 10;
        else if(rarity.equalsIgnoreCase("demoniaque") || rarity.equalsIgnoreCase("démoniaque"))
            return 11;

        return 1;
    }

    public static ItemStack generateSword(String name){
        String namef = ChatColor.stripColor(name);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(namef));
        ItemStack itemStack = new ItemStack(data.getInt("material"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', data.getString("name")));
        if(data.getString("rarete").equalsIgnoreCase("mythique") || data.getString("rarete").equalsIgnoreCase("supreme") || data.getString("rarete").equalsIgnoreCase("suprême") || data.getString("rarete").equalsIgnoreCase("colossale")
                || data.getString("rarete").equalsIgnoreCase("divin") || data.getString("rarete").equalsIgnoreCase("inconnu") || data.getString("rarete").equalsIgnoreCase("demoniaque")|| data.getString("rarete").equalsIgnoreCase("démoniaque")){
            itemMeta.setLore(Arrays.asList("§7Dégats §8: §c" + data.getInt("damage"),
                    "§7Taux Critique §8: §c" + data.getInt("tauxcritique"),
                    "§7Dégats Critique §8: §c" + data.getInt("degatcritique"),
                    "§7Force §8: §c" + data.getInt("force"),
                    "§7Mana §8: §c" + data.getInt("mana"),
                    "",
                    "" + colorByRarity(data.getString("rarete")) + ChatColor.MAGIC + "  " + colorByRarity(data.getString("rarete")) + " §l" + data.getString("rarete") + " " +colorByRarity(data.getString("rarete")) + ChatColor.MAGIC + "  "));
        } else {
            itemMeta.setLore(Arrays.asList("§7Dégats §8: §c" + data.getInt("damage"),
                    "§7Taux Critique §8: §c" + data.getInt("tauxcritique"),
                    "§7Dégats Critique §8: §c" + data.getInt("degatcritique"),
                    "§7Force §8: §c" + data.getInt("force"),
                    "§7Mana §8: §c" + data.getInt("mana"),
                    "",
                    "" + colorByRarity(data.getString("rarete")) + "§l" + data.getString("rarete")));
        }

        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack generateArmor(String name){
        String namef = ChatColor.stripColor(name);
        YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile(namef));
        ItemStack itemStack = new ItemStack(data.getInt("material"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', data.getString("name")));
        itemMeta.setLore(Arrays.asList("§7Dégats §8: §c" + data.getInt("damage"),
                "§7Taux Critique §8: §c" + data.getInt("tauxcritique"),
                "§7Dégats Critique §8: §c" + data.getInt("degatcritique"),
                "§7Force §8: §c" + data.getInt("force"),
                "§7Mana §8: §c" + data.getInt("mana"),
                "§7Défense §8: §c" + data.getInt("defense"),
                "§7Vie §8 §c" + data.getInt("health")));
        itemMeta.spigot().setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
