package org.dungeonrealms.database;

import com.lambdaworks.redis.RedisAsyncConnection;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisURI;
import org.dungeonrealms.utils.Preconditions;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class RedisConnection extends Connection {

    private String password;
    private String host;
    private int port;
    private boolean checkOnReference;

    private RedisAsyncConnection<String, String> asyncConnection;

    /**
     * @param password         The password.
     * @param host             The host ip/dns.
     * @param port             The port.
     * @param checkOnReference Ensure the connection never terminates.
     */
    public RedisConnection(String password, String host, int port, boolean checkOnReference) throws URISyntaxException {
        this.password = password;
        this.host = host;
        this.port = port;
        this.checkOnReference = checkOnReference;

        this.asyncConnection = new RedisClient(RedisURI.create(new URI("redis://:" + getPassword() + "@" + getHost() + ":" + String.valueOf(getPort())))).connectAsync();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    protected String getHost() {
        return this.host;
    }

    @Override
    protected int getPort() {
        return this.port;
    }

    @Override
    protected boolean checkOnReference() {
        return this.checkOnReference;
    }

    @Override
    protected RedisAsyncConnection<String, String> getAsyncConnection() {
        if (checkOnReference()) {
            if (Preconditions.validAsyncConnection(this.asyncConnection)) {
                return this.asyncConnection;
            } else {
                //TODO: More halting.
                this.asyncConnection = connect();
                return getAsyncConnection();
            }
        } else {
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
        return null;
    }


}
