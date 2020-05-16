package pl.jkkk.task2.logic.model;

import javax.persistence.Entity;

import org.hibernate.internal.util.SerializationHelper;

import pl.jkkk.task2.logic.fuzzy.linguistic.Label;

@Entity
public class LabelWrapper extends BaseEntity {

    private byte[] label;

    public void serializeLabel(Label<Pollution> label) {
        this.label = SerializationHelper.serialize(label);
    }

    public Label<Pollution> deserializeLabel() {
        return (Label<Pollution>)SerializationHelper.deserialize(label);
    }

    public void setLabel(byte[] label) {
        this.label = label;
    }

    public byte[] getLabel() {
        return label;
    }
}
