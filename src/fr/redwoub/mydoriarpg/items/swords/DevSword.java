package fr.redwoub.mydoriarpg.items.swords;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.ItemManager;
import fr.redwoub.mydoriarpg.utils.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.File;
import java.util.Arrays;

public class DevSword {
    public static ItemStack getDevSword(){
        YamlConfiguration data = YamlConfiguration.loadConfiguration(FileUtils.getItemFile("devsword"));
        ItemStack itemStack = new ItemStack(data.getInt("material"));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(ItemManager.colorByRarity(data.getString("rarete")) + data.getString("name"));

        if(ItemManager.getNumberForRarity(data.getString("rarete")) >= 6){
            itemMeta.setLore(Arrays.asList("§7Damage: §c+" + data.getInt("damage"), "§7Force: §c+" + data.getInt("force"),
                    "§7Dégat Critique: §c+" + data.getInt("degat_critique"), "§7Taux Critique: §c+" + data.getInt("taux_critique"),
                    "", ItemManager.colorByRarity(data.getString("rarete")) + "§k  " + ItemManager.colorByRarity(data.getString("rarete")) + " §l" + data.getString("rarete") + " " + ItemManager.colorByRarity(data.getString("rarete")) + "§k  "));
        } else {
            itemMeta.setLore(Arrays.asList("§7Damage: §c+" + data.getInt("damage"), "§7Force: §c+" + data.getInt("force"),
                    "§7Dégat Critique: §c+" + data.getInt("degat_critique"), "§7Taux Critique: §c+" + data.getInt("taux_critique"),
                    "", ItemManager.colorByRarity(data.getString("rarete")) + " §l" + data.getString("rarete")));
        }

        itemMeta.setUnbreakable(true);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
