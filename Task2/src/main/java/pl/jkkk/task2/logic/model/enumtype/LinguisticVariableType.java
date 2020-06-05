package pl.jkkk.task2.logic.model.enumtype;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;
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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum LinguisticVariableType {

    /*------------------------ FIELDS REGION ------------------------*/
    AQI_VALUE_CO(AQIValueCO.NAME),
    AQI_VALUE_NO2(AQIValueNO2.NAME),
    AQI_VALUE_O3(AQIValueO3.NAME),
    AQI_VALUE_SO2(AQIValueSO2.NAME),

    MAX_CONCENTRATION_CO(MaxConcentrationCO.NAME),
    MAX_CONCENTRATION_NO2(MaxConcentrationNO2.NAME),
    MAX_CONCENTRATION_O3(MaxConcentrationO3.NAME),
    MAX_CONCENTRATION_SO2(MaxConcentrationSO2.NAME),

    MEAN_CONCENTRATION_CO(MeanConcentrationCO.NAME),
    MEAN_CONCENTRATION_NO2(MeanConcentrationNO2.NAME),
    MEAN_CONCENTRATION_O3(MeanConcentrationO3.NAME),
    MEAN_CONCENTRATION_SO2(MeanConcentrationSO2.NAME),

    MAX_HOUR_CO(MaxHourCO.NAME),
    MAX_HOUR_NO2(MaxHourNO2.NAME),
    MAX_HOUR_O3(MaxHourO3.NAME),
    MAX_HOUR_SO2(MaxHourSO2.NAME),

    MEASUREMENT_SEASON(MeasurementSeason.NAME);

    private final String name;

    /*------------------------ METHODS REGION ------------------------*/
    LinguisticVariableType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static LinguisticVariableType fromString(final String text) {
        return Arrays.asList(LinguisticVariableType.values())
                .stream()
                .filter((it) -> it.getName().equals(text))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<String> getNamesList() {
        return Arrays.asList(LinguisticVariableType.values())
                .stream()
                .map((it) -> it.getName())
                .collect(Collectors.toList());
    }

    public static LinguisticVariable<Pollution> getObjectFromString(final String text) {
        LinguisticVariableType linguisticVariableType = fromString(text);

        switch (linguisticVariableType) {
            case AQI_VALUE_CO: {
                return new AQIValueCO();
            }
            case AQI_VALUE_NO2: {
                return new AQIValueNO2();
            }
            case AQI_VALUE_O3: {
                return new AQIValueO3();
            }
            case AQI_VALUE_SO2: {
                return new AQIValueSO2();
            }

            case MAX_CONCENTRATION_CO: {
                return new MaxConcentrationCO();
            }
            case MAX_CONCENTRATION_NO2: {
                return new MaxConcentrationNO2();
            }
            case MAX_CONCENTRATION_O3: {
                return new MaxConcentrationO3();
            }
            case MAX_CONCENTRATION_SO2: {
                return new MaxConcentrationSO2();
            }

            case MEAN_CONCENTRATION_CO: {
                return new MeanConcentrationCO();
            }
            case MEAN_CONCENTRATION_NO2: {
                return new MeanConcentrationNO2();
            }
            case MEAN_CONCENTRATION_O3: {
                return new MeanConcentrationO3();
            }
            case MEAN_CONCENTRATION_SO2: {
                return new MeanConcentrationSO2();
            }

            case MAX_HOUR_CO: {
                return new MaxHourCO();
            }
            case MAX_HOUR_NO2: {
                return new MaxHourNO2();
            }
            case MAX_HOUR_O3: {
                return new MaxHourO3();
            }
            case MAX_HOUR_SO2: {
                return new MaxHourSO2();
            }

            case MEASUREMENT_SEASON: {
                return new MeasurementSeason();
            }
        }

        return null;
    }
}
