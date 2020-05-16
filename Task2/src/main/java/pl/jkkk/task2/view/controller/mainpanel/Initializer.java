package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizer;
    private TextField textFieldSaveSummarizationNumber;

    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierService;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQualifier, ComboBox comboBoxSummarizer,
                       TextField textFieldSaveSummarizationNumber, LabelWrapperService labelWrapperService,
                       LinguisticQuantifierWrapperService quantifierService) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizer = comboBoxSummarizer;
        this.textFieldSaveSummarizationNumber = textFieldSaveSummarizationNumber;
        this.labelWrapperService = labelWrapperService;
        this.quantifierService = quantifierService;
    }

    public void fillScene() {
        //        fillComboBox(comboBoxQualifier, );
        //        fillComboBox(comboBoxSummarizer, );
        textFieldSaveSummarizationNumber.setText("0");
    }
}
    