package pl.jkkk.task2.view.controller.mainpanel;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.writer.FileWriterPlainText;
import pl.jkkk.task2.view.fxml.PopOutWindow;
import pl.jkkk.task2.view.fxml.StageController;

import java.util.ArrayList;

import static pl.jkkk.task2.view.fxml.FxHelper.fillListView;
import static pl.jkkk.task2.view.fxml.FxHelper.getValueFromComboBox;

public class Loader {

    /*------------------------ FIELDS REGION ------------------------*/
    private ComboBox comboBoxQualifier;
    private ComboBox comboBoxSummarizerBasic;
    private ComboBox comboBoxConjunction;
    private ComboBox comboBoxSummarizeAdvanced;
    private ListView listViewResults;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

    /*------------------------ METHODS REGION ------------------------*/
    public Loader(ComboBox comboBoxQualifier, ComboBox comboBoxSummarizerBasic,
                  ComboBox comboBoxConjunction, ComboBox comboBoxSummarizeAdvanced,
                  ListView listViewResults) {
        this.comboBoxQualifier = comboBoxQualifier;
        this.comboBoxSummarizerBasic = comboBoxSummarizerBasic;
        this.comboBoxConjunction = comboBoxConjunction;
        this.comboBoxSummarizeAdvanced = comboBoxSummarizeAdvanced;
        this.listViewResults = listViewResults;
    }

    private void saveDataLog(String value) {
        try {
            fileWriterPlainText.writePlainText(Main.getMainArgs(), value);
        } catch (FileOperationException e) {
            System.out.println(e);
        }
    }

    public void generateBasicSummarization() {
        //        TODO CHANGE FOR REAL DATA
        String selectedQualifier = getValueFromComboBox(comboBoxQualifier);
        String selectedSummarizerBasic = getValueFromComboBox(comboBoxSummarizerBasic);

        fillListView(listViewResults, new ArrayList<>());
//        saveDataLog("abc");
    }

    public void generateAdvancedSummarization() {
        //        TODO CHANGE FOR REAL DATA
        String selectedQualifier = getValueFromComboBox(comboBoxQualifier);
        String selectedSummarizerBasic = getValueFromComboBox(comboBoxSummarizerBasic);
        String selectedConjunction = getValueFromComboBox(comboBoxConjunction);
        String selectedSummarizerAdvanced = getValueFromComboBox(comboBoxSummarizeAdvanced);

        fillListView(listViewResults, new ArrayList<>());
//        saveDataLog("cde");
    }

    public void saveSummarization() {
        //        try {
        //            if (!=null){
        //            String filename = new FileChooser()
        //                    .showSaveDialog(StageController.getApplicationStage())
        //                    .getName();
        //            fileWriterPlainText.writePlainText(filename, "");
        //            } else{
        //                PopOutWindow.messageBox("File Write Error",
        //                        "Summarization has not been generated yet",
        //                        Alert.AlertType.WARNING);
        //            }
        //        } catch (NullPointerException | FileOperationException e) {
        //            PopOutWindow.messageBox("File Write Error",
        //                    "Could not save the selected file", Alert.AlertType.WARNING);
        //        }
    }
}
    