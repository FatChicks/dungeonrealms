package org.dungeonrealms.game.tier;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Tier {

    private static final int TIER_1 = 10;
    private static final int TIER_2 = 20;
    private static final int TIER_3 = 30;
    private static final int TIER_4 = 40;
    private static final int TIER_5 = 100;

    /**
     * Tier Enum.
     */
    public enum TierType {

        /**
         * Used for misc reasons.
         */
        TIER_0(0, "Tier 0"),

        TIER_1(1, "Tier 1"),
        TIER_2(2, "Tier 2"),
        TIER_3(3, "Tier 3"),
        TIER_4(4, "Tier 4"),
        TIER_5(5, "Tier 5");

        private int id;
        private String name;

        TierType(int id, String name) {
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
         * @param num Integer num.
         * @return TierType enum associated with value.
         */
        public static TierType getByInt(int num) {
            if (num <= 10) {
                return TIER_1;
            } else if (num <= 20) {
                return TIER_2;
            } else if (num <= 30) {
                return TIER_3;
            } else if (num <= 40) {
                return TIER_4;
            } else if (num <= 100) {
                return TIER_5;
            }
            return null;
        }
    }

}
