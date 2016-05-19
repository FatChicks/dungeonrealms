package org.dungeonrealms.utils;

import com.lambdaworks.redis.RedisAsyncConnection;
import org.dungeonrealms.utils.exceptions.AsyncConnectionException;
import org.dungeonrealms.utils.exceptions.InvalidChannelException;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Preconditions {

    /**
     * @param asyncConnection The asynchronous connection.
     * @return True, if the connection is valid.
     */
    public static boolean validAsyncConnection(RedisAsyncConnection<String, String> asyncConnection) {
        if (!asyncConnection.isOpen() || asyncConnection == null) {
            try {
                throw new AsyncConnectionException("Async Connection timeout");
            } catch (AsyncConnectionException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

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
