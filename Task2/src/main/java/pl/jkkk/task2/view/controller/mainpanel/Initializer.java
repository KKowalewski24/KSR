package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import pl.jkkk.task2.logic.model.enumtype.ConjuctionType;
import pl.jkkk.task2.logic.model.enumtype.QualifierSummarizationType;

import java.util.stream.Collectors;

import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizerBasic;
    private ComboBox comboBoxConjunction;
    private ComboBox comboBoxSummarizeAdvanced;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQualifier,
                       ComboBox comboBoxSummarizerBasic,
                       ComboBox comboBoxConjunction,
                       ComboBox comboBoxSummarizeAdvanced) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizerBasic = comboBoxSummarizerBasic;
        this.comboBoxConjunction = comboBoxConjunction;
        this.comboBoxSummarizeAdvanced = comboBoxSummarizeAdvanced;
    }

    public void fillScene() {
        fillComboBox(comboBoxQualifier, QualifierSummarizationType.getNamesList());
        fillComboBox(comboBoxSummarizerBasic, QualifierSummarizationType.getNamesList());
        fillComboBox(comboBoxConjunction,
                ConjuctionType.getNamesList()
                        .stream()
                        .map((it) -> it.toUpperCase())
                        .collect(Collectors.toList()));
        fillComboBox(comboBoxSummarizeAdvanced, QualifierSummarizationType.getNamesList());
    }
}
    