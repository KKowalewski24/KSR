package pl.jkkk.task2.logic.service.label;

import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.service.BaseService;

public interface LabelService extends BaseService<Label> {
    //    TODO CHANGE LABEL TO LABELS' PARAMS CONSTRUCTOR
    Label save(Label object);

    Label findByName(String name);

    void deleteByName(String name);
}
