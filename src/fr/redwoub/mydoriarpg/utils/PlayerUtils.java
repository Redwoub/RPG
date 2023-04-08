package fr.redwoub.mydoriarpg.utils;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.UUID;

public class PlayerUtils {

    public static String getNMSVersion() {
        String packageName = Bukkit.getServer().getClass().getPackage().getName();

        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static Object getChatComponentText(String message) {
        Class<?> clazz;
        Class<?> parameterClass;
        Constructor<?> constructor;

        if (message == null)
            return null;
        try {
            clazz = Class.forName("net.minecraft.server." + getNMSVersion() + ".ChatComponentText");
            constructor = clazz.getConstructor(String.class);
            constructor.setAccessible(true);
            return constructor.newInstance(message);
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {}
        return null;
    }

    public static Object getEntityPlayer(Player player) {
        Class<?> clazz;
        Method method;

        if (player == null)
            return null;
        try {
            clazz = Class.forName("org.bukkit.craftbukkit." + getNMSVersion() + ".entity.CraftPlayer");
            method = clazz.getMethod("getHandle");
            return method.invoke(player);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
        return null;
    }

    public static Object getPlayerConnection(Player player) {
        Class<?> clazz;
        Field field;
        Object entityPlayer = getEntityPlayer(player);

        if (entityPlayer == null)
            return null;
        try {
            clazz = Class.forName("net.minecraft.server." + getNMSVersion() + ".EntityPlayer");
            field = clazz.getDeclaredField("playerConnection");
            field.setAccessible(true);
            return field.get(entityPlayer);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException ignored) {}
        return null;
    }

    public static boolean sendPacket(Player player, Object packet) {
        Object playerConnection = getPlayerConnection(player);
        Class<?> clazz;
        Class<?> parameterClass;
        Method method;

        if (playerConnection == null)
            return false;
        try {
            clazz = Class.forName("net.minecraft.server." + getNMSVersion() + ".PlayerConnection");
            parameterClass = Class.forName("net.minecraft.server." + getNMSVersion() + ".Packet");
            method = clazz.getMethod("sendPacket", parameterClass);
            method.invoke(playerConnection, packet);
            return true;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {}
        return false;
    }

    public static boolean sendPlayerActionBar(Player player, String message) {
        Class<?> clazz;
        Class<?> parameterClass;
        Constructor<?> constructor;
        Object chatComponentText;

        if (player == null)
            return false;
        chatComponentText = getChatComponentText(message);
        if (chatComponentText == null)
            return false;
        try {
            clazz = Class.forName("net.minecraft.server." + getNMSVersion() + ".PacketPlayOutChat");
            parameterClass = Class.forName("net.minecraft.server." + getNMSVersion() + ".IChatBaseComponent");
            constructor = clazz.getConstructor(parameterClass, byte.class);
            constructor.setAccessible(true);
            return sendPacket(player, constructor.newInstance(chatComponentText, (byte) 2));
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ignored) {}
        return false;
    }

    public static float generateSpeed(Accounts accounts){
        float speed = accounts.getDataStatistique().getSpeed();
        if(speed >= 125 && speed < 150){
            return 0.22f;
        } else if(speed >= 150 && speed < 175){
            return 0.25f;
        } else if(speed >= 175 && speed < 200){
            return 0.28f;
        } else if(speed >= 200 && speed < 225){
            return 0.3f;
        } else if(speed >= 225 && speed < 250){
            return 0.32f;
        } else if(speed >= 250 && speed < 275){
            return 0.35f;
        } else if(speed >= 275 && speed < 300){
            return 0.38f;
        } else if(speed >= 300 && speed < 325){
            return 0.4f;
        } else if(speed >= 325 && speed < 350){
            return 0.42f;
        } else if(speed >= 350 && speed < 375){
            return 0.45f;
        } else if(speed >= 375 && speed < 400){
            return 0.48f;
        } else if(speed == 400){
            return 0.5f;
        }

        return 0.2f;
    }

    public static long generateTimeForShoorAnotherArrow(Accounts accounts){
        float speed = accounts.getDataStatistique().getAttaqueSpeed();
        if(speed >= 5 && speed < 10){
            return 30;
        } else if(speed >= 10 && speed < 15){
            return 28;
        } else if(speed >= 15 && speed < 20){
            return 27;
        } else if(speed >= 20 && speed < 25){
            return 25;
        } else if(speed >= 25 && speed < 30){
            return 24;
        } else if(speed >= 30 && speed < 35){
            return 22;
        } else if(speed >= 35 && speed < 40){
            return 21;
        } else if(speed >= 40 && speed < 45){
            return 19;
        } else if(speed >= 45 && speed < 50){
            return 18;
        } else if(speed >= 50 && speed < 55){
            return 16;
        } else if(speed >= 55 && speed < 60){
            return 15;
        } else if(speed >= 60 && speed < 65){
            return 13;
        } else if(speed >= 65 && speed < 70){
            return 12;
        } else if(speed >= 70 && speed < 75){
            return 10;
        } else if(speed >= 75 && speed < 80){
            return 9;
        } else if(speed >= 80 && speed < 85){
            return 8;
        } else if(speed >= 85 && speed < 90){
            return 7;
        } else if(speed >= 90 && speed < 95){
            return 6;
        } else if(speed >= 95 && speed < 100){
            return 6;
        } else if(speed >= 100){
            return 6;
        } else {
            return 30;
        }
    }

    public static String generateColoredNameForRpgLevel(Accounts accounts){
        int rpgLvl = accounts.getDataLvl().getRpgLvl();
        if(rpgLvl > 0 && rpgLvl <= 20){
            return "§7" + rpgLvl;
        } else if(rpgLvl > 20 && rpgLvl <= 40){
            return "§8" + rpgLvl;
        } else if(rpgLvl > 40 && rpgLvl <= 60){
            return "§0" + rpgLvl;
        } else if(rpgLvl > 60 && rpgLvl <= 80){
            return "§5" + rpgLvl;
        } else if(rpgLvl > 80 && rpgLvl <= 100){
            return "§d" + rpgLvl;
        }else if(rpgLvl > 100 && rpgLvl <= 120){
            return "§1" + rpgLvl;
        } else if(rpgLvl > 120 && rpgLvl <= 140){
            return "§9" + rpgLvl;
        } else if(rpgLvl > 140 && rpgLvl <= 160){
            return "§3" + rpgLvl;
        } else if(rpgLvl > 160 && rpgLvl <= 180){
            return "§b" + rpgLvl;
        } else if(rpgLvl > 180 && rpgLvl <= 200){
            return "§2" + rpgLvl;
        } else if(rpgLvl > 200 && rpgLvl <= 220){
            return "§a" + rpgLvl;
        } else if(rpgLvl > 220 && rpgLvl <= 240){
            return "§e" + rpgLvl;
        } else if(rpgLvl > 240 && rpgLvl < 280){
            return "§c" + rpgLvl;
        } else if(rpgLvl >= 280){
            return "§6" + rpgLvl;
        } else {
            return "§7" + rpgLvl;
        }
    }


    public static String generateColoredNameWithoutRPG(Accounts accounts) {
        int rpgLvl = accounts.getDataLvl().getRpgLvl();
        if(rpgLvl > 0 && rpgLvl <= 20){
            return "§7";
        } else if(rpgLvl > 20 && rpgLvl <= 40){
            return "§8";
        } else if(rpgLvl > 40 && rpgLvl <= 60){
            return "§0";
        } else if(rpgLvl > 60 && rpgLvl <= 80){
            return "§5";
        } else if(rpgLvl > 80 && rpgLvl <= 100){
            return "§d";
        }else if(rpgLvl > 100 && rpgLvl <= 120){
            return "§1";
        } else if(rpgLvl > 120 && rpgLvl <= 140){
            return "§9";
        } else if(rpgLvl > 140 && rpgLvl <= 160){
            return "§3";
        } else if(rpgLvl > 160 && rpgLvl <= 180){
            return "§b";
        } else if(rpgLvl > 180 && rpgLvl <= 200){
            return "§2";
        } else if(rpgLvl > 200 && rpgLvl <= 220){
            return "§a";
        } else if(rpgLvl > 220 && rpgLvl <= 240){
            return "§e";
        } else if(rpgLvl > 240 && rpgLvl <= 260){
            return "§c";
        } else if(rpgLvl > 260 && rpgLvl <= 280){
            return "§6";
        } else if(rpgLvl > 280){
            return "§6";
        } else {
            return "§7";
        }
    }

    public static ChatColor getByString(String color){

        if(color.equalsIgnoreCase("Rouge")) {
            return ChatColor.RED;
        } else if(color.equalsIgnoreCase("Dore")) {
            return ChatColor.GOLD;
        } else if(color.equalsIgnoreCase("Jaune")){
            return ChatColor.YELLOW;
        } else if(color.equalsIgnoreCase("Dark_Green")) {
            return ChatColor.DARK_GREEN;
        } else if(color.equalsIgnoreCase("Vert")){
            return ChatColor.GREEN;
        } else if(color.equalsIgnoreCase("Bleu_claire")) {
            return ChatColor.AQUA;
        } else if(color.equalsIgnoreCase("Cyan")) {
            return ChatColor.DARK_AQUA;
        } else if(color.equalsIgnoreCase("Bleu_fonce")) {
            return ChatColor.DARK_BLUE;
        } else if(color.equalsIgnoreCase("Bleu")) {
            return ChatColor.BLUE;
        } else if(color.equalsIgnoreCase("Rose")) {
            return ChatColor.LIGHT_PURPLE;
        } else if(color.equalsIgnoreCase("Violet")) {
            return ChatColor.DARK_PURPLE;
        } else if(color.equalsIgnoreCase("Blanc")) {
            return ChatColor.WHITE;
        } else if(color.equalsIgnoreCase("Gris")) {
            return ChatColor.GRAY;
        } else if(color.equalsIgnoreCase("dark_gris")){
            return ChatColor.DARK_GRAY;
        } else if(color.equalsIgnoreCase("noir")){
            return ChatColor.BLACK;
        } else if(color.equalsIgnoreCase("dark_red")){
            return ChatColor.DARK_RED;
        }

        return ChatColor.WHITE;
    }

    public static boolean hasDeletedAccount(UUID uuid){
        File file = new File(Main.getInstance().getDeletedAccounts(), uuid + "/");
        return file.exists();
    }

    public static File getDeletedAccount(UUID uuid){
        return new File(Main.getInstance().getDeletedAccounts(), uuid + "/");
    }

    public static boolean hasItemInHand(Player player){
        return player.getInventory().getItemInMainHand() != null;
    }

    public static boolean hasItemInOffHand(Player player){
        return player.getInventory().getItemInOffHand() != null;
    }

    public static boolean hasHelmet(Player player){
        return player.getInventory().getHelmet() != null;
    }

    public static boolean hasChestplate(Player player){
        return player.getInventory().getChestplate() != null;
    }

    public static boolean hasLeggings(Player player){
        return player.getInventory().getLeggings() != null;
    }

    public static boolean hasBoots(Player player){
        return player.getInventory().getBoots() != null;
    }
}
