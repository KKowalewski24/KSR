package pl.jkkk.task2.view.controller.editpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import pl.jkkk.task2.logic.model.enumtype.FunctionType;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;

import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.setLabelTextInPane;
import static pl.jkkk.task2.view.fxml.FxHelper.setPaneVisibility;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxSelectObject;

    private HBox paneTextField;

    private VBox paneComboBoxType;
    private ComboBox comboBoxType;

    private VBox paneFunctionType;
    private ComboBox comboBoxFunctionType;
    private HBox paneFunctionTypePaneParamFirst;
    private HBox paneFunctionTypePaneParamSecond;
    private HBox paneFunctionTypePaneParamThird;
    private HBox paneFunctionTypePaneParamFourth;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxSelectObject, HBox paneTextField,
                       VBox paneComboBoxType, ComboBox comboBoxType, VBox paneFunctionType,
                       ComboBox comboBoxFunctionType, HBox paneFunctionTypePaneParamFirst,
                       HBox paneFunctionTypePaneParamSecond, HBox paneFunctionTypePaneParamThird,
                       HBox paneFunctionTypePaneParamFourth) {
        this.comboBoxSelectObject = comboBoxSelectObject;
        this.paneTextField = paneTextField;
        this.paneComboBoxType = paneComboBoxType;
        this.comboBoxType = comboBoxType;
        this.paneFunctionType = paneFunctionType;
        this.comboBoxFunctionType = comboBoxFunctionType;
        this.paneFunctionTypePaneParamFirst = paneFunctionTypePaneParamFirst;
        this.paneFunctionTypePaneParamSecond = paneFunctionTypePaneParamSecond;
        this.paneFunctionTypePaneParamThird = paneFunctionTypePaneParamThird;
        this.paneFunctionTypePaneParamFourth = paneFunctionTypePaneParamFourth;
    }

    public void prepareStage() {
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
    