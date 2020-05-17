package pl.jkkk.task2.logic.model.wrapper;

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
}
