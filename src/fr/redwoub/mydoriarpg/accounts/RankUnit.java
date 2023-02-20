package fr.redwoub.mydoriarpg.accounts;

import org.bukkit.ChatColor;

import java.util.Arrays;

public enum RankUnit {

    JOUEUR("Joueur", 7, "§7Joueur", ChatColor.GRAY),
    VIP("VIP", 6, "§eVIP", ChatColor.YELLOW),
    MVP("MVP", 5, "§bMVP", ChatColor.AQUA),
    SUPPORT("SUPPORT", 4, "§2Support", ChatColor.DARK_GREEN),
    BUILDER("BUILDER", 3, "§3Builder", ChatColor.DARK_BLUE),
    MODERATEUR("MODERATEUR", 2, "§9Modérateur", ChatColor.BLUE),
    DEVELOPPER("DEVELOPPEUR", 1, "§6Développeur", ChatColor.GOLD),
    ADMINISTRATEUR("ADMINISTRATEUR", 0, "§4Administrateur", ChatColor.DARK_RED);


    private final String name;
    private final int power;
    private final String prefix;
    private final ChatColor chatColor;

    RankUnit(String name, int power, String prefix, ChatColor chatColor) {
        this.name = name;
        this.power = power;
        this.prefix = prefix;
        this.chatColor = chatColor;
    }

    public static RankUnit getByName(String name) {
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(RankUnit.JOUEUR);
    }

    public static RankUnit getbyPower(int power) {
        return Arrays.stream(values()).filter(r -> r.getPower() == power).findAny().orElse(RankUnit.JOUEUR);
    }

    public String getPrefix() {
        return prefix;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }
}
