package pl.jkkk.task2.view.controller.mainpanel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticSummary;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;

import java.util.ArrayList;
import java.util.List;

import static pl.jkkk.task2.view.fxml.FxHelper.clearListView;
import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
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

    private List<String> results = new ArrayList<>();

    /*------------------------ METHODS REGION ------------------------*/
    public Loader(ComboBox comboBoxQuantifier, Pane paneQualifier, Pane paneSummarizer,
                  ListView listViewResults, PollutionService pollutionService,
                  LabelWrapperService labelWrapperService,
                  LinguisticQuantifierWrapperService quantifierWrapperService) {
        this.comboBoxQuantifier = comboBoxQuantifier;
        this.paneQualifier = paneQualifier;
        this.paneSummarizer = paneSummarizer;
        this.listViewResults = listViewResults;
        this.pollutionService = pollutionService;
        this.labelWrapperService = labelWrapperService;
        this.quantifierWrapperService = quantifierWrapperService;
    }

    public void generateBasicSummarization() {
//        String selectedQuantifier = getValueFromComboBox(comboBoxQuantifier);
//        String selectedQualifier = getValueFromComboBox(comboBoxQualifier);
//        String selectedSummarizer = getValueFromComboBox(comboBoxSummarizerBasic);
//
//        LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>(
//                quantifierWrapperService.findByName(selectedQuantifier),
//                labelWrapperService.findByName(selectedSummarizer),
//                pollutionService.findAll()
//        );

//        generateAndFill(linguisticSummary);
    }

    private void generateAndFill(LinguisticSummary<Pollution> linguisticSummary) {
        double degreeOfTruth = linguisticSummary.degreeOfTruth();
        String generateResult = linguisticSummary.toString() + " [" + degreeOfTruth + "]";
        results.add(generateResult);

        Platform.runLater(() -> fillListView(listViewResults, generateResult));
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
    