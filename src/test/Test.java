import org.bukkit.Bukkit;
import org.dungeonrealms.api.player.NetPlayer;

import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Test {

    private static final Logger log = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {
        NetPlayer p = () -> Bukkit.getPlayer("test");
    }

}
