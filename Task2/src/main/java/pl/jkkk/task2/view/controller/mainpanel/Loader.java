package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;

import java.util.ArrayList;
import java.util.List;

import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;

public class Loader {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizerBasic;
    private ListView listViewResults;
    private TextField textFieldSaveSummarizationNumber;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();
    private final PollutionService pollutionService;
    private final LabelWrapperService labelWrapperService;
    private final LinguisticQuantifierWrapperService quantifierService;

    private List results;

    /*------------------------ METHODS REGION ------------------------*/
    public Loader(ComboBox comboBoxQualifier, ComboBox comboBoxSummarizerBasic,
                  ListView listViewResults, TextField textFieldSaveSummarizationNumber,
                  PollutionService pollutionService, LabelWrapperService labelWrapperService,
                  LinguisticQuantifierWrapperService quantifierService) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizerBasic = comboBoxSummarizerBasic;
        this.listViewResults = listViewResults;
        this.textFieldSaveSummarizationNumber = textFieldSaveSummarizationNumber;
        this.pollutionService = pollutionService;
        this.labelWrapperService = labelWrapperService;
        this.quantifierService = quantifierService;
    }

    public void generateBasicSummarization() {
        String selectedQualifier = getValueFromComboBox(comboBoxQualifier);
        String selectedSummarizer = getValueFromComboBox(comboBoxSummarizerBasic);

        //        TODO
        //        LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>();
        //        results = Stream.of(linguisticSummary.toString()).collect(Collectors.toList());

        fillListView(listViewResults, results);
        textFieldSaveSummarizationNumber.setText(String.valueOf(results.size()));
    }

    public void saveSummarization(Integer summarizationNumber) {
        try {
            if (results != null) {
                String filename = new FileChooser()
                        .showSaveDialog(StageController.getApplicationStage())
                        .getName();
                fileWriterPlainText.writePlainText(filename, new ArrayList<>(results)
                        .subList(0, summarizationNumber));
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
    