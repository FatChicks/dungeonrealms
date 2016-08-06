package org.dungeonrealms.database.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Save {

    private static Map<UUID, CopyOnWriteArrayList<Update>> playerUpdates = new HashMap<>();

    /**
     * @param uuid The player's unique id.
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
