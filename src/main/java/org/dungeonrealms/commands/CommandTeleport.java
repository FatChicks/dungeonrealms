package org.dungeonrealms.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dungeonrealms.game.world.Area;
import org.dungeonrealms.game.world.Settlements;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class CommandTeleport implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length > 0) {
            StringBuilder builder = new StringBuilder();

            for (String arg : args) {
                builder.append(arg).append(" ");
            }

            String name = builder.toString().trim();

            Settlements settlements = Settlements.getSettlementByName(name);

            if (settlements != null) {
                Area area = settlements.getArea();
                if (area != null) {
                    player.teleport(area.getLocation(-20));
                    player.sendMessage(ChatColor.GREEN.toString() + ChatColor.BOLD + "You teleported to " + ChatColor.AQUA.toString() + ChatColor.BOLD + settlements.getName());
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "I don't understand that location.");
                return true;
            }

        } else {
            player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "You didn't specify a location? /teleport <area name>");
            return true;
        }

        return false;
    }
}
