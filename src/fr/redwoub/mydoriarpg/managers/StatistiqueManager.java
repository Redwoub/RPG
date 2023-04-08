package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.DataBonusStatistique;
import fr.redwoub.mydoriarpg.accounts.DataStatistique;
import fr.redwoub.mydoriarpg.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class StatistiqueManager extends BukkitRunnable {

    @Override
    public void run() {
        for(Player players : Bukkit.getOnlinePlayers()){
            Main.getInstance().getAccount(players).ifPresent(account -> {
                DataStatistique stats = account.getDataStatistique();
                DataBonusStatistique bonus = account.getDataBonusStatistique();

                int manaBonus = 0;
                int healthBonus = 0;
                int speedBonus = 0;
                int defenseBonus = 0;
                int forceBonus = 0;
                int degatCritBonus = 0;
                int attackSpeedBonus = 0;
                int tauxCritBonus = 0;

                if(PlayerUtils.hasItemInHand(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getItemInHand());
                    healthBonus += ItemManager.getVie(players.getInventory().getItemInHand());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getItemInHand());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getItemInHand());
                    forceBonus += ItemManager.getForce(players.getInventory().getItemInHand());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getItemInHand());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getItemInHand());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getItemInHand());
                }

                if(PlayerUtils.hasItemInOffHand(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getItemInOffHand());
                    healthBonus += ItemManager.getVie(players.getInventory().getItemInOffHand());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getItemInOffHand());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getItemInOffHand());
                    forceBonus += ItemManager.getForce(players.getInventory().getItemInOffHand());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getItemInOffHand());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getItemInOffHand());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getItemInOffHand());
                }

                if(PlayerUtils.hasHelmet(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getHelmet());
                    healthBonus += ItemManager.getVie(players.getInventory().getHelmet());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getHelmet());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getHelmet());
                    forceBonus += ItemManager.getForce(players.getInventory().getHelmet());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getHelmet());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getHelmet());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getHelmet());
                }

                if(PlayerUtils.hasChestplate(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getChestplate());
                    healthBonus += ItemManager.getVie(players.getInventory().getChestplate());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getChestplate());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getChestplate());
                    forceBonus += ItemManager.getForce(players.getInventory().getChestplate());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getChestplate());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getChestplate());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getChestplate());
                }

                if(PlayerUtils.hasLeggings(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getLeggings());
                    healthBonus += ItemManager.getVie(players.getInventory().getLeggings());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getLeggings());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getLeggings());
                    forceBonus += ItemManager.getForce(players.getInventory().getLeggings());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getLeggings());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getLeggings());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getLeggings());
                }

                if(PlayerUtils.hasBoots(players)){
                    manaBonus += ItemManager.getMana(players.getInventory().getBoots());
                    healthBonus += ItemManager.getVie(players.getInventory().getBoots());
                    speedBonus += ItemManager.getSpeed(players.getInventory().getBoots());
                    defenseBonus += ItemManager.getDefense(players.getInventory().getBoots());
                    forceBonus += ItemManager.getForce(players.getInventory().getBoots());
                    degatCritBonus += ItemManager.getDegatCrit(players.getInventory().getBoots());
                    attackSpeedBonus += ItemManager.getAttackSpeed(players.getInventory().getBoots());
                    tauxCritBonus += ItemManager.getTauxCrit(players.getInventory().getBoots());
                }

                stats.setMaxMana(manaBonus + bonus.getMaxMana());
                stats.setMaxHealth(healthBonus + bonus.getMaxHealth());
                stats.setSpeed(speedBonus + bonus.getSpeed());
                stats.setDefense(defenseBonus + bonus.getDefense());
                stats.setForce(forceBonus + bonus.getForce());
                stats.setDegatCritique(degatCritBonus + bonus.getDegatCritique());
                stats.setTauxCritique(tauxCritBonus + bonus.getTauxCritique());
                stats.setAttaqueSpeed(attackSpeedBonus + bonus.getAttaqueSpeed());
            });
        }
    }
}
