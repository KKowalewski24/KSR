package pl.jkkk.task2.logic.model.variable.maxconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxConcentrationCO extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Max Concentration CO";

    /*------------------------ METHODS REGION ------------------------*/
    public MaxConcentrationCO() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getCO1stMaxValue());
    }
}
    