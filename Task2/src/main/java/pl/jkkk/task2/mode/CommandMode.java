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
import pl.jkkk.task2.logic.model.variable.aqi.AQIValueCO;
import pl.jkkk.task2.logic.model.variable.aqi.AQIValueNO2;
import pl.jkkk.task2.logic.model.variable.aqi.AQIValueO3;
import pl.jkkk.task2.logic.model.variable.aqi.AQIValueSO2;
import pl.jkkk.task2.logic.model.variable.maxconcentration.MaxConcentrationCO;
import pl.jkkk.task2.logic.model.variable.maxconcentration.MaxConcentrationNO2;
import pl.jkkk.task2.logic.model.variable.maxconcentration.MaxConcentrationO3;
import pl.jkkk.task2.logic.model.variable.maxconcentration.MaxConcentrationSO2;
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
import java.util.List;
import java.util.stream.Collectors;

import static pl.jkkk.task2.Main.IS_LOGGING_DATA;
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

                } else if (args[0].equals("all")) {
                    System.out.println("Trying all quantifier/label combinations...\n");

                    List<Label<Pollution>> labels = labelWrapperService.findAll()
                            .stream()
                            .map(wrapper -> wrapper.deserialize())
                            .collect(Collectors.toList());

                    List<LinguisticQuantifier> quantifiers = linguisticQuantifierWrapperService
                            .findAll()
                            .stream()
                            .map(wrapper -> wrapper.deserialize())
                            .collect(Collectors.toList());
                    List<Pollution> measurements = pollutionService.findAll();

                    labels.forEach(label -> {
                        quantifiers.forEach(quantifier -> {
                            LinguisticSummary<Pollution> summary
                                    = new LinguisticSummary<>(quantifier, label, measurements);
                            double degreeOfTruth = summary.degreeOfTruth();

                            if (degreeOfTruth > 0.0) {
                                System.out.println(summary.toString() + " [" + degreeOfTruth + "]");
                                saveDataLog(summary.toString() + " [" + degreeOfTruth + "]");
                            }
                        });
                    });
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
                        + " [" + degreeOfTruth + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            printUsage();
        }
    }

    private void seedPollutionInDatabase() throws IOException {
        pollutionService.deleteAll();
        pollutionService.saveAll(new FileReaderCsv().readCsv(POLLUTION_DATA_FILENAME, false));
    }

    private void seedLinguisticFacilitiesInDatabase() {
        linguisticQuantifierWrapperService.deleteAll();
        labelWrapperService.deleteAll();

        // Kwantyfikatory
        saveLinguisticQuantifier(new LinguisticQuantifier(
                "In almost none",
                new TrapezoidalFuzzySet<Double>(x -> x, 0.00, 0.00, 0.04, 0.16),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                "In some",
                new TrapezoidalFuzzySet<Double>(x -> x, 0.12, 0.16, 0.32, 0.4),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                "In about half of all",
                new TrapezoidalFuzzySet<Double>(x -> x, 0.32, 0.44, 0.56, 0.68),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                "In many",
                new TrapezoidalFuzzySet<Double>(x -> x, 0.6, 0.68, 0.84, 0.88),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                "In all",
                new TrapezoidalFuzzySet<Double>(x -> x, 0.84, 0.96, 1, 1),
                QuantifierType.RELATIVE)
        );

        /*----------------------------------------------------------------------------------------*/
        // Sezon wykonania pomiaru
        final MeasurementSeason measurementSeason = new MeasurementSeason();
        saveLabel(new Label<>(
                "season is spring.",
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        32, 92, 122, 183),
                measurementSeason)
        );
        saveLabel(new Label<>(
                "season is summer.",
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        122, 183, 214, 275),
                measurementSeason)
        );
        saveLabel(new Label<>(
                "season is autumn.",
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        214, 275, 306, 336),
                measurementSeason)
        );
        saveLabel(new Label<>(
                "season is winter.",
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        275, 336, 32, 92),
                measurementSeason)
        );

        /*----------------------------------------------------------------------------------------*/
        // Godzina maksymalnego stężenia
        final MaxHourCO maxHourCO = new MaxHourCO();
        saveLabel(new Label<>(
                "maximum CO concentration is in the morning.",
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        3, 6, 10, 13),
                maxHourCO)
        );
        saveLabel(new Label<>(
                "maximum CO concentration is in the afternoon.",
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        12, 13, 17, 19),
                maxHourCO)
        );
        saveLabel(new Label<>(
                "maximum CO concentration is in the evening.",
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        18, 20, 21, 22),
                maxHourCO)
        );
        saveLabel(new Label<>(
                "maximum CO concentration is in the night.",
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        21, 23, 4, 5),
                maxHourCO)
        );

        // Godzina maksymalnego stężenia
        final MaxHourNO2 maxHourNO2 = new MaxHourNO2();
        saveLabel(new Label<>(
                "maximum NO2 concentration is in the morning.",
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        3, 6, 10, 13),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                "maximum NO2 concentration is in the afternoon.",
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        12, 13, 17, 19),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                "maximum NO2 concentration is in the evening.",
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        18, 20, 21, 22),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                "maximum NO2 concentration is in the night.",
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        21, 23, 4, 5),
                maxHourNO2)
        );

        // Godzina maksymalnego stężenia
        final MaxHourO3 maxHourO3 = new MaxHourO3();
        saveLabel(new Label<>(
                "maximum O3 concentration is in the morning.",
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        3, 6, 10, 13),
                maxHourO3)
        );
        saveLabel(new Label<>(
                "maximum O3 concentration is in the afternoon.",
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        12, 13, 17, 19),
                maxHourO3)
        );
        saveLabel(new Label<>(
                "maximum O3 concentration is in the evening.",
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        18, 20, 21, 22),
                maxHourO3)
        );
        saveLabel(new Label<>(
                "maximum O3 concentration is in the night.",
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        21, 23, 4, 5),
                maxHourO3)
        );

        // Godzina maksymalnego stężenia
        final MaxHourSO2 maxHoursSO2 = new MaxHourSO2();
        saveLabel(new Label<>(
                "maximum SO2 concentration is in the morning.",
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        3, 6, 10, 13),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                "maximum SO2 concentration is in the afternoon.",
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        12, 13, 17, 19),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                "maximum SO2 concentration is in the evening.",
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        18, 20, 21, 22),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                "maximum SO2 concentration is in the night.",
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        21, 23, 4, 5),
                maxHoursSO2)
        );

        /*----------------------------------------------------------------------------------------*/
        // Średnie stężenie
        final MeanConcentrationCO meanConcentrationCO = new MeanConcentrationCO();
        saveLabel(new Label<>(
                "mean CO concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        0, 4, 9, 12),
                meanConcentrationCO)
        );
        saveLabel(new Label<>(
                "mean CO concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        9, 12, 15, 30),
                meanConcentrationCO)
        );
        saveLabel(new Label<>(
                "mean CO concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        15, 30, 40, 50),
                meanConcentrationCO)
        );

        // Średnie stężenie
        final MeanConcentrationNO2 meanConcentrationNO2 = new MeanConcentrationNO2();
        saveLabel(new Label<>(
                "mean NO2 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0, 0.05, 0.1, 0.36),
                meanConcentrationNO2)
        );
        saveLabel(new Label<>(
                "mean NO2 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0.1, 0.36, 0.65, 1.25),
                meanConcentrationNO2)
        );
        saveLabel(new Label<>(
                "mean NO2 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0.65, 1.25, 1.65, 2.04),
                meanConcentrationNO2)
        );

        // Średnie stężenie
        final MeanConcentrationO3 meanConcentrationO3 = new MeanConcentrationO3();
        saveLabel(new Label<>(
                "mean O3 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0, 0.03, 0.6, 0.75),
                meanConcentrationO3)
        );
        saveLabel(new Label<>(
                "mean O3 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0.03, 0.06, 0.075, 0.095),
                meanConcentrationO3)
        );
        saveLabel(new Label<>(
                "mean O3 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0.075, 0.095, 0.115, 0.375),
                meanConcentrationO3)
        );

        // Średnie stężenie
        final MeanConcentrationSO2 meanConcentrationSO2 = new MeanConcentrationSO2();
        saveLabel(new Label<>(
                "mean SO2 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        0, 0, 75, 300),
                meanConcentrationSO2)
        );
        saveLabel(new Label<>(
                "mean SO2 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        150, 450, 600, 800),
                meanConcentrationSO2)
        );
        saveLabel(new Label<>(
                "mean SO2 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        600, 1000, 1000, 1000),
                meanConcentrationSO2)
        );

        /*----------------------------------------------------------------------------------------*/
        // Maksymalne stężenie
        final MaxConcentrationCO maxConcentrationCO = new MaxConcentrationCO();
        saveLabel(new Label<>(
                "max CO concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        0, 4, 9, 12),
                maxConcentrationCO));
        saveLabel(new Label<>(
                "max CO concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        9, 12, 15, 30),
                maxConcentrationCO));
        saveLabel(new Label<>(
                "max CO concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        15, 30, 40, 50),
                maxConcentrationCO));

        final MaxConcentrationNO2 maxConcentrationNO2 = new MaxConcentrationNO2();
        saveLabel(new Label<>(
                "max NO2 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0, 0.05, 0.1, 0.36),
                maxConcentrationNO2));
        saveLabel(new Label<>(
                "max NO2 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0.1, 0.36, 0.65, 1.25),
                maxConcentrationNO2));
        saveLabel(new Label<>(
                "max NO2 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0.65, 1.25, 1.65, 2.04),
                maxConcentrationNO2));

        final MaxConcentrationO3 maxConcentrationO3 = new MaxConcentrationO3();
        saveLabel(new Label<>(
                "max O3 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0, 0.03, 0.6, 0.75),
                maxConcentrationO3));
        saveLabel(new Label<>(
                "max O3 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0.03, 0.06, 0.075, 0.095),
                maxConcentrationO3));
        saveLabel(new Label<>(
                "max O3 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0.075, 0.095, 0.115, 0.375),
                maxConcentrationO3));

        final MaxConcentrationSO2 maxConcentrationSO2 = new MaxConcentrationSO2();
        saveLabel(new Label<>(
                "max SO2 concentration is low.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        0, 0, 75, 300),
                maxConcentrationSO2));
        saveLabel(new Label<>(
                "max SO2 concentration is middle.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        150, 450, 600, 800),
                maxConcentrationSO2));
        saveLabel(new Label<>(
                "max SO2 concentration is high.",
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        600, 1000, 1000, 1000),
                maxConcentrationSO2));

        /*----------------------------------------------------------------------------------------*/
        // Wartosc AQI
        final AQIValueCO aqiValueCO = new AQIValueCO();
        saveLabel(new Label<>(
                "CO AQI value is correct.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute, 0, 25, 50, 75),
                aqiValueCO)
        );
        saveLabel(new Label<>(
                "CO AQI value is unhealthy.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute, 50, 75, 150, 250),
                aqiValueCO)
        );
        saveLabel(new Label<>(
                "CO AQI value is hazardous.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute, 150, 250, 400,
                        500),
                aqiValueCO)
        );

        // Wartosc AQI
        final AQIValueNO2 aqiValueNO2 = new AQIValueNO2();
        saveLabel(new Label<>(
                "NO2 AQI value is correct.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute, 0, 25, 50, 75),
                aqiValueNO2)
        );
        saveLabel(new Label<>(
                "NO2 AQI value is unhealthy.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute, 50, 75, 150, 250),
                aqiValueNO2)
        );
        saveLabel(new Label<>(
                "NO2 AQI value is hazardous.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute, 150, 250, 400,
                        500),
                aqiValueNO2)
        );

        // Wartosc AQI
        final AQIValueO3 aqiValueO3 = new AQIValueO3();
        saveLabel(new Label<>(
                "O3 AQI value is correct.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute, 0, 25, 50, 75),
                aqiValueO3)
        );
        saveLabel(new Label<>(
                "O3 AQI value is unhealthy.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute, 50, 75, 150, 250),
                aqiValueO3)
        );
        saveLabel(new Label<>(
                "O3 AQI value is hazardous.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute, 150, 250, 400,
                        500),
                aqiValueO3)
        );

        // Wartosc AQI
        final AQIValueSO2 aqiValueSO2 = new AQIValueSO2();
        saveLabel(new Label<>(
                "SO2 AQI value is correct.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute, 0, 25, 50, 75),
                aqiValueSO2)
        );
        saveLabel(new Label<>(
                "SO2 AQI value is unhealthy.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute, 50, 75, 150, 250),
                aqiValueSO2)
        );
        saveLabel(new Label<>(
                "SO2 AQI value is hazardous.",
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute, 150, 250, 400,
                        500),
                aqiValueSO2)
        );

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
    