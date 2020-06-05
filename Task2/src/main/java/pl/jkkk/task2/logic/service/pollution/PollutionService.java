package pl.jkkk.task2.logic.service.pollution;

import java.util.Collection;
import java.util.List;

import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.service.BaseService;

public interface PollutionService extends BaseService<Pollution> {

    List<Pollution> findAll();

    Pollution save(Pollution object);

    List<Pollution> saveAll(Collection<Pollution> collection);
}
    