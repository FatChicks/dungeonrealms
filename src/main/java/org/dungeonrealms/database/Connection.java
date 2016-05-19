package org.dungeonrealms.database;

import com.lambdaworks.redis.RedisAsyncConnection;

import java.util.concurrent.TimeUnit;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
abstract class Connection {

    protected abstract String getPassword();

    protected abstract String getHost();

    protected abstract int getPort();

    protected abstract long getTimeOut();

    protected abstract TimeUnit getTimeUnit();

    protected abstract RedisAsyncConnection<String, String> getAsyncConnection();

}
