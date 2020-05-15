package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;

import java.util.Optional;

public interface LabelRepository extends CrudRepository<Label, Long> {

    Optional<Label> findByName(String name);

    void deleteByName(String name);
}
