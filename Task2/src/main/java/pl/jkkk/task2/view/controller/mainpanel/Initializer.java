package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.view.model.CustomBoolean;
import pl.jkkk.task2.view.model.CustomComboBox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.jkkk.task2.view.constant.ViewConstants.CITY_FIRST;
import static pl.jkkk.task2.view.constant.ViewConstants.CITY_SECOND;
import static pl.jkkk.task2.view.constant.ViewConstants.DEACTIVATED;
import static pl.jkkk.task2.view.constant.ViewConstants.MULTI_SUBJECT;
import static pl.jkkk.task2.view.constant.ViewConstants.QUALIFIER;
import static pl.jkkk.task2.view.constant.ViewConstants.SELECT_ITEM;
import static pl.jkkk.task2.view.constant.ViewConstants.SINGLE_SUBJECT;
import static pl.jkkk.task2.view.fxml.FxHelper.addNodeToPane;
import static pl.jkkk.task2.view.fxml.FxHelper.fillComboBox;
import static pl.jkkk.task2.view.fxml.FxHelper.getNodeFromPane;
import static pl.jkkk.task2.view.fxml.FxHelper.setPaneVisibility;

public class Initializer {

    /*------------------------ FIELDS REGION ------------------------*/
    private Button buttonSubject;
    private ComboBox comboBoxQuantifier;
    private Pane paneCenterFirst;
    private Pane paneCenterSecond;
    private Pane paneSummarizer;

    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    private List<Pollution> pollutionData;

    private CustomBoolean isMultiSubject;

    /*------------------------ METHODS REGION ------------------------*/
    public Initializer(Button buttonSubject, ComboBox comboBoxQuantifier, Pane paneCenterFirst,
                       Pane paneCenterSecond,
                       Pane paneSummarizer,
                       LabelWrapperService labelWrapperService,
                       LinguisticQuantifierWrapperService quantifierWrapperService,
                       List<Pollution> pollutionData, CustomBoolean isMultiSubject) {
        this.buttonSubject = buttonSubject;
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.paneCenterFirst = paneCenterFirst;
        this.paneCenterSecond = paneCenterSecond;
        this.paneSummarizer = paneSummarizer;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
        this.pollutionData = pollutionData;
        this.isMultiSubject = isMultiSubject;
    }

    public void fillScene() {
        buttonSubject.setText(SINGLE_SUBJECT);
        fillComboBoxQuantifier();
        fillPaneCenterFirstQualifier();
        fillPaneCenterSecondCity();
        fillPaneSummarizer();
    }

    public void changeSubjectType() {
        if (isMultiSubject.isState()) {
            isMultiSubject.setState(false);
            buttonSubject.setText(MULTI_SUBJECT);
            setPaneVisibility(false, paneCenterSecond);
            fillPaneCenterFirstQualifier();
        } else {
            isMultiSubject.setState(true);
            buttonSubject.setText(SINGLE_SUBJECT);
            setPaneVisibility(true, paneCenterSecond);
            fillPaneCenterFirstCity();
        }
    }

    private void fillComboBoxQuantifier() {
        fillComboBox(comboBoxQuantifier, quantifierWrapperService
                .findAll()
                .stream()
                .map((it) -> it.deserialize().getName())
                .collect(Collectors.toList()));
    }

    private void fillPaneCenterFirstQualifier() {
        paneCenterFirst.getChildren().subList(1, paneCenterFirst.getChildren().size()).clear();
        CustomComboBox customComboBox = createCustomComboBox(200);
        fillComboBox(customComboBox, addItemToLabelList(DEACTIVATED));
        addNodeToPane(paneCenterFirst, customComboBox);
        Label label = (Label) getNodeFromPane(paneCenterFirst, 0);
        label.setText(QUALIFIER);
    }

    private void fillPaneCenterFirstCity() {
        paneCenterFirst.getChildren().subList(1, paneCenterFirst.getChildren().size()).clear();
        CustomComboBox customComboBox = createCustomComboBox(200);
        fillComboBox(customComboBox, pollutionData
                .stream()
                .map((it) -> it.getCity())
                .distinct()
                .collect(Collectors.toList())
        );
        addNodeToPane(paneCenterFirst, customComboBox);
        Label label = (Label) getNodeFromPane(paneCenterFirst, 0);
        label.setText(CITY_FIRST);
    }

    private void fillPaneCenterSecondCity() {
        CustomComboBox customComboBox = createCustomComboBox(200);
        fillComboBox(customComboBox, pollutionData
                .stream()
                .map((it) -> it.getCity())
                .distinct()
                .collect(Collectors.toList())
        );

        addNodeToPane(paneCenterSecond, customComboBox);
        Label label = (Label) getNodeFromPane(paneCenterSecond, 0);
        label.setText(CITY_SECOND);
        setPaneVisibility(false, paneCenterSecond);
    }

    private void fillPaneSummarizer() {
        addComboBox(paneSummarizer, SELECT_ITEM);
    }

    private void addComboBox(Pane pane, String prompt) {
        CustomComboBox customComboBox = createCustomComboBox(200);
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
