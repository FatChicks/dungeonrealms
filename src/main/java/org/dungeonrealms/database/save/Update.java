package org.dungeonrealms.database.save;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Update {

    private int playerId;
    private UpdateType updateType;
    private PlayerUpdate playerUpdate;
    private long invoked;

    /**
     * @param playerId The player's id
     * @param updateType The type of update.
     * @param playerUpdate The playerUpdate object.
     * @param invoked The epochTime of invocation.
     */
    public Update(int playerId, UpdateType updateType, PlayerUpdate playerUpdate, long invoked) {
        this.playerId = playerId;
        this.updateType = updateType;
        this.playerUpdate = playerUpdate;
        this.invoked = invoked;
    }

    public int getPlayerId() {
        return playerId;
    }

    public UpdateType getUpdateType() {
        return updateType;
    }

    public PlayerUpdate getPlayerUpdate() {
        return playerUpdate;
    }

    public long getInvoked() {
        return invoked;
    }
}
