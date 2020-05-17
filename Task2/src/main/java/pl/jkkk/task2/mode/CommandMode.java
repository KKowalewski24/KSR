package pl.jkkk.task2.mode;

import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.Main;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticSummary;
import pl.jkkk.task2.logic.fuzzy.set.TrapezoidalFuzzySet;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.model.enumtype.QuantifierType;
import pl.jkkk.task2.logic.model.variable.maxhour.MaxHourCO;
import pl.jkkk.task2.logic.model.variable.maxhour.MaxHourNO2;
import pl.jkkk.task2.logic.model.variable.maxhour.MaxHourO3;
import pl.jkkk.task2.logic.model.variable.maxhour.MaxHourSO2;
import pl.jkkk.task2.logic.model.variable.meanconcentration.MeanConcentrationCO;
import pl.jkkk.task2.logic.model.variable.meanconcentration.MeanConcentrationNO2;
import pl.jkkk.task2.logic.model.variable.meanconcentration.MeanConcentrationO3;
import pl.jkkk.task2.logic.model.variable.meanconcentration.MeanConcentrationSO2;
import pl.jkkk.task2.logic.model.variable.season.MeasurementSeason;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.readerwriter.FileReaderCsv;
import pl.jkkk.task2.logic.readerwriter.FileWriterPlainText;
import pl.jkkk.task2.logic.service.label.LabelWrapperService;
import pl.jkkk.task2.logic.service.linguisticquantifier.LinguisticQuantifierWrapperService;
import pl.jkkk.task2.logic.service.pollution.PollutionService;

import java.io.IOException;

import static pl.jkkk.task2.Main.IS_LOGGING_DATA;
import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

