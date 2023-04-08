package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataStatistique extends AbstractData{

    public DataStatistique(UUID uuid) {
        this.uuid = uuid;
    }

    private String colorName;
    private String colorChat;
    private int maxHealth;
    private int health;
    private int speed;
    private int defense;
    private int force;
    private int degatCritique;
    private int tauxCritique;
    private int maxMana;
    private int currentMana;
    private int attaqueSpeed;
    private PlayerType playerType;

    private String nickname;

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
