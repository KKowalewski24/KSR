package pl.jkkk.task2.view.controller.mainpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import pl.jkkk.task2.view.fxml.StageController;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.Constants.PATH_CSS_STYLING;
import static pl.jkkk.task2.view.constant.Constants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.Constants.TITLE_MAIN_PANEL;

public class MainPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxQualifier;
    @FXML
    private ComboBox comboBoxSummarizerBasic;
    @FXML
    private ComboBox comboBoxConjunction;
    @FXML
    private ComboBox comboBoxSummarizeAdvanced;
    @FXML
    private ListView listViewResults;

    private Initializer initializer;
    private Loader loader;

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializer = new Initializer(
                comboBoxQualifier, comboBoxSummarizerBasic,
                comboBoxConjunction, comboBoxSummarizeAdvanced
        );

        loader = new Loader(
                comboBoxQualifier, comboBoxSummarizerBasic,
                comboBoxConjunction, comboBoxSummarizeAdvanced,
                listViewResults
        );

        initializer.fillScene();
    }

    @FXML
    private void onActionButtonGenerateBasicSummarization(ActionEvent actionEvent) {
        loader.generateBasicSummarization();
    }

    @FXML
    private void onActionButtonGenerateAdvancedSummarization(ActionEvent actionEvent) {
        loader.generateAdvancedSummarization();
    }

    @FXML
    private void onActionSaveSummarization(ActionEvent actionEvent) {
        loader.saveSummarization();
    }

    @FXML
    private void onActionButtonChangeTheme(ActionEvent actionEvent) {
        if (StageController.getGlobalCssStyling() != null) {
            StageController.setGlobalCssStyling(null);
            StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL);
        } else {
            StageController.setGlobalCssStyling(PATH_CSS_STYLING);
            StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL);
        }
    }

    @FXML
    private void onActionButtonReloadStage(ActionEvent actionEvent) {
        StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL);
    }

    @FXML
    private void onActionButtonCloseProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
}
    