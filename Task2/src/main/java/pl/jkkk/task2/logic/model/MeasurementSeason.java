package pl.jkkk.task2.logic.model;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;

public class MeasurementSeason extends LinguisticVariable<Pollution> {
    
    public MeasurementSeason() {
        super("measurement season");
    }

    @Override
    protected Double extractAttribute(Pollution pollution) {
        return new Double(pollution.getDateLocal().getDayOfYear());
    }
}
