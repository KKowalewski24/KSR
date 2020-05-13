package pl.jkkk.task2.view.controller.editpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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


}
    