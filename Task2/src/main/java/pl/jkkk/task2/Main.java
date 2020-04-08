package pl.jkkk.task2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jkkk.task2.logic.model.ConjuctionType;
import pl.jkkk.task2.logic.model.QualifierSummarizationType;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;

@SpringBootApplication
public class Main extends Application {

    /*------------------------ FIELDS REGION ------------------------*/
    private static ConfigurableApplicationContext applicationContext;
    private static List<String> mainArgs;

    /*------------------------ METHODS REGION ------------------------*/
    public static List<String> getMainArgs() {
        return Collections.unmodifiableList(mainArgs);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StageController.buildStage(stage, PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                new WindowDimensions(1200, 700), PATH_CSS_STYLING);
        StageController.getApplicationStage().setResizable(false);
    }

    private static void printUsage() {
        System.out.println("Required parameters:  \n"
                + "\t<Is Console Mode (true / false)>\n"
        );
        System.exit(0);
    }

    public static void main(String[] args) {
        boolean isConsoleMode = false;
        QualifierSummarizationType qualifier = null;
        QualifierSummarizationType basicSummarizer = null;
        ConjuctionType conjuntion = null;
        QualifierSummarizationType advancedSummarizer = null;

        try {
            isConsoleMode = Boolean.valueOf(args[0]);

            if (args.length > 1) {
                qualifier = QualifierSummarizationType.fromString(args[1]);
                basicSummarizer = QualifierSummarizationType.fromString(args[2]);
                conjuntion = ConjuctionType.fromString(args[3]);
                advancedSummarizer = QualifierSummarizationType.fromString(args[4]);
            }

        } catch (Exception e) {
            System.out.println(e);
            printUsage();
        }

        applicationContext = SpringApplication.run(Main.class, args);
        mainArgs = Arrays.asList(args);

        if (isConsoleMode) {
            //TODO ADD CALLING METHOD FROM LOGIC
            System.exit(0);
        } else {
            launch(args);
        }

        applicationContext.stop();
    }

    /**
     * Different way of initializing and stopping application content both ways
     * (this and implemented in main give the same result) - properly close context at the end
     * <p>
     * //    @Override
     * //    public void init() throws Exception {
     * //        applicationContext = SpringApplication.run(Main.class);
     * //    }
     * //
     * //    @Override
     * //    public void stop() throws Exception {
     * //        applicationContext.stop();
     * //    }
     */
}
