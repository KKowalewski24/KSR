package pl.jkkk.task2.view.controller.editpanel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.model.enumtype.ObjectType;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.view.fxml.FxHelper;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.fxml.core.WindowDimensions;

import java.net.URL;
import java.util.ResourceBundle;

import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_HEIGHT;
import static pl.jkkk.task2.view.constant.ViewConstants.MAIN_PANEL_WIDTH;
import static pl.jkkk.task2.view.constant.ViewConstants.PATH_MAIN_PANEL;
import static pl.jkkk.task2.view.constant.ViewConstants.TITLE_MAIN_PANEL;
import static pl.jkkk.task2.view.fxml.FxHelper.getSelectedTabIndex;
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
/*
        try {
            LinguisticQuantifier linguisticQuantifier =
                    linguisticQuantifierService.findByName(name);
            MembershipFunction membershipFunction
                    = linguisticQuantifier.getFuzzySet().getMembershipFunction();

            if (membershipFunction instanceof TrapezoidalMembershipFunction) {
                TrapezoidalMembershipFunction trapezoidalMembershipFunction
                        = (TrapezoidalMembershipFunction) membershipFunction;

                initializer.updateEditor(
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

                initializer.updateEditor(
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

                initializer.updateEditor(
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
        } catch (LinguisticQuantifierNotFoundException e) {
            e.printStackTrace();
            PopOutWindow.messageBox("Linguistic Quantifier Not Found",
                    "", Alert.AlertType.WARNING);
        }
*/
    }

    @FXML
    private void OnMouseClickedListViewSummarizer(MouseEvent mouseEvent) {
        String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);
        //        TODO UNCOMMENT THIS CODE BELOW
        /*
        try {
            pl.jkkk.task2.logic.fuzzy.linguistic.Label label = labelService.findByName(name);
            labelService.deleteByName(name);
            MembershipFunction membershipFunction = label.getFuzzySet().getMembershipFunction();

            if (membershipFunction instanceof TrapezoidalMembershipFunction) {
                TrapezoidalMembershipFunction trapezoidalMembershipFunction
                        = (TrapezoidalMembershipFunction) membershipFunction;

                initializer.updateEditor(
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

                initializer.updateEditor(
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

                initializer.updateEditor(
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
        } catch (LabelNotFoundException e) {
            e.printStackTrace();
            PopOutWindow.messageBox("Label Not Found",
                    "", Alert.AlertType.WARNING);
        }
*/
    }

    @FXML
    private void onActionButtonAdd(ActionEvent actionEvent) {
        paneRightSide.setVisible(true);
    }

    @FXML
    private void onActionButtonEdit(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        initializer.prepareTabPane();
    }

    @FXML
    private void onActionButtonRemove(ActionEvent actionEvent) {
        removeQuantifierOrSummarizer();
        initializer.prepareTabPane();
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

        initializer.prepareTabPane();
        paneRightSide.setVisible(false);
    }

    /*--------------------------------------------------------------------------------------------*/
    private void removeQuantifierOrSummarizer() {
        Integer selectedTab = getSelectedTabIndex(tabPane);

        switch (selectedTab) {
            // Quantifier
            case 0: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewQuantifier);
                System.out.println(name);
                //    TODO
//                linguisticQuantifierService.deleteByName(name);
                break;
            }
            // Summarizer
            case 1: {
                String name = FxHelper.<String>getSelectedItemFromListView(listViewSummarizer);
                System.out.println(name);
                //    TODO
//                labelService.deleteByName(name);
                break;
            }
        }
    }
}
    