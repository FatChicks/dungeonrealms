package org.dungeonrealms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.dungeonrealms.bungee.listener.BungeeListener;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class DungeonRealms extends JavaPlugin {

    private static final Logger log = Logger.getLogger(DungeonRealms.class.getName());
    private static DungeonRealms instance;

    public void onEnable() {
        log.log(Level.INFO, "Starting up ...");

        instance = this;

        saveDefaultConfig();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        //Custom Bungee Messaging Channels.
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "DungeonRealms");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "DungeonRealms", new BungeeListener());

    }

    public void onDisable() {
        saveConfig();
    }

    public static DungeonRealms getInstance() {
        return instance;
    }
}
