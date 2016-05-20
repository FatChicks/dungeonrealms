package org.dungeonrealms.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.dungeonrealms.api.bar.BarUtils;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class CommandNotify implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length > 0) {
            String name = args[0];

            if (Bukkit.getPlayer(name) != null) {
                BarUtils.sendNotification(Bukkit.getPlayer(name));
                return true;
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Player!");
                return true;
            }
        }

        return false;
    }
}
