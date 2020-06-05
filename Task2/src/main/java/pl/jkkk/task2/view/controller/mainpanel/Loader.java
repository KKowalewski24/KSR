package pl.jkkk.task2.view.controller.mainpanel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import org.springframework.core.env.Environment;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticSummary;
import pl.jkkk.task2.logic.fuzzy.linguistic.MultisubjectLinguisticSummary;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;
import pl.jkkk.task2.view.model.CustomBoolean;

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
    private Pane paneCenterFirst;
    private Pane paneCenterSecond;
    private Pane paneSummarizer;
    private ListView listViewResults;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();
    private final PollutionService pollutionService;
    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierWrapperService;

    private List<Pollution> pollutionData;
    private List<String> results = new ArrayList<>();

    private Environment environment;
    private CustomBoolean isMultiSubject;

    /*------------------------ METHODS REGION ------------------------*/
    public Loader(ComboBox comboBoxQuantifier, Pane paneCenterFirst, Pane paneCenterSecond,
                  Pane paneSummarizer,
                  ListView listViewResults, PollutionService pollutionService,
                  LabelWrapperService labelWrapperService,
                  LinguisticQuantifierWrapperService quantifierWrapperService,
                  List<Pollution> pollutionData, CustomBoolean isMultiSubject,
                  Environment environment) {
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.paneCenterFirst = paneCenterFirst;
        this.paneCenterSecond = paneCenterSecond;
        this.paneSummarizer = paneSummarizer;
        this.listViewResults = listViewResults;
        this.pollutionService = pollutionService;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
        this.pollutionData = pollutionData;
        this.isMultiSubject = isMultiSubject;
        this.environment = environment;
    }

    public void generateBasicSummarization() {
        String selectedQuantifier = getValueFromComboBox(comboBoxQuantifier);
        String firstSelectedSummarizer
                = getValueFromComboBox((ComboBox) getNodeFromPane(paneSummarizer, 1));

        if (!isMultiSubject.isState()) {
            String firstSelectedQualifier
                    = getValueFromComboBox((ComboBox) getNodeFromPane(paneCenterFirst, 1));

            if (firstSelectedQualifier.equals(DEACTIVATED)) {
                if (!firstSelectedSummarizer.equals(SELECT_ITEM)) {
                    generateBasicSummary(selectedQuantifier);
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

        } else {
            String attributeValue1
                    = getValueFromComboBox((ComboBox) getNodeFromPane(paneCenterFirst, 1));
            String attributeValue2
                    = getValueFromComboBox((ComboBox) getNodeFromPane(paneCenterSecond, 1));

            if (!firstSelectedSummarizer.equals(SELECT_ITEM)) {
                generateBasicMultiSubjectSummary(selectedQuantifier,
                        attributeValue1, attributeValue2);
            } else {
                Platform.runLater(() -> {
                    PopOutWindow.messageBox("", "Summarizer Not Selected",
                            Alert.AlertType.WARNING);
                });
            }
        }
    }

    private void generateBasicSummary(String selectedQuantifier) {
        LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>(
                quantifierWrapperService.findByName(selectedQuantifier),
                pollutionData,
                getCompoundLabelNameFromPane(paneSummarizer)
        );

        generateAndFill(linguisticSummary);
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

    private void generateBasicMultiSubjectSummary(String selectedQuantifier,
                                                  String attributeValue1, String attributeValue2) {
        MultisubjectLinguisticSummary<Pollution> summary = new MultisubjectLinguisticSummary<>(
                quantifierWrapperService.findByName(selectedQuantifier),
                pollutionData,
                (Pollution pollution) -> pollution.getCity(),
                attributeValue1,
                attributeValue2,
                getCompoundLabelNameFromPane(paneSummarizer)
        );

        generateAndFillMultiSubject(summary);
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
        final String baseName = "optional.summary.quality.w";

        String quality = df.format(linguisticSummary.quality(
                Double.valueOf(environment.getProperty(baseName + "1")),
                Double.valueOf(environment.getProperty(baseName + "2")),
                Double.valueOf(environment.getProperty(baseName + "3")),
                Double.valueOf(environment.getProperty(baseName + "4")),
                Double.valueOf(environment.getProperty(baseName + "5")),
                Double.valueOf(environment.getProperty(baseName + "6")),
                Double.valueOf(environment.getProperty(baseName + "7")),
                Double.valueOf(environment.getProperty(baseName + "8")),
                Double.valueOf(environment.getProperty(baseName + "9")),
                Double.valueOf(environment.getProperty(baseName + "10")),
                Double.valueOf(environment.getProperty(baseName + "11"))
        ));

        StringBuilder generatedResult = new StringBuilder();
        generatedResult
                .append(linguisticSummary.toString())
                .append(". [")
                .append(quality)
                .append("]");

        results.add(generatedResult.toString());
        Platform.runLater(() -> fillListView(listViewResults, generatedResult.toString()));
    }

    private void generateAndFillMultiSubject(MultisubjectLinguisticSummary<Pollution> summary) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", decimalFormatSymbols);

        String degreeOfTruth = df.format(summary.degreeOfTruth());

        StringBuilder generatedResult = new StringBuilder();
        generatedResult
                .append(summary.toString())
                .append(". [")
                .append(degreeOfTruth)
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
