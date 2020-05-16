package pl.jkkk.task2.logic.model.variable.meanconcentration;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MeanConcentrationCO extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME = "Mean Concentration CO";

    /*------------------------ METHODS REGION ------------------------*/
    public MeanConcentrationCO() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getCOMean());
    }
}
    