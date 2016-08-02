package org.dungeonrealms.game;

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
    private Map<UUID, GamePlayer> players = new HashMap<>();

    public static void GetPrerequisites() {
        log.log(Level.INFO, "[Game] Getting Prerequisites ... Starting");
    }

    public static void addPlayer(GamePlayer gamePlayer) {
        //todo: add player to players cache.
    }

    public static GameAchievement getAchievementById(int id) {
        return achievements.get(id);
    }

}
