package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;

import java.util.Optional;

public interface LabelWrapperRepository extends CrudRepository<LabelWrapper, Long> {

    //    TODO
    //    Optional<LabelWrapper> findByName(String name);

//    void deleteByName(String name);
}
