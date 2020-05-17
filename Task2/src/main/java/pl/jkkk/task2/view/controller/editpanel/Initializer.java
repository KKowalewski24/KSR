package pl.jkkk.task2.view.controller.editpanel;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import pl.jkkk.task2.logic.model.enumtype.FunctionType;
import pl.jkkk.task2.logic.model.enumtype.LinguisticVariableType;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;

import java.util.stream.Collectors;

import static pl.jkkk.task2.view.fxml.FxHelper.clearAndFillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.getTextFieldFromPaneAndSetValue;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.setLabelTextInPane;
import static pl.jkkk.task2.view.fxml.FxHelper.setPaneVisibility;
import static pl.jkkk.task2.view.fxml.FxHelper.switchComboBoxValue;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String FUNCTION_POINT_A = "Point A";
    public static final String FUNCTION_POINT_B = "Point B";
    public static final String FUNCTION_POINT_C = "Point C";
    public static final String FUNCTION_POINT_D = "Point D";
    public static final String FUNCTION_CENTER = "Center";
    public static final String FUNCTION_WIDTH = "Width";

    /*----- LEFT SIDE -----*/
    private TabPane tabPane;
    private ListView listViewQuantifier;
    private ListView listViewSummarizer;

    /*----- RIGHT SIDE -----*/
    private VBox paneRightSide;
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
    private Button buttonConfirm;

    private final LinguisticQuantifierWrapperService linguisticQuantifierWrapperService;
    private final LabelWrapperService labelWrapperService;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(TabPane tabPane, ListView listViewQuantifier, ListView listViewSummarizer,
                       Button buttonConfirm, VBox paneRightSide, ComboBox comboBoxSelectObject,
                       HBox paneTextField, VBox paneComboBoxType, ComboBox comboBoxType,
                       VBox paneFunctionType, ComboBox comboBoxFunctionType,
                       HBox paneFunctionTypePaneParamFirst, HBox paneFunctionTypePaneParamSecond,
                       HBox paneFunctionTypePaneParamThird, HBox paneFunctionTypePaneParamFourth,
                       LinguisticQuantifierWrapperService linguisticQuantifierWrapperService,
                       LabelWrapperService labelWrapperService) {
        this.tabPane = tabPane;
        this.listViewQuantifier = listViewQuantifier;
        this.listViewSummarizer = listViewSummarizer;
        this.buttonConfirm = buttonConfirm;
        this.paneRightSide = paneRightSide;
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
        this.linguisticQuantifierWrapperService = linguisticQuantifierWrapperService;
        this.labelWrapperService = labelWrapperService;
    }

    public void updateEditor(String selectObjectValue, Pane textFieldPane,
                             String textFieldNameValue, String typeComboBoxValue,
                             String comboBoxFunctionTypeValue,
                             Pane paneParamFirst, String textFieldParamFirstValue,
                             Pane paneParamSecond, String textFieldParamSecondValue,
                             Pane paneParamThird, String textFieldParamThirdValue,
                             Pane paneParamFourth, String textFieldParamFourthValue) {
        updateEditorTopPart(selectObjectValue, textFieldPane, textFieldNameValue,
                typeComboBoxValue, comboBoxFunctionTypeValue);

        getTextFieldFromPaneAndSetValue(paneParamFirst, 1, textFieldParamFirstValue);
        getTextFieldFromPaneAndSetValue(paneParamSecond, 1, textFieldParamSecondValue);
        getTextFieldFromPaneAndSetValue(paneParamThird, 1, textFieldParamThirdValue);
        getTextFieldFromPaneAndSetValue(paneParamFourth, 1, textFieldParamFourthValue);
    }

    public void updateEditor(String selectObjectValue, Pane textFieldPane,
                             String textFieldNameValue, String typeComboBoxValue,
                             String comboBoxFunctionTypeValue,
                             Pane paneParamFirst, String textFieldParamFirstValue,
                             Pane paneParamSecond, String textFieldParamSecondValue,
                             Pane paneParamThird, String textFieldParamThirdValue) {
        updateEditorTopPart(selectObjectValue, textFieldPane, textFieldNameValue,
                typeComboBoxValue, comboBoxFunctionTypeValue);

        getTextFieldFromPaneAndSetValue(paneParamFirst, 1, textFieldParamFirstValue);
        getTextFieldFromPaneAndSetValue(paneParamSecond, 1, textFieldParamSecondValue);
        getTextFieldFromPaneAndSetValue(paneParamThird, 1, textFieldParamThirdValue);
    }

    public void updateEditor(String selectObjectValue, Pane textFieldPane,
                             String textFieldNameValue, String typeComboBoxValue,
                             String comboBoxFunctionTypeValue,
                             Pane paneParamFirst, String textFieldParamFirstValue,
                             Pane paneParamSecond, String textFieldParamSecondValue) {
        updateEditorTopPart(selectObjectValue, textFieldPane, textFieldNameValue,
                typeComboBoxValue, comboBoxFunctionTypeValue);

        getTextFieldFromPaneAndSetValue(paneParamFirst, 1, textFieldParamFirstValue);
        getTextFieldFromPaneAndSetValue(paneParamSecond, 1, textFieldParamSecondValue);
    }

    private void updateEditorTopPart(String selectObjectValue, Pane textFieldPane,
                                     String textFieldNameValue, String typeComboBoxValue,
                                     String comboBoxFunctionTypeValue) {
        switchComboBoxValue(comboBoxSelectObject, selectObjectValue);
        getTextFieldFromPaneAndSetValue(textFieldPane, 1, textFieldNameValue);
        switchComboBoxValue(comboBoxType, typeComboBoxValue);
        switchComboBoxValue(comboBoxFunctionType, comboBoxFunctionTypeValue);
    }

    /*--------------------------------------------------------------------------------------------*/
    public void prepareStage() {
        prepareTabPane();
        fillComboBoxes();
        prepareTypeComboBox(ObjectType.QUANTIFIER);

        setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, FUNCTION_POINT_A);
        setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, FUNCTION_POINT_B);
        setLabelTextInPane(paneFunctionTypePaneParamThird, 0, FUNCTION_POINT_C);
        setLabelTextInPane(paneFunctionTypePaneParamFourth, 0, FUNCTION_POINT_D);

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

    public void prepareTabPane() {
        clearAndFillListView(listViewQuantifier, linguisticQuantifierWrapperService
                .findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));
        clearAndFillListView(listViewSummarizer, labelWrapperService
                .findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));
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
            fillComboBox(comboBoxType, LinguisticVariableType.getNamesList());
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

                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, FUNCTION_POINT_A);
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, FUNCTION_POINT_B);
                setLabelTextInPane(paneFunctionTypePaneParamThird, 0, FUNCTION_POINT_C);
                setLabelTextInPane(paneFunctionTypePaneParamFourth, 0, FUNCTION_POINT_D);

            } else if (FunctionType.TRIANGULAR == FunctionType.fromString(selectedFunction)) {
                setPaneVisibility(true,
                        paneFunctionTypePaneParamFirst,
                        paneFunctionTypePaneParamSecond,
                        paneFunctionTypePaneParamThird);
                setPaneVisibility(false, paneFunctionTypePaneParamFourth);

                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, FUNCTION_POINT_A);
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, FUNCTION_POINT_B);
                setLabelTextInPane(paneFunctionTypePaneParamThird, 0, FUNCTION_POINT_C);

            } else if (FunctionType.GAUSSIAN == FunctionType.fromString(selectedFunction)) {
                setPaneVisibility(true,
                        paneFunctionTypePaneParamFirst,
                        paneFunctionTypePaneParamSecond);
                setPaneVisibility(false,
                        paneFunctionTypePaneParamThird,
                        paneFunctionTypePaneParamFourth);

                setLabelTextInPane(paneFunctionTypePaneParamFirst, 0, FUNCTION_CENTER);
                setLabelTextInPane(paneFunctionTypePaneParamSecond, 0, FUNCTION_WIDTH);
            }
        }));
    }
}
    