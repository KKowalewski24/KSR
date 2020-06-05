package pl.jkkk.task2.logic.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;

public interface LabelWrapperRepository extends CrudRepository<LabelWrapper, Long> {

}
