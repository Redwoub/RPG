package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataStatistique extends AbstractData{

    public DataStatistique(UUID uuid) {
        this.uuid = uuid;
    }

    private String colorName;
    private String colorChat;
    private int maxVie;
    private int vie;
    private int speed;
    private int defense;
    private int force;
    private int degatCritique;
    private int tauxCritique;
    private int maxMana;
    private int currentMana;
    private int energie;
    private int attaqueSpeed;
    private PlayerType playerType;

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getMaxVie() {
        return maxVie;
    }

    public void setMaxVie(int maxVie) {
        this.maxVie = maxVie;
    }

    public int getVie() {
        return vie;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getDegatCritique() {
        return degatCritique;
    }

    public void setDegatCritique(int degatCritique) {
        this.degatCritique = degatCritique;
    }

    public int getTauxCritique() {
        return tauxCritique;
    }

    public void setTauxCritique(int tauxCritique) {
        this.tauxCritique = tauxCritique;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getEnergie() {
        return energie;
    }

    public void setEnergie(int energie) {
        this.energie = energie;
    }

    public int getAttaqueSpeed() {
        return attaqueSpeed;
    }

    public void setAttaqueSpeed(int attaqueSpeed) {
        this.attaqueSpeed = attaqueSpeed;
    }

    public String getColorChat() {
        return colorChat;
    }

    public void setColorChat(String colorChat) {
        this.colorChat = colorChat;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
