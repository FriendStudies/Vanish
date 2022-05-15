package eu.elnino.main;
import eu.elnino.commands.*;
import eu.elnino.listener.PlayerListener;
import eu.elnino.util.AdvancedLicense;
import eu.elnino.util.Config;
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

        Config.setDefaults();

        if(!new AdvancedLicense(Config.getString("LicenseSystem.License"), "http://45.142.114.159/license/verify.php", this).register()) return;

        register();
        instance = this;

        prefix = "§8[§9§lMCLands§8] §7";

        Bukkit.getConsoleSender().sendMessage("Das Plugin wurde aktiviert.");
        Bukkit.getConsoleSender().sendMessage("Made by elNino0916 with <3");

    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("Das Plugin wurde deaktiviert.");
        Bukkit.getConsoleSender().sendMessage("Auf Wiedersehen!");
    }

    public void register() {
        getCommand("vanish").setExecutor(new VanishCommand());

        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
    }

}
