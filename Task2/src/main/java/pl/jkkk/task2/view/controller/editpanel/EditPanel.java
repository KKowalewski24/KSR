package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.fuzzy.membershipfunction.GaussianMembershipFunction;
import pl.jkkk.task2.logic.fuzzy.membershipfunction.MembershipFunction;
import pl.jkkk.task2.logic.fuzzy.membershipfunction.TrapezoidalMembershipFunction;
import pl.jkkk.task2.logic.fuzzy.membershipfunction.TriangularMembershipFunction;
import pl.jkkk.task2.logic.model.enumtype.FunctionType;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;
import pl.jkkk.task2.logic.service.label.LabelService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierService;
import pl.jkkk.task2.view.fxml.FxHelper;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.getSelectedTabIndex;
import static pl.jkkk.task2.view.fxml.FxHelper.getTextFieldFromPaneAndSetValue;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.setLabelTextInPane;
import static pl.jkkk.task2.view.fxml.FxHelper.setPaneVisibility;
import static pl.jkkk.task2.view.fxml.FxHelper.switchComboBoxValue;

@Component
public class EditPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String FUNCTION_POINT_A = "Point A";
    public static final String FUNCTION_POINT_B = "Point B";
    public static final String FUNCTION_POINT_C = "Point C";
    public static final String FUNCTION_POINT_D = "Point D";
    public static final String FUNCTION_CENTER = "Center";
    public static final String FUNCTION_WIDTH = "Width";

    /*----- LEFT SIDE -----*/
    @FXML
    private TabPane tabPane;
    @FXML
    private ListView listViewQuantifier;
    @FXML
    private ListView listViewSummarizer;
    @FXML
    private Button buttonConfirm;

    /*----- RIGHT SIDE -----*/
    @FXML
    private VBox paneRightSide;
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

    private final LabelService labelService;
    private final LinguisticQuantifierService linguisticQuantifierService;

    /*------------------------ METHODS REGION ------------------------*/
    public EditPanel(LabelService labelService,
                     LinguisticQuantifierService linguisticQuantifierService) {
        this.labelService = labelService;
        this.linguisticQuantifierService = linguisticQuantifierService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareStage();
    }

    @FXML
    private void OnMouseClickedListViewQuantifier(MouseEvent mouseEvent) {
        String name = FxHelper.<String>getSelectedItemFromListView(listViewQuantifier);
        LinguisticQuantifier linguisticQuantifier = linguisticQuantifierService.findByName(name);
        linguisticQuantifierService.deleteByName(name);
        MembershipFunction membershipFunction
                = linguisticQuantifier.getFuzzySet().getMembershipFunction();

        if (membershipFunction instanceof TrapezoidalMembershipFunction) {
            TrapezoidalMembershipFunction trapezoidalMembershipFunction
                    = (TrapezoidalMembershipFunction) membershipFunction;

            updateEditor(
                    ObjectType.QUANTIFIER.getName(),
                    paneTextField,
                    linguisticQuantifier.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.TRAPEZOIDAL.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(trapezoidalMembershipFunction.getA()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(trapezoidalMembershipFunction.getB()),
                    paneFunctionTypePaneParamThird,
                    String.valueOf(trapezoidalMembershipFunction.getC()),
                    paneFunctionTypePaneParamFourth,
                    String.valueOf(trapezoidalMembershipFunction.getD())
            );
        } else if (membershipFunction instanceof TriangularMembershipFunction) {
            TriangularMembershipFunction triangularMembershipFunction
                    = (TriangularMembershipFunction) membershipFunction;

            updateEditor(
                    ObjectType.QUANTIFIER.getName(),
                    paneTextField,
                    linguisticQuantifier.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.TRIANGULAR.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(triangularMembershipFunction.getA()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(triangularMembershipFunction.getB()),
                    paneFunctionTypePaneParamThird,
                    String.valueOf(triangularMembershipFunction.getC())
            );

        } else if (membershipFunction instanceof GaussianMembershipFunction) {
            GaussianMembershipFunction gaussianMembershipFunction
                    = ((GaussianMembershipFunction) membershipFunction);

            updateEditor(
                    ObjectType.QUANTIFIER.getName(),
                    paneTextField,
                    linguisticQuantifier.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.GAUSSIAN.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(gaussianMembershipFunction.getCenter()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(gaussianMembershipFunction.getWidth())
            );
        }
    }

    @FXML
    private void OnMouseClickedListViewSummarizer(MouseEvent mouseEvent) {
        String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);
        pl.jkkk.task2.logic.fuzzy.linguistic.Label label = labelService.findByName(name);
        labelService.deleteByName(name);
        MembershipFunction membershipFunction = label.getFuzzySet().getMembershipFunction();

        if (membershipFunction instanceof TrapezoidalMembershipFunction) {
            TrapezoidalMembershipFunction trapezoidalMembershipFunction
                    = (TrapezoidalMembershipFunction) membershipFunction;

            updateEditor(
                    ObjectType.SUMMARIZER.getName(),
                    paneTextField,
                    label.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.TRAPEZOIDAL.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(trapezoidalMembershipFunction.getA()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(trapezoidalMembershipFunction.getB()),
                    paneFunctionTypePaneParamThird,
                    String.valueOf(trapezoidalMembershipFunction.getC()),
                    paneFunctionTypePaneParamFourth,
                    String.valueOf(trapezoidalMembershipFunction.getD())
            );
        } else if (membershipFunction instanceof TriangularMembershipFunction) {
            TriangularMembershipFunction triangularMembershipFunction
                    = (TriangularMembershipFunction) membershipFunction;

            updateEditor(
                    ObjectType.SUMMARIZER.getName(),
                    paneTextField,
                    label.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.TRIANGULAR.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(triangularMembershipFunction.getA()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(triangularMembershipFunction.getB()),
                    paneFunctionTypePaneParamThird,
                    String.valueOf(triangularMembershipFunction.getC())
            );

        } else if (membershipFunction instanceof GaussianMembershipFunction) {
            GaussianMembershipFunction gaussianMembershipFunction
                    = ((GaussianMembershipFunction) membershipFunction);

            updateEditor(
                    ObjectType.SUMMARIZER.getName(),
                    paneTextField,
                    label.getName(),
                    // TODO CHANGE BELOW PARAM FOR REAL
                    QuantifierType.ABSOLUTE.getName(),
                    FunctionType.GAUSSIAN.getName(),
                    paneFunctionTypePaneParamFirst,
                    String.valueOf(gaussianMembershipFunction.getCenter()),
                    paneFunctionTypePaneParamSecond,
                    String.valueOf(gaussianMembershipFunction.getWidth())
            );
        }
    }

    @FXML
    private void onActionButtonAdd(ActionEvent actionEvent) {
        paneRightSide.setVisible(true);
    }

    @FXML
    private void onActionButtonEdit(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        prepareTabPane();
    }

    @FXML
    private void onActionButtonRemove(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        prepareTabPane();
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
        ObjectType objectType = ObjectType.fromString(getValueFromComboBox(comboBoxSelectObject));

        switch (objectType) {
            case QUANTIFIER: {
                // TODO ADD VALUES IN CONSTRUCTOR
                //  linguisticQuantifierService.save(new LinguisticQuantifier());
                break;
            }
            case SUMMARIZER: {
                // TODO ADD VALUES IN CONSTRUCTOR
                //  labelService.save(new pl.jkkk.task2.logic.fuzzy.linguistic.Label());
                break;
            }
        }

        prepareTabPane();
        paneRightSide.setVisible(false);
    }

    /*--------------------------------------------------------------------------------------------*/
    private void removeQuantifierOrSummarizer() {
        Integer selectedTab = getSelectedTabIndex(tabPane);

        switch (selectedTab) {
            // Quantifier
            case 0: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewQuantifier);
                linguisticQuantifierService.deleteByName(name);
                break;
            }
            // Summarizer
            case 1: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);
                labelService.deleteByName(name);
                break;
            }
        }
    }

    /*--------------------------------------------------------------------------------------------*/
    private void updateEditor(String selectObjectValue, Pane textFieldPane,
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

    private void updateEditor(String selectObjectValue, Pane textFieldPane,
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

    private void updateEditor(String selectObjectValue, Pane textFieldPane,
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
    private void prepareStage() {
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

    private void prepareTabPane() {
        fillListView(listViewQuantifier, labelService
                .findAll()
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList()));
        fillListView(listViewSummarizer, linguisticQuantifierService
                .findAll()
                .stream()
                .map((it) -> it.getName())
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
            fillComboBox(comboBoxType, labelService
                    .findAll()
                    .stream()
                    .map((it) -> it.getLinguisticVariable())
                    .collect(Collectors.toList()));
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
    