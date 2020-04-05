package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import pl.jkkk.task2.logic.model.ConjuctionType;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pl.jkkk.task2.logic.constant.LogicConstants.QUALIFIER_SUMMARIZER_OPERATIONS;
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
        fillComboBox(comboBoxQualifier, QUALIFIER_SUMMARIZER_OPERATIONS);
        fillComboBox(comboBoxSummarizerBasic, QUALIFIER_SUMMARIZER_OPERATIONS);
        fillComboBox(comboBoxConjunction, Stream.of(ConjuctionType.values())
                .collect(Collectors.toCollection(ArrayList::new)));
        fillComboBox(comboBoxSummarizeAdvanced, QUALIFIER_SUMMARIZER_OPERATIONS);
    }
}
    