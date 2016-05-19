package org.dungeonrealms.database;

import com.lambdaworks.redis.RedisAsyncConnection;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
abstract class Connection {

    protected abstract String getPassword();

    protected abstract String getHost();

    protected abstract int getPort();

    protected abstract boolean checkOnReference();

    protected abstract RedisAsyncConnection<String, String> getAsyncConnection();

}
