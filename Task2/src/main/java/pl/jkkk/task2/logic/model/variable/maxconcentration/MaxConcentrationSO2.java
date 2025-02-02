package pl.jkkk.task2.logic.model.variable.maxconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxConcentrationSO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Concentration SO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxConcentrationSO2() {
        super(NAME);
    }

    @Override
    public Double extractAttribute(Pollution object) {
        return new Double(object.getSO21stMaxValue());
    }
}
    