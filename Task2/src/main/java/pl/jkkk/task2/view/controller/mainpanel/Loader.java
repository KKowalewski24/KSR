package pl.jkkk.task2.view.controller.mainpanel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticSummary;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static pl.jkkk.task2.view.constant.ViewConstants.DEACTIVATED;
import static pl.jkkk.task2.view.constant.ViewConstants.SELECT_ITEM;
import static pl.jkkk.task2.view.fxml.FxHelper.clearListView;
import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.getNodeFromPane;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;

public class Loader {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQuantifier;
    private Pane paneQualifier;
    private Pane paneSummarizer;
    private ListView listViewResults;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();
    private final PollutionService pollutionService;
    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    private List<Pollution> pollutionData;
    private List<String> results = new ArrayList<>();

    /*------------------------ METHODS REGION ------------------------*/
    public Loader(ComboBox comboBoxQuantifier, Pane paneQualifier, Pane paneSummarizer,
                  ListView listViewResults, PollutionService pollutionService,
                  LabelWrapperService labelWrapperService,
                  LinguisticQuantifierWrapperService quantifierWrapperService,
                  List<Pollution> pollutionData) {
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.paneQualifier = paneQualifier;
        this.paneSummarizer = paneSummarizer;
        this.listViewResults = listViewResults;
        this.pollutionService = pollutionService;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
        this.pollutionData = pollutionData;
    }

    public void generateBasicSummarization() {
        String selectedQuantifier = getValueFromComboBox(comboBoxQuantifier);
        String firstSelectedQualifier
                = getValueFromComboBox((ComboBox) getNodeFromPane(paneQualifier, 1));
        String firstSelectedSummarizer
                = getValueFromComboBox((ComboBox) getNodeFromPane(paneSummarizer, 1));

        if (firstSelectedQualifier.equals(DEACTIVATED)) {
            if (!firstSelectedSummarizer.equals(SELECT_ITEM)) {
                generateBasicSummary(selectedQuantifier, firstSelectedSummarizer);
            } else {
                Platform.runLater(() -> {
                    PopOutWindow.messageBox("", "Summarizer Not Selected",
                            Alert.AlertType.WARNING);
                });
            }
        } else {
            if (!firstSelectedSummarizer.equals(SELECT_ITEM)) {
                generateAdvancedSummary(selectedQuantifier, firstSelectedQualifier);
            } else {
                Platform.runLater(() -> {
                    PopOutWindow.messageBox("", "Summarizer Not Selected",
                            Alert.AlertType.WARNING);
                });
            }
        }
    }

    private void generateAdvancedSummary(String selectedQuantifier, String selectedQualifier) {
        LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>(
                quantifierWrapperService.findByName(selectedQuantifier),
                labelWrapperService.findByName(selectedQualifier),
                pollutionData,
                getCompoundLabelNameFromPane(paneSummarizer)
        );

        generateAndFill(linguisticSummary);
    }

    private void generateBasicSummary(String selectedQuantifier, String firstSelectedSummarizer) {
        LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>(
                quantifierWrapperService.findByName(selectedQuantifier),
                pollutionData,
                getCompoundLabelNameFromPane(paneSummarizer)
        );

        generateAndFill(linguisticSummary);
    }

    private Label<Pollution>[] getCompoundLabelNameFromPane(Pane pane) {
        List<Label<Pollution>> labels = new ArrayList<>();

        for (int i = 1; i < pane.getChildren().size() - 1; i++) {
            labels.add(labelWrapperService
                    .findByName(getValueFromComboBox((ComboBox) getNodeFromPane(pane, i))));
        }

        Label<Pollution>[] labelsArray = new Label[labels.size()];

        return labels.toArray(labelsArray);
    }

    private void generateAndFill(LinguisticSummary<Pollution> linguisticSummary) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", decimalFormatSymbols);

        String degreeOfTruth = df.format(
                linguisticSummary.degreeOfTruth());
        String degreeOfImprecision = df.format(
                linguisticSummary.degreeOfImprecision());
        String degreeOfCovering = df.format(
                linguisticSummary.degreeOfCovering());
        String degreeOfAppropriateness = df.format(
                linguisticSummary.degreeOfAppropriateness());
        String lengthOfSummary = df.format(
                linguisticSummary.lengthOfSummary());
        String degreeOfQuantifierImprecision = df.format(
                linguisticSummary.degreeOfQuantifierImprecision());
        String degreeOfQuantifierCardinality = df.format(
                linguisticSummary.degreeOfQuantifierCardinality());
        String degreeOfSummarizerCardinality = df.format(
                linguisticSummary.degreeOfSummarizerCardinality());
        String degreeOfQualifierImprecision = df.format(
                linguisticSummary.degreeOfQualifierImprecision());
        String degreeOfQualifierCardinality = df.format(
                linguisticSummary.degreeOfQualifierCardinality());
        String lengthOfQualifier = df.format(
                linguisticSummary.lengthOfQualifier());

        StringBuilder generatedResult = new StringBuilder();
        generatedResult
                .append(linguisticSummary.toString())
                .append(". [")
                .append(degreeOfTruth)
                .append(", ")
                .append(degreeOfImprecision)
                .append(", ")
                .append(degreeOfCovering)
                .append(", ")
                .append(degreeOfAppropriateness)
                .append(", ")
                .append(lengthOfSummary)
                .append(", ")
                .append(degreeOfQuantifierImprecision)
                .append(", ")
                .append(degreeOfQuantifierCardinality)
                .append(", ")
                .append(degreeOfSummarizerCardinality)
                .append(", ")
                .append(degreeOfQualifierImprecision)
                .append(", ")
                .append(degreeOfQualifierCardinality)
                .append(", ")
                .append(lengthOfQualifier)
                .append("]");

        results.add(generatedResult.toString());
        Platform.runLater(() -> fillListView(listViewResults, generatedResult.toString()));
    }

    public void clearSummarization() {
        Platform.runLater(() -> {
            results.clear();
            clearListView(listViewResults);
        });
    }

    public void saveSummarization() {
        try {
            if (results != null) {
                String filename = new FileChooser()
                        .showSaveDialog(StageController.getApplicationStage())
                        .getName();
                fileWriterPlainText.writePlainText(filename, results);
            } else {
                PopOutWindow.messageBox("File Write Error",
                        "Summarization has not been generated yet",
                        Alert.AlertType.WARNING);
            }
        } catch (NullPointerException | FileOperationException e) {
            PopOutWindow.messageBox("File Write Error",
                    "Could not save the selected file", Alert.AlertType.WARNING);
        }
    }
}
