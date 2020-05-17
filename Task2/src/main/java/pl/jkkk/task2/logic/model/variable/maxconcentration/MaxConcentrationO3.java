package pl.jkkk.task2.logic.model.variable.maxconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxConcentrationO3 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Concentration O3";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxConcentrationO3() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getO31stMaxValue());
    }
}
    