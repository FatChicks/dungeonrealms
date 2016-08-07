package org.dungeonrealms.database.save;

import org.dungeonrealms.database.mysql.utils.Query;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class Update {

    private int playerId;
    private UpdateType updateType;
    private Query playerUpdate;
    private long invoked;

    /**
     * @param playerId The player's id
     * @param updateType The type of update.
     * @param playerUpdate The playerUpdate object.
     */
    public Update(int playerId, UpdateType updateType, Query playerUpdate) {
        this.playerId = playerId;
        this.updateType = updateType;
        this.playerUpdate = playerUpdate;
        this.invoked = System.currentTimeMillis();
    }

    /**
     * @param playerId The player's id
     * @param updateType The type of update.
     * @param playerUpdate The playerUpdate object.
     * @param invoked The epochTime of invocation.
     */
    public Update(int playerId, UpdateType updateType, Query playerUpdate, long invoked) {
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

    public Query getPlayerUpdate() {
        return playerUpdate;
    }

    public long getInvoked() {
        return invoked;
    }
}
