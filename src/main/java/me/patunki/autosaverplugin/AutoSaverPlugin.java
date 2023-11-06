package me.patunki.autosaverplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public final class AutoSaverPlugin extends JavaPlugin implements Listener {

    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(saveRunnable,1,1, TimeUnit.MINUTES);


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){

        System.out.println("[AutoSaverPlugin] Player " + event.getPlayer().getName() + "disconnected. Saving world.");
        Bukkit.dispatchCommand(console,"save-all");


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    Runnable saveRunnable = new Runnable() {
        public void run() {
            System.out.println("[AutoSaverPlugin] Timed autosave triggered. Saving world.");
            Bukkit.dispatchCommand(console,"save-all");

        }
    };
}


