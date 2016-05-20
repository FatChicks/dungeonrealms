package org.dungeonrealms.game.item.stats;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class DamageBonus {

    //These stats ignore 80% of the target's armor.
    public enum DamageBonusType {

        PURE_DAMAGE(0, "Pure Damage"), //Adds to the total damage that a weapon does, whilst ignoring the targets elemental resistance.
        ICE_DAMAGE(1, "Ice Damage"), //Adds to the total damage that a weapon does, causes slowness, affected by the target's ice resistance.
        FIRE_DAMAGE(2, "Fire Damage"), //Adds to the total damage that a weapon does, does fire damage for 1 tick, affected by the targets fire resistance.
        POISON_DAMAGE(3, "Poison Damage"), //Adds to the total damage that a weapon does, does poison damage for 2 ticks affected by the target's poison resistance.

        ;

        private int id;
        private String name;

        DamageBonusType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        /**
         * @param id The id of bonus type.
         * @return The associated enum for that id.
         */
        public static DamageBonusType getById(int id) {
            for (DamageBonusType dbt : values()) {
                if (dbt.getId() == id) {
                    return dbt;
                }
            }
            return null;
        }
    }

}
