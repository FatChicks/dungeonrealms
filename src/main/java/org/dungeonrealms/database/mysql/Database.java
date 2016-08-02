package org.dungeonrealms.database.mysql;

import org.bukkit.configuration.file.FileConfiguration;
import org.dungeonrealms.DungeonRealms;
import org.dungeonrealms.database.mysql.utils.Query;
import org.dungeonrealms.game.Game;
import org.dungeonrealms.game.achievement.GameAchievement;
import org.dungeonrealms.game.player.GamePlayer;
import org.dungeonrealms.game.player.PlayerCache;

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
    }

    /**
     * @param uuid The player's unique id.
     */
    public void getGamePlayer(UUID uuid, Consumer<GamePlayer> gp) {
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
                        String userName = result.getString("username");
                        int level = result.getInt("level");
                        double experience = result.getDouble("experience");
                        PlayerCache cache = getPlayerCache(playerId);
                        List<GameAchievement> achievements = getPlayerAchievements(playerId);
                        Game.addPlayer(new GamePlayer(playerId, playerUuid, userName, level, experience, cache, achievements));
                    }
                } else {
                    //todo; add player
                }

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
