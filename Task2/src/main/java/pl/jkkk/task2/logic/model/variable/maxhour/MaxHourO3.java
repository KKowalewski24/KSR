package pl.jkkk.task2.logic.model.variable.maxhour;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxHourO3 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Hour Concentration O3";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxHourO3() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getO31stMaxHour());
    }
}
    