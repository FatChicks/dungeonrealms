package org.dungeonrealms.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public final class MySQLConnection {

    private String host, userName, password, databaseName;
    private int port;

    private Connection connection;

    /**
     * @param host     The MySQL host (ip)
     * @param userName The MySQL userName.
     * @param password The mysql password.
     * @param port     The mysql port.
     */
    public MySQLConnection(String host, String userName, String password, String databaseName, int port) {
        this.host = host;
        this.userName = userName;
        this.password = password;
        this.databaseName = databaseName;
        this.port = port;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/"+databaseName, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getHost() {
        return host;
    }

    private String getUserName() {
        return userName;
    }

    private String getPassword() {
        return password;
    }

    private String getDatabaseName() {
        return databaseName;
    }

    private int getPort() {
        return port;
    }

    public Connection getDatabase() {
        return connection;
    }
}
