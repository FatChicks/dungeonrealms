import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created by Dr. Nick Doran on 5/19/2016.
 */
public class Test {

    private static final Logger log = Logger.getLogger(Test.class.getName());

    public static void main(String[] args) {

        String command = "!help Shmozo";

        String[] lines = command.split(" ");

        System.out.println(lines.length);

        System.out.println(command.split(" ")[1]);

    }

}
