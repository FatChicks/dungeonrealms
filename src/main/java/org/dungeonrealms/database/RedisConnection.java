package org.dungeonrealms.database;

import com.lambdaworks.redis.RedisAsyncConnection;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import org.dungeonrealms.utils.Preconditions;
import org.dungeonrealms.utils.exceptions.AsyncConcurrentException;
import org.dungeonrealms.utils.exceptions.AsyncConnectionException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class RedisConnection extends Connection {

    private static final Logger log = Logger.getLogger(RedisConnection.class.getName());

    private String password;
    private String host;
    private int port;
    private long timeOut;
    private TimeUnit timeUnit;

    private volatile RedisAsyncConnection<String, String> asyncConnection;

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
    public RedisAsyncConnection<String, String> getAsyncConnection() {
        try {
            if (Preconditions.validAsyncConnection(this.asyncConnection)) {
                return this.asyncConnection;
            }
        } catch (AsyncConnectionException e) {
            e.printStackTrace();
        }
        try {
            this.asyncConnection = connect();
        } catch (AsyncConcurrentException | AsyncConnectionException e) {
            e.printStackTrace();
        }
        return getAsyncConnection();
    }

    /**
     * @return The newly connected async database object.
     */
    private RedisAsyncConnection<String, String> connect() throws AsyncConcurrentException, AsyncConnectionException {
        log.log(Level.INFO, "RedisConnection {0} .. Invoked Connect() Method.", hashCode());
        if (!Preconditions.validAsyncConnection(getAsyncConnection())) {
            try {
                this.asyncConnection = new RedisClient(RedisURI.create(new URI("redis://:" + getPassword() + "@" + getHost() + ":" + String.valueOf(getPort())))).connectAsync();
                return getAsyncConnection();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            throw new AsyncConcurrentException("Connection is still healthy");
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
