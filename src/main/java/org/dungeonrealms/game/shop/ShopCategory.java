package org.dungeonrealms.game.shop;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public enum ShopCategory {

    T1("t1"),
    T2("t2"),
    T3("t3"),
    T4("t4"),
    T5("t5"),

    AXE("axe"),
    SWORD("sword"),
    POLE_ARM("pole_arm"),
    STAFF("staff"),

    FOOD("food"),
    MISCELLANEOUS("miscellaneous");

    private String name;

    ShopCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
