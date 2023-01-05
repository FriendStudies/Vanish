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

        prefix = "§8[§9§lVanish§8] §7";
		Bukkit.getConsoleSender().sendMessage("--- VANISH PLUGIN ---");
        Bukkit.getConsoleSender().sendMessage("Welcome!");
        Bukkit.getConsoleSender().sendMessage("Made by elNino0916 and JxstColin with <3");
		Bukkit.getConsoleSender().sendMessage("---------------------");

    }

    @Override
    public void onDisable(){
		Bukkit.getConsoleSender().sendMessage("§cStopping vanish plugin...");
		Bukkit.getConsoleSender().sendMessage("--- VANISH PLUGIN ---");
        Bukkit.getConsoleSender().sendMessage("Plugin disabled, goodbye!");
		Bukkit.getConsoleSender().sendMessage("---------------------");
    }

    public void register() {
        getCommand("vanish").setExecutor(new VanishCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

}
