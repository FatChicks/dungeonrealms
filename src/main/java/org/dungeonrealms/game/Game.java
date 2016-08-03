package org.dungeonrealms.game;

import org.dungeonrealms.database.mysql.Database;
import org.dungeonrealms.game.achievement.GameAchievement;
import org.dungeonrealms.game.player.GamePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Game {

    private static final Logger log = Logger.getLogger(Game.class.getName());

    private static Map<Integer, GameAchievement> achievements = new HashMap<>();
    private static Map<UUID, GamePlayer> players = new HashMap<>();

    public static void GetPrerequisites() {
        log.log(Level.INFO, "[Game] Getting Prerequisites ... Starting");
        Database.getInstance().getServerAchievements(sa -> sa.forEach(a -> {
            achievements.put(a.getId(), a);
        }));
    }

    public static void addPlayer(UUID uuid, String userName) {
        Database.getInstance().getGamePlayer(uuid, userName, gp -> players.put(uuid, gp));
    }

    public static void removePlayer(UUID uniqueId) {
        //todo: remove player
    }

    public static GameAchievement getAchievementById(int id) {
        return achievements.get(id);
    }
}
