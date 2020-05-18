package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;

import java.util.stream.Collectors;

import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQuantifier;
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizer;

    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQuantifier, ComboBox comboBoxQualifier,
                       ComboBox comboBoxSummarizer, LabelWrapperService labelWrapperService,
                       LinguisticQuantifierWrapperService quantifierWrapperService) {
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizer = comboBoxSummarizer;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
    }

    public void fillScene() {
        fillComboBox(comboBoxQuantifier, quantifierWrapperService.findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));

        fillComboBox(comboBoxQualifier, labelWrapperService.findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));

        fillComboBox(comboBoxSummarizer, labelWrapperService.findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));
    }
}
    