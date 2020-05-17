package pl.jkkk.task2.logic.service.label;

import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.service.WrapperService;

public interface LabelWrapperService extends WrapperService<LabelWrapper, Label<Pollution>> {

}
