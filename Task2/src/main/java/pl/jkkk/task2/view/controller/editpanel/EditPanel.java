package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.exception.LabelNotFoundException;
import pl.jkkk.task2.logic.exception.LinguisticQuantifierNotFoundException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.fuzzy.set.FuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.GaussianFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.TrapezoidalFuzzySet;
import pl.jkkk.task2.logic.fuzzy.set.TriangularFuzzySet;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.model.enumtype.FunctionType;
import pl.jkkk.task2.logic.model.enumtype.LinguisticVariableType;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.view.fxml.FxHelper;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.getSelectedTabIndex;
import static pl.jkkk.task2.view.fxml.FxHelper.getTextFieldFromPaneAndGetValue;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;

@Component
public class EditPanel implements Initializable {

    /*------------------------ FIELDS REGION ------------------------*/
    /*----- LEFT SIDE -----*/
    @FXML
    private TabPane tabPane;
    @FXML
    private ListView listViewQuantifier;
    @FXML
    private ListView listViewSummarizer;

    /*----- RIGHT SIDE -----*/
    @FXML
    private Button buttonHideDetails;
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

    @FXML
    private Button buttonConfirm;

    private final LinguisticQuantifierWrapperService linguisticQuantifierWrapperService;
    private final LabelWrapperService labelWrapperService;
    private Initializer initializer;

    /*------------------------ METHODS REGION ------------------------*/
    public EditPanel(LabelWrapperService labelWrapperService,
                     LinguisticQuantifierWrapperService linguisticQuantifierWrapperService) {
        this.labelWrapperService = labelWrapperService;
        this.linguisticQuantifierWrapperService = linguisticQuantifierWrapperService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializer = new Initializer(
                tabPane, listViewQuantifier, listViewSummarizer, buttonConfirm,
                paneRightSide, comboBoxSelectObject, paneTextField, paneComboBoxType,
                comboBoxType, paneFunctionType, comboBoxFunctionType,
                paneFunctionTypePaneParamFirst, paneFunctionTypePaneParamSecond,
                paneFunctionTypePaneParamThird, paneFunctionTypePaneParamFourth,
                linguisticQuantifierWrapperService, labelWrapperService
        );

        initializer.prepareStage();
    }

