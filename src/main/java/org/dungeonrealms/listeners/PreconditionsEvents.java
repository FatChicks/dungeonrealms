package org.dungeonrealms.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.dungeonrealms.utils.Preconditions;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class PreconditionsEvents implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPreJoin(AsyncPlayerPreLoginEvent event) {
        if (Preconditions.maliciousPlayer(event.getUniqueId())) {
            event.setKickMessage("You are deemed a malicious player.");
        }
    }

}
