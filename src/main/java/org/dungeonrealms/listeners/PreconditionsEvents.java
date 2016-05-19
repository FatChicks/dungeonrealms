package org.dungeonrealms.listeners;

import com.lambdaworks.redis.RedisFuture;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.utils.Preconditions;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class PreconditionsEvents implements Listener {

    private static final Logger log = Logger.getLogger(PreconditionsEvents.class.getName());

    @EventHandler(priority = EventPriority.LOWEST)
    public void onAsyncPreJoin(AsyncPlayerPreLoginEvent event) {
        if (Preconditions.maliciousPlayer(event.getUniqueId())) {
            event.setKickMessage("You are deemed a malicious player.");
            return;
        }

        log.log(Level.INFO, "{0} Logged in at", System.currentTimeMillis());

        RedisFuture<Boolean> query = DungeonRealms.getNet().getRedis().getAsyncConnection().exists("");

        try {
            if (query.get()) {
                log.log(Level.INFO, "{0} is all good to go!", event.getName());
            } else {
                log.log(Level.INFO, "{0} not good", System.currentTimeMillis());
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

}
