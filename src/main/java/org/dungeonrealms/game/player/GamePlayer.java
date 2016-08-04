package org.dungeonrealms.game.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

    private int gems;

    /**
     * @param id           The player's unique id.
     * @param uuid         The player's uuid.
     * @param userName     The player's String username.
     * @param level        The player's integer level.
     * @param experience   The player's double experience.
     * @param cache        The player's cached location.
     * @param achievements The player's achievements.
     */
    public GamePlayer(int id, UUID uuid, String userName, int level, double experience, PlayerCache cache, List<GameAchievement> achievements, int gems) {
        this.id = id;
        this.uuid = uuid;
        this.userName = userName;
        this.level = level;
        this.experience = experience;
        this.cache = cache;
        this.achievements = achievements;
        this.gems = gems;
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

    public int getGems() {
        return gems;
    }

    public void addGems(int gems) {
        this.gems += gems;
    }

    /**
     * @param achievement The achievement you're giving to the player.
     */
    public void addAchievement(GameAchievement achievement) {
        if (!getAchievements().contains(achievement)) {
            this.achievements.add(achievement);
        }
    }

    /**
     * @param experience The experience you're adding to the player.
     */
    public void addExperience(double experience) {
        if (getLevel() >= 75) return;
        double futureExp = getExperience() + experience;
        //The experience needed to get to the next level.
        double nxtLvlExp = getNxtLvl(getLevel() + 1);
        if (futureExp >= nxtLvlExp) {
            double expToCarry = futureExp - nxtLvlExp;
            this.level++;
            addExperience(expToCarry);
        } else {
            this.experience = futureExp;
        }
    }

    /**
     * @param level The level.
     * @return The needed experience to get to this level.
     */
    private double getNxtLvl(int level) {
        return 200 * (Math.pow(level, 1.75));
    }

    /**
     * @return Bukkit's player object, from the player's uuid.
     */
    private Player getPlayer() {
        return Bukkit.getPlayer(getUuid());
    }
}