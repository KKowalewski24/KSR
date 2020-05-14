package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;

public interface LinguisticQuantifierRepository extends CrudRepository<LinguisticQuantifier, Long> {

}
