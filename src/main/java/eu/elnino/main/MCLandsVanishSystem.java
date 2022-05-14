package eu.elnino.main;
import eu.elnino.commands.*;
import eu.elnino.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.provider.Sun;

import java.util.ArrayList;

public class MCLandsVanishSystem extends JavaPlugin {

    private static MCLandsVanishSystem instance;
    String prefix;
    public static ArrayList<String> vanishedPlayers = new ArrayList<>();

    @Override
    public void onEnable() {
        register();
        instance = this;

        prefix = "§8[§9§lMCLands§8] §7";

        Bukkit.getConsoleSender().sendMessage("Das Plugin wurde aktiviert.");
        Bukkit.getConsoleSender().sendMessage("Made by elNino0916 with <3");

    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("Das Plugin wird deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("Sucess");
    }

    public void register() {
        getCommand("vanish").setExecutor(new VanishCommand());
    }

}
