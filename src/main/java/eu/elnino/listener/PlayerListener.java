package eu.elnino.listener;

import com.avaje.ebean.annotation.Encrypted;
import eu.elnino.main.MCLandsVanishSystem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        for (Player v : Bukkit.getOnlinePlayers()){
            if (MCLandsVanishSystem.vanishedPlayers.contains(v.getName())){
                if (!event.getPlayer().hasPermission("mclands.vanish.show")) {
                    event.getPlayer().hidePlayer(v);
                } else {
                    event.getPlayer().showPlayer(v);
                    event.getPlayer().sendMessage("§8-----------------");
                    event.getPlayer().sendMessage("§7Aktuell sind §a" + MCLandsVanishSystem.vanishedPlayers.size() + " §7Spieler im Vanish§8: ");
                    event.getPlayer().sendMessage("§9" + MCLandsVanishSystem.vanishedPlayers.toString());
                    event.getPlayer().sendMessage("§8-----------------");
                    return;
                }
            }
        }
    }
}
