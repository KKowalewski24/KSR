package pl.jkkk.task2.logic.model.wrapper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LinguisticQuantifierWrapper that = (LinguisticQuantifierWrapper) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(linguisticQuantifier, that.linguisticQuantifier)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(linguisticQuantifier)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("linguisticQuantifier", linguisticQuantifier)
                .toString();
    }
}
    