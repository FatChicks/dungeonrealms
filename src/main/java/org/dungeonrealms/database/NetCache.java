package org.dungeonrealms.database;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class NetCache {

    private RedisConnection netRedis;

    /**
     * @param netRedis The RedisConnection object.
     */
    public NetCache(RedisConnection netRedis) {
        this.netRedis = netRedis;
    }

    /**
     * @return Get the RedisConnection Object.
     * @see RedisConnection
     */
    public RedisConnection getRedis() {
        return netRedis;
    }
}
