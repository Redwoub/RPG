package fr.redwoub.mydoriarpg.managers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

import java.lang.reflect.Field;
import java.util.Base64;

public class HeadsManager {

    public ItemStack generateCustomHead(String name, int amount, String url) {

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        assert skullMeta != null;

        if (url.length() < 16) {

            skullMeta.setOwner(url);

            skullMeta.setDisplayName(name);

            skull.setItemMeta(skullMeta);
            return skull;
        }

        StringBuilder s_url = new StringBuilder();
        s_url.append("https://textures.minecraft.net/texture/").append(url); // We get the texture link.

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null); // We create a GameProfile

        // We get the bytes from the texture in Base64 encoded that comes from the Minecraft-URL.
        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s_url.toString()).getBytes());

        // We set the texture property in the GameProfile.
        gameProfile.getProperties().put("textures", new Property("textures", new String(data)));

        try {

            Field field = skullMeta.getClass().getDeclaredField("profile"); // We get the field profile.

            field.setAccessible(true); // We set as accessible to modify.
            field.set(skullMeta, gameProfile); // We set in the skullMeta the modified GameProfile that we created.

        } catch (Exception e) {
            e.printStackTrace();
        }


        skullMeta.setDisplayName(name); // We set a displayName to the skull
        skull.setItemMeta(skullMeta);

        return skull; //Finally, you have the custom head!

    }

    public ItemStack generateCustomHead(String name, int amount, String url, List<String> lore) {

        ItemStack skull = new ItemStack(Material.SKULL_ITEM, amount, (byte) 3);
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();

        assert skullMeta != null;

        if (url.length() < 16) {

            skullMeta.setOwner(url);

            skullMeta.setDisplayName(name);

            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);
            return skull;
        }

        StringBuilder s_url = new StringBuilder();
        s_url.append("https://textures.minecraft.net/texture/").append(url); // We get the texture link.

        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null); // We create a GameProfile

        // We get the bytes from the texture in Base64 encoded that comes from the Minecraft-URL.
        byte[] data = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", s_url.toString()).getBytes());

        // We set the texture property in the GameProfile.
        gameProfile.getProperties().put("textures", new Property("textures", new String(data)));

        try {

            Field field = skullMeta.getClass().getDeclaredField("profile"); // We get the field profile.

            field.setAccessible(true); // We set as accessible to modify.
            field.set(skullMeta, gameProfile); // We set in the skullMeta the modified GameProfile that we created.

        } catch (Exception e) {
            e.printStackTrace();
        }


        skullMeta.setDisplayName(name); // We set a displayName to the skull
        skullMeta.setLore(lore);
        skull.setItemMeta(skullMeta);

        return skull; //Finally, you have the custom head!

    }

}
