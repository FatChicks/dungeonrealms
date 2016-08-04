package org.dungeonrealms.api.player;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.player.GamePlayer;
import org.dungeonrealms.game.player.PlayerCache;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public interface NetPlayer extends ITargetable {

    /**
     * Teleport the player to the last known location, stored in MySQL.
     */
    default void teleportLastPosition() {
        GamePlayer gamePlayer = Game.getGamePlayer(getPlayer().getUniqueId());
        PlayerCache c = gamePlayer.getCache();
        getPlayer().teleport(new Location(Bukkit.getWorld(c.getWorld()), c.getX(), c.getY(), c.getZ(), c.getYaw(), c.getPitch()));
    }

    /**
     * Sets the player's displayName and TabList Name.
     */
    default void setupName() {
        GamePlayer gamePlayer = Game.getGamePlayer(getPlayer().getUniqueId());
        getPlayer().setDisplayName(ChatColor.WHITE + getPlayer().getName() + " " + ChatColor.AQUA + "[Lvl. " + String.valueOf(gamePlayer.getLevel()) + "]");
        getPlayer().setPlayerListName(ChatColor.GRAY + getPlayer().getName());
    }

    /**
     * @param kickMessage The message that prompts the player once they're kicked.
     */
    default void networkKick(String kickMessage) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("KickPlayer");
        out.writeUTF(getPlayer().getName());
        out.writeUTF(kickMessage);
        getPlayer().sendPluginMessage(DungeonRealms.getInstance(), "BungeeCord", out.toByteArray());
    }

    /**
     * @param serverName The server you're proxying the player to.
     */
    default void proxyTo(String serverName) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        getPlayer().sendPluginMessage(DungeonRealms.getInstance(), "BungeeCord", out.toByteArray());
    }

    /**
     * @param message The message you're sending to the player.
     * @apiNote Calling this immediately after PlayerJoinEvent, will fail.
     */
    default void sendMessage(String message) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Message");
        out.writeUTF(getPlayer().getName());
        out.writeUTF(message);
        getPlayer().sendPluginMessage(DungeonRealms.getInstance(), "BungeeCord", out.toByteArray());
    }

}
