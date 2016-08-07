package org.dungeonrealms.discord.commands;

/**
 * Created by Dr. Nick Doran on 8/7/2016.
 */
public class DiscordCommandStats extends DiscordCommand {

    public DiscordCommandStats() {
        super("stats", "Get a players stats while in discord.", new Object[]{"playerName"});
    }
}
