package org.dungeonrealms.game.player;

import java.util.UUID;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public class FakePlayer {

    private int id;
    private UUID uuid;
    private String name;

    public FakePlayer(int id, UUID uuid, String name) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }
}
