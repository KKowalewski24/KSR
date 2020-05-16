package pl.jkkk.task2.logic.model.variable.meanconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MeanConcentrationO3 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME ="Mean Concentration O3" ;

    /*------------------------ METHODS REGION ------------------------*/
    public MeanConcentrationO3() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getO3Mean());
    }
}
    