package org.dungeonrealms.game.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class Area {

    private double x, y = -20, z;

    /**
     * @param x The chunk's X
     * @param z The chunk'z Z
     */
    public Area(double x, double z) {
        this.x = x;
        this.z = z;
    }

    public Area(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public double getY() {
        return y;
    }

    /**
     * @param y The Y you want.
     * @return The primary location w/ your Y.
     */
    public Location getLocation(double y) {
        return new Location(Bukkit.getWorlds().get(0), getX(), getY() == -20 ? getY() : Bukkit.getWorlds().get(0).getHighestBlockAt((int) getX(), (int) getZ()).getY(), getZ());
    }
}
