package org.dungeonrealms.game.world.living;

import org.bukkit.entity.EntityType;
import org.dungeonrealms.game.tier.Tier;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class FakeTestPig extends Creature {
    @Override
    long getId() {
        return 1;
    }

    @Override
    long getExistence() {
        return System.currentTimeMillis();
    }

    @Override
    EntityType getEntityType() {
        return EntityType.PIG;
    }

    @Override
    Tier.TierType getTier() {
        return Tier.TierType.TIER_2;
    }
}
