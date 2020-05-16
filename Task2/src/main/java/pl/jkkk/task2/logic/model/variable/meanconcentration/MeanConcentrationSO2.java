package pl.jkkk.task2.logic.model.variable.meanconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MeanConcentrationSO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Mean Concentration SO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MeanConcentrationSO2() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getSO2Mean());
    }
}
    