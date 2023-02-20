package fr.redwoub.mydoriarpg.commands;


import fr.redwoub.mydoriarpg.Main;
import fr.redwoub.mydoriarpg.inventory.InventoryList;
import fr.redwoub.mydoriarpg.managers.FriendsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class Friends implements CommandExecutor {
    private final String prefix = ChatColor.translateAlternateColorCodes('&', Main.getInstance().getConfig().getString("messages.friends-prefix"));

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;

        if(args.length == 0){
            player.sendMessage(prefix + "------------------------------------");
            player.sendMessage(prefix + "§e/friend §b- §7Afficher l'aide");
            player.sendMessage(prefix + "§e/friend add §b- §7Ajouter un amis");
            player.sendMessage(prefix + "§e/friend remove §b- §7Retirer un amis");
            player.sendMessage(prefix + "§e/friend accept §b- §7Accepter une demande");
            player.sendMessage(prefix + "§e/friend deny §b- §7Refuser une demande");
            player.sendMessage(prefix + "§e/friend list §b- §7Voir ses amis sous forme de message");
            player.sendMessage(prefix + "§e/friend gui §b- §7 voir ses amis sous forme d'inventaire");
            player.sendMessage(prefix + "§e/friend msg §b- §7 envoyer un message à un ami");
            player.sendMessage(prefix + "------------------------------------");
            return false;
        }

        if(args[0].equalsIgnoreCase("add")){
            if(args.length != 2){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend add <Pseudo>");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            if(args[1].equalsIgnoreCase(player.getName())){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cVous ne pouvez pas vous ajouter en ami.");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            if(Bukkit.getPlayer(args[1]) == null){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cCe joueur n'est pas connecté");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            //player1 = joueur demandé
            Player player1 = Bukkit.getPlayer(args[1]);
            Main.getInstance().getAccount(player1).ifPresent(accounts -> {
                YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts.getOptionFriends());
                if(data.get("reciveRequest").equals("OFF")){
                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§cCe joueur a désactivé les demandes d'amis");
                    player.sendMessage(prefix + "------------------------------------");

                } else {
                    FriendsManager friendsManager = new FriendsManager(accounts);
                    if(friendsManager.alreadyFriends(player1.getName())){
                        player.sendMessage(prefix + "------------------------------------");
                        player.sendMessage(prefix + "§cVous êtes déjà ami avec §7: §b" + player1.getName());
                        player.sendMessage(prefix + "------------------------------------");
                        return;
                    }

                    if(Main.getInstance().getFriendsRequest().containsKey(player1)){
                        player.sendMessage(prefix + "------------------------------------");
                        player.sendMessage(prefix + "§cVous avez déjà envoyé une demande d'ami à §7: §b" + player1.getName());
                        player.sendMessage(prefix + "------------------------------------");
                        return;
                    }

                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§aVous avez envoyé une demande d'ami à §7: §b" + player1.getName());
                    player.sendMessage(prefix + "§b" + player1.getName() + "§a à 5 minutes pour accepter votre demande");
                    player.sendMessage(prefix + "------------------------------------");


                    player1.sendMessage(prefix + "------------------------------------");
                    player1.sendMessage(prefix + "§b" + player.getName() + " §avous à demandé en ami");
                    player1.sendMessage(prefix + "§aVous avez 5 minutes pour accepter sa demande");
                    player1.sendMessage(prefix + "------------------------------------");

                    Main.getInstance().getFriendsRequest().put(player1, player);
                }
            });

            Bukkit.getScheduler().runTaskLater(Main.getInstance(), () -> {
                if(Main.getInstance().getFriendsRequest().containsKey(player)){
                    Main.getInstance().getFriendsRequest().remove(player);

                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§cLa demande d'ami pour : §b" + player1.getName() + "§c a expiré");
                    player.sendMessage(prefix + "------------------------------------");

                    player1.sendMessage(prefix + "------------------------------------");
                    player1.sendMessage(prefix + "§cLa demande d'ami que vous a envoyé §b" + player.getName() + "§c a expiré");
                    player1.sendMessage(prefix + "------------------------------------");
                }
            }, 300 * 20L);
        } else if(args[0].equalsIgnoreCase("remove")){
            if(args.length != 2){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend remove <Pseudo>");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            Main.getInstance().getAccount(player).ifPresent(accounts -> {
                FriendsManager friendsManager = new FriendsManager(accounts);

                if(Bukkit.getPlayer(args[1]) == null){
                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§cCe joueur n'est pas connecté");
                    player.sendMessage(prefix + "------------------------------------");

                } else {
                    Player player1 = Bukkit.getPlayer(args[1]);

                    if(!friendsManager.alreadyFriends(player1.getName())){
                        player.sendMessage(prefix + "------------------------------------");
                        player.sendMessage(prefix + "§cVous n'êtes pas ami avec §7: §b" + player1.getName());
                        player.sendMessage(prefix + "------------------------------------");
                        return;
                    }

                    friendsManager.removeFriend(player1.getName());
                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§aVous avez bien retiré §b" + player1.getName() + "§a de votre liste d'amis");
                    player.sendMessage(prefix + "------------------------------------");

                    player1.sendMessage(prefix + "------------------------------------");
                    player1.sendMessage(prefix + "§b" + player.getName() + "§c vous a retiré de sa liste d'amis");
                    player1.sendMessage(prefix + "------------------------------------");
                }
            });
        } else if(args[0].equalsIgnoreCase("accept")){
            if(args.length != 2){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend accept <Pseudo>");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            if(Bukkit.getPlayer(args[1]) == null){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cCe joueur n'est pas connecté");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            Player player1 = Bukkit.getPlayer(args[1]);
            if(Main.getInstance().getFriendsRequest().containsValue(player1)){
                Main.getInstance().getAccount(player).ifPresent(accounts -> {
                    FriendsManager friendsManager = new FriendsManager(accounts);
                    friendsManager.addFriend(player1);

                    player.sendMessage(prefix + "------------------------------------");
                    player.sendMessage(prefix + "§aVous avez bien accepté §b" + player1.getName() + "§a en ami.");
                    player.sendMessage(prefix + "------------------------------------");

                    player1.sendMessage(prefix + "------------------------------------");
                    player1.sendMessage(prefix + "§b" + player.getName() + "§a a accepté votre demande d'ami");
                    player1.sendMessage(prefix + "------------------------------------");
                    Main.getInstance().getFriendsRequest().remove(player1);
                });
            } else {
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§b" + player1.getName() + "§c ne vous a pas demandé en ami");
                player.sendMessage(prefix + "------------------------------------");
            }
        } else if(args[0].equalsIgnoreCase("deny")){
            if(args.length != 2){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend deny <Pseudo>");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            if(Bukkit.getPlayer(args[1]) == null){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cCe joueur n'est pas connecté");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            Player player1 = Bukkit.getPlayer(args[1]);
            if(Main.getInstance().getFriendsRequest().containsKey(player1)){
                Main.getInstance().getFriendsRequest().remove(player);

                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§aVous avez bien refusé la demande d'ami de §b" + player1.getName());
                player.sendMessage(prefix + "------------------------------------");

                player1.sendMessage(prefix + "------------------------------------");
                player1.sendMessage(prefix + "§b" + player.getName() + "§c a refusé votre demande d'ami");
                player1.sendMessage(prefix + "------------------------------------");
            } else {
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cVous n'avez pas de demande d'ami de la part de : §b" + player1.getName());
                player.sendMessage(prefix + "------------------------------------");
            }
        } else if(args[0].equalsIgnoreCase("list")){
            if(args.length != 1){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend list");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            Main.getInstance().getAccount(player).ifPresent(accounts -> {
                FriendsManager friendsManager = new FriendsManager(accounts);
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§aVoici vos amis §7: ");

                for(String f : friendsManager.listFriend()){
                    if(f.equals("Auncun ami")){
                        player.sendMessage(prefix + ChatColor.RED + "Vous n'avez actuellement pas d'ami");
                        player.sendMessage(prefix + "------------------------------------");
                        return;
                    }
                    player.sendMessage(prefix + ChatColor.AQUA + f + " " + friendsManager.generateConnetedMessage(f));
                }
                player.sendMessage(prefix + "------------------------------------");

            });
        } else if(args[0].equalsIgnoreCase("gui")){
            if(args.length != 1){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend gui");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            player.openInventory(new InventoryList().friendsMenu(player));

        } else if(args[0].equalsIgnoreCase("msg")){
            if(args.length < 3){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend msg <Pseudo | all> <Message>");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            if(args[1].equalsIgnoreCase("all")){
                Main.getInstance().getAccount(player).ifPresent(accounts -> {
                    FriendsManager friendsManager = new FriendsManager(accounts);
                    for(String f : friendsManager.listFriend()){
                        if(Bukkit.getPlayer(f) != null){
                            Main.getInstance().getAccount(Bukkit.getPlayer(f)).ifPresent(accounts1 -> {
                                YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts1.getOptionFriends());
                                if(data.getString("reciveMessages").equalsIgnoreCase("OFF")) return;

                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i = 2; i < args.length; i++){
                                    stringBuilder.append(args[i]).append(" ");
                                }
                                Bukkit.getPlayer(f).sendMessage(prefix + ChatColor.YELLOW + player.getDisplayName() + "§8 : §7" + stringBuilder.toString());
                                player.sendMessage(prefix + "§aEnvoyé à tous vos amis §8: §7" + stringBuilder.toString());
                            });
                        }
                    }
                });
            } else {
                Main.getInstance().getAccount(player).ifPresent(accounts -> {
                    FriendsManager friendsManager = new FriendsManager(accounts);
                    if(Bukkit.getPlayer(args[1]) != null){
                        if(friendsManager.alreadyFriends(Bukkit.getPlayer(args[1]).getName())){
                            Main.getInstance().getAccount(Bukkit.getPlayer(args[1])).ifPresent(accounts1 -> {
                                YamlConfiguration data = YamlConfiguration.loadConfiguration(accounts1.getOptionFriends());
                                if(data.getString("reciveMessages").equalsIgnoreCase("OFF")){
                                    player.sendMessage(prefix + "------------------------------------");
                                    player.sendMessage(prefix + ChatColor.AQUA + Bukkit.getPlayer(args[1]).getName() + " §ca désactivé les messages venant des amis");
                                    player.sendMessage(prefix + "------------------------------------");
                                    return;
                                }

                                StringBuilder stringBuilder = new StringBuilder();
                                for(int i = 2; i < args.length; i++){
                                    stringBuilder.append(args[i]).append(" ");
                                }
                                player.sendMessage(prefix + "§aEnvoyé à §e" + Bukkit.getPlayer(args[1]).getDisplayName() + "§8 : §7" + stringBuilder.toString());
                                Bukkit.getPlayer(args[1]).sendMessage(prefix + player.getDisplayName() + "§a vous a envoyé §8: §7" + stringBuilder.toString());
                            });
                        } else {
                            player.sendMessage(prefix + "------------------------------------");
                            player.sendMessage(prefix + "§cVous n'êtes pas ami avec §7: §b" + Bukkit.getPlayer(args[1]).getName());
                            player.sendMessage(prefix + "------------------------------------");
                        }
                    } else {
                        player.sendMessage(prefix + "------------------------------------");
                        player.sendMessage(prefix + "§cCe joueur n'est pas connecté");
                        player.sendMessage(prefix + "------------------------------------");
                    }
                });
            }
        } else if(args[0].equalsIgnoreCase("settings")) {
            if(args.length != 1){
                player.sendMessage(prefix + "------------------------------------");
                player.sendMessage(prefix + "§cMauvaise utilisation §7: §e/friend settings");
                player.sendMessage(prefix + "------------------------------------");
                return false;
            }

            player.openInventory(new InventoryList().optionFriends(player));
        } else {
            player.sendMessage(prefix + "------------------------------------");
            player.sendMessage(prefix + "§e/friend §b- §7Afficher l'aide");
            player.sendMessage(prefix + "§e/friend add §b- §7Ajouter un amis");
            player.sendMessage(prefix + "§e/friend remove §b- §7Retirer un amis");
            player.sendMessage(prefix + "§e/friend accept §b- §7Accepter une demande");
            player.sendMessage(prefix + "§e/friend deny §b- §7Refuser une demande");
            player.sendMessage(prefix + "§e/friend list §b- §7Voir ses amis sous forme de message");
            player.sendMessage(prefix + "§e/friend gui §b- §7 Voir ses amis sous forme d'inventaire");
            player.sendMessage(prefix + "§e/friend msg §b- §7 Envoyer un message à un ami");
            player.sendMessage(prefix + "§e/friend settings §b- §7Ouvrir le menu d'option");
            player.sendMessage(prefix + "------------------------------------");
        }
        return false;
    }
}
