package pl.jkkk.task2.logic.model.variable.maxhour;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxHourSO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Hour Concentration SO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxHourSO2() {
        super(NAME);
    }

    @Override
    public Double extractAttribute(Pollution object) {
        return new Double(object.getSO21stMaxHour());
    }
}
    