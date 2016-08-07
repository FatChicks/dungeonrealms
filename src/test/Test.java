import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Test {

    private static final Logger log = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {
        String query = new PlayerUpdate().Update().Gems(23).TargetUuid(UUID.randomUUID()).End().getUpdate();
        System.out.println(query);
    }

}
