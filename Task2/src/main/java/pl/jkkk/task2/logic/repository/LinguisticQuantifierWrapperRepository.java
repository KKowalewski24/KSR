package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;

import java.util.Optional;

public interface LinguisticQuantifierWrapperRepository
        extends CrudRepository<LinguisticQuantifierWrapper, Long> {

    //    TODO
    //    Optional<LinguisticQuantifierWrapper> findByName(String name);

    //    void deleteByName(String name);
}
