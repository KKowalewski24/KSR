package pl.jkkk.task2.mode;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import static pl.jkkk.task2.view.constant.ViewConstants.PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_DARK_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;

public class GraphicalMode extends Application {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void start(Stage stage) throws Exception {
        StageController.buildStage(stage, PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                new WindowDimensions(PANEL_WIDTH, PANEL_HEIGHT), PATH_CSS_DARK_STYLING);
        StageController.getApplicationStage().setResizable(false);
    }

    public void main(String[] args) {
        launch(args);
    }
}
    