package pl.jkkk.task2;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import static pl.jkkk.task2.view.constant.Constants.PATH_CSS_STYLING;
import static pl.jkkk.task2.view.constant.Constants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.Constants.TITLE_MAIN_PANEL;

//@SpringBootApplication
public class Main extends Application {

    /*------------------------ FIELDS REGION ------------------------*/
    private ConfigurableApplicationContext applicationContext;

    // TODO UNCOMMENT SPRING CONTEXT
    /*------------------------ METHODS REGION ------------------------*/
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
        launch(args);
    }
}
