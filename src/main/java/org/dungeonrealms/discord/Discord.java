package org.dungeonrealms.discord;

import net.dv8tion.jda.JDA;
import net.dv8tion.jda.JDABuilder;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.discord.commands.DiscordCommand;
import org.dungeonrealms.discord.commands.DiscordCommandStats;
import org.dungeonrealms.discord.listeners.MessageListener;

import javax.security.auth.login.LoginException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 8/7/2016.
 */
public class Discord {

    private static Logger log = Logger.getLogger(Discord.class.getName());

    private static final String TOKEN = DungeonRealms.getInstance().getConfig().getString("discord.token");
    private static List<DiscordCommand> commands = new ArrayList<>();

    /**
     * Connects DungeonBot to Discord, for functionality.
     */
    public static void init() {
        log.log(Level.INFO, "[Discord] Connecting with Discord Server ...");
        try {
            new JDABuilder()
                    .setBotToken(TOKEN)
                    .addListener(new MessageListener())
                    .buildBlocking();
        } catch (IllegalArgumentException e) {
            System.out.println("The config was not populated. Please enter a bot token.");
        } catch (LoginException e) {
            System.out.println("The provided bot token was incorrect. Please provide valid details.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        registerCommand(new DiscordCommandStats());
    }

    /**
     * @param command Register a DiscordCommand.
     */
    private static void registerCommand(DiscordCommand command) {
        log.log(Level.INFO, "[Discord] Registering Command: {0} ... OKAY", command.getName());
        commands.add(command);
    }

    /**
     * @param par1 The command. I.E !help
     * @return The corresponding Discord Command to that command.
     */
    public static DiscordCommand getCommand(String par1) {
        for (DiscordCommand c : commands) {
            if (c.getName().equalsIgnoreCase(par1)) {
                return c;
            }
        }
        return null;
    }

}
