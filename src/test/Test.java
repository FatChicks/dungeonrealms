import org.dungeonrealms.database.mysql.Database;
import org.dungeonrealms.database.mysql.utils.Query;
import org.dungeonrealms.game.player.PlayerCache;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Test {

    private static final Logger log = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {
        String query = new Query().Select().All().From().Table("player_cache").Where().Field("player_id").Equals().asInt(12).End().getQuery();
        System.out.println(query);
    }

}
