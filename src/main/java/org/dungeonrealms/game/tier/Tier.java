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
         * @param tier Integer representation of tier(1,2,3,4,5)
         * @return TierType enum associated with value.
         */
        public static TierType getByInt(int tier) {
            for (TierType tt : values()) {
                if (tt.getId() == tier) {
                    return tt;
                }
            }
            return null;
        }
    }

}
