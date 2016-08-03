package org.dungeonrealms.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.dungeonrealms.game.Game;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class PlayerEvents implements Listener {

    @EventHandler
    public void onAsyncJoin(AsyncPlayerPreLoginEvent event) {
        Game.addPlayer(event.getUniqueId(), event.getName());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Game.removePlayer(event.getPlayer().getUniqueId());
    }

}
