package pl.jkkk.task2.mode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.jkkk.task2.logic.exception.FileOperationException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticSummary;
import pl.jkkk.task2.logic.fuzzy.linguistic.MultisubjectLinguisticSummary;
import pl.jkkk.task2.logic.fuzzy.set.GaussianFuzzySet;
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static pl.jkkk.task2.Main.IS_LOGGING_DATA;
import static pl.jkkk.task2.logic.constant.LogicConstants.POLLUTION_DATA_FILENAME;

@Component
public class CommandMode {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String ALMOST_NONE = "Almost none";
    public static final String SOME = "Some";
    public static final String ABOUT_HALF_OF_ALL = "About half of all";
    public static final String MANY = "Many";
    public static final String ALL = "All";

    public static final String BEEN_DONE_IN_SPRING = "been done in spring";
    public static final String BEEN_DONE_IN_SUMMER = "been done in summer";
    public static final String BEEN_DONE_IN_AUTUMN = "been done in autumn";
    public static final String BEEN_DONE_IN_WINTER = "been done in winter";

    public static final String MAXIMUM_CO_CONCENTRATION_IN_THE_MORNING =
            "maximum CO concentration in the morning";
    public static final String MAXIMUM_CO_CONCENTRATION_IN_THE_AFTERNOON =
            "maximum CO concentration in the afternoon";
    public static final String MAXIMUM_CO_CONCENTRATION_IN_THE_EVENING =
            "maximum CO concentration in the evening";
    public static final String MAXIMUM_CO_CONCENTRATION_IN_THE_NIGHT =
            "maximum CO concentration in the night";
    public static final String MAXIMUM_NO_2_CONCENTRATION_IN_THE_MORNING =
            "maximum NO2 concentration in the morning";
    public static final String MAXIMUM_NO_2_CONCENTRATION_IN_THE_AFTERNOON =
            "maximum NO2 concentration in the afternoon";
    public static final String MAXIMUM_NO_2_CONCENTRATION_IN_THE_EVENING =
            "maximum NO2 concentration in the evening";
    public static final String MAXIMUM_NO_2_CONCENTRATION_IN_THE_NIGHT =
            "maximum NO2 concentration in the night";
    public static final String MAXIMUM_O_3_CONCENTRATION_IN_THE_MORNING =
            "maximum O3 concentration in the morning";
    public static final String MAXIMUM_O_3_CONCENTRATION_IN_THE_AFTERNOON =
            "maximum O3 concentration in the afternoon";
    public static final String MAXIMUM_O_3_CONCENTRATION_IN_THE_EVENING =
            "maximum O3 concentration in the evening";
    public static final String MAXIMUM_O_3_CONCENTRATION_IN_THE_NIGHT =
            "maximum O3 concentration in the night";
    public static final String MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING =
            "maximum SO2 concentration in the morning";
    public static final String MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON =
            "maximum SO2 concentration in the afternoon";
    public static final String MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING =
            "maximum SO2 concentration in the evening";
    public static final String MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT =
            "maximum SO2 concentration in the night";

    public static final String LOW_MEAN_CO_CONCENTRATION = "low mean CO concentration";
    public static final String MIDDLE_MEAN_CO_CONCENTRATION = "middle mean CO concentration";
    public static final String HIGH_MEAN_CO_CONCENTRATION = "high mean CO concentration";
    public static final String LOW_MEAN_NO_2_CONCENTRATION = "low mean NO2 concentration";
    public static final String MIDDLE_MEAN_NO_2_CONCENTRATION =
            "middle mean NO2 concentration";
    public static final String HIGH_MEAN_NO_2_CONCENTRATION = "high mean NO2 concentration";
    public static final String LOW_MEAN_O_3_CONCENTRATION = "low mean O3 concentration";
    public static final String MIDDLE_MEAN_O_3_CONCENTRATION =
            "middle mean O3 concentration";
    public static final String HIGH_MEAN_O_3_CONCENTRATION = "high mean O3 concentration";
    public static final String LOW_MEAN_SO_2_CONCENTRATION = "low mean SO2 concentration";
    public static final String MIDDLE_MEAN_SO_2_CONCENTRATION =
            "middle mean SO2 concentration";
    public static final String HIGH_MEAN_SO_2_CONCENTRATION = "high mean SO2 concentration";

