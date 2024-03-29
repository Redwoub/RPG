package fr.redwoub.mydoriarpg.accounts;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.managers.StatistiqueManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Accounts extends AbstractData {

    private final Main main = Main.getInstance();
    private boolean newPlayer;
    private final DataCols dataCols;
    private final DataLvl dataLvl;
    private final DataStatistique dataStatistique;
    private final DataBonusStatistique dataBonusStatistique;
    private final DataRank dataRank;
    private final File account;
    private final File saveDir;
    private final File friends;
    private final File optionFriends;
    private final YamlConfiguration dataAccount;
    private final YamlConfiguration dataOptionFriends;

    public Accounts(UUID uuid){
        this.uuid = uuid;
        dataCols = new DataCols(uuid);
        dataLvl = new DataLvl(uuid);
        dataStatistique = new DataStatistique(uuid);
        dataBonusStatistique = new DataBonusStatistique(uuid);
        dataRank = new DataRank(uuid);

        saveDir = new File(Main.getInstance().getDataFolder(), "/accounts/" + uuid + "/");
        account = new File(saveDir, "Account.yml");
        friends = new File(saveDir, "Friends.yml");
        optionFriends = new File(saveDir, "OptionFriends.yml");
        dataAccount = YamlConfiguration.loadConfiguration(account);
        dataOptionFriends = YamlConfiguration.loadConfiguration(optionFriends);
        newPlayer = false;

        if(!saveDir.exists())
            saveDir.mkdir();
    }

    private String[] getDataFromYML(){
        String[] info = new String[22];
        info[0] = String.valueOf(dataAccount.getInt("vie_max"));
        info[1] = String.valueOf(dataAccount.getInt("speed"));
        info[2] = String.valueOf(dataAccount.getInt("defense"));
        info[3] = String.valueOf(dataAccount.getInt("force"));
        info[4] = String.valueOf(dataAccount.getInt("degat_critique"));
        info[5] = String.valueOf(dataAccount.getInt("mana_max"));
        info[6] = String.valueOf(dataAccount.getInt("attaque_speed"));
        info[7] = String.valueOf(dataAccount.getInt("combat_lvl"));
        info[8] = String.valueOf(dataAccount.getLong("combat_missing_xp"));
        info[9] = String.valueOf(dataAccount.getInt("donjon_lvl"));
        info[10] = String.valueOf(dataAccount.getLong("donjon_missing_xp"));
        info[11] = String.valueOf(dataAccount.getInt("forgeron_lvl"));
        info[12] = String.valueOf(dataAccount.getLong("forgeron_missing_xp"));
        info[13] = dataAccount.getString("color_name");
        info[14] = String.valueOf(dataAccount.getLong("col"));
        info[15] = String.valueOf(dataAccount.getInt("taux_critique"));
        info[16] = String.valueOf(dataAccount.getInt("rpg_lvl"));
        info[17] = String.valueOf(dataAccount.getLong("rpg_missing_xp"));
        info[18] = dataAccount.getString("color_chat");
        info[19] = dataAccount.getString("grade");
        info[20] = dataAccount.getString("player_type");
        info[21] = dataAccount.getString("nickname");
        if(dataAccount.getLong("col") == 0) newPlayer = true;

        return info;
    }

    private void saveDataToYML(){
        dataAccount.set("vie_max", getDataBonusStatistique().getMaxHealth());
        dataAccount.set("speed", getDataBonusStatistique().getSpeed());
        dataAccount.set("defense", getDataBonusStatistique().getDefense());
        dataAccount.set("force", getDataBonusStatistique().getForce());
        dataAccount.set("degat_critique", getDataBonusStatistique().getDegatCritique());
        dataAccount.set("taux_critique", getDataBonusStatistique().getTauxCritique());
        dataAccount.set("mana_max", getDataBonusStatistique().getMaxMana());
        dataAccount.set("attaque_speed", getDataBonusStatistique().getAttaqueSpeed());
        dataAccount.set("combat_lvl", dataLvl.getCombatLvl());
        dataAccount.set("combat_missing_xp", dataLvl.getMissingCombatXp());
        dataAccount.set("donjon_lvl", dataLvl.getDonjonLvl());
        dataAccount.set("donjon_missing_xp", dataLvl.getMissingDonjonXp());
        dataAccount.set("forgeron_lvl", dataLvl.getForgeronLvl());
        dataAccount.set("forgeron_missing_xp", dataLvl.getMissingForgeronXp());
        dataAccount.set("color_name", dataStatistique.getColorName());
        dataAccount.set("col", dataCols.getCols());
        dataAccount.set("rpg_lvl", dataLvl.getRpgLvl());
        dataAccount.set("rpg_missing_xp", dataLvl.getMissingRpgXp());
        dataAccount.set("color_chat", dataStatistique.getColorChat());
        dataAccount.set("grade", dataRank.getRank().getName());
        dataAccount.set("player_type", dataStatistique.getPlayerType().getName());
        dataAccount.set("nickname", dataStatistique.getNickname());

        try {
            if(!friends.exists())
                friends.createNewFile();
            dataAccount.save(account);
            dataOptionFriends.save(optionFriends);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onLogin(){
        Main.getInstance().getAccounts().add(this);
        String[] data = getDataFromYML();
        if(newPlayer){
            dataCols.setCols(500);
            dataLvl.setCombatLvl(1);
            dataLvl.setMissingCombatXp(CombatLevel.needingxp(2));
            dataLvl.setDonjonLvl(1);
            dataLvl.setMissingDonjonXp(DonjonLevel.needingxp(2));
            dataLvl.setForgeronLvl(1);
            dataLvl.setMissingForgeronXp(ForgeronLevel.needingxp(2));
            dataLvl.setRpgLvl(1);
            dataLvl.setMissingRpgXp(RpgLevel.needingxp(2));
            dataStatistique.setColorName("blanc");
            dataStatistique.setMaxHealth(100);
            dataStatistique.setHealth(dataStatistique.getMaxHealth());
            dataStatistique.setDefense(10);
            dataStatistique.setSpeed(100);
            dataStatistique.setMaxMana(300);
            dataStatistique.setTauxCritique(20);
            dataStatistique.setDegatCritique(10);
            dataStatistique.setAttaqueSpeed(0);
            dataStatistique.setForce(10);
            dataStatistique.setColorChat("gris");
            dataRank.setRank(RankUnit.JOUEUR);
            dataOptionFriends.set("reciveMessages", "ON");
            dataOptionFriends.set("reciveRequest", "ON");
            dataOptionFriends.set("reciveJoinNotification", "ON");
            dataStatistique.setCurrentMana(dataStatistique.getMaxMana());
            dataStatistique.setHealth(dataStatistique.getMaxHealth());
            dataStatistique.setPlayerType(PlayerType.generateType(uuid));
            dataStatistique.setNickname(Bukkit.getPlayer(uuid).getName());
            saveDataToYML();

        } else {
            dataStatistique.setMaxHealth(Integer.parseInt(data[0]));
            dataStatistique.setSpeed(Integer.parseInt(data[1]));
            dataStatistique.setDefense(Integer.parseInt(data[2]));
            dataStatistique.setForce(Integer.parseInt(data[3]));
            dataStatistique.setDegatCritique(Integer.parseInt(data[4]));
            dataStatistique.setMaxMana(Integer.parseInt(data[5]));
            dataStatistique.setAttaqueSpeed(Integer.parseInt(data[6]));
            dataLvl.setCombatLvl(Integer.parseInt(data[7]));
            dataLvl.setMissingCombatXp(Long.parseLong(data[8]));
            dataLvl.setDonjonLvl(Integer.parseInt(data[9]));
            dataLvl.setMissingDonjonXp(Long.parseLong(data[10]));
            dataLvl.setForgeronLvl(Integer.parseInt(data[11]));
            dataLvl.setMissingForgeronXp(Long.parseLong(data[12]));
            dataStatistique.setColorName(data[13]);
            dataCols.setCols(Long.parseLong(data[14]));
            dataStatistique.setTauxCritique(Integer.parseInt(data[15]));
            dataStatistique.setHealth(dataStatistique.getMaxHealth());
            dataLvl.setRpgLvl(Integer.parseInt(data[16]));
            dataLvl.setMissingRpgXp(Long.parseLong(data[17]));
            dataStatistique.setColorChat(data[18]);
            dataRank.setRank(RankUnit.getByName(data[19]));
            dataStatistique.setCurrentMana(dataStatistique.getMaxMana());
            dataStatistique.setHealth(dataStatistique.getMaxHealth());
            dataStatistique.setPlayerType(PlayerType.getByName(data[20]));
            dataStatistique.setNickname(data[21]);
        }

        DataBonusStatistique statsWithoutBonus = getDataBonusStatistique();
        DataStatistique stats = getDataStatistique();
        statsWithoutBonus.setMaxHealth(stats.getMaxHealth());
        statsWithoutBonus.setSpeed(stats.getSpeed());
        statsWithoutBonus.setDefense(stats.getDefense());
        statsWithoutBonus.setForce(stats.getForce());
        statsWithoutBonus.setDegatCritique(stats.getDegatCritique());
        statsWithoutBonus.setTauxCritique(stats.getTauxCritique());
        statsWithoutBonus.setMaxMana(stats.getMaxMana());
        statsWithoutBonus.setAttaqueSpeed(stats.getAttaqueSpeed());
    }

    public void onLogout(){
        saveDataToYML();
        Main.getInstance().getAccounts().remove(this);
    }

    public DataCols getDataCols() {
        return dataCols;
    }

    public DataLvl getDataLvl() {
        return dataLvl;
    }

    public File getAccount() {
        return account;
    }

    public DataStatistique getDataStatistique() {
        return dataStatistique;
    }

    public DataBonusStatistique getDataBonusStatistique() {
        return dataBonusStatistique;
    }

    public DataRank getDataRank() {
        return dataRank;
    }

    public File getOptionFriends() {
        return optionFriends;
    }

    public File getSaveDir() {
        return saveDir;
    }

    public File getFriends() {
        return friends;
    }

    public boolean isNewPlayer() {
        return newPlayer;
    }
}
