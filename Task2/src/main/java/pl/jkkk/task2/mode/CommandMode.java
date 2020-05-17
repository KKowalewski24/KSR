package pl.jkkk.task2.mode;

import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.readerwriter.FileReaderCsv;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;

import java.io.IOException;

import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

@Component
public class CommandMode {

    /*------------------------ FIELDS REGION ------------------------*/
    private final PollutionService pollutionService;
    private final LinguisticQuantifierWrapperService linguisticQuantifierWrapperService;
    private final LabelWrapperService labelWrapperService;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

    /*------------------------ METHODS REGION ------------------------*/
    public CommandMode(PollutionService pollutionService,
                       LinguisticQuantifierWrapperService linguisticQuantifierWrapperService,
                       LabelWrapperService labelWrapperService) {
        this.pollutionService = pollutionService;
        this.linguisticQuantifierWrapperService = linguisticQuantifierWrapperService;
        this.labelWrapperService = labelWrapperService;
    }

    public void main(String[] args) {
        try {
            if (args.length == 1) {
                if ((args[0].equals("seed_pollution") || args[0].equals("-sp"))) {
                    seedPollutionInDatabase();
                } else if ((args[0].equals("seed_linguistic") || args[0].equals("-sl"))) {
                    seedLinguisticFacilitiesInDatabase();
                }
            }
            //            else if (args.length > 1) {
            //                int argCounter = 0;
            //
            //                if (args.length == 4) {
            //
            //                }
            //        }
        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println(e);
            //            printUsage();
        }

    }

    private void seedPollutionInDatabase() throws IOException, CsvException {
        pollutionService.deleteAll();
        pollutionService.saveAll(new FileReaderCsv().readCsv(POLLUTION_DATA_FILENAME, false));
    }

    private void seedLinguisticFacilitiesInDatabase() {
        //TODO ADD SAVING LINGUISTIC DATA TO DB
        linguisticQuantifierWrapperService.deleteAll();
        labelWrapperService.deleteAll();

        //        savelinguisticQuantifier(new LinguisticQuantifier());
        //        savelinguisticQuantifier(new LinguisticQuantifier());
        //        savelinguisticQuantifier(new LinguisticQuantifier());
        //        savelinguisticQuantifier(new LinguisticQuantifier());
        //        savelinguisticQuantifier(new LinguisticQuantifier());

        //        savelabel(new Label<>());
        //        savelabel(new Label<>());
        //        savelabel(new Label<>());
        //        savelabel(new Label<>());
        //        savelabel(new Label<>());
    }

    private void savelinguisticQuantifier(LinguisticQuantifier linguisticQuantifier) {
        LinguisticQuantifierWrapper quantifierWrapper = new LinguisticQuantifierWrapper();
        quantifierWrapper.serialize(linguisticQuantifier);
        linguisticQuantifierWrapperService.save(quantifierWrapper);
    }

    private void savelabel(Label<Pollution> label) {
        LabelWrapper labelWrapper = new LabelWrapper();
        labelWrapper.serialize(label);
        labelWrapperService.save(labelWrapper);
    }

    //    private static void printUsage() {
    //        System.out.println(""
    //                + "------------------------------------------------------------------------\n"
    //                + "Required parameters:  \n"
    //                + "\t<qualifier>\n"
    //                + "\t<basicSummarizer>\n"
    //                + "\t<conjuntion>\n"
    //                + "\t<advancedSummarizer>\n"
    //                + "------------------------------------------------------------------------"
    //        );
    //        System.exit(0);
    //    }

    //    private void saveDataLog(String value) {
    //        if (IS_LOGGING_DATA) {
    //            try {
    //                fileWriterPlainText.writePlainText(Main.getMainArgs(), value);
    //            } catch (FileOperationException e) {
    //                System.out.println(e);
    //            }
    //        } else {
    //            System.out.println("-------------------------------------------------");
    //            System.out.println("LOGGING TO FILE DISABLED");
    //            System.out.println("-------------------------------------------------");
    //        }
    //    }
}
    