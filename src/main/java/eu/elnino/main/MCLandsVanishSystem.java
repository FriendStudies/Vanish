package eu.elnino.main;
import eu.elnino.commands.*;
import eu.elnino.listener.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import sun.security.provider.Sun;

public class MCLandsVanishSystem extends JavaPlugin {

    private static MCLandsVanishSystem instance;
    String prefix;

    @Override
    public void onEnable() {
        register();
        instance = this;

        prefix = "§8[§9§lMCLands§8] §7";

    }

    private void register() {
    }
}
