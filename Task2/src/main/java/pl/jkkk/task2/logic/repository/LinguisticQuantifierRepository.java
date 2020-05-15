package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;

import java.util.Optional;

public interface LinguisticQuantifierRepository extends CrudRepository<LinguisticQuantifier, Long> {

    Optional<LinguisticQuantifier> findByName(String name);

    void deleteByName(String name);
}
