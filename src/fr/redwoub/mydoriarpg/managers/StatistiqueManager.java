package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StatistiqueManager extends BukkitRunnable {
    private final Player player;
    public int mana;
    public int vie;
    public int speed;
    public int defense;
    public int force;
    public int degatCrit;
    public int attackSpeed;
    public int tauxCrit;

    private final Accounts accounts;

    public StatistiqueManager(Player player){
        this.player = player;
        accounts = Main.getInstance().getAccount(player).get();
        mana = accounts.getDataStatistique().getMaxMana();
        vie = accounts.getDataStatistique().getMaxVie();
        speed = accounts.getDataStatistique().getSpeed();
        defense = accounts.getDataStatistique().getDefense();
        force = accounts.getDataStatistique().getForce();
        degatCrit = accounts.getDataStatistique().getDegatCritique();
        attackSpeed = accounts.getDataStatistique().getAttaqueSpeed();
        tauxCrit = accounts.getDataStatistique().getTauxCritique();

    }

    @Override
    public void run() {
        int manaBonus = 0;
        int vieBonus = 0;
        int speedBonus = 0;
        int defenseBonus = 0;
        int forceBonus = 0;
        int degatCritBonus = 0;
        int attackSpeedBonus = 0;
        int tauxCritBonus = 0;

        if(PlayerUtils.hasItemInHand(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getItemInHand());
            vieBonus += ItemManager.getVie(player.getInventory().getItemInHand());
            speedBonus += ItemManager.getSpeed(player.getInventory().getItemInHand());
            defenseBonus += ItemManager.getDefense(player.getInventory().getItemInHand());
            forceBonus += ItemManager.getForce(player.getInventory().getItemInHand());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getItemInHand());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getItemInHand());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getItemInHand());
        }

        if(PlayerUtils.hasItemInOffHand(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getItemInOffHand());
            vieBonus += ItemManager.getVie(player.getInventory().getItemInOffHand());
            speedBonus += ItemManager.getSpeed(player.getInventory().getItemInOffHand());
            defenseBonus += ItemManager.getDefense(player.getInventory().getItemInOffHand());
            forceBonus += ItemManager.getForce(player.getInventory().getItemInOffHand());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getItemInOffHand());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getItemInOffHand());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getItemInOffHand());
        }

        if(PlayerUtils.hasHelmet(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getHelmet());
            vieBonus += ItemManager.getVie(player.getInventory().getHelmet());
            speedBonus += ItemManager.getSpeed(player.getInventory().getHelmet());
            defenseBonus += ItemManager.getDefense(player.getInventory().getHelmet());
            forceBonus += ItemManager.getForce(player.getInventory().getHelmet());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getHelmet());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getHelmet());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getHelmet());
        }

        if(PlayerUtils.hasChestplate(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getChestplate());
            vieBonus += ItemManager.getVie(player.getInventory().getChestplate());
            speedBonus += ItemManager.getSpeed(player.getInventory().getChestplate());
            defenseBonus += ItemManager.getDefense(player.getInventory().getChestplate());
            forceBonus += ItemManager.getForce(player.getInventory().getChestplate());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getChestplate());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getChestplate());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getChestplate());
        }

        if(PlayerUtils.hasLeggings(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getLeggings());
            vieBonus += ItemManager.getVie(player.getInventory().getLeggings());
            speedBonus += ItemManager.getSpeed(player.getInventory().getLeggings());
            defenseBonus += ItemManager.getDefense(player.getInventory().getLeggings());
            forceBonus += ItemManager.getForce(player.getInventory().getLeggings());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getLeggings());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getLeggings());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getLeggings());
        }

        if(PlayerUtils.hasBoots(player)){
            manaBonus += ItemManager.getMana(player.getInventory().getBoots());
            vieBonus += ItemManager.getVie(player.getInventory().getBoots());
            speedBonus += ItemManager.getSpeed(player.getInventory().getBoots());
            defenseBonus += ItemManager.getDefense(player.getInventory().getBoots());
            forceBonus += ItemManager.getForce(player.getInventory().getBoots());
            degatCritBonus += ItemManager.getDegatCrit(player.getInventory().getBoots());
            attackSpeedBonus += ItemManager.getAttackSpeed(player.getInventory().getBoots());
            tauxCritBonus += ItemManager.getTauxCrit(player.getInventory().getBoots());
        }

        accounts.getDataStatistique().setMaxMana(mana + manaBonus);
        accounts.getDataStatistique().setVie(vie + vieBonus);
        accounts.getDataStatistique().setSpeed(speed + speedBonus);
        accounts.getDataStatistique().setDefense(defense + defenseBonus);
        accounts.getDataStatistique().setForce(force + forceBonus);
        accounts.getDataStatistique().setDegatCritique(degatCrit + degatCritBonus);
        accounts.getDataStatistique().setAttaqueSpeed(attackSpeed + attackSpeedBonus);
        accounts.getDataStatistique().setTauxCritique(tauxCrit + tauxCritBonus);
    }


    public int getMana() {
        return mana;
    }

    public int getVie() {
        return vie;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDefense() {
        return defense;
    }

    public int getForce() {
        return force;
    }

    public int getDegatCrit() {
        return degatCrit;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public int getTauxCrit() {
        return tauxCrit;
    }

    public Accounts getAccounts() {
        return accounts;
    }
}
