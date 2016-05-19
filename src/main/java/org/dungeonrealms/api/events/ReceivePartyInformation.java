package org.dungeonrealms.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class ReceivePartyInformation extends Event {

    private static final HandlerList handlers = new HandlerList();

    private String playerName;

    /**
     * @param playerName The player's nam.e
     */
    public ReceivePartyInformation(String playerName) {
        this.playerName = playerName;
    }

    /**
     * @param isAsync Is executed asynchronously?
     * @param playerName The player's name.
     */
    public ReceivePartyInformation(boolean isAsync, String playerName) {
        super(isAsync);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
