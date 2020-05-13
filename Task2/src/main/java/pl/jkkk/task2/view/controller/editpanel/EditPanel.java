package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;

@Component
public class EditPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private ComboBox comboBoxSelectObject;

    @FXML
    private HBox paneTextField;

    @FXML
    private VBox paneComboBoxType;
    @FXML
    private ComboBox comboBoxType;

    @FXML
    private VBox paneFunctionType;
    @FXML
    private ComboBox comboBoxFunctionType;
    @FXML
    private HBox paneFunctionTypePaneParamFirst;
    @FXML
    private HBox paneFunctionTypePaneParamSecond;
    @FXML
    private HBox paneFunctionTypePaneParamThird;
    @FXML
    private HBox paneFunctionTypePaneParamFourth;

    private Initializer initializer;

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializer = new Initializer(
                comboBoxSelectObject, paneTextField, paneComboBoxType, comboBoxType,
                paneFunctionType, comboBoxFunctionType, paneFunctionTypePaneParamFirst,
                paneFunctionTypePaneParamSecond, paneFunctionTypePaneParamThird,
                paneFunctionTypePaneParamFourth
        );

        initializer.prepareStage();
    }

    public void onActionReturn(ActionEvent actionEvent) {
        StageController.setWindowDimensions(
                new WindowDimensions(MAIN_PANEL_WIDTH, MAIN_PANEL_HEIGHT));
        StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                Main.getApplicationContext());
        StageController.getApplicationStage().setResizable(false);
    }

    public void onActionConfirm(ActionEvent actionEvent) {
        //        TODO ADD SAVING DATA TO FILE

        StageController.setWindowDimensions(
                new WindowDimensions(MAIN_PANEL_WIDTH, MAIN_PANEL_HEIGHT));
        StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                Main.getApplicationContext());
        StageController.getApplicationStage().setResizable(false);
    }
}
    