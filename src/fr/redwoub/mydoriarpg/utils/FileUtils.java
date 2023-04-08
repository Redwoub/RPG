package fr.redwoub.mydoriarpg.utils;

import fr.redwoub.mydoriarpg.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
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

    public static void removeLine(String lineToRemove, File file) throws IOException{
        //Reading File Content and storing it to a StringBuilder variable ( skips lineToRemove)
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            String currentLine;
            while(sc.hasNext()){
                currentLine = sc.nextLine();
                if(currentLine.equalsIgnoreCase(lineToRemove)){
                    continue; //skips lineToRemove
                }
                sb.append(currentLine).append("\n");
            }
        }
        //Delete File Content
        PrintWriter pw = new PrintWriter(file);
        pw.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.append(sb.toString());
        writer.close();
    }
}
