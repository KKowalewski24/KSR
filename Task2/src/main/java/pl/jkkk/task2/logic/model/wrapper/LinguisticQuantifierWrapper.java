package pl.jkkk.task2.logic.model.wrapper;

import org.hibernate.internal.util.SerializationHelper;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.model.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Lob;

@Entity
public class LinguisticQuantifierWrapper extends BaseEntity
        implements Wrapper<LinguisticQuantifier> {

    /*------------------------ FIELDS REGION ------------------------*/
    @Lob
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
        this.linguisticQuantifier = SerializationHelper.serialize(linguisticQuantifier);
    }

    @Override
    public LinguisticQuantifier deserialize() {
        return (LinguisticQuantifier) SerializationHelper.deserialize(linguisticQuantifier);
    }
}
    