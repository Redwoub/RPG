package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataLvl extends AbstractData{

    private int combatLvl;
    private long missingCombatXp;
    private int donjonLvl;
    private long missingDonjonXp;
    private int forgeronLvl;
    private long missingForgeronXp;

    private int rpgLvl;
    private long missingRpgXp;
    public DataLvl(UUID uuid){
        this.uuid = uuid;
    }

    public void setCombatLvl(int lvl){
        this.combatLvl = lvl;
    }

    public void setMissingCombatXp(long missingCombatXp){
        this.missingCombatXp = missingCombatXp;
    }

    public void addCombatXp(long combatXp){
        this.missingCombatXp -= combatXp;
    }

    public void removeCombatXp(long combatXp){
        this.missingCombatXp += combatXp;
    }

    public int getCombatLvl(){
        return combatLvl;
    }

    public int getNextCombatLvl(){
        return combatLvl + 1;
    }
    public long getMissingCombatXp(){
        return missingCombatXp;
    }

    public void setDonjonLvl(int lvl){
        this.donjonLvl = lvl;
    }

    public void setMissingDonjonXp(long missingDonjonXp){
        this.missingDonjonXp = missingDonjonXp;
    }
    public void addDonjonXp(long donjonXp){
        this.missingDonjonXp -= donjonXp;
    }

    public void removeDonjonXp(long donjonXp){
        this.missingDonjonXp += donjonXp;
    }

    public int getDonjonLvl(){
        return donjonLvl;
    }

    public int getNextDonjonLvl(){
        return donjonLvl + 1;
    }

    public long getMissingDonjonXp(){
        return missingDonjonXp;
    }

    public void setForgeronLvl(int lvl){
        this.forgeronLvl = lvl;
    }

    public void setMissingForgeronXp(long missingForgeronXp){
        this.missingForgeronXp = missingForgeronXp;
    }
    public void addForgeronXp(long forgeronXp){
        this.missingForgeronXp -= forgeronXp;
    }

    public void removeForgeronXp(long forgeronXp){
        this.missingForgeronXp += forgeronXp;
    }

    public int getForgeronLvl(){
        return forgeronLvl;
    }

    public int getNextForgeronLvl(){
        return forgeronLvl + 1;
    }

    public long getMissingForgeronXp(){
        return missingForgeronXp;
    }

    public void setRpgLvl(int lvl){
        this.rpgLvl = lvl;
    }

    public void setMissingRpgXp(long missingRpgXp){
        this.missingRpgXp = missingRpgXp;
    }
    public void addRpgXp(long rpgXp){
        this.missingRpgXp -= rpgXp;
    }

    public void removeRpgXp(long rpgXp){
        this.missingRpgXp += rpgXp;
    }

    public int getRpgLvl(){
        return rpgLvl;
    }

    public int getNexpRpgLvl(){
        return rpgLvl + 1;
    }

    public long getMissingRpgXp(){
        return missingRpgXp;
    }
}
