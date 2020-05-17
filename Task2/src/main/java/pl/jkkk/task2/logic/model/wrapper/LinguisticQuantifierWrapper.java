package pl.jkkk.task2.logic.model.wrapper;

import org.hibernate.internal.util.SerializationHelper;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.model.BaseEntity;

import javax.persistence.Entity;

@Entity
public class LinguisticQuantifierWrapper extends BaseEntity
        implements Wrapper<LinguisticQuantifier> {

    /*------------------------ FIELDS REGION ------------------------*/
    private byte[] linguisticQuantifier;

    /*------------------------ METHODS REGION ------------------------*/
    public LinguisticQuantifierWrapper() {
    }

    public byte[] getLinguisticQuantifier() {
        return linguisticQuantifier;
    }

    public void setLinguisticQuantifier(byte[] linguisticQuantifier) {
        this.linguisticQuantifier = linguisticQuantifier;
    }

    @Override
    public void serialize(LinguisticQuantifier object) {
        linguisticQuantifier = SerializationHelper.serialize(object);
    }

    @Override
    public LinguisticQuantifier deserialize() {
        return (LinguisticQuantifier) SerializationHelper.deserialize(linguisticQuantifier);
    }
}
    