package pl.jkkk.task2.view.controller.mainpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.StageController;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_DARK_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_LIGHT_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.changeTheme;
import static pl.jkkk.task2.view.fxml.FxHelper.showProgressIndicator;

@Component
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
    @FXML
    private ProgressIndicator progressIndicator;

    private Initializer initializer;
    private Loader loader;

    private PollutionService pollutionService;

    /*------------------------ METHODS REGION ------------------------*/
    public MainPanel() {
    }

    @Autowired
    public MainPanel(PollutionService pollutionService) {
        this.pollutionService = pollutionService;
    }

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
        showProgressIndicator(() -> loader.generateBasicSummarization(), progressIndicator);
    }

    @FXML
    private void onActionButtonGenerateAdvancedSummarization(ActionEvent actionEvent) {
        showProgressIndicator(() -> loader.generateAdvancedSummarization(), progressIndicator);
    }

    @FXML
    private void onActionSaveSummarization(ActionEvent actionEvent) {
        loader.saveSummarization();
    }

    @FXML
    private void onActionButtonChangeTheme(ActionEvent actionEvent) {
        changeTheme(PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                PATH_CSS_DARK_STYLING, PATH_CSS_LIGHT_STYLING,
                Main.getApplicationContext());
    }

    @FXML
    private void onActionButtonReloadStage(ActionEvent actionEvent) {
        StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                Main.getApplicationContext());
    }

    @FXML
    private void onActionButtonCloseProgram(ActionEvent actionEvent) {
        System.exit(0);
    }
}
    