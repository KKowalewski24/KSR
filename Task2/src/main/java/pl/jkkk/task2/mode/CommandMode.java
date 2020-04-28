package pl.jkkk.task2.mode;

import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.model.enumtype.ConjuctionType;
import pl.jkkk.task2.logic.model.enumtype.QualifierSummarizationType;
import pl.jkkk.task2.logic.readerwriter.FileReaderCsv;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.pollution.PollutionService;

import java.io.IOException;

import static pl.jkkk.task2.Main.IS_LOGGING_DATA;
import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

@Component
public class CommandMode {

    /*------------------------ FIELDS REGION ------------------------*/
    private final PollutionService pollutionService;
    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

    /*------------------------ METHODS REGION ------------------------*/
    @Autowired
    public CommandMode(PollutionService pollutionService) {
        this.pollutionService = pollutionService;
    }

    public void main(String[] args) {
        QualifierSummarizationType qualifier = null;
        QualifierSummarizationType basicSummarizer = null;
        ConjuctionType conjuntion = null;
        QualifierSummarizationType advancedSummarizer = null;

        try {
            if (args.length == 1 && (args[0].equals("seed") || args[0].equals("-s"))) {
                seedDatabase();
            } else if (args.length > 1) {
                int argCounter = 0;
                qualifier = QualifierSummarizationType.fromString(args[argCounter++]);
                basicSummarizer = QualifierSummarizationType.fromString(args[argCounter++]);

                if (args.length == 4) {
                    conjuntion = ConjuctionType.fromString(args[argCounter++]);
                    advancedSummarizer = QualifierSummarizationType.fromString(args[argCounter++]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            printUsage();
        }

        //TODO ADD CALLING METHOD FROM LOGIC
        //        saveDataLog("");
    }

    private void seedDatabase() throws IOException, CsvException {
        pollutionService.saveAll(new FileReaderCsv().readCsv(POLLUTION_DATA_FILENAME));
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
    