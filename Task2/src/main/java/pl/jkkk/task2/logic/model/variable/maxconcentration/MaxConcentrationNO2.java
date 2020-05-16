package pl.jkkk.task2.logic.model.variable.maxconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxConcentrationNO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Concentration NO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxConcentrationNO2() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getNO21stMaxValue());
    }
}
    