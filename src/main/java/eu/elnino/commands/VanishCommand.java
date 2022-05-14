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
            commandSender.sendMessage("§cDas können nur Spieler.");
            return true;
        }

        Player player = (Player) commandSender;

        if (!player.hasPermission("mclands.vanish")){

            player.sendMessage("§cDazu bist du nicht berechtigt.");

            return true;
        }

        if (MCLandsVanishSystem.vanishedPlayers.contains(player.getName())){

            player.sendMessage("// UNVANISHED NACHRICHT");

            MCLandsVanishSystem.vanishedPlayers.remove(player.getName());

            for (Player onlinePlayers : Bukkit.getOnlinePlayers()){
                onlinePlayers.showPlayer(player);
            }

        } else {

            player.sendMessage("// VANISH NACHRICHT");

            MCLandsVanishSystem.vanishedPlayers.add(player.getName());

            for (Player onlinePlayers : Bukkit.getOnlinePlayers()){
                onlinePlayers.hidePlayer(player);
            }
        }

        return false;
    }
}
