package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.model.Pollution;

public interface PollutionRepository extends CrudRepository<Pollution, Long> {

}
    