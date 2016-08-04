package org.dungeonrealms.game.chat;

import org.bukkit.ChatColor;

/**
 * Created by Dr. Nick Doran on 8/4/2016.
 */
public enum ChatType {

    LOCAL("local", ""),
    GLOBAL("global", ChatColor.AQUA + "<" + ChatColor.BOLD + "G" + ChatColor.AQUA + ">" + ChatColor.RESET + " "),
    TRADE("trade", ChatColor.GREEN + "<" + ChatColor.BOLD + "T" + ChatColor.GREEN + ">" + ChatColor.RESET + " ");

    private String name, tag;

    ChatType(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public static ChatType getByName(String par1) {
        for (ChatType ct : values()) {
            if (ct.getName().equalsIgnoreCase(par1)) {
                return ct;
            }
        }
        return null;
    }
}
