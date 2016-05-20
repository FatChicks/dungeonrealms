package org.dungeonrealms.game.world.spawning;

import org.bukkit.Location;
import org.dungeonrealms.game.world.living.Creature;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Spawner {

    private Location spawnLocation;
    private Creature creature;

    public Spawner(Location spawnLocation, Creature creature) {
        this.spawnLocation = spawnLocation;
        this.creature = creature;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public Creature getCreature() {
        return creature;
    }
}
