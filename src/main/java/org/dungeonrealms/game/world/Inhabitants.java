package org.dungeonrealms.game.world;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
enum Inhabitants {

    //TODO: Referential Creature Types

    HUMAN_TRIBES(-1, "Human Tribes"),

    BANDIT(0, "Bandit"),
    TROLL(1, "Troll"),
    GOBLIN(2, "Goblin"),
    DAEMON(3, "Daemon"),
    ZOMBIE(4, "Zombie"),
    SKELETON(5, "Skeleton"),
    NAGA(6, "Naga"),

    LIZARDMEN(7, "Lizardmen"),
    TRIPOLI_SOLDIER(8, "Tripoli Soldier"),

    WORKERS(9, "Workers")
    ;

    private int id;
    private String name;

    Inhabitants(int id, String name) {
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