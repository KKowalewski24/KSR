package pl.jkkk.task2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jkkk.task2.mode.CommandMode;
import pl.jkkk.task2.mode.GraphicalMode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Main {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final boolean IS_LOGGING_DATA = true;

    private static ConfigurableApplicationContext applicationContext;
    private static List<String> mainArgs;

    /*------------------------ METHODS REGION ------------------------*/
    public static List<String> getMainArgs() {
        return Collections.unmodifiableList(mainArgs);
    }

    public static void main(String[] args) {
        mainArgs = Arrays.asList(args);
        applicationContext = SpringApplication.run(Main.class, args);

        if (args.length == 0) {
            new GraphicalMode().main(args);
        } else {
            new CommandMode().main(args);
            System.exit(0);
        }

        applicationContext.stop();
    }
}
