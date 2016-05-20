package org.dungeonrealms.game.world;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
enum PrimaryZone {

    WILDERNESS(0, "Wilderness"),
    CHAOTIC(1, "Chaotic"),
    SAFEZONE(2, "SafeZone"),
    MIXED(3, "Mixed")
    ;

    private int id;
    private String name;

    PrimaryZone(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}