package org.dungeonrealms.utils;

import org.dungeonrealms.utils.exceptions.InvalidChannelException;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Preconditions {

    /**
     * @param par1 Ensures messaging channel is for DungeonRealms network listener.
     */
    public static boolean validChannel(String par1) {
        if (!par1.equalsIgnoreCase("DungeonRealms")) {
            try {
                throw new InvalidChannelException("Channel is not of `DungeonRealms`");
            } catch (InvalidChannelException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

}
