package pl.jkkk.task2.logic.model.variable.maxhour;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxHourNO2 extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Hour Concentration NO2";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxHourNO2() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getNO21stMaxHour());
    }
}
    