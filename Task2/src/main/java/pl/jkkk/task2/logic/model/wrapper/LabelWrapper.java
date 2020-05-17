package pl.jkkk.task2.logic.model.wrapper;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.internal.util.SerializationHelper;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.model.BaseEntity;
import pl.jkkk.task2.logic.model.Pollution;

import javax.persistence.Entity;

@Entity
public class LabelWrapper extends BaseEntity implements Wrapper<Label<Pollution>> {

    /*------------------------ FIELDS REGION ------------------------*/
    private byte[] label;

    /*------------------------ METHODS REGION ------------------------*/
    public LabelWrapper() {
    }

    public byte[] getLabel() {
        return label;
    }

    public void setLabel(byte[] label) {
        this.label = label;
    }

    @Override
    public void serialize(Label<Pollution> object) {
        this.label = SerializationHelper.serialize(object);
    }

    @Override
    public Label<Pollution> deserialize() {
        return (Label<Pollution>) SerializationHelper.deserialize(label);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LabelWrapper that = (LabelWrapper) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(label, that.label)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(label)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("label", label)
                .toString();
    }
}
