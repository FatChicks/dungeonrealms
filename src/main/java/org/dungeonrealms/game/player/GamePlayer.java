package org.dungeonrealms.game.player;

import org.dungeonrealms.game.achievement.GameAchievement;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class GamePlayer {

    private int id;
    private UUID uuid;
    private String userName;

    private int level;
    private double experience;

    private PlayerCache cache;
    private List<GameAchievement> achievements;

    /**
     * @param id           The player's unique id.
     * @param uuid         The player's uuid.
     * @param userName     The player's String username.
     * @param level        The player's integer level.
     * @param experience   The player's double experience.
     * @param cache        The player's cached location.
     * @param achievements The player's achievements.
     */
    public GamePlayer(int id, UUID uuid, String userName, int level, double experience, PlayerCache cache, List<GameAchievement> achievements) {
        this.id = id;
        this.uuid = uuid;
        this.userName = userName;
        this.level = level;
        this.experience = experience;
        this.cache = cache;
        this.achievements = achievements;
    }

    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getUserName() {
        return userName;
    }

    public int getLevel() {
        return level;
    }

    public double getExperience() {
        return experience;
    }

    public PlayerCache getCache() {
        return cache;
    }

    private List<GameAchievement> getAchievements() {
        return achievements;
    }

    /**
     * @param achievement The achievement you're giving to the player.
     */
    public void addAchievement(GameAchievement achievement) {
        if (!getAchievements().contains(achievement)) {
            this.achievements.add(achievement);
        }
    }
}
