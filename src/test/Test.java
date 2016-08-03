import org.dungeonrealms.database.mysql.utils.Query;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Test {

    private static final Logger log = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {

        UUID uuid = UUID.randomUUID();
        String userName = "Shmozo";

        String query = new Query().Insert().Into().Table("players").Parenthesis("uuid", "username").Values("'" + uuid.toString() + "'", "'" + userName + "'").End().getQuery();
        System.out.println(query);
    }

}
