package org.dungeonrealms.game.item.attribute;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class ItemAttribute {

    public enum ItemAttributeType {

        VITALITY(0, "Vitality"), // More health & sword damage.
        DEXTERITY(1, "Dexterity"), // More dodge percentage, bow damage, critical damage and armor penetration.
        STRENGTH(2, "Strength"),; // More armor, axe damage and block percentage.

        private int id;
        private String name;

        ItemAttributeType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static ItemAttributeType getAttributeById(int id) {
            for (ItemAttributeType iat : values()) {
                if (iat.getId() == id) {
                    return iat;
                }
            }
            return null;
        }
    }

}
