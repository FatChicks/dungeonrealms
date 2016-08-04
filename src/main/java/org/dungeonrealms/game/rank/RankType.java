package org.dungeonrealms.game.rank;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public enum RankType {

    NONE(-1, new String[]{"none"}, ""),
    SUBSCRIBER(0, new String[]{"sub", "subscriber"}, "S"),
    SUBSCRIBER_PLUS(1, new String[]{"sub+", "subscriber_plus"}, "S+"),
    P_MOD(2, new String[]{"pmod", "p_mod"}, "PMOD"),
    GAME_MASTER(3, new String[]{"gm", "game_master"}, "GM"),
    DEVELOPER(4, new String[]{"dev", "developer"}, "DEV"),
    CHRIST_OUR_SAVIOR(5, new String[]{"christ our savior"}, "CHRIST");

    private int id;
    private String[] aliases;
    private String tag;

    RankType(int id, String[] aliases, String tag) {
        this.id = id;
        this.aliases = aliases;
        this.tag = tag;
    }

    public int getId() {
        return id;
    }

    public String[] getAliases() {
        return aliases;
    }

    public String getTag() {
        return tag;
    }

    public static RankType getByName(String rank) {
        for (RankType rt : values()) {
            for (String s : rt.aliases) {
                if (s.equalsIgnoreCase(rank)) {
                    return rt;
                }
            }
        }
        return null;
    }
}
