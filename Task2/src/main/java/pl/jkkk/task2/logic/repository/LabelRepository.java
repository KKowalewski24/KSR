package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;

public interface LabelRepository extends CrudRepository<Label, Long> {

}