    public static final String LOW_MAXIMUM_CO_CONCENTRATION = "low maximum CO concentration";
    public static final String MIDDLE_MAXIMUM_CO_CONCENTRATION = "middle maximum CO concentration";
    public static final String HIGH_MAXIMUM_CO_CONCENTRATION = "high maximum CO concentration";
    public static final String LOW_MAXIMUM_NO_2_CONCENTRATION = "low maximum NO2 concentration";
    public static final String MIDDLE_MAXIMUM_NO_2_CONCENTRATION =
            "middle maximum NO2 concentration";
    public static final String HIGH_MAXIMUM_NO_2_CONCENTRATION = "high maximum NO2 concentration";
    public static final String LOW_MAXIMUM_O_3_CONCENTRATION = "low maximum O3 concentration";
    public static final String MIDDLE_MAXIMUM_O_3_CONCENTRATION = "middle maximum O3 concentration";
    public static final String HIGH_MAXIMUM_O_3_CONCENTRATION = "high maximum O3 concentration";
    public static final String LOW_MAXIMUM_SO_2_CONCENTRATION = "low maximum SO2 concentration";
    public static final String MIDDLE_MAXIMUM_SO_2_CONCENTRATION =
            "middle maximum SO2 concentration";
    public static final String HIGH_MAXIMUM_SO_2_CONCENTRATION = "high maximum SO2 concentration";

    public static final String CORRECT_CO_AQI_VALUE = "correct CO AQI value";
    public static final String UNHEALTHY_CO_AQI_VALUE = "unhealthy CO AQI value";
    public static final String HAZARDOUS_CO_AQI_VALUE = "hazardous CO AQI value";
    public static final String CORRECT_NO_2_AQI_VALUE = "correct NO2 AQI value";
    public static final String UNHEALTHY_NO_2_AQI_VALUE = "unhealthy NO2 AQI value";
    public static final String HAZARDOUS_NO_2_AQI_VALUE = "hazardous NO2 AQI value";
    public static final String CORRECT_O_3_AQI_VALUE = "correct O3 AQI value";
    public static final String UNHEALTHY_O_3_AQI_VALUE = "unhealthy O3 AQI value";
    public static final String HAZARDOUS_O_3_AQI_VALUE = "hazardous O3 AQI value";
    public static final String CORRECT_SO_2_AQI_VALUE = "correct SO2 AQI value";
    public static final String UNHEALTHY_SO_2_AQI_VALUE = "unhealthy SO2 AQI value";
    public static final String HAZARDOUS_SO_2_AQI_VALUE = "hazardous SO2 AQI value";

    public static final String SUMMARY_SEPARATOR_CMD = ",";

    public static final String TYPE_BASIC = "basic";
    public static final String TYPE_ADVANCED = "advanced";
    public static final String TYPE_MULTI_FIRST = "multiFirst";
    public static final String TYPE_MULTI_SECOND = "multiSecond";
    public static final String TYPE_MULTI_THIRD = "multiThird";

    private final PollutionService pollutionService;
    private final LinguisticQuantifierWrapperService linguisticQuantifierWrapperService;
    private final LabelWrapperService labelWrapperService;

    private FileWriterPlainText fileWriterPlainText = new FileWriterPlainText();

