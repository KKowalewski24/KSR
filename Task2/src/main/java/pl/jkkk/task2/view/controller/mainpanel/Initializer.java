package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizer;
    private TextField textFieldSaveSummarizationNumber;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQualifier,
                       ComboBox comboBoxSummarizer,
                       TextField textFieldSaveSummarizationNumber) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizer = comboBoxSummarizer;
        this.textFieldSaveSummarizationNumber = textFieldSaveSummarizationNumber;
    }

    public void fillScene() {
        //        fillComboBox(comboBoxQualifier, QualifierSummarizationType.getNamesList());
        //        fillComboBox(comboBoxSummarizerBasic, QualifierSummarizationType.getNamesList());
        textFieldSaveSummarizationNumber.setText("0");
    }
}
    