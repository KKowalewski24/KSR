package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.model.enumtype.FunctionType;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.getSelectedTabIndex;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.setLabelTextInPane;
import static pl.jkkk.task2.view.fxml.FxHelper.setPaneVisibility;

@Component
public class EditPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    @FXML
    private TabPane tabPane;
    @FXML
    private ListView listViewQuantifier;
    @FXML
    private ListView listViewSummarizer;

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

    /*------------------------ METHODS REGION ------------------------*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareStage();
    }

    @FXML
    private void onActionButtonAdd(ActionEvent actionEvent) {
        Integer selectedTab = getSelectedTabIndex(tabPane);

        switch (selectedTab) {
            // Quantifier
            case 0: {

                break;
            }
            // Summarizer
            case 1: {

                break;
            }
        }
    }

    @FXML
    private void onActionButtonRemove(ActionEvent actionEvent) {
        Integer selectedTab = getSelectedTabIndex(tabPane);

        switch (selectedTab) {
            case 0: {

                break;
            }
            case 1: {

                break;
            }
        }
    }

    @FXML
    private void onActionReturn(ActionEvent actionEvent) {
        StageController.setWindowDimensions(
                new WindowDimensions(MAIN_PANEL_WIDTH, MAIN_PANEL_HEIGHT));
        StageController.reloadStage(PATH_MAIN_PANEL, TITLE_MAIN_PANEL,
                Main.getApplicationContext());
        StageController.getApplicationStage().setResizable(false);
    }

    @FXML
    private void onActionConfirm(ActionEvent actionEvent) {
        //        TODO ADD SAVING DATA TO FILE
        prepareStage();
    }

    /*--------------------------------------------------------------------------------------------*/
    public void prepareStage() {
        fillTabPane();
        fillComboBoxes();
        prepareTypeComboBox(ObjectType.QUANTIFIER);

        setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, "todo");
        setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, "todo");
        setLabelTextInPane(paneFunctionTypePaneParamThird, 0, "todo");
        setLabelTextInPane(paneFunctionTypePaneParamFourth, 0, "todo");

        comboBoxSelectObject.setOnAction((event) -> {
            final String selectedObject = getValueFromComboBox(comboBoxSelectObject);
            Label labelTextField = (Label) paneTextField.getChildren().get(0);

            if (ObjectType.QUANTIFIER == ObjectType.fromString(selectedObject)) {
                labelTextField.setText("Name");
                prepareTypeComboBox(ObjectType.QUANTIFIER);

            } else if (ObjectType.SUMMARIZER == ObjectType.fromString(selectedObject)) {
                labelTextField.setText("Label Name");
                prepareTypeComboBox(ObjectType.SUMMARIZER);
            }
        });

        prepareFunctionTypeComboBox();
    }

    private void fillTabPane() {
        //        todo
        //        fillListView(listViewQuantifier, );
        //        fillListView(listViewSummarizer, );
    }

    private void fillComboBoxes() {
        fillComboBox(comboBoxSelectObject, ObjectType.getNamesList());
        fillComboBox(comboBoxFunctionType, FunctionType.getNamesList());
    }

    private void prepareTypeComboBox(ObjectType objectType) {
        Label label = (Label) paneComboBoxType.getChildren().get(0);

        if (ObjectType.QUANTIFIER == objectType) {
            label.setText("Type");
            fillComboBox(comboBoxType, QuantifierType.getNamesList());
        } else if (ObjectType.SUMMARIZER == objectType) {
            label.setText("Linguistic Variable");
            //            fillComboBox(comboBoxType, /*todo*/);
        }
    }

    private void prepareFunctionTypeComboBox() {
        comboBoxFunctionType.setOnAction(((event) -> {
            final String selectedFunction = getValueFromComboBox(comboBoxFunctionType);

            if (FunctionType.TRAPEZOIDAL == FunctionType.fromString(selectedFunction)) {
                setPaneVisibility(true,
                        paneFunctionTypePaneParamFirst,
                        paneFunctionTypePaneParamSecond,
                        paneFunctionTypePaneParamThird,
                        paneFunctionTypePaneParamFourth);

                //            TODO SET NAMES
                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamThird, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamFourth, 0, "todo");
            } else if (FunctionType.TRIANGULAR == FunctionType.fromString(selectedFunction)) {
                setPaneVisibility(true,
                        paneFunctionTypePaneParamFirst,
                        paneFunctionTypePaneParamSecond,
                        paneFunctionTypePaneParamThird);

                setPaneVisibility(false, paneFunctionTypePaneParamFourth);
                //            TODO SET NAMES
                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamThird, 0, "todo");

            } else if (FunctionType.GAUSSIAN == FunctionType.fromString(selectedFunction)) {
                setPaneVisibility(true,
                        paneFunctionTypePaneParamFirst,
                        paneFunctionTypePaneParamSecond);

                setPaneVisibility(false,
                        paneFunctionTypePaneParamThird,
                        paneFunctionTypePaneParamFourth);
                //            TODO SET NAMES
                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, "todo");
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, "todo");
            }

        }));
    }
}
    