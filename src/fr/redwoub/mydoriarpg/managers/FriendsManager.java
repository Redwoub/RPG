package fr.redwoub.mydoriarpg.managers;

import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.accounts.Accounts;
import fr.redwoub.mydoriarpg.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FriendsManager {

    private final Accounts accounts;
    private final List<String> friends;

    public FriendsManager(Accounts accounts){
        this.accounts = accounts;
        friends = new ArrayList<>();
        String line;

        try {
            if(accounts.getFriends().exists()){
                BufferedReader reader = new BufferedReader(new FileReader(accounts.getFriends()));
                while((line = reader.readLine()) != null){
                    friends.add(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean alreadyFriends(String player){
        return friends.contains(player);
    }

    //accounts = joueur qui demande
    //accounts1 = joueur qui accept ou pas

    private void removeLineFromFile(String lineToRemove, File file) throws IOException{
        //Reading File Content and storing it to a StringBuilder variable ( skips lineToRemove)
        StringBuilder sb = new StringBuilder();
        try (Scanner sc = new Scanner(file)) {
            String currentLine;
            while(sc.hasNext()){
                currentLine = sc.nextLine();
                if(currentLine.equals(lineToRemove)){
                    continue; //skips lineToRemove
                }
                sb.append(currentLine).append("\n");
            }
        }
        //Delete File Content
        PrintWriter pw = new PrintWriter(file);
        pw.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.append(sb.toString());
        writer.close();
    }

    public void addFriend(Player player){
        if(alreadyFriends(player.getName())) return;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(accounts.getFriends()));
            friends.add(player.getName());
            writer.write(player.getName());
            writer.close();

            Main.getInstance().getAccount(player).ifPresent(accounts1 -> {
                try {
                    BufferedWriter wr = new BufferedWriter(new FileWriter(accounts1.getFriends()));
                    wr.write(Bukkit.getPlayer(accounts.getUUID()).getName());
                    wr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void removeFriend(String player){
        try {
            if(Bukkit.getPlayer(ChatColor.GREEN + player) != null){
                removeLineFromFile(ChatColor.stripColor(player), accounts.getFriends());
                Main.getInstance().getAccount(Bukkit.getPlayer(ChatColor.stripColor(player))).ifPresent(accounts1 -> {
                    try {
                        removeLineFromFile(Bukkit.getPlayer(accounts.getUUID()).getName(), accounts1.getFriends());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                friends.remove(ChatColor.stripColor(player));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String[] listFriend(){
        if(friends.size() == 0) {
            String[] a = new String[1];
            a[0] = "Aucun ami";
            return a;
        }
        String[] friend = new String[friends.size()];
        for(int i = 0; i < friends.size(); i++){
            friend[i] = friends.get(i);
        }

        return friend;
    }

    public String generateConnetedMessage(String player){
        if(Bukkit.getPlayer(player) != null){
            return "§aest accuellement connecté.";
        }
        return "§cest accuellement déconnecté";
    }

    public String generateConnectedMessageForHeads(String player){
        if(Bukkit.getPlayer(player) != null){
            return "§aConnecté";
        }

        return "§cDéconnecté";
    }
    private boolean isConnected(String name){
        return Bukkit.getPlayer(name) != null;
    }
    public ItemStack[] generateFriendsHead(){
        YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());

        int i = 0;
        for(String nbrf : listFriend()) i++;
        ItemStack[] heads = new ItemStack[i];
        int nbf = 0;

        for(String f : listFriend()){
            if(f.equals("Aucun ami")){
                ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(Bukkit.getPlayer(accounts.getUUID()).getName()).setName(ChatColor.RED + "Vous n'avez aucun amis").toItemStack();
                heads[nbf] = itemStack;
                return heads;
            }

            if(isConnected(f)){
                ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(f).setName(ChatColor.GREEN + Bukkit.getPlayer(f).getName()).setLore(Arrays.asList(" ", generateConnectedMessageForHeads(f))).toItemStack();
                heads[nbf] = itemStack;
            } else {

                ItemStack itemStack = new ItemBuilder(Material.SKULL_ITEM, 1, (byte) 3).setSkullOwner(f).setName(ChatColor.RED + f).setLore(Arrays.asList(" ", generateConnectedMessageForHeads(f))).toItemStack();
                heads[nbf] = itemStack;
            }

            nbf++;
        }

        return heads;
    }

    public ChatColor generateColorForFriendsOption(String options){
        if(options.equalsIgnoreCase("on")){
            return ChatColor.GREEN;
        } else {
            return ChatColor.RED;
        }
    }

    public void sendJoinNotification(){
        final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.friends-prefix"));
        YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());

        for(String p : listFriend()){
            if(Bukkit.getPlayer(p) != null){
                Player player = Bukkit.getPlayer(p);
                Main.getInstance().getAccount(player).ifPresent(accounts1 -> {
                    YamlConfiguration playerData = YamlConfiguration.loadConfiguration(accounts1.getOptionFriends());
                    if(playerData.getString("reciveJoinNotification").equalsIgnoreCase("OFF")) return;

                    player.sendMessage(prefix + " " + ChatColor.YELLOW + Bukkit.getPlayer(accounts.getUUID()).getName() + " viens de se connecter");
                });
            }
        }
    }

    public void sendLeftNotification(){
        final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.friends-prefix"));
        YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());

        for(String p : listFriend()){
            if(Bukkit.getPlayer(p) != null){
                Player player = Bukkit.getPlayer(p);
                Main.getInstance().getAccount(player).ifPresent(accounts1 -> {
                    YamlConfiguration playerData = YamlConfiguration.loadConfiguration(accounts1.getOptionFriends());
                    if(playerData.getString("reciveJoinNotification").equalsIgnoreCase("OFF")) return;

                    player.sendMessage(prefix + " " + ChatColor.YELLOW + Bukkit.getPlayer(accounts.getUUID()).getName() + " viens de se déconnecter");
                });
            }
        }
    }
}
