package pl.jkkk.task2.logic.service.label;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.LabelNotFoundException;
import pl.jkkk.task2.logic.fuzzy.linguistic.Label;
import pl.jkkk.task2.logic.repository.LabelRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LabelServiceImpl implements LabelService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final LabelRepository labelRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label findById(Long id) {
        Optional<Label> label = labelRepository.findById(id);

        if (!label.isPresent()) {
            throw new LabelNotFoundException();
        }

        return label.get();
    }

    @Override
    public Label findByName(String name) {
        Optional<Label> label = labelRepository.findByName(name);

        if (!label.isPresent()) {
            throw new LabelNotFoundException();
        }

        return label.get();
    }

    @Override
    public List<Label> findAll() {
        return StreamSupport
                .stream(labelRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Label save(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }

    @Override
    public void delete(Label object) {
        labelRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        labelRepository.deleteAll();
    }

    @Override
    public void deleteByName(String name) {
        labelRepository.deleteByName(name);
    }
}
    