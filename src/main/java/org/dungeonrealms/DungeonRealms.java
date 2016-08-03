package org.dungeonrealms;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.dungeonrealms.api.bar.BarUtils;
import org.dungeonrealms.bungee.listener.BungeeListener;
import org.dungeonrealms.commands.CommandDungeonRealms;
import org.dungeonrealms.commands.CommandHistory;
import org.dungeonrealms.commands.CommandNotify;
import org.dungeonrealms.commands.CommandTeleport;
import org.dungeonrealms.database.mysql.Database;
import org.dungeonrealms.database.redis.NetCache;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.listeners.PlayerEvents;
import org.dungeonrealms.listeners.TempEvents;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class DungeonRealms extends JavaPlugin {

    private static final Logger log = Logger.getLogger(DungeonRealms.class.getName());
    private static NetCache netCache;
    private static DungeonRealms instance;

    private String nmsVers;

    public void onEnable() {
        log.log(Level.INFO, "Starting up ...");

        instance = this;

        saveDefaultConfig();

        //Connect to database.

        Database.getInstance().Connect();
        Game.GetPrerequisites();

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        //Custom Bungee Messaging Channels.
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "DungeonRealms");
        Bukkit.getMessenger().registerIncomingPluginChannel(this, "DungeonRealms", new BungeeListener());

        PluginManager pm = Bukkit.getPluginManager();

        //pm.registerEvents(new PreconditionsEvents(), this);

        //This listener will be removed.
        //Using this to stop actions on 7the map whilst
        //Testing.
        pm.registerEvents(new TempEvents(), this);
        pm.registerEvents(new PlayerEvents(), this);

        getCommand("dungeonrealms").setExecutor(new CommandDungeonRealms());
        getCommand("history").setExecutor(new CommandHistory());
        getCommand("teleport").setExecutor(new CommandTeleport());
        getCommand("notify").setExecutor(new CommandNotify());

        nmsVers = Bukkit.getServer().getClass().getPackage().getName();
        nmsVers = nmsVers.substring(nmsVers.lastIndexOf(".") + 1);

        BarUtils.init();//TODO: Remove

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

    public String getNmsVers() {
        return nmsVers;
    }

    /**
     * @return Get the WorldGuard plugin.
     */
    public static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = DungeonRealms.getInstance().getServer().getPluginManager().getPlugin("WorldGuard");
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }
        return (WorldGuardPlugin) plugin;
    }
}
