package pl.jkkk.task2.logic.service.linguisticquantifier;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.service.BaseService;

public interface LinguisticQuantifierWrapperService
        extends BaseService<LinguisticQuantifierWrapper> {

    LinguisticQuantifierWrapper save(LinguisticQuantifierWrapper linguisticQuantifier);

    LinguisticQuantifier findByName(String name);

    void deleteByName(String name);
}
