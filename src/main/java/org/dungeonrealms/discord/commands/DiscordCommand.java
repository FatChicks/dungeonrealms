package org.dungeonrealms.discord.commands;

/**
 * Created by Dr. Nick Doran on 8/7/2016.
 */
public abstract class DiscordCommand {

    private String name;
    private String description;
    private Object[] arguments;

    public DiscordCommand(String name, String description, Object[] arguments) {
        this.name = name;
        this.description = description;
        this.arguments = arguments;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Object[] getArguments() {
        return arguments;
    }
}
