package org.dungeonrealms.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.achievement.GameAchievement;
import org.dungeonrealms.game.data.Data;
import org.dungeonrealms.game.player.GamePlayer;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class CommandDungeonRealms implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (args.length > 0) {
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("achievement")) {
                    GameAchievement achievement = Game.getAchievementById(Integer.parseInt(args[1]));
                    if (achievement != null) {
                        GamePlayer gamePlayer = Game.getGamePlayer(player.getUniqueId());
                        gamePlayer.addAchievement(achievement);
                    }
                    return true;
                }
            }
        }

        return true;
    }
}
