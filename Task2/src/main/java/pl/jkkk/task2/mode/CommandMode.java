package pl.jkkk.task2.mode;

import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.model.ConjuctionType;
import pl.jkkk.task2.logic.model.QualifierSummarizationType;
import pl.jkkk.task2.logic.writer.FileWriterPlainText;

import static pl.jkkk.task2.logic.constant.LogicConstants.IS_LOGGING_DATA;

public class CommandMode {

    /*------------------------ FIELDS REGION ------------------------*/
    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

    /*------------------------ METHODS REGION ------------------------*/
    public void main(String[] args) {
        QualifierSummarizationType qualifier = null;
        QualifierSummarizationType basicSummarizer = null;
        ConjuctionType conjuntion = null;
        QualifierSummarizationType advancedSummarizer = null;

        try {
            if (args.length > 0) {
                qualifier = QualifierSummarizationType.fromString(args[1]);
                basicSummarizer = QualifierSummarizationType.fromString(args[2]);
                conjuntion = ConjuctionType.fromString(args[3]);
                advancedSummarizer = QualifierSummarizationType.fromString(args[4]);
            }
        } catch (Exception e) {
            System.out.println(e);
            printUsage();
        }

        //TODO ADD CALLING METHOD FROM LOGIC
//        saveDataLog("");
    }

    private static void printUsage() {
        System.out.println(""
                + "------------------------------------------------------------------------\n"
                + "Required parameters:  \n"
                + "\t<qualifier>\n"
                + "\t<basicSummarizer>\n"
                + "\t<conjuntion>\n"
                + "\t<advancedSummarizer>\n"
                + "------------------------------------------------------------------------"
        );
        System.exit(0);
    }

    private void saveDataLog(String value) {
        if (IS_LOGGING_DATA) {
            try {
                fileWriterPlainText.writePlainText(Main.getMainArgs(), value);
            } catch (FileOperationException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("-------------------------------------------------");
            System.out.println("LOGGING TO FILE DISABLED");
            System.out.println("-------------------------------------------------");
        }
    }
}
    