package org.dungeonrealms.game.achievement;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class GameAchievement {

    private int id;
    private String achievementName;
    private String[] achievementDesc;

    /**
     * @param id              The achievement's unique id.
     * @param achievementName The achievement's name.
     * @param achievementDesc The achievement's description.
     */
    public GameAchievement(int id, String achievementName, String[] achievementDesc) {
        this.id = id;
        this.achievementName = achievementName;
        this.achievementDesc = achievementDesc;
    }

    public int getId() {
        return id;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public String[] getAchievementDesc() {
        return achievementDesc;
    }
}
