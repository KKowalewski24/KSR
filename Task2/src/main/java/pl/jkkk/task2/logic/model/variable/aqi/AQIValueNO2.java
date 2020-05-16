package pl.jkkk.task2.logic.model.variable.aqi;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class AQIValueNO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "AQI Value NO2";

    /*------------------------ METHODS REGION ------------------------*/
    public AQIValueNO2() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getNO2AQI());
    }
}
    