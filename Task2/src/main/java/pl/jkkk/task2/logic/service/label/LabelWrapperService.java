package pl.jkkk.task2.logic.service.label;

import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.service.BaseService;

public interface LabelWrapperService extends BaseService<LabelWrapper> {

    LabelWrapper save(LabelWrapper object);

    Label<Pollution> findByName(String name);

    void deleteByName(String name);
}
