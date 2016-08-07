package org.dungeonrealms.database.save;

import org.bukkit.Bukkit;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.database.mysql.Database;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Save {

    private static final Logger log = Logger.getLogger(Save.class.getName());

    private volatile static Map<UUID, CopyOnWriteArrayList<Update>> playerUpdates = new HashMap<>();

    public static void init() {
        log.log(Level.INFO, "[Save] Starting scheduled Task ...");

        Bukkit.getScheduler().scheduleSyncRepeatingTask(DungeonRealms.getInstance(), () -> Bukkit.getOnlinePlayers().forEach(player -> {
            for (Update u : playerUpdates.get(player.getUniqueId())) {
                Database.getInstance().execUpdate(u);
                playerUpdates.get(player.getUniqueId()).remove(u);
            }
        }), 0, 20 * 15);

    }

    /**
     * @param uuid   The player's unique id.
     * @param update The update object.
     */
    public static void cacheUpdate(UUID uuid, Update update) {
        if (playerUpdates.containsKey(uuid)) {
            //exist
            playerUpdates.get(uuid).stream().filter(u -> u.getUpdateType() == update.getUpdateType()).forEach(u -> playerUpdates.get(uuid).remove(u));
            playerUpdates.get(uuid).add(update);
        } else {
            playerUpdates.put(uuid, new CopyOnWriteArrayList<Update>() {{
                add(update);
            }});
        }
    }

}
