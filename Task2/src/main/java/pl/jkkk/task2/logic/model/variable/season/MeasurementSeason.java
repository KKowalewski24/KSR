package pl.jkkk.task2.logic.model.variable.season;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MeasurementSeason extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Measurement Season";

    /*------------------------ METHODS REGION ------------------------*/
    public MeasurementSeason() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution pollution) {
        return new Double(pollution.getDateLocal().getDayOfYear());
    }
}