    @FXML
    private void OnMouseClickedListViewQuantifier(MouseEvent mouseEvent) {
        String name = FxHelper.<String>getSelectedItemFromListView(listViewQuantifier);
        try {
            LinguisticQuantifier quantifier = linguisticQuantifierWrapperService.findByName(name);
            FuzzySet fuzzySet = quantifier.getFuzzySet();

            if (fuzzySet instanceof TrapezoidalFuzzySet) {
                TrapezoidalFuzzySet trapezoidalFuzzySet = (TrapezoidalFuzzySet) fuzzySet;

                initializer.updateEditor(
                        ObjectType.QUANTIFIER.getName(),
                        paneTextField,
                        quantifier.getName(),
                        quantifier.getQuantifierType().getName(),
                        FunctionType.TRAPEZOIDAL.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(trapezoidalFuzzySet.getA()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(trapezoidalFuzzySet.getB()),
                        paneFunctionTypePaneParamThird,
                        String.valueOf(trapezoidalFuzzySet.getC()),
                        paneFunctionTypePaneParamFourth,
                        String.valueOf(trapezoidalFuzzySet.getD())
                );
            } else if (fuzzySet instanceof TriangularFuzzySet) {
                TriangularFuzzySet triangularMembershipFunction = (TriangularFuzzySet) fuzzySet;

                initializer.updateEditor(
                        ObjectType.QUANTIFIER.getName(),
                        paneTextField,
                        quantifier.getName(),
                        quantifier.getQuantifierType().getName(),
                        FunctionType.TRIANGULAR.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(triangularMembershipFunction.getA()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(triangularMembershipFunction.getB()),
                        paneFunctionTypePaneParamThird,
                        String.valueOf(triangularMembershipFunction.getC())
                );
            } else if (fuzzySet instanceof GaussianFuzzySet) {
                GaussianFuzzySet gaussianMembershipFunction = ((GaussianFuzzySet) fuzzySet);

                initializer.updateEditor(
                        ObjectType.QUANTIFIER.getName(),
                        paneTextField,
                        quantifier.getName(),
                        quantifier.getQuantifierType().getName(),
                        FunctionType.GAUSSIAN.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(gaussianMembershipFunction.getCenter()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(gaussianMembershipFunction.getWidth())
                );
            }
        } catch (LinguisticQuantifierNotFoundException e) {
            e.printStackTrace();
            PopOutWindow.messageBox("Linguistic Quantifier Not Found",
                    "", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void OnMouseClickedListViewSummarizer(MouseEvent mouseEvent) {
        String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);

        try {
            Label label = labelWrapperService.findByName(name);
            FuzzySet fuzzySet = label.getFuzzySet();

            if (fuzzySet instanceof TrapezoidalFuzzySet) {
                TrapezoidalFuzzySet trapezoidalFuzzySet = (TrapezoidalFuzzySet) fuzzySet;

                initializer.updateEditor(
                        ObjectType.SUMMARIZER.getName(),
                        paneTextField,
                        label.getName(),
                        label.getLinguisticVariable().getName(),
                        FunctionType.TRAPEZOIDAL.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(trapezoidalFuzzySet.getA()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(trapezoidalFuzzySet.getB()),
                        paneFunctionTypePaneParamThird,
                        String.valueOf(trapezoidalFuzzySet.getC()),
                        paneFunctionTypePaneParamFourth,
                        String.valueOf(trapezoidalFuzzySet.getD())
                );
            } else if (fuzzySet instanceof TriangularFuzzySet) {
                TriangularFuzzySet triangularMembershipFunction = (TriangularFuzzySet) fuzzySet;

                initializer.updateEditor(
                        ObjectType.SUMMARIZER.getName(),
                        paneTextField,
                        label.getName(),
                        label.getLinguisticVariable().getName(),
                        FunctionType.TRIANGULAR.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(triangularMembershipFunction.getA()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(triangularMembershipFunction.getB()),
                        paneFunctionTypePaneParamThird,
                        String.valueOf(triangularMembershipFunction.getC())
                );
            } else if (fuzzySet instanceof GaussianFuzzySet) {
                GaussianFuzzySet gaussianMembershipFunction = ((GaussianFuzzySet) fuzzySet);

                initializer.updateEditor(
                        ObjectType.SUMMARIZER.getName(),
                        paneTextField,
                        label.getName(),
                        label.getLinguisticVariable().getName(),
                        FunctionType.GAUSSIAN.getName(),
                        paneFunctionTypePaneParamFirst,
                        String.valueOf(gaussianMembershipFunction.getCenter()),
                        paneFunctionTypePaneParamSecond,
                        String.valueOf(gaussianMembershipFunction.getWidth())
                );
            }

        } catch (LabelNotFoundException e) {
            e.printStackTrace();
            PopOutWindow.messageBox("Label Not Found",
                    "", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void onActionButtonAdd(ActionEvent actionEvent) {
        paneRightSide.setVisible(true);
        buttonConfirm.setVisible(true);
    }

    @FXML
    private void onActionButtonShowDetails(ActionEvent actionEvent) {
        paneRightSide.setVisible(true);
        buttonConfirm.setVisible(false);
        buttonHideDetails.setVisible(true);
    }

    @FXML
    private void onActionButtonEdit(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        paneRightSide.setVisible(true);
		buttonHideDetails.setVisible(false);
        buttonConfirm.setVisible(true);
        initializer.prepareTabPane();
    }

    @FXML
    private void onActionButtonRemove(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        buttonConfirm.setVisible(true);
        initializer.prepareTabPane();
    }

    @FXML
    private void onActionHideDetails(ActionEvent actionEvent) {
        paneRightSide.setVisible(false);
        buttonHideDetails.setVisible(false);
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
                onActionConfirmCaseQuantifier();
                break;
            }
            case SUMMARIZER: {
                onActionConfirmSummarizer();
                break;
            }
        }

        initializer.prepareTabPane();
        paneRightSide.setVisible(false);
    }

    /*--------------------------------------------------------------------------------------------*/
    private void onActionConfirmCaseQuantifier() {
        FunctionType functionType = FunctionType
                .fromString(getValueFromComboBox(comboBoxFunctionType));
        LinguisticQuantifierWrapper quantifierWrapper = new LinguisticQuantifierWrapper();

        try {
            switch (functionType) {
                case TRAPEZOIDAL: {
                    quantifierWrapper.serialize(new LinguisticQuantifier(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new TrapezoidalFuzzySet<Double>(
                                    x -> x,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamThird, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFourth, 1))
                            ),
                            QuantifierType.fromString(getValueFromComboBox(comboBoxType))
                    ));
                    break;
                }
                case TRIANGULAR: {
                    quantifierWrapper.serialize(new LinguisticQuantifier(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new TriangularFuzzySet<Double>(
                                    x -> x,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamThird, 1))
                            ),
                            QuantifierType.fromString(getValueFromComboBox(comboBoxType))
                    ));
                    break;
                }
                case GAUSSIAN: {
                    quantifierWrapper.serialize(new LinguisticQuantifier(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new GaussianFuzzySet<Double>(
                                    x -> x,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1))
                            ),
                            QuantifierType.fromString(getValueFromComboBox(comboBoxType))
                    ));
                    break;
                }
            }

            linguisticQuantifierWrapperService.save(quantifierWrapper);
        } catch (NumberFormatException e) {
            PopOutWindow.messageBox("", "Wrong type of data", Alert.AlertType.WARNING);
        }
    }

    private void onActionConfirmSummarizer() {
        FunctionType functionType = FunctionType
                .fromString(getValueFromComboBox(comboBoxFunctionType));
        LabelWrapper labelWrapper = new LabelWrapper();
        try {
            final LinguisticVariable<Pollution> linguisticVariable =
                    LinguisticVariableType.getObjectFromString(getValueFromComboBox(comboBoxType));
            switch (functionType) {
                case TRAPEZOIDAL: {
                    labelWrapper.serialize(new Label<>(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new TrapezoidalFuzzySet<Pollution>(
                                    linguisticVariable::extractAttribute,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamThird, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFourth, 1))
                            ),
                            linguisticVariable
                    ));

                    break;
                }
                case TRIANGULAR: {
                    labelWrapper.serialize(new Label<>(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new TriangularFuzzySet<Pollution>(
                                    linguisticVariable::extractAttribute,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamThird, 1))
                            ),
                            linguisticVariable
                    ));

                    break;
                }
                case GAUSSIAN: {
                    labelWrapper.serialize(new Label<>(
                            getTextFieldFromPaneAndGetValue(paneTextField, 1),
                            new GaussianFuzzySet<Pollution>(
                                    linguisticVariable::extractAttribute,
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamFirst, 1)),
                                    Double.parseDouble(getTextFieldFromPaneAndGetValue(paneFunctionTypePaneParamSecond, 1))
                            ),
                            linguisticVariable
                    ));

                    break;
                }
            }

            labelWrapperService.save(labelWrapper);
        } catch (NumberFormatException e) {
            PopOutWindow.messageBox("", "Wrong type of data", Alert.AlertType.WARNING);
        }
    }

    /*--------------------------------------------------------------------------------------------*/
    private void removeQuantifierOrSummarizer() {
        Integer selectedTab = getSelectedTabIndex(tabPane);

        switch (selectedTab) {
            // Quantifier
            case 0: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewQuantifier);
                linguisticQuantifierWrapperService.deleteByName(name);
                break;
            }
            // Summarizer
            case 1: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);
                labelWrapperService.deleteByName(name);
                break;
            }
        }
    }
}
    