package pl.jkkk.task2.logic.model.variable.meanconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MeanConcentrationNO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Mean Concentration NO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MeanConcentrationNO2() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getNO2Mean());
    }
}
    