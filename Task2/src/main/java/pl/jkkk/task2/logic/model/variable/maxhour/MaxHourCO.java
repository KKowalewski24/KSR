package pl.jkkk.task2.logic.model.variable.maxhour;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticVariable;
import pl.jkkk.task2.logic.model.Pollution;

public class MaxHourCO extends LinguisticVariable<Pollution> {

    /*------------------------ FIELDS REGION ------------------------*/
    public static final String NAME ="Max Hour Concentration CO" ;

    /*------------------------ METHODS REGION ------------------------*/
    public MaxHourCO() {
        super(NAME);
    }

    @Override
    protected Double extractAttribute(Pollution object) {
        return new Double(object.getCO1stMaxHour());
    }
}
    