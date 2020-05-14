package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.jkkk.task2.logic.service.label.LabelService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierService;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizer;
    private TextField textFieldSaveSummarizationNumber;

    private final LabelService labelService;
    private final LinguisticQuantifierService quantifierService;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQualifier, ComboBox comboBoxSummarizer,
                       TextField textFieldSaveSummarizationNumber, LabelService labelService,
                       LinguisticQuantifierService quantifierService) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizer = comboBoxSummarizer;
        this.textFieldSaveSummarizationNumber = textFieldSaveSummarizationNumber;
        this.labelService = labelService;
        this.quantifierService = quantifierService;
    }

    public void fillScene() {
        //        fillComboBox(comboBoxQualifier, );
        //        fillComboBox(comboBoxSummarizer, );
        textFieldSaveSummarizationNumber.setText("0");
    }
}
    