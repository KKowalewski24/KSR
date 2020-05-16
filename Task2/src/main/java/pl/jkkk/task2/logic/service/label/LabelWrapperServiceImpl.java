package pl.jkkk.task2.logic.service.label;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.LabelNotFoundException;
import pl.jkkk.task2.logic.model.wrapper.LabelWrapper;
import pl.jkkk.task2.logic.repository.LabelWrapperRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LabelWrapperServiceImpl implements LabelWrapperService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final LabelWrapperRepository labelWrapperRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public LabelWrapperServiceImpl(LabelWrapperRepository labelWrapperRepository) {
        this.labelWrapperRepository = labelWrapperRepository;
    }

    @Override
    public LabelWrapper findById(Long id) {
        Optional<LabelWrapper> label = labelWrapperRepository.findById(id);

        if (!label.isPresent()) {
            throw new LabelNotFoundException();
        }

        return label.get();
    }

    @Override
    public LabelWrapper findByName(String name) {
        //        TODO
        //        Optional<LabelWrapper> label = labelWrapperRepository.findByName(name);
        //
        //        if (!label.isPresent()) {
        //            throw new LabelNotFoundException();
        //        }
        //
        //        return label.get();
        return null;
    }

    @Override
    public List<LabelWrapper> findAll() {
        return StreamSupport
                .stream(labelWrapperRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public LabelWrapper save(LabelWrapper label) {
        return labelWrapperRepository.save(label);
    }

    @Override
    public void deleteById(Long id) {
        labelWrapperRepository.deleteById(id);
    }

    @Override
    public void delete(LabelWrapper object) {
        labelWrapperRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        labelWrapperRepository.deleteAll();
    }

    @Override
    public void deleteByName(String name) {
        //        TODO
        //        labelWrapperRepository.deleteByName(name);
    }
}
    