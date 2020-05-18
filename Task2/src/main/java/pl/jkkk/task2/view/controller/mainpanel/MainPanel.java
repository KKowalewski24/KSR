package pl.jkkk.task2.view.controller.mainpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.EDIT_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.EDIT_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_DARK_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_CSS_LIGHT_STYLING;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_EDIT_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_EDIT_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.changeTheme;
import static pl.jkkk.task2.view.fxml.FxHelper.showProgressIndicator;

@Component
public class MainPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxQuantifier;
    @FXML
    private ComboBox comboBoxQualifier;
    @FXML
    private ComboBox comboBoxSummarizer;
    @FXML
    private ListView listViewResults;
    @FXML
    private ProgressIndicator progressIndicator;

    private Initializer initializer;
    private Loader loader;

    private PollutionService pollutionService;
    private LabelWrapperService labelWrapperService;
    private LinguisticQuantifierWrapperService quantifierService;

    /*------------------------ METHODS REGION ------------------------*/
    public MainPanel() {
    }

    @Autowired
    public MainPanel(PollutionService pollutionService, LabelWrapperService labelWrapperService,
                     LinguisticQuantifierWrapperService quantifierService) {
        this.pollutionService = pollutionService;
        this.labelWrapperService = labelWrapperService;
        this.quantifierService = quantifierService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializer = new Initializer(
                comboBoxQuantifier, comboBoxQualifier, comboBoxSummarizer,
                labelWrapperService, quantifierService
        );

        loader = new Loader(
                comboBoxQuantifier, comboBoxSummarizer,
                listViewResults, pollutionService, labelWrapperService,
                quantifierService
        );

        initializer.fillScene();
    }

    @FXML
    private void onActionButtonGenerateBasicSummarization(ActionEvent actionEvent) {
        showProgressIndicator(() -> loader.generateBasicSummarization(), progressIndicator);
    }

    @FXML
    private void onActionButtonOpenEditWindow(ActionEvent actionEvent) {
        StageController.setWindowDimensions(
                new WindowDimensions(EDIT_PANEL_WIDTH, EDIT_PANEL_HEIGHT));
        StageController.reloadStage(PATH_EDIT_PANEL, TITLE_EDIT_PANEL,
                Main.getApplicationContext());
        StageController.getApplicationStage().setResizable(false);
    }

    @FXML
    private void onActionClearSummarization(ActionEvent actionEvent) {
        loader.clearSummarization();
    }

    @FXML
    private void onActionSaveSummarization(ActionEvent actionEvent) {
        try {
            loader.saveSummarization();
        } catch (NumberFormatException e) {
            PopOutWindow.messageBox("Wrong Value",
                    "Wrong value - must be a number", Alert.AlertType.WARNING);
        }
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
    