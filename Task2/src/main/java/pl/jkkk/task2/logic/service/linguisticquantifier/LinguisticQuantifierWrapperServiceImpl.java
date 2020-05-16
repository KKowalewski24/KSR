package pl.jkkk.task2.logic.service.linguisticquantifier;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.LinguisticQuantifierNotFoundException;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.repository.LinguisticQuantifierWrapperRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LinguisticQuantifierWrapperServiceImpl implements LinguisticQuantifierWrapperService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final LinguisticQuantifierWrapperRepository quantifierRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public LinguisticQuantifierWrapperServiceImpl(LinguisticQuantifierWrapperRepository quantifierRepository) {
        this.quantifierRepository = quantifierRepository;
    }

    @Override
    public LinguisticQuantifierWrapper findById(Long id) {
        Optional<LinguisticQuantifierWrapper> quantifier = quantifierRepository.findById(id);

        if (!quantifier.isPresent()) {
            throw new LinguisticQuantifierNotFoundException();
        }

        return quantifier.get();
    }

    //    @Override
    //    public LinguisticQuantifierWrapper findByName(String name) {
    //        Optional<LinguisticQuantifierWrapper> quantifier = quantifierRepository.findByName
    //        (name);
    //
    //        if (!quantifier.isPresent()) {
    //            throw new LinguisticQuantifierNotFoundException();
    //        }
    //
    //        return quantifier.get();
    //    }

    @Override
    public List<LinguisticQuantifierWrapper> findAll() {
        return StreamSupport
                .stream(quantifierRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public LinguisticQuantifierWrapper save(LinguisticQuantifierWrapper linguisticQuantifier) {
        return quantifierRepository.save(linguisticQuantifier);
    }

    @Override
    public void deleteById(Long id) {
        quantifierRepository.deleteById(id);
    }

    @Override
    public void delete(LinguisticQuantifierWrapper object) {
        quantifierRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        quantifierRepository.deleteAll();
    }

    //    @Override
    //    public void deleteByName(String name) {
    //        quantifierRepository.deleteByName(name);
    //    }
}
    