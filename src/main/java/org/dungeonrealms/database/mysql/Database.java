package org.dungeonrealms.database.mysql;

import org.bukkit.configuration.file.FileConfiguration;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.database.mysql.utils.Query;
import org.dungeonrealms.database.mysql.utils.ScriptRunner;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.achievement.GameAchievement;
import org.dungeonrealms.game.player.GamePlayer;
import org.dungeonrealms.game.player.PlayerCache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 8/2/2016.
 */
public class Database {

    private static final Logger log = Logger.getLogger(Database.class.getName());

    private static Database instance = null;
    private static MySQLConnection connection;
    private ExecutorService pool = Executors.newFixedThreadPool(2);

    public static Database getInstance() {
        return instance == null ? instance = new Database() : instance;
    }

    public void Connect() {
        log.log(Level.INFO, "[Database] Connecting to [MySQL] Starting Connection ...");
        FileConfiguration c = DungeonRealms.getInstance().getConfig();
        connection = new MySQLConnection(c.getString("mysql.host"), c.getString("mysql.userName"), c.getString("mysql.password"), c.getString("mysql.database"), c.getInt("mysql.port"));
        verifyTableIntegrity();
    }

    /**
     * Reads `mysql.sql` file located inside directory, and auto creates all tables
     * with respected columns. `mysql.sql` isn't generated by default, it must be created and placed inside the directory
     * every update. To update the mysql with new entries, remove the tables in mysql and update the `mysql.sql` file locally.
     */
    private void verifyTableIntegrity() {
        log.info("[Database] Verifying MySQL tables integrity ...");

        String sqlFilePath = DungeonRealms.getInstance().getDataFolder() + File.separator + "mysql.sql";
        ScriptRunner runner = new ScriptRunner(getConnection().getDatabase(), false, true);

        try {
            runner.runScript(new BufferedReader(new FileReader(new File(sqlFilePath))));
        } catch (IOException | SQLException e) {
            log.log(Level.SEVERE, "[Database] Unable to [read/inject] mysql.sql into BufferReader and send to MySQL for table creation (!) Read Error Below.");
            e.printStackTrace();
        }

        log.info("[Database] Table(s) integrity has been verified ... OKAY");
    }

    /**
     * @param ga A list of server achievements.
     */
    public void getServerAchievements(Consumer<List<GameAchievement>> ga) {
        pool.submit(() -> {
            String query = new Query().Select().All().From().Table("achievements").End().getQuery();
            try (
                    PreparedStatement statement = getConnection().getDatabase().prepareStatement(query);
                    ResultSet result = statement.executeQuery();
            ) {
                List<GameAchievement> gaList = new ArrayList<>();
                while (result.next()) {
                    int id = result.getInt("ID");
                    String achievementName = result.getString("achievementName");
                    String achievementDescription = result.getString("achievementDesc");
                    gaList.add(new GameAchievement(id, achievementName, achievementDescription.split("|")));
                }
                ga.accept(gaList);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param uuid The player's unique id.
     */
    public void getGamePlayer(UUID uuid, String userName, Consumer<GamePlayer> gp) {
        pool.submit(() -> {
            String query = new Query().Select().All().From().Table("players").Where().Field("uuid").Equals().asString(uuid.toString()).End().getQuery();
            try (
                    PreparedStatement statement = getConnection().getDatabase().prepareStatement(query);
                    ResultSet result = statement.executeQuery();
            ) {
                if (result.next()) {
                    while (result.next()) {
                        int playerId = result.getInt("player_id");
                        UUID playerUuid = UUID.fromString(result.getString("uuid"));
                        String _userName = result.getString("username");
                        int level = result.getInt("level");
                        double experience = result.getDouble("experience");
                        PlayerCache cache = getPlayerCache(playerId);
                        List<GameAchievement> achievements = getPlayerAchievements(playerId);
                        int gems = result.getInt("gems");
                        gp.accept(new GamePlayer(playerId, playerUuid, _userName, level, experience, cache, achievements, gems));
                    }
                } else {
                    addPlayer(uuid, userName);
                    getGamePlayer(uuid, userName, null);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param uuid     The player's unique id.
     * @param userName The player's String userName.
     */
    private void addPlayer(UUID uuid, String userName) {
        pool.submit(() -> {
            String query = new Query().Insert().Into().Table("players").Parenthesis("uuid", "username").Values("'" + uuid.toString() + "'", "'" + userName + "'").End().getQuery();
            try {
                getConnection().getDatabase().prepareStatement(query).executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getIdByName(userName, id -> {
                String query1 = new Query().Insert().Into().Table("player_cache").Parenthesis("player_id").Values(id).End().getQuery();
                try {
                    getConnection().getDatabase().prepareStatement(query1).executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    /**
     * @param userName The player's name.
     * @param id       The player's id.
     */
    private void getIdByName(String userName, Consumer<Integer> id) {
        pool.submit(() -> {
            String query = new Query().Select().Field("player_id").From().Table("players").Where().Field("username").Equals().asString(userName).End().getQuery();
            try (
                    PreparedStatement statement = getConnection().getDatabase().prepareStatement(query);
                    ResultSet result = statement.executeQuery();
            ) {
                id.accept(result.getInt("player_id"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * @param playerId The player's achievement id.
     * @return A list of the player's achievements.
     */
    private List<GameAchievement> getPlayerAchievements(int playerId) {
        String query = new Query().Select().Values("achievementId").From().Table("player_achievements").Where().Field("player_id").Equals().asInt(playerId).End().getQuery();
        try (
                PreparedStatement statement = getConnection().getDatabase().prepareStatement(query);
                ResultSet result = statement.executeQuery();
        ) {
            List<GameAchievement> temp = new ArrayList<>();
            while (result.next()) {
                int achievementId = result.getInt("achievementId");
                //long time = result.getLong("time");
                temp.add(Game.getAchievementById(achievementId));
            }
            return temp;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param playerId The player's unique id.
     * @return The player's PlayerCache object, as it is in MySQL.
     */
    private PlayerCache getPlayerCache(int playerId) {
        String query = new Query().Select().All().From().Table("player_cache").Where().Field("player_id").Equals().asInt(playerId).End().getQuery();
        try (
                PreparedStatement statement = getConnection().getDatabase().prepareStatement(query);
                ResultSet result = statement.executeQuery();
        ) {
            while (result.next()) {
                String world = result.getString("world");
                double x = result.getDouble("x");
                double y = result.getDouble("y");
                double z = result.getDouble("z");
                float yaw = result.getFloat("yaw");
                float pitch = result.getFloat("pitch");
                return new PlayerCache(world, x, y, z, yaw, pitch);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MySQLConnection getConnection() {
        return connection;
    }
}
