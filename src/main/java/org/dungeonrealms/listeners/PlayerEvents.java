package org.dungeonrealms.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.dungeonrealms.game.Game;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class PlayerEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        Game.addPlayer(event.getUniqueId(), event.getName());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent event) {
        Game.removePlayer(event.getPlayer().getUniqueId());
    }

}
