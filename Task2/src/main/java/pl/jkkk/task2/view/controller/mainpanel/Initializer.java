package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.view.model.CustomComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.jkkk.task2.view.constant.ViewConstants.DEACTIVATED;
import static pl.jkkk.task2.view.constant.ViewConstants.SELECT_ITEM;
import static pl.jkkk.task2.view.fxml.FxHelper.addNodeToPane;
import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQuantifier;
    private Pane paneCenterFirst;
    private Pane paneCenterSecond;
    private Pane paneSummarizer;

    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    private List<Pollution> pollutionData;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(ComboBox comboBoxQuantifier, Pane paneCenterFirst, Pane paneCenterSecond,
                       Pane paneSummarizer,
                       LabelWrapperService labelWrapperService,
                       LinguisticQuantifierWrapperService quantifierWrapperService,
                       List<Pollution> pollutionData) {
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.paneCenterFirst = paneCenterFirst;
        this.paneCenterSecond = paneCenterSecond;
        this.paneSummarizer = paneSummarizer;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
        this.pollutionData = pollutionData;
    }

    public void fillScene() {
        fillComboBoxQuantifier();
        fillPaneQualifier();
        fillPaneSummarizer();
    }

    private void fillComboBoxQuantifier() {
        fillComboBox(comboBoxQuantifier, quantifierWrapperService
                .findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));
    }

    private void fillPaneQualifier() {
        CustomComboBox customComboBox = createCustomComboBox(250);
        fillComboBox(customComboBox, addItemToLabelList(DEACTIVATED));
        addNodeToPane(paneCenterFirst, customComboBox);

        //        TODO OLD APPROACH WITH RECURSIVE COMBOBOX FOR QUALIFIER
        //        addComboBox(paneQualifier, DEACTIVATED);
    }

    private void fillPaneSummarizer() {
        addComboBox(paneSummarizer, SELECT_ITEM);
    }

    private void addComboBox(Pane pane, String prompt) {
        CustomComboBox customComboBox = createCustomComboBox(250);
        fillComboBox(customComboBox, addItemToLabelList(prompt));
        addNodeToPane(pane, customComboBox);

        customComboBox.setOnAction((event) -> {
            if (customComboBox.getCounter() == 0) {
                addComboBox(pane, prompt);
                customComboBox.setCounter(customComboBox.getCounter() + 1);
            }
        });
    }

    private CustomComboBox createCustomComboBox(double width) {
        CustomComboBox customComboBox = new CustomComboBox();
        customComboBox.setMinWidth(width);
        customComboBox.setMaxWidth(width);

        return customComboBox;
    }

    private List<String> addItemToLabelList(String item) {
        List<String> list = new ArrayList<>();

        list.add(item);
        labelWrapperService
                .findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .forEach((it) -> list.add(it));

        return list;
    }
}
