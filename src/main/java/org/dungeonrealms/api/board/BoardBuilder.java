package org.dungeonrealms.api.board;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
@SuppressWarnings("all")
public class BoardBuilder {

    private String displayName = "NULL";
    private ScoreboardManager scoreboardManager;
    private Scoreboard board;
    private Objective objective;

    /**
     * @param displayName The name of the scoreboard.
     */
    public BoardBuilder(String displayName) {
        this.displayName = displayName;
        this.scoreboardManager = Bukkit.getScoreboardManager();
        this.board = getScoreboardManager().getNewScoreboard();
        this.objective = getBoard().registerNewObjective("who", "cares");

        setDisplaySlot(DisplaySlot.SIDEBAR);
        setDisplayName(displayName);
    }

    /**
     * @param displaySlot The type of Scoreboard this object will hold.
     * @return Returns, this, itself, the object.
     */
    public BoardBuilder setDisplaySlot(DisplaySlot displaySlot) {
        getObjective().setDisplaySlot(displaySlot);
        return this;
    }

    /**
     * @param name The name of the scoreboard.
     * @return Returns, this, itself, the object.
     */
    public BoardBuilder setDisplayName(String name) {
        getObjective().setDisplayName(name);
        return this;
    }

    /**
     * @param key   The text on the scoreboard. I.E Kills
     * @param value The value to the right of the text. I.E Kills -> 5
     * @return Returns, this, itself, the object.
     */
    public BoardBuilder setScore(String key, int value) {
        getObjective().getScore(key).setScore(value);
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public Scoreboard getBoard() {
        return board;
    }

    public Objective getObjective() {
        return objective;
    }

    /**
     * @param player The player receiving the scoreboard.
     * @return The object.
     */
    public BoardBuilder make(Player player) {
        player.setScoreboard(getBoard());
        return this;
    }
}
