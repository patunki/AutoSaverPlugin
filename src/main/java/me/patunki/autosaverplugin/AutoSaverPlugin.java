package me.patunki.autosaverplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class AutoSaverPlugin extends JavaPlugin implements Listener {

    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            System.out.println("[AutoSaverPlugin] Autosave triggered. Saving world.");
            Bukkit.dispatchCommand(console,"save-all");
        },6550,6550);
        System.out.println("[AutoSaverPlugin] Plugin started and will save game every 5 minutes and when a player disconnects.");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

       int players = getServer().getOnlinePlayers().size();
        if (players <= 1) {
            System.out.println("[AutoSaverPlugin] Player " + event.getPlayer().getName() + " is the last player to disconnect. Saving world.");
            Bukkit.dispatchCommand(console,"save-all");
        }

    }

}


