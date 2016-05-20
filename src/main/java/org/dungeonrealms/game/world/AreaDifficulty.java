package org.dungeonrealms.game.world;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
enum AreaDifficulty {

    LOW(0, "Low"),
    MEDIUM(1, "Medium"),
    HIGH(2, "High");

    private int id;
    private String name;

    AreaDifficulty(int id, String name) {
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