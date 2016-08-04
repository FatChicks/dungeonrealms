package org.dungeonrealms.game.guild;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public enum GuildRoll {

    OWNER("owner"),
    MODERATOR("moderator"),
    MEMBER("member");

    private String name;

    GuildRoll(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static GuildRoll getByName(String par1) {
        for (GuildRoll gr : values()) {
            if (gr.getName().equalsIgnoreCase(par1)) {
                return gr;
            }
        }
        return null;
    }
}
