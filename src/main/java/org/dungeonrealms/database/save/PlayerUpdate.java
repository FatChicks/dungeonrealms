package org.dungeonrealms.database.save;

import java.util.UUID;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class PlayerUpdate {

    private String query;

    public PlayerUpdate() {
        query = "";
    }

    public PlayerUpdate Update() {
        query += "UPDATE" + " ";
        return this;
    }

    public PlayerUpdate Gems(int par1) {
        query += "'players' SET gems=" + par1 + " ";
        return this;
    }

    public PlayerUpdate TargetUuid(UUID uuid) {
        query += "WHERE uuid='" + uuid + "'" + " ";
        return this;
    }

    public PlayerUpdate TargetId(int playerId) {
        query += "WHERE player_id=" + playerId + " ";
        return this;
    }

    public PlayerUpdate End() {
        query += ";";
        return this;
    }

    public String getUpdate() {
        if (!query.contains(";")) {
            End();
        }
        return query;
    }

}
