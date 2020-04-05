package pl.jkkk.task2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;

//@SpringBootApplication
public class Main extends Application {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final boolean IS_LOGGING_DATA = true;
    private static List<String> mainArgs;

    private ConfigurableApplicationContext applicationContext;

    // TODO UNCOMMENT SPRING CONTEXT
    /*------------------------ METHODS REGION ------------------------*/
    public static List<String> getMainArgs() {
        return Collections.unmodifiableList(mainArgs);
    }

    @Override
    public void init() throws Exception {
        //        applicationContext = SpringApplication.run(Main.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StageController.buildStage(stage, PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                new WindowDimensions(1200, 700), PATH_CSS_STYLING);
        StageController.getApplicationStage().setResizable(false);
    }

    @Override
    public void stop() throws Exception {
        //        applicationContext.stop();
    }

    public static void main(String[] args) {
        mainArgs = Arrays.asList(args);
        launch(args);
    }
}
