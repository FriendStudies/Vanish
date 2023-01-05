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

        if (!(commandSender instanceof Player)){
            commandSender.sendMessage("§cThe console cant use the vanish command.");
            return false;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("vanish.vanish")){

            player.sendMessage("§cYou do not have permission to use that command.");

            return true;
        }

        if (args.length == 1) {

            Player target = (Player) Bukkit.getPlayer(args[0]);

            if (MCLandsVanishSystem.vanishedPlayers.contains(target.getName())) {

                target.sendMessage("§7You are not vanished anymore.§8.");
                player.sendMessage("§7The player §9" + target.getName() + " §7is no longer vanished§8.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("vanish.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7The player §9" + target.getName() + " §7is no longer vanished.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.remove(target.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.showPlayer(target);
                }

            } else {

                target.sendMessage("§7You are now vanished.");
                player.sendMessage("§7The user §9" + target.getName() + " §7is now vanished.§8.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("vanish.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7The player §9" + target.getName() + " §7is vanished.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.add(target.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    if (!onlinePlayers.hasPermission("vanish.vanish.show")) {
                        onlinePlayers.hidePlayer(target);
                    }
                }
            }

        } else {
            if (MCLandsVanishSystem.vanishedPlayers.contains(player.getName())) {

                player.sendMessage("§7You are not vanished anymore.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("vanish.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7The player §9" + player.getName() + " §7is not vanished anymore.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.remove(player.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    onlinePlayers.showPlayer(player);
                }

            } else {

                player.sendMessage("§7You are now vanished.");

                for (Player perm : Bukkit.getOnlinePlayers()) {
                    if (perm.hasPermission("vanish.vanish.notify")) {
                        if (perm.getName() != player.getName()) {
                            perm.sendMessage("§7The player §9" + player.getName() + " §7is now vanished.");
                        }
                    }
                }

                MCLandsVanishSystem.vanishedPlayers.add(player.getName());

                for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                    if (!onlinePlayers.hasPermission("vanish.vanish.show")) {
                        onlinePlayers.hidePlayer(player);
                    }
                }
            }
        }

        return false;
    }
}
