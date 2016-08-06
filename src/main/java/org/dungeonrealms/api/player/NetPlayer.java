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
