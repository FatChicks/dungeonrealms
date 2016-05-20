package org.dungeonrealms.commands;

import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.dungeonrealms.game.world.Settlements;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by Dr. Nick Doran on 5/20/2016.
 */
public class CommandHistory implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        ApplicableRegionSet setRegion = WGBukkit.getRegionManager(player.getWorld()).getApplicableRegions(player.getLocation());
        Set<ProtectedRegion> protectedRegions = setRegion.getRegions();

        if (!protectedRegions.isEmpty()) {
            Iterator<ProtectedRegion> iterator = protectedRegions.iterator();
            Settlements settlement = Settlements.getSettlementByRegion(iterator.next().getId());

            if (settlement != null) {
                String[] desc = settlement.getDesc();
                if (desc.length > 0) {
                    for (int o = 0; o < 3; o++) {
                        player.sendMessage("");
                    }
                    player.sendMessage("                      " + ChatColor.YELLOW.toString() + ChatColor.BOLD + "History of " + ChatColor.AQUA.toString() + ChatColor.BOLD + settlement.getName());
                    player.sendMessage("");
                    for (String s : desc) {
                        player.sendMessage(s);
                    }
                    /*
                    Sending as  player.sendMessage(String[]) causes chat to not pop up,
                    rather the chat slides under and player must click 'T' or chat button
                    to open it, this method for(String s : par) opens chat.
                     */
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 63f);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "History hasn't been programmed for this area yet.");
                }
            } else {
                player.sendMessage(ChatColor.RED.toString() + ChatColor.BOLD + "There is no history for the area you're in.");
                return true;
            }

        }


        return false;
    }
}
