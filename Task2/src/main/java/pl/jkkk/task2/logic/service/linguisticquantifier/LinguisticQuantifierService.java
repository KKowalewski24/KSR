package pl.jkkk.task2.logic.service.linguisticquantifier;

import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.service.BaseService;

public interface LinguisticQuantifierService extends BaseService<LinguisticQuantifier> {
    //    TODO CHANGE LABEL TO LABELS' PARAMS CONSTRUCTOR
    LinguisticQuantifier save(LinguisticQuantifier linguisticQuantifier);

    LinguisticQuantifier findByName(String name);

    void deleteByName(String name);
}