    @Autowired
    private Environment environment;

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
            } else if (args.length > 1) {
                List<List<String>> callArguments = convertStreamOfArgsToList(args);
                List<Pollution> pollutionData = pollutionService.findAll();

                callArguments.forEach((it) -> {
                    LinguisticSummary<Pollution> linguisticSummary = null;

                    if (it.size() > 2) {

                        switch (it.get(0)) {
                            case TYPE_BASIC: {
                                String selectedQuantifier = it.get(1);
                                List<String> selectedSummarizers = new ArrayList<>();

                                for (int i = 2; i < it.size(); i++) {
                                    selectedSummarizers.add(it.get(i));
                                }

                                linguisticSummary = new LinguisticSummary<>(
                                        linguisticQuantifierWrapperService.findByName(selectedQuantifier),
                                        pollutionData,
                                        labelWrapperService.findByNames(selectedSummarizers)
                                );

                                saveDataLog(generateSummaryToString(linguisticSummary), it);
                                break;
                            }
                            case TYPE_ADVANCED: {
                                String selectedQuantifier = it.get(1);
                                String selectedQualifier = it.get(2);
                                List<String> selectedSummarizers = new ArrayList<>();

                                for (int i = 3; i < it.size(); i++) {
                                    selectedSummarizers.add(it.get(i));
                                }

                                linguisticSummary = new LinguisticSummary<>(
                                        linguisticQuantifierWrapperService.findByName(selectedQuantifier),
                                        labelWrapperService.findByName(selectedQualifier),
                                        pollutionData,
                                        labelWrapperService.findByNames(selectedSummarizers)
                                );

                                saveDataLog(generateSummaryToString(linguisticSummary), it);
                                break;
                            }
                            case TYPE_MULTI_FIRST: {
                                String selectedQuantifier = it.get(1);
                                String attributeValue1 = it.get(2);
                                String attributeValue2 = it.get(3);
                                List<String> selectedSummarizers = new ArrayList<>();

                                for (int i = 4; i < it.size(); i++) {
                                    selectedSummarizers.add(it.get(i));
                                }

                                MultisubjectLinguisticSummary<Pollution> summary =
                                        new MultisubjectLinguisticSummary<>(
                                                linguisticQuantifierWrapperService.findByName(selectedQuantifier),
                                                pollutionData,
                                                (Pollution pollution) -> pollution.getCity(),
                                                attributeValue1,
                                                attributeValue2,
                                                labelWrapperService.findByNames(selectedSummarizers)
                                        );

                                saveDataLog(generateMultiSubjectSummaryToString(summary), it);
                                break;
                            }
                            case TYPE_MULTI_SECOND: {
                                String selectedQuantifier = it.get(1);
                                String attributeValue1 = it.get(2);
                                String attributeValue2 = it.get(3);
                                String selectedQualifier = it.get(4);
                                List<String> selectedSummarizers = new ArrayList<>();

                                for (int i = 5; i < it.size(); i++) {
                                    selectedSummarizers.add(it.get(i));
                                }

                                //TODO
//                                MultisubjectLinguisticSummary<Pollution> summary = null;
//
//                                saveDataLog(generateMultiSubjectSummaryToString(summary), it);
                                break;
                            }
                            case TYPE_MULTI_THIRD: {
                                String selectedQuantifier = it.get(1);
                                String attributeValue1 = it.get(2);
                                String attributeValue2 = it.get(3);
                                String selectedQualifier = it.get(4);
                                List<String> selectedSummarizers = new ArrayList<>();

                                for (int i = 5; i < it.size(); i++) {
                                    selectedSummarizers.add(it.get(i));
                                }

                                //TODO
//                                MultisubjectLinguisticSummary<Pollution> summary = null;
//
//                                saveDataLog(generateMultiSubjectSummaryToString(summary), it);
                                break;
                            }
                        }
                    }

                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            printUsage();
        }
    }

    private List<List<String>> convertStreamOfArgsToList(String[] args) {
        List<List<String>> processedArgs = new ArrayList<>();
        List<Integer> separatorIndexes = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(SUMMARY_SEPARATOR_CMD)) {
                separatorIndexes.add(i);
            }
        }

        processedArgs.add(Arrays.asList(
                Arrays.copyOfRange(args, 0, separatorIndexes.get(0))));

        for (int i = 0; i < separatorIndexes.size() - 1; i++) {
            processedArgs.add(Arrays.asList(Arrays.copyOfRange(
                    args, separatorIndexes.get(i) + 1,
                    separatorIndexes.get(i + 1))
            ));
        }

        return processedArgs;
    }

    private String generateMultiSubjectSummaryToString(MultisubjectLinguisticSummary summary) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", decimalFormatSymbols);

        String degreeOfTruth = df.format(summary.degreeOfTruth());

        StringBuilder generatedResult = new StringBuilder();
        generatedResult
                .append(summary.toString())
                .append(". [")
                .append(degreeOfTruth)
                .append("]");

        return generatedResult.toString();
    }

    private String generateSummaryToString(LinguisticSummary linguisticSummary) {
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

        return generatedResult.toString();
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
                ALMOST_NONE,
                new GaussianFuzzySet<Double>(x -> x, 0.00, 0.07),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                SOME,
                new GaussianFuzzySet<Double>(x -> x, 0.28, 0.07),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                ABOUT_HALF_OF_ALL,
                new GaussianFuzzySet<Double>(x -> x, 0.5, 0.08),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                MANY,
                new GaussianFuzzySet<Double>(x -> x, 0.72, 0.07),
                QuantifierType.RELATIVE)
        );
        saveLinguisticQuantifier(new LinguisticQuantifier(
                ALL,
                new GaussianFuzzySet<Double>(x -> x, 1.00, 0.07),
                QuantifierType.RELATIVE)
        );

        /*----------------------------------------------------------------------------------------*/
        // Sezon wykonania pomiaru
        final MeasurementSeason measurementSeason = new MeasurementSeason();
        saveLabel(new Label<>(
                BEEN_DONE_IN_SPRING,
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        32, 92, 122, 183, 0, 366),
                measurementSeason)
        );
        saveLabel(new Label<>(
                BEEN_DONE_IN_SUMMER,
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        122, 183, 214, 275, 0, 366),
                measurementSeason)
        );
        saveLabel(new Label<>(
                BEEN_DONE_IN_AUTUMN,
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        214, 275, 306, 336, 0, 366),
                measurementSeason)
        );
        saveLabel(new Label<>(
                BEEN_DONE_IN_WINTER,
                new TrapezoidalFuzzySet<Pollution>(measurementSeason::extractAttribute,
                        275, 336, 32, 92, 0, 366),
                measurementSeason)
        );

        /*----------------------------------------------------------------------------------------*/
        // Godzina maksymalnego stężenia
        final MaxHourCO maxHourCO = new MaxHourCO();
        saveLabel(new Label<>(
                MAXIMUM_CO_CONCENTRATION_IN_THE_MORNING,
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        3, 6, 10, 13, 0, 24),
                maxHourCO)
        );
        saveLabel(new Label<>(
                MAXIMUM_CO_CONCENTRATION_IN_THE_AFTERNOON,
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        12, 13, 17, 19, 0, 24),
                maxHourCO)
        );
        saveLabel(new Label<>(
                MAXIMUM_CO_CONCENTRATION_IN_THE_EVENING,
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        18, 20, 21, 22, 0, 24),
                maxHourCO)
        );
        saveLabel(new Label<>(
                MAXIMUM_CO_CONCENTRATION_IN_THE_NIGHT,
                new TrapezoidalFuzzySet<Pollution>(maxHourCO::extractAttribute,
                        21, 23, 4, 5, 0, 24),
                maxHourCO)
        );

        // Godzina maksymalnego stężenia
        final MaxHourNO2 maxHourNO2 = new MaxHourNO2();
        saveLabel(new Label<>(
                MAXIMUM_NO_2_CONCENTRATION_IN_THE_MORNING,
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        3, 6, 10, 13, 0, 24),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_NO_2_CONCENTRATION_IN_THE_AFTERNOON,
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        12, 13, 17, 19, 0, 24),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_NO_2_CONCENTRATION_IN_THE_EVENING,
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        18, 20, 21, 22, 0, 24),
                maxHourNO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_NO_2_CONCENTRATION_IN_THE_NIGHT,
                new TrapezoidalFuzzySet<Pollution>(maxHourNO2::extractAttribute,
                        21, 23, 4, 5, 0, 24),
                maxHourNO2)
        );

        // Godzina maksymalnego stężenia
        final MaxHourO3 maxHourO3 = new MaxHourO3();
        saveLabel(new Label<>(
                MAXIMUM_O_3_CONCENTRATION_IN_THE_MORNING,
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        3, 6, 10, 13, 0, 24),
                maxHourO3)
        );
        saveLabel(new Label<>(
                MAXIMUM_O_3_CONCENTRATION_IN_THE_AFTERNOON,
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        12, 13, 17, 19, 0, 24),
                maxHourO3)
        );
        saveLabel(new Label<>(
                MAXIMUM_O_3_CONCENTRATION_IN_THE_EVENING,
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        18, 20, 21, 22, 0, 24),
                maxHourO3)
        );
        saveLabel(new Label<>(
                MAXIMUM_O_3_CONCENTRATION_IN_THE_NIGHT,
                new TrapezoidalFuzzySet<Pollution>(maxHourO3::extractAttribute,
                        21, 23, 4, 5, 0, 24),
                maxHourO3)
        );

        // Godzina maksymalnego stężenia
        final MaxHourSO2 maxHoursSO2 = new MaxHourSO2();
        saveLabel(new Label<>(
                MAXIMUM_SO_2_CONCENTRATION_IN_THE_MORNING,
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        3, 6, 10, 13, 0, 24),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_SO_2_CONCENTRATION_IN_THE_AFTERNOON,
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        12, 13, 17, 19, 0, 24),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_SO_2_CONCENTRATION_IN_THE_EVENING,
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        18, 20, 21, 22, 0, 24),
                maxHoursSO2)
        );
        saveLabel(new Label<>(
                MAXIMUM_SO_2_CONCENTRATION_IN_THE_NIGHT,
                new TrapezoidalFuzzySet<Pollution>(maxHoursSO2::extractAttribute,
                        21, 23, 4, 5, 0, 24),
                maxHoursSO2)
        );

        /*----------------------------------------------------------------------------------------*/
        // Średnie stężenie
        final MeanConcentrationCO meanConcentrationCO = new MeanConcentrationCO();
        saveLabel(new Label<>(
                LOW_MEAN_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        0, 4, 9, 12),
                meanConcentrationCO)
        );
        saveLabel(new Label<>(
                MIDDLE_MEAN_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        9, 12, 15, 30),
                meanConcentrationCO)
        );
        saveLabel(new Label<>(
                HIGH_MEAN_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationCO::extractAttribute,
                        15, 30, 40, 50),
                meanConcentrationCO)
        );

        // Średnie stężenie
        final MeanConcentrationNO2 meanConcentrationNO2 = new MeanConcentrationNO2();
        saveLabel(new Label<>(
                LOW_MEAN_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0, 0.05, 0.1, 0.36),
                meanConcentrationNO2)
        );
        saveLabel(new Label<>(
                MIDDLE_MEAN_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0.1, 0.36, 0.65, 1.25),
                meanConcentrationNO2)
        );
        saveLabel(new Label<>(
                HIGH_MEAN_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationNO2::extractAttribute,
                        0.65, 1.25, 1.65, 2.04),
                meanConcentrationNO2)
        );

        // Średnie stężenie
        final MeanConcentrationO3 meanConcentrationO3 = new MeanConcentrationO3();
        saveLabel(new Label<>(
                LOW_MEAN_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0, 0.03, 0.6, 0.75),
                meanConcentrationO3)
        );
        saveLabel(new Label<>(
                MIDDLE_MEAN_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0.03, 0.06, 0.075, 0.095),
                meanConcentrationO3)
        );
        saveLabel(new Label<>(
                HIGH_MEAN_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationO3::extractAttribute,
                        0.075, 0.095, 0.115, 0.375),
                meanConcentrationO3)
        );

        // Średnie stężenie
        final MeanConcentrationSO2 meanConcentrationSO2 = new MeanConcentrationSO2();
        saveLabel(new Label<>(
                LOW_MEAN_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        0, 0, 75, 300),
                meanConcentrationSO2)
        );
        saveLabel(new Label<>(
                MIDDLE_MEAN_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        150, 450, 600, 800),
                meanConcentrationSO2)
        );
        saveLabel(new Label<>(
                HIGH_MEAN_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(meanConcentrationSO2::extractAttribute,
                        600, 1000, 1000, 1000),
                meanConcentrationSO2)
        );

        /*----------------------------------------------------------------------------------------*/
        // Maksymalne stężenie
        final MaxConcentrationCO maxConcentrationCO = new MaxConcentrationCO();
        saveLabel(new Label<>(
                LOW_MAXIMUM_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        0, 4, 9, 12),
                maxConcentrationCO));
        saveLabel(new Label<>(
                MIDDLE_MAXIMUM_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        9, 12, 15, 30),
                maxConcentrationCO));
        saveLabel(new Label<>(
                HIGH_MAXIMUM_CO_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationCO::extractAttribute,
                        15, 30, 40, 50),
                maxConcentrationCO));

        final MaxConcentrationNO2 maxConcentrationNO2 = new MaxConcentrationNO2();
        saveLabel(new Label<>(
                LOW_MAXIMUM_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0, 0.05, 0.1, 0.36),
                maxConcentrationNO2));
        saveLabel(new Label<>(
                MIDDLE_MAXIMUM_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0.1, 0.36, 0.65, 1.25),
                maxConcentrationNO2));
        saveLabel(new Label<>(
                HIGH_MAXIMUM_NO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationNO2::extractAttribute,
                        0.65, 1.25, 1.65, 2.04),
                maxConcentrationNO2));

        final MaxConcentrationO3 maxConcentrationO3 = new MaxConcentrationO3();
        saveLabel(new Label<>(
                LOW_MAXIMUM_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0, 0.03, 0.6, 0.75),
                maxConcentrationO3));
        saveLabel(new Label<>(
                MIDDLE_MAXIMUM_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0.03, 0.06, 0.075, 0.095),
                maxConcentrationO3));
        saveLabel(new Label<>(
                HIGH_MAXIMUM_O_3_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationO3::extractAttribute,
                        0.075, 0.095, 0.115, 0.375),
                maxConcentrationO3));

        final MaxConcentrationSO2 maxConcentrationSO2 = new MaxConcentrationSO2();
        saveLabel(new Label<>(
                LOW_MAXIMUM_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        0, 0, 75, 300),
                maxConcentrationSO2));
        saveLabel(new Label<>(
                MIDDLE_MAXIMUM_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        150, 450, 600, 800),
                maxConcentrationSO2));
        saveLabel(new Label<>(
                HIGH_MAXIMUM_SO_2_CONCENTRATION,
                new TrapezoidalFuzzySet<Pollution>(maxConcentrationSO2::extractAttribute,
                        600, 1000, 1000, 1000),
                maxConcentrationSO2));

        /*----------------------------------------------------------------------------------------*/
        // Wartosc AQI
        final AQIValueCO aqiValueCO = new AQIValueCO();
        saveLabel(new Label<>(
                CORRECT_CO_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute,
                        0, 25, 50, 75),
                aqiValueCO)
        );
        saveLabel(new Label<>(
                UNHEALTHY_CO_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute,
                        50, 75, 150, 250),
                aqiValueCO)
        );
        saveLabel(new Label<>(
                HAZARDOUS_CO_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueCO::extractAttribute,
                        150, 250, 400, 500),
                aqiValueCO)
        );

        // Wartosc AQI
        final AQIValueNO2 aqiValueNO2 = new AQIValueNO2();
        saveLabel(new Label<>(
                CORRECT_NO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute,
                        0, 25, 50, 75),
                aqiValueNO2)
        );
        saveLabel(new Label<>(
                UNHEALTHY_NO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute,
                        50, 75, 150, 250),
                aqiValueNO2)
        );
        saveLabel(new Label<>(
                HAZARDOUS_NO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueNO2::extractAttribute,
                        150, 250, 400, 500),
                aqiValueNO2)
        );

        // Wartosc AQI
        final AQIValueO3 aqiValueO3 = new AQIValueO3();
        saveLabel(new Label<>(
                CORRECT_O_3_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute,
                        0, 25, 50, 75),
                aqiValueO3)
        );
        saveLabel(new Label<>(
                UNHEALTHY_O_3_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute,
                        50, 75, 150, 250),
                aqiValueO3)
        );
        saveLabel(new Label<>(
                HAZARDOUS_O_3_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueO3::extractAttribute,
                        150, 250, 400, 500),
                aqiValueO3)
        );

        // Wartosc AQI
        final AQIValueSO2 aqiValueSO2 = new AQIValueSO2();
        saveLabel(new Label<>(
                CORRECT_SO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute,
                        0, 25, 50, 75),
                aqiValueSO2)
        );
        saveLabel(new Label<>(
                UNHEALTHY_SO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute,
                        50, 75, 150, 250),
                aqiValueSO2)
        );
        saveLabel(new Label<>(
                HAZARDOUS_SO_2_AQI_VALUE,
                new TrapezoidalFuzzySet<Pollution>(aqiValueSO2::extractAttribute,
                        150, 250, 400, 500),
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

    private void saveDataLog(String value, List<String> filename) {
        if (IS_LOGGING_DATA) {
            try {
                fileWriterPlainText.writePlainText(filename, value);
            } catch (FileOperationException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("-------------------------------------------------");
            System.out.println("LOGGING TO FILE DISABLED");
            System.out.println("-------------------------------------------------");
        }
    }
}
