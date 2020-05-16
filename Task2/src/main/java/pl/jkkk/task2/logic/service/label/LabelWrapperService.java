package pl.jkkk.task2.logic.service.label;

import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.service.BaseService;

public interface LabelWrapperService extends BaseService<LabelWrapper> {

    LabelWrapper save(LabelWrapper object);

    LabelWrapper findByName(String name);

    void deleteByName(String name);
}
