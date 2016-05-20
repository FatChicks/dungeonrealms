package org.dungeonrealms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dungeonrealms.game.data.Data;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class CommandDungeonRealms implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player))return false;

        Player player = (Player)sender;

        Data.test(player);

        return true;
    }
}
