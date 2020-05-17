package pl.jkkk.task2.logic.service.pollution;

import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.service.BaseService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PollutionService extends BaseService<Pollution> {

    Set<Pollution> findAll();

    Pollution save(Pollution object);

    List<Pollution> saveAll(Collection<Pollution> collection);
}
    