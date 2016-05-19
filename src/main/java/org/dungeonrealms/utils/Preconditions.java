package org.dungeonrealms.utils;

import com.lambdaworks.redis.RedisAsyncConnection;
import org.apache.commons.codec.digest.DigestUtils;
import org.dungeonrealms.utils.exceptions.AsyncConnectionException;
import org.dungeonrealms.utils.exceptions.InvalidChannelException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Preconditions {

    private static List<String> maliciousPlayers = new ArrayList<>();

    static {
        maliciousPlayers.add("088202811203e377a345bdf32a711221b896bae5");
        maliciousPlayers.add("f69c9e7a2cb7a0d7c71065da978a8cc89b472ace");
    }

    /**
     * @param uuid The player's unique id.
     * @return True, if the player is malicious
     */
    public static boolean maliciousPlayer(UUID uuid) {
        return maliciousPlayers.contains(DigestUtils.sha1Hex(uuid.toString().replaceAll("-", "")));
    }

    /**
     * @param asyncConnection The asynchronous connection.
     * @return True, if the connection is valid.
     */
    public static boolean validAsyncConnection(RedisAsyncConnection<String, String> asyncConnection) {
        if (!asyncConnection.isOpen()) {
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
