package org.dungeonrealms.database;

import com.lambdaworks.redis.RedisAsyncConnection;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import org.dungeonrealms.utils.Preconditions;
import org.dungeonrealms.utils.exceptions.AsyncConnectionException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
class RedisConnection extends Connection {

    private String password;
    private String host;
    private int port;
    private long timeOut;
    private TimeUnit timeUnit;

    private RedisAsyncConnection<String, String> asyncConnection;

    /**
     * @param password The password.
     * @param host     The host ip/dns.
     * @param port     The port.
     */
    public RedisConnection(String password, String host, int port, long timeOut, TimeUnit timeUnit) throws URISyntaxException {
        this.password = password;
        this.host = host;
        this.port = port;
        this.timeOut = timeOut;
        this.timeUnit = timeUnit;

        this.asyncConnection = new RedisClient(RedisURI.create(new URI("redis://:" + getPassword() + "@" + getHost() + ":" + String.valueOf(getPort())))).connectAsync();
        getAsyncConnection().setTimeout(getTimeOut(), getTimeUnit());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    protected String getHost() {
        return host;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public long getTimeOut() {
        return timeOut;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    protected RedisAsyncConnection<String, String> getAsyncConnection() {
        if (Preconditions.validAsyncConnection(this.asyncConnection)) {
            return this.asyncConnection;
        } else {
            this.asyncConnection = connect();
            return getAsyncConnection();
        }
    }

    /**
     * @return The newly connected async database object.
     */
    private RedisAsyncConnection<String, String> connect() {
        try {
            this.asyncConnection = new RedisClient(RedisURI.create(new URI("redis://:" + getPassword() + "@" + getHost() + ":" + String.valueOf(getPort())))).connectAsync();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            throw new AsyncConnectionException("Cannot allocate new asynchronous connection.");
        } catch (AsyncConnectionException e) {
            e.printStackTrace();
            //TODO: Lock server and prepare for local caching.
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RedisConnection that = (RedisConnection) o;

        return port == that.port && timeOut == that.timeOut && (password != null ? password.equals(that.password) : that.password == null && (host != null ? host.equals(that.host) : that.host == null && timeUnit == that.timeUnit && (asyncConnection != null ? asyncConnection.equals(that.asyncConnection) : that.asyncConnection == null)));

    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + port;
        result = 31 * result + (int) (timeOut ^ (timeOut >>> 32));
        result = 31 * result + (timeUnit != null ? timeUnit.hashCode() : 0);
        result = 31 * result + (asyncConnection != null ? asyncConnection.hashCode() : 0);
        return result;
    }
}
