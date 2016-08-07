package org.dungeonrealms.game.player.prompt;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.game.achievement.GameAchievement;

import java.util.Arrays;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Prompt {

    /**
     * @param player      The player that received the achievement.
     * @param achievement The achievement the player received.
     */
    public static void achievement(Player player, GameAchievement achievement) {
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1f, 63f);
        player.sendMessage(ChatColor.DARK_AQUA + ">> Achievement Unlocked" + ChatColor.GRAY + ":" + ChatColor.DARK_AQUA + " `" + ChatColor.GRAY + achievement.getAchievementName() + ChatColor.DARK_AQUA + "`" + ChatColor.GRAY + "!");
    }

    /**
     * @param player The player you're sending the message to.
     */
    public static void welcomeMessage(Player player) {
        sendCenteredMessage(player, new String[]{
                "",
                ChatColor.WHITE + "Welcome to" + ChatColor.AQUA.toString() + ChatColor.BOLD + " Dungeon Realms" + ChatColor.WHITE + "!",
                "",
                ChatColor.BLUE + "Patch" + ChatColor.GRAY + ":" + ChatColor.RESET + " " + ChatColor.RED.toString() + ChatColor.BOLD + DungeonRealms.BUILD,
                ChatColor.GREEN + "Website" + ChatColor.GRAY + ": " + ChatColor.GRAY.toString() + ChatColor.ITALIC + "https://dungeonrealms.org",
                "",
                ChatColor.YELLOW + "You are on shard " + ChatColor.BOLD + "US-1",
                "",
        });

    }

    private final static int CENTER_PX = 154;

    /**
     * @param player The player you're sending the message to.
     * @param par1   The message.
     * @apiNote https://www.spigotmc.org/threads/free-code-sending-perfectly-centered-chat-message.95872/
     */
    private static void sendCenteredMessage(Player player, String[] par1) {
        for (String s : par1) {
            if (s == null || s.equals("")) player.sendMessage("");
            s = ChatColor.translateAlternateColorCodes('&', s);

            int messagePxSize = 0;
            boolean previousCode = false;
            boolean isBold = false;

            for (char c : s.toCharArray()) {
                if (c == '§') {
                    previousCode = true;
                } else if (previousCode) {
                    previousCode = false;
                    isBold = c == 'l' || c == 'L';
                } else {
                    DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                    messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                    messagePxSize++;
                }
            }

            int halvedMessageSize = messagePxSize / 2;
            int toCompensate = CENTER_PX - halvedMessageSize;
            int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
            int compensated = 0;
            StringBuilder sb = new StringBuilder();
            while (compensated < toCompensate) {
                sb.append(" ");
                compensated += spaceLength;
            }
            player.sendMessage(sb.toString() + s);
        }
    }
}
