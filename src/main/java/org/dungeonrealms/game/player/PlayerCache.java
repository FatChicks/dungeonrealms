package org.dungeonrealms.game.player;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class PlayerCache {

    private String world;
    private double x, y, z;
    private float yaw, pitch;

    /**
     * @param world The world the player's in.
     * @param x     The player's X location.
     * @param y     The player's Y location.
     * @param z     The player's Z location.
     * @param yaw   The player's yaw location.
     * @param pitch The player's pitch location.
     */
    public PlayerCache(String world, double x, double y, double z, float yaw, float pitch) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public String getWorld() {
        return world;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    @Override
    public String toString() {
        return "PlayerCache{" +
                "world='" + world + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", yaw=" + yaw +
                ", pitch=" + pitch +
                '}';
    }
}
