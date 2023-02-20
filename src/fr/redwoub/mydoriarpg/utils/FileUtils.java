package fr.redwoub.mydoriarpg.utils;

import fr.redwoub.mydoriarpg.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileUtils {

    public static void save(YamlConfiguration config, File file){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getItemFile(String fileName){
        return new File(Main.getInstance().getDataFolder() + "/items/template", fileName + ".yml");
    }
}
