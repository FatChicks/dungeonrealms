package org.dungeonrealms.game.world.living;

import org.bukkit.entity.EntityType;
import org.dungeonrealms.game.tier.Tier;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public abstract class Creature {

    abstract long getId();

    abstract long getExistence();

    abstract EntityType getEntityType();

    abstract Tier.TierType getTier();

}