@Component
public class CommandMode {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String ALMOST_NONE = "Almost None";
    public static final String SOME = "Some";
    public static final String ABOUT_HALF = "About Half";
    public static final String MANY = "Many";
    public static final String ALMOST_ALL = "Almost All";
    public static final String SPRING = "Spring";
    public static final String SUMMER = "Summer";
    public static final String AUTUMN = "Autumn";
    public static final String WINTER = "Winter";
    public static final String MORNING_HOUR = "Morning Hour";
    public static final String AFTERNOON_HOUR = "Afternoon Hour";
    public static final String EVENING_HOUR = "Evening Hour";
    public static final String NIGHT_HOUR = "Night Hour";
    public static final String LOW_CONCENTRATION = "Low Concentration";
    public static final String MIDDLE_CONCENTRATION = "Middle Concentration";
    public static final String HIGH_CONCENTRATION = "High Concentration";

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
            } else if (args.length == 2) {
                String selectedQualifier = args[0];
                String selectedSummarizer = args[1];

                LinguisticSummary<Pollution> linguisticSummary = new LinguisticSummary<>(
                        linguisticQuantifierWrapperService.findByName(selectedQualifier),
                        labelWrapperService.findByName(selectedSummarizer),
                        pollutionService.findAll()
                );

                double degreeOfTruth = linguisticSummary.degreeOfTruth();
                saveDataLog(linguisticSummary.toString()
                        + " | degree of truth: " + degreeOfTruth);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            printUsage();
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

        saveLinguisticQuantifier(new LinguisticQuantifier(
                ALMOST_NONE,
                new TrapezoidalFuzzySet(0.00, 0.00, 0.04, 0.16),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                SOME,
                new TrapezoidalFuzzySet(0.12, 0.16, 0.32, 0.4),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                ABOUT_HALF,
                new TrapezoidalFuzzySet(0.32, 0.44, 0.56, 0.68),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                MANY,
                new TrapezoidalFuzzySet(0.6, 0.68, 0.84, 0.88),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                ALMOST_ALL,
                new TrapezoidalFuzzySet(0.84, 0.96, 1, 1),
                QuantifierType.RELATIVE)
        );

        /*----------------------------------------------------------------------------------------*/
        // Sezon wykonania pomiaru
        saveLabel(new Label<>(
                SPRING,
                new TrapezoidalFuzzySet(32, 92, 122, 183),
                new MeasurementSeason())
        );
        saveLabel(new Label<>(
                SUMMER,
                new TrapezoidalFuzzySet(122, 183, 214, 275),
                new MeasurementSeason())
        );
        saveLabel(new Label<>(
                AUTUMN,
                new TrapezoidalFuzzySet(214, 275, 306, 336),
                new MeasurementSeason())
        );
        saveLabel(new Label<>(
                WINTER,
                new TrapezoidalFuzzySet(275, 336, 32, 92),
                new MeasurementSeason())
        );

        /*----------------------------------------------------------------------------------------*/
        // Godzina maksymalnego stężenia
        saveLabel(new Label<>(
                MORNING_HOUR,
                new TrapezoidalFuzzySet(3, 6, 10, 13),
                new MaxHourCO())
        );
        saveLabel(new Label<>(
                AFTERNOON_HOUR,
                new TrapezoidalFuzzySet(12, 13, 17, 19),
                new MaxHourCO())
        );
        saveLabel(new Label<>(
                EVENING_HOUR,
                new TrapezoidalFuzzySet(18, 20, 21, 22),
                new MaxHourCO())
        );
        saveLabel(new Label<>(
                NIGHT_HOUR,
                new TrapezoidalFuzzySet(21, 23, 4, 5),
                new MaxHourCO())
        );

        // Godzina maksymalnego stężenia
        saveLabel(new Label<>(
                MORNING_HOUR,
                new TrapezoidalFuzzySet(3, 6, 10, 13),
                new MaxHourNO2())
        );
        saveLabel(new Label<>(
                AFTERNOON_HOUR,
                new TrapezoidalFuzzySet(12, 13, 17, 19),
                new MaxHourNO2())
        );
        saveLabel(new Label<>(
                EVENING_HOUR,
                new TrapezoidalFuzzySet(18, 20, 21, 22),
                new MaxHourNO2())
        );
        saveLabel(new Label<>(
                NIGHT_HOUR,
                new TrapezoidalFuzzySet(21, 23, 4, 5),
                new MaxHourNO2())
        );

        // Godzina maksymalnego stężenia
        saveLabel(new Label<>(
                MORNING_HOUR,
                new TrapezoidalFuzzySet(3, 6, 10, 13),
                new MaxHourO3())
        );
        saveLabel(new Label<>(
                AFTERNOON_HOUR,
                new TrapezoidalFuzzySet(12, 13, 17, 19),
                new MaxHourO3())
        );
        saveLabel(new Label<>(
                EVENING_HOUR,
                new TrapezoidalFuzzySet(18, 20, 21, 22),
                new MaxHourO3())
        );
        saveLabel(new Label<>(
                NIGHT_HOUR,
                new TrapezoidalFuzzySet(21, 23, 4, 5),
                new MaxHourO3())
        );

        // Godzina maksymalnego stężenia
        saveLabel(new Label<>(
                MORNING_HOUR,
                new TrapezoidalFuzzySet(3, 6, 10, 13),
                new MaxHourSO2())
        );
        saveLabel(new Label<>(
                AFTERNOON_HOUR,
                new TrapezoidalFuzzySet(12, 13, 17, 19),
                new MaxHourSO2())
        );
        saveLabel(new Label<>(
                EVENING_HOUR,
                new TrapezoidalFuzzySet(18, 20, 21, 22),
                new MaxHourSO2())
        );
        saveLabel(new Label<>(
                NIGHT_HOUR,
                new TrapezoidalFuzzySet(21, 23, 4, 5),
                new MaxHourSO2())
        );

        /*----------------------------------------------------------------------------------------*/
        // Średnie stężenie
        saveLabel(new Label<>(
                LOW_CONCENTRATION,
                new TrapezoidalFuzzySet(0, 4, 9, 12),
                new MeanConcentrationCO())
        );
        saveLabel(new Label<>(
                MIDDLE_CONCENTRATION,
                new TrapezoidalFuzzySet(9, 12, 15, 30),
                new MeanConcentrationCO())
        );
        saveLabel(new Label<>(
                HIGH_CONCENTRATION,
                new TrapezoidalFuzzySet(15, 30, 40, 50),
                new MeanConcentrationCO())
        );

        // Średnie stężenie
        saveLabel(new Label<>(
                LOW_CONCENTRATION,
                new TrapezoidalFuzzySet(0, 0.05, 0.1, 0.36),
                new MeanConcentrationNO2())
        );
        saveLabel(new Label<>(
                MIDDLE_CONCENTRATION,
                new TrapezoidalFuzzySet(0.1, 0.36, 0.65, 1.25),
                new MeanConcentrationNO2())
        );
        saveLabel(new Label<>(
                HIGH_CONCENTRATION,
                new TrapezoidalFuzzySet(0.65, 1.25, 1.65, 2.04),
                new MeanConcentrationNO2())
        );

        // Średnie stężenie
        saveLabel(new Label<>(
                LOW_CONCENTRATION,
                new TrapezoidalFuzzySet(0, 0.03, 0.6, 0.75),
                new MeanConcentrationO3())
        );
        saveLabel(new Label<>(
                MIDDLE_CONCENTRATION,
                new TrapezoidalFuzzySet(0.03, 0.06, 0.075, 0.095),
                new MeanConcentrationO3())
        );
        saveLabel(new Label<>(
                HIGH_CONCENTRATION,
                new TrapezoidalFuzzySet(0.075, 0.095, 0.115, 0.375),
                new MeanConcentrationO3())
        );

        // Średnie stężenie
        saveLabel(new Label<>(
                LOW_CONCENTRATION,
                new TrapezoidalFuzzySet(0, 0, 75, 300),
                new MeanConcentrationSO2())
        );
        saveLabel(new Label<>(
                MIDDLE_CONCENTRATION,
                new TrapezoidalFuzzySet(150, 450, 600, 800),
                new MeanConcentrationSO2())
        );
        saveLabel(new Label<>(
                HIGH_CONCENTRATION,
                new TrapezoidalFuzzySet(600, 1000, 1000, 1000),
                new MeanConcentrationSO2())
        );

        /*----------------------------------------------------------------------------------------*/
        // Maksymalne stężenie
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));

        /*----------------------------------------------------------------------------------------*/
        // Wartosc AQI
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));
        //        saveLabel(new Label<>("", new TrapezoidalFuzzySet(), new ()));

        /*----------------------------------------------------------------------------------------*/
    }

    private void saveLinguisticQuantifier(LinguisticQuantifier linguisticQuantifier) {
        LinguisticQuantifierWrapper quantifierWrapper = new LinguisticQuantifierWrapper();
        quantifierWrapper.serialize(linguisticQuantifier);
        linguisticQuantifierWrapperService.save(quantifierWrapper);
    }

    private void saveLabel(Label<Pollution> label) {
        LabelWrapper labelWrapper = new LabelWrapper();
        labelWrapper.serialize(label);
        labelWrapperService.save(labelWrapper);
    }

    private static void printUsage() {
        System.out.println(""
                + "------------------------------------------------------------------------\n"
                + "Required parameters for summarization:  \n"
                + "\t<Quantifier>\n"
                + "\t<Summarizer>\n"
                + "Required parameters for seeding pollution:  \n"
                + "\t<seed_pollution> |OR| <-sp>\n"
                + "Required parameters for seeding linguistic data:  \n"
                + "\t<seed_linguistic> |OR| <-sl>\n"
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
    