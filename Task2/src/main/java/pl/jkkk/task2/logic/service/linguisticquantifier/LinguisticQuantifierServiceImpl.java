package pl.jkkk.task2.logic.service.linguisticquantifier;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.LinguisticQuantifierNotFoundException;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.repository.LinguisticQuantifierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LinguisticQuantifierServiceImpl implements LinguisticQuantifierService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final LinguisticQuantifierRepository quantifierRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public LinguisticQuantifierServiceImpl(LinguisticQuantifierRepository quantifierRepository) {
        this.quantifierRepository = quantifierRepository;
    }

    @Override
    public LinguisticQuantifier findById(Long id) {
        Optional<LinguisticQuantifier> quantifier = quantifierRepository.findById(id);

        if (!quantifier.isPresent()) {
            throw new LinguisticQuantifierNotFoundException();
        }

        return quantifier.get();
    }

    @Override
    public List<LinguisticQuantifier> findAll() {
        return StreamSupport
                .stream(quantifierRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public LinguisticQuantifier save(LinguisticQuantifier linguisticQuantifier) {
        return quantifierRepository.save(linguisticQuantifier);
    }

    @Override
    public void deleteById(Long id) {
        quantifierRepository.deleteById(id);
    }

    @Override
    public void delete(LinguisticQuantifier object) {
        quantifierRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        quantifierRepository.deleteAll();
    }

    @Override
    public LinguisticQuantifier findByName(String name) {
        return quantifierRepository.findByName(name);
    }

    @Override
    public void deleteByName(String name) {
        quantifierRepository.deleteByName(name);
    }
}
    