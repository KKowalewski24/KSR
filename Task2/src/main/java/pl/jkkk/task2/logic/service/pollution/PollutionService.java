package pl.jkkk.task2.logic.service.pollution;

import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.service.BaseService;

import java.util.Collection;
import java.util.List;

public interface PollutionService extends BaseService<Pollution> {
    Pollution save(Pollution object);

    List<Pollution> saveAll(Collection<Pollution> collection);
}
    