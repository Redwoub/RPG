package fr.redwoub.mydoriarpg.accounts;

import java.util.UUID;

public class DataBonusStatistique extends AbstractData{

    public DataBonusStatistique(UUID uuid){
        this.uuid = uuid;
    }

    private int maxHealth;
    private int speed;
    private int defense;
    private int force;
    private int degatCritique;
    private int tauxCritique;
    private int maxMana;
    private int attaqueSpeed;

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
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
}
