package eu.elnino.commands;

import eu.elnino.main.MCLandsVanishSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (commandSender instanceof ConsoleCommandSender){
            commandSender.sendMessage("§cDas können nur Spieler ausführen. Wie soll die Konsole verschwinden?");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("mclands.vanish")){

            player.sendMessage("§cDazu bist du nicht berechtigt.");

            return true;
        }

        if (args.length == 1) {

            Player target = (Player) Bukkit.getPlayer(args[0]);

            if (MCLandsVanishSystem.vanishedPlayers.contains(target.getName())) {

                target.sendMessage("§7Du bist nun nicht mehr im Vanish§8.");
                player.sendMessage("§7Der Spieler §9" + target.getName() + " §7ist nun nicht mehr im Vanish§8.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("mclands.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7Der Spieler §9" + target.getName() + " §7ist nun nicht mehr im Vanish.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.remove(target.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.showPlayer(target);
                }

            } else {

                target.sendMessage("§7Du bist nun im Vanish.");
                player.sendMessage("§7Der Spieler §9" + target.getName() + " §7ist nun im Vanish§8.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("mclands.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7Der Spieler §9" + target.getName() + " §7ist nun im Vanish.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.add(target.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    if (!onlinePlayers.hasPermission("mclands.vanish.show")) {
                        onlinePlayers.hidePlayer(target);
                    }
                }
            }

        } else {
            if (MCLandsVanishSystem.vanishedPlayers.contains(player.getName())) {

                player.sendMessage("§7Du bist nun nicht mehr im Vanish.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("mclands.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7Der Spieler §9" + player.getName() + " §7ist nun nicht mehr im Vanish.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.remove(player.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.showPlayer(player);
                }

            } else {

                player.sendMessage("§7Du bist nun im Vanish.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("mclands.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7Der Spieler §9" + player.getName() + " §7ist nun im Vanish.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.add(player.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    if (!onlinePlayers.hasPermission("mclands.vanish.show")) {
                        onlinePlayers.hidePlayer(player);
                    }
                }
            }
        }

        return false;
    }
}
