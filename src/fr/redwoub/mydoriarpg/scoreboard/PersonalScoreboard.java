package fr.redwoub.mydoriarpg.scoreboard;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.accounts.RankUnit;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class PersonalScoreboard {

    private Player player;
    private final UUID uuid;
    private final ObjectiveSign objectiveSign;
    private final Main main = Main.getInstance();
    private final Optional<Accounts> accounts;
    private RankUnit rank;
    private Long Cols;
    private String rpglvl;
    private int currentMana;
    private int maxMana;
    private int currentHealth;
    private int maxHealth;

    PersonalScoreboard(Player player){
        this.player = player;
        uuid = player.getUniqueId();
        objectiveSign = new ObjectiveSign("sidebar", "Mydoria");
        objectiveSign.addReceiver(player);
        accounts = Main.getInstance().getAccount(player);
        reloadData();
    }

    public void reloadData(){
        accounts.ifPresent(a -> {
            Cols = a.getDataCols().getCols();
            rpglvl = PlayerUtils.generateColoredNameForRpgLevel(a);
            rank = a.getDataRank().getRank();
            currentMana = a.getDataStatistique().getCurrentMana();
            maxMana = a.getDataStatistique().getMaxMana();
            currentHealth = a.getDataStatistique().getVie();
            maxHealth = a.getDataStatistique().getMaxVie();
        });
    }

    public void setLines(String ip){

        objectiveSign.setDisplayName("§6Mydoria");
        objectiveSign.setLine(1, "§1");
        objectiveSign.setLine(2, " • " + player.getDisplayName());
        objectiveSign.setLine(3, " • §7Grade §f: " + rank.getPrefix());
        objectiveSign.setLine(4, "§2");
        objectiveSign.setLine(5, " §7• Cols §f: §e" + Cols);
        objectiveSign.setLine(6, " §7• Niveau §f: " + rpglvl);
        objectiveSign.setLine(7, " §7• Mana §f: §b" + currentMana + "§f/§b" + maxMana);
        objectiveSign.setLine(8, " §7• Vie §f: §c" + currentHealth + "§f/§c" + maxHealth);
        objectiveSign.setLine(9,"§3");
        objectiveSign.setLine(10," §7• Players §f: §e" + Bukkit.getOnlinePlayers().size() + " / " + Bukkit.getMaxPlayers());
        objectiveSign.setLine(11," §7• §2Guild §f: §bA FAIRE");
        objectiveSign.setLine(12,"§4");
        objectiveSign.setLine(13, ip);

        objectiveSign.updateLines();
    }

    public void onLogout(){
        objectiveSign.removeReceiver(Bukkit.getServer().getOfflinePlayer(uuid));
    }
}
