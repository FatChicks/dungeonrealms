package org.dungeonrealms.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.api.player.NetPlayer;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.player.GamePlayer;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class PlayerEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChat(AsyncPlayerChatEvent event) {
        GamePlayer gamePlayer = Game.getGamePlayer(event.getPlayer().getUniqueId());
        event.setFormat(gamePlayer.getChat().getChatType().getTag() +
                ChatColor.GRAY + event.getPlayer().getName() + ": " + ChatColor.WHITE + event.getMessage());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(DungeonRealms.getInstance(), () -> {
            GamePlayer gamePlayer = Game.getGamePlayer(event.getPlayer().getUniqueId());
            gamePlayer.setupName();
            gamePlayer.teleportLastPosition();
        }, 20 * 3);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        Game.addPlayer(event.getUniqueId(), event.getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Game.removePlayer(event.getPlayer().getUniqueId());
    }

}
