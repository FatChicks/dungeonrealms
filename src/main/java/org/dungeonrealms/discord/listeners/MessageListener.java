package org.dungeonrealms.discord.listeners;

import net.dv8tion.jda.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.dungeonrealms.discord.Discord;
import org.dungeonrealms.discord.commands.DiscordCommand;
import org.dungeonrealms.game.chat.ChatType;


/**
 * Created by Dr. Nick Doran on 8/7/2016.
 */
public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String channelName = event.getChannel().getName();
        String player = event.getAuthorName();
        String message = event.getMessage().getContent();

        System.out.println(message);

        switch (channelName) {
            case "trade":
                Bukkit.broadcastMessage(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "DISCORD "
                        + ChatType.TRADE.getTag()
                        + ChatColor.AQUA + player
                        + ChatColor.GRAY + ": "
                        + ChatColor.WHITE + message);
            case "general":
                if (!message.startsWith("!")) {
                    return;
                }
                String[] lines = message.split(" ");
                if (lines.length == 2) {
                    //Good command argument length.

                    String command = lines[0].split("!")[1];
                    String argument = lines[0].split(" ")[1];

                    DiscordCommand discordCommand = Discord.getCommand(command);

                    if (discordCommand == null) {
                        event.getChannel().sendMessage("I do not recognize that as a valid command.");
                    }

                }
                break;
        }

    }

}
