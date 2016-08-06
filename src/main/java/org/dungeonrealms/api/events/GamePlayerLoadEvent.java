package org.dungeonrealms.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.dungeonrealms.game.player.GamePlayer;

/**
 * Created by Dr. Nick Doran on 8/6/2016.
 */
public class GamePlayerLoadEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private int playerId;
    private GamePlayer gamePlayer;

    public GamePlayerLoadEvent(int playerId, GamePlayer gamePlayer) {
        this.playerId = playerId;
        this.gamePlayer = gamePlayer;
    }

    public GamePlayerLoadEvent(boolean isAsync, int playerId, GamePlayer gamePlayer) {
        super(isAsync);
        this.playerId = playerId;
        this.gamePlayer = gamePlayer;
    }

    public int getPlayerId() {
        return playerId;
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