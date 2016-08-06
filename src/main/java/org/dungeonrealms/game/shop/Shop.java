package org.dungeonrealms.game.shop;

import org.bukkit.Location;
import org.dungeonrealms.game.player.GamePlayer;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Shop {

    private GamePlayer gamePlayer;
    private String name;
    private long created;
    private Location location;

    //todo: storage

    /**
     * @param gamePlayer The player's GamePlayer object.
     * @param name       The name of the shop. (Name above shop)
     * @param created    The timestamp when the shop was placed.
     * @param location   The location of the singular chest.
     */
    public Shop(GamePlayer gamePlayer, String name, long created, Location location) {
        this.gamePlayer = gamePlayer;
        this.name = name;
        this.created = created;
        this.location = location;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public String getName() {
        return name;
    }

    public long getCreated() {
        return created;
    }

    public Location getLocation() {
        return location;
    }
}
