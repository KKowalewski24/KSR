package pl.jkkk.task2.logic.model.variable.aqi;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class AQIValueO3 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "AQI Value O3";

    /*------------------------ METHODS REGION ------------------------*/
    public AQIValueO3() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getO3AQI());
    }
}
    