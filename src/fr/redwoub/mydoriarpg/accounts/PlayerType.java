package fr.redwoub.mydoriarpg.accounts;

import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Arrays;
import java.util.UUID;

public enum PlayerType {

    FEU("FEU", "§cFeu", 18),
    EAU("EAU", "§9Eau", 18),
    FOUDRE("FOUDRE", "§eFoudre", 18),
    TERRE("TERRE", "§aTerre", 18),
    VENT("VENT", "§fVent", 18),
    SPECIAL("SPECIAL", "§4Spécial", 5),
    INCONNU("INCONNU", "§7Inconnu", 1),
    DEMONIAQUE("DEMONIAQUE", "§8Démoniaque", 0);

    private final String name;
    private final String prefix;
    private final double pourcentage;

    PlayerType(String name, String prefix, double pourcentage) {
        this.name = name;
        this.prefix = prefix;
        this.pourcentage = pourcentage;
    }

    private static PlayerType generateFakeType(){
        int type = (int) ((Math.random() * (10-1)) + 1);
        if(type < 3) // 1-2
            return FEU;
        else if(type >= 3 && type < 5) //3-4
            return EAU;
        else if(type >= 5 && type < 7) //5-6
            return FOUDRE;
        else if(type >= 7 && type < 9) //7-8
            return TERRE;
        else if(type >= 9 && type < 11) //9-10
            return VENT;

        return FEU;
    }

    public static PlayerType generateType(UUID uuid){
        if(PlayerUtils.hasDeletedAccount(uuid)){
            File account = PlayerUtils.getDeletedAccount(uuid);
            File file = new File(account, "Account.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            return PlayerType.getByName(config.getString("player_type"));
        } else {
            int type = (int) ((Math.random() * (10-1)) + 1);
            if(generateFakeType() == FOUDRE){
                if(type < 8)//1,2,3,4,5,6,7
                    return FOUDRE;
                else if(type >= 8 && type < 10)//8,9
                    return SPECIAL;
                else if(type == 10)//10
                    return INCONNU;
            }
            return generateFakeType();
        }
    }

    public static PlayerType getByName(String name) {
        return Arrays.stream(values()).filter(r -> r.getName().equalsIgnoreCase(name)).findAny().orElse(PlayerType.FEU);
    }

    public String getName() {
        return name;
    }

    public String getPrefix() {
        return prefix;
    }
}
