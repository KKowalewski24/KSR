package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;

import java.util.stream.Collectors;

import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizer;
    private TextField textFieldSaveSummarizationNumber;

    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQualifier, ComboBox comboBoxSummarizer,
                       TextField textFieldSaveSummarizationNumber,
                       LabelWrapperService labelWrapperService,
                       LinguisticQuantifierWrapperService quantifierWrapperService) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizer = comboBoxSummarizer;
        this.textFieldSaveSummarizationNumber = textFieldSaveSummarizationNumber;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
    }

    public void fillScene() {
        fillComboBox(comboBoxQualifier, quantifierWrapperService.findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));

        fillComboBox(comboBoxSummarizer, labelWrapperService.findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));

        textFieldSaveSummarizationNumber.setText("0");
    }
}
    