package org.dungeonrealms;

import net.md_5.bungee.api.plugin.Plugin;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/21/2016.
 */
public class DungeonBungee extends Plugin {

    private static final Logger log = Logger.getLogger(DungeonBungee.class.getName());

    public void onEnable() {
        log.log(Level.INFO, "Starting up...");
    }

    public void onDisable() {

    }

}
