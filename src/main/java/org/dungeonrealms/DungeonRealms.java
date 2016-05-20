package org.dungeonrealms;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dungeonrealms.bungee.listener.BungeeListener;
import org.dungeonrealms.commands.CommandDungeonRealms;
import org.dungeonrealms.database.NetCache;
import org.dungeonrealms.database.RedisConnection;
import org.dungeonrealms.listeners.PreconditionsEvents;

import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class DungeonRealms extends JavaPlugin {

    private static final Logger log = Logger.getLogger(DungeonRealms.class.getName());
    private static NetCache netCache;
    private static DungeonRealms instance;

    public void onEnable() {
        log.log(Level.INFO, "Starting up ...");

        instance = this;

        saveDefaultConfig();

        //Connect to database.
        try {
            netCache = new NetCache(new RedisConnection(getConfig().getString("redis.password"), getConfig().getString("redis.host"), getConfig().getInt("redis.port"), 5, TimeUnit.HOURS));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            Bukkit.shutdown();
            return;
        }

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        //Custom Bungee Messaging Channels.
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "DungeonRealms");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "DungeonRealms", new BungeeListener());

        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new PreconditionsEvents(), this);

        getCommand("dungeonrealms").setExecutor(new CommandDungeonRealms());

    }

    public void onDisable() {
        saveConfig();
    }

    public static DungeonRealms getInstance() {
        return instance;
    }

    public static NetCache getNet() {
        return netCache;
    }
}
