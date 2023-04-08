package fr.redwoub.mydoriarpg.utils;

import fr.redwoub.mydoriarpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class FileUtils {

    public static void save(YamlConfiguration config, File file){
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static File getItemFile(String fileName){
        return new File(Main.getInstance().getDataFolder() + "/items/templates", fileName + ".yml");
    }

    public static void deleteDirectory(File file) {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()){

            // if it is a subfolder,e.g Rohan and Ritik,
            //  recursively call function to empty subfolder
            if (subfile.isDirectory()){
                deleteDirectory(subfile);
            }

            // delete files and empty subfolders
            subfile.delete();
        }
    }

    public static void createFile(File file){
        if(file.exists()) return;
        try {
            file.createNewFile();
        } catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage("§cFailed to create file named §7: §b" + file.getName() + "\n" + new RuntimeException(e));
        }
    }

    public static void fillMapWithAllLineOfFile(File file, Map<String, String> map){

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String[] strings;
        String currentLine;
        while(true){
            try {
                if ((currentLine = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            strings = currentLine.split(":");
            map.put(strings[1].substring(1), strings[0]);
        }
        try {
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
