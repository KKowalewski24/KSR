package pl.jkkk.task2.logic.model.variable.aqi;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class AQIValueCO extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "AQI Value CO";

    /*------------------------ METHODS REGION ------------------------*/
    public AQIValueCO() {
        super(NAME);
    }

    @Override
    public Double extractAttribute(Pollution object) {
        return new Double(object.getCOAQI());
    }
}
    