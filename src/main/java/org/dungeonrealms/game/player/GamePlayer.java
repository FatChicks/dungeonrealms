package org.dungeonrealms.game.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.dungeonrealms.database.mysql.utils.Query;
import org.dungeonrealms.database.save.Save;
import org.dungeonrealms.database.save.Update;
import org.dungeonrealms.database.save.UpdateType;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.achievement.GameAchievement;
import org.dungeonrealms.game.chat.Chat;
import org.dungeonrealms.game.chat.ChatType;
import org.dungeonrealms.game.player.prompt.Prompt;
import org.dungeonrealms.game.rank.RankType;

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
    private int guild;

    private Chat chat;
    private RankType rankType;

    /**
     * @param id           The player's unique id.
     * @param uuid         The player's uuid.
     * @param userName     The player's String username.
     * @param level        The player's integer level.
     * @param experience   The player's double experience.
     * @param cache        The player's cached location.
     * @param achievements The player's achievements.
     */
    public GamePlayer(int id, UUID uuid, String userName, int level, double experience, PlayerCache cache, List<GameAchievement> achievements, int gems, int guild, RankType rankType) {
        this.id = id;
        this.uuid = uuid;
        this.userName = userName;
        this.level = level;
        this.experience = experience;
        this.cache = cache;
        this.achievements = achievements;
        this.gems = gems;
        this.guild = guild;
        this.chat = new Chat(ChatType.LOCAL);
        this.rankType = rankType;
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

    public int getGuild() {
        return guild;
    }

    public Chat getChat() {
        return chat;
    }

    public RankType getRankType() {
        return rankType;
    }

    public void addGems(int gems) {
        this.gems += gems;
    }

    /**
     * Teleport the player to the last known location, stored in MySQL.
     */
    public void teleportLastPosition() {
        if(getCache() == null) {
            System.out.println("GET CACHE IS NULL");
        }
        System.out.println(getCache().toString());
        getPlayer().teleport(new Location(Bukkit.getWorld(getCache().getWorld()), getCache().getX(), getCache().getY(), getCache().getZ(), getCache().getYaw(), getCache().getPitch()));
    }

    /**
     * Sets the player's displayName and TabList Name.
     */
    public void setupName() {
        getPlayer().setDisplayName(ChatColor.WHITE + getPlayer().getName() + " " + ChatColor.AQUA + "[Lvl. " + String.valueOf(getLevel()) + "]");
        getPlayer().setPlayerListName(ChatColor.GRAY + getPlayer().getName());
    }

    /**
     * @param achievement The achievement you're giving to the player.
     */
    public void addAchievement(GameAchievement achievement) {
        if (!getAchievements().contains(achievement)) {
            this.achievements.add(achievement);
            Save.cacheUpdate(getUuid(), new Update(getId(), UpdateType.ACHIEVEMENT,
                    new Query().Insert().Into().Table("player_achievements").Parenthesis("player_id", "achievementId", "time").Values(getId(), achievement.getId(), System.currentTimeMillis())
                    ));
            Prompt.achievement(getPlayer(), achievement);
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
        if (Bukkit.getPlayer(getUserName()) == null) {
            System.out.println("GET PLAYER IS NULL INSIDE GAME PLAYER!");
        }
        return Bukkit.getPlayer(getUserName());
    }
}
