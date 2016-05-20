package org.dungeonrealms.game.item.stats;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class ItemStat {

    public enum ItemStatType {

        DODGE(0, "Dodge"),
        REFLECTION(1, "Reflection"),
        THORNS(2, "Thorns"),
        BLOCK(3, "Block"),

        //Weapon Stats

        ICE(10, "Ice"), //Has a chance to slow the target for a short period of time.
        BLIND(11, "Blind"), //Has a chance to blind the target for a short period of time.
        FIRE(12, "Fire"), //Sets the target on fire, dealing damage over time.
        KNOCK_BACK(13, "Knock Back"), //Has a chance to knock the target back several blocks.
        LIFE_STEAL(14, "Life Steal"), //Heals the player by a percentage of the damage dealt.
        ARMOR_PENETRATION(15, "Armor Penetration"), //Ignores a percentage of the target's armor.
        CRITICAL_HIT_CHANCE(16, "Critical Hit Chance"), //Has a chance to deal double damage.
        POISON(17, "Poison"), //Poisons the target, dealing damage over time.

        ;


        private int id;
        private String name;

        ItemStatType(int id, String name) {
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
         * @param id The id.
         * @return The associated stat for that id.
         */
        public static ItemStatType getStatById(int id) {
            for (ItemStatType ist : values()) {
                if (ist.getId() == id) {
                    return ist;
                }
            }
            return null;
        }
    }

}
