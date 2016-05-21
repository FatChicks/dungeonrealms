package org.dungeonrealms.game.world.living;

import org.bukkit.Location;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
interface Spawnable {

    //Experimental Radius Spawning.
    int getSpawnRadius();

    Location getSpawnLocation();

}
