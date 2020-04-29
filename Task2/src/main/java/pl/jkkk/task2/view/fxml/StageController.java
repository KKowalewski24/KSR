package pl.jkkk.task2.view.fxml;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;
import pl.jkkk.task2.view.fxml.core.FxmlStageSetup;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.io.IOException;

public class StageController {

    /*------------------------ FIELDS REGION ------------------------*/

    /*------------------------ METHODS REGION ------------------------*/
    public static Stage getApplicationStage() {
        return FxmlStageSetup.getApplicationStage();
    }

    public static WindowDimensions getWindowDimensions() {
        return FxmlStageSetup.getWindowDimensions();
    }

    public static String getGlobalCssStyling() {
        return FxmlStageSetup.getGlobalCssStyling();
    }

    public static void setGlobalCssStyling(String globalCssStyling) {
        FxmlStageSetup.setGlobalCssStyling(globalCssStyling);
    }

    public static void buildStage(Stage stage, String filePath, String title,
                                  WindowDimensions dimensions, String cssFilePath,
                                  ConfigurableApplicationContext context) {
        try {
            FxmlStageSetup.buildStage(stage, filePath, title, dimensions, cssFilePath, context);
        } catch (IOException | IllegalStateException e) {
            PopOutWindow.messageBox("Stage Building Error",
                    "Stage cannot be properly built", Alert.AlertType.ERROR);
        }
    }

    public static void loadStage(String filePath, String title,
                                 ConfigurableApplicationContext context) {
        try {
            FxmlStageSetup.loadStage(filePath, title, context);
        } catch (IOException | IllegalStateException e) {
            PopOutWindow.messageBox("Stage Loading Error",
                    "Stage cannot be properly loaded", Alert.AlertType.ERROR);
        }
    }

    public static void reloadStage(String filePath, String title,
                                   ConfigurableApplicationContext context) {
        try {
            FxmlStageSetup.reloadStage(filePath, title, context);
        } catch (IOException | IllegalStateException e) {
            PopOutWindow.messageBox("Stage Reloading Error",
                    "Stage cannot be properly reloaded", Alert.AlertType.ERROR);
        }
    }
}
    