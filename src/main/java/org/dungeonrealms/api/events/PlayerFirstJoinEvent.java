package org.dungeonrealms.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.dungeonrealms.game.player.GamePlayer;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class PlayerFirstJoinEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private int id;
    private GamePlayer gamePlayer;

    public PlayerFirstJoinEvent(int id, GamePlayer gamePlayer) {
        this.id = id;
        this.gamePlayer = gamePlayer;
    }

    public PlayerFirstJoinEvent(boolean isAsync, int id, GamePlayer gamePlayer) {
        super(isAsync);
        this.id = id;
        this.gamePlayer = gamePlayer;
    }

    public int getId() {
        return id;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}
