package pl.jkkk.task2.logic.service.linguisticquantifier;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.LinguisticQuantifierNotFoundException;
import pl.jkkk.task2.logic.fuzzy.linguistic.LinguisticQuantifier;
import pl.jkkk.task2.logic.model.wrapper.LinguisticQuantifierWrapper;
import pl.jkkk.task2.logic.repository.LinguisticQuantifierWrapperRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LinguisticQuantifierWrapperServiceImpl implements LinguisticQuantifierWrapperService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final LinguisticQuantifierWrapperRepository linguisticQuantifierWrapperRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public LinguisticQuantifierWrapperServiceImpl(
            LinguisticQuantifierWrapperRepository linguisticQuantifierWrapperRepository) {
        this.linguisticQuantifierWrapperRepository = linguisticQuantifierWrapperRepository;
    }

    @Override
    public LinguisticQuantifierWrapper findById(Long id) {
        Optional<LinguisticQuantifierWrapper> quantifier =
                linguisticQuantifierWrapperRepository.findById(id);

        if (!quantifier.isPresent()) {
            throw new LinguisticQuantifierNotFoundException();
        }

        return quantifier.get();
    }

    @Override
    public LinguisticQuantifier findByName(String name) {
        List<LinguisticQuantifierWrapper> linguisticQuantifierWrappers = this.findAll();
        List<LinguisticQuantifier> linguisticQuantifiers = linguisticQuantifierWrappers
                .stream()
                .map((it) -> it.deserialize())
                .collect(Collectors.toList());

        return linguisticQuantifiers
                .stream()
                .filter((it) -> it.getName().equals(name))
                .findFirst()
                .orElseThrow(LinguisticQuantifierNotFoundException::new);
    }

    @Override
    public List<LinguisticQuantifierWrapper> findAll() {
        return StreamSupport
                .stream(linguisticQuantifierWrapperRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public LinguisticQuantifierWrapper save(LinguisticQuantifierWrapper linguisticQuantifier) {
        return linguisticQuantifierWrapperRepository.save(linguisticQuantifier);
    }

    @Override
    public void deleteById(Long id) {
        linguisticQuantifierWrapperRepository.deleteById(id);
    }

    @Override
    public void delete(LinguisticQuantifierWrapper object) {
        linguisticQuantifierWrapperRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        linguisticQuantifierWrapperRepository.deleteAll();
    }

    @Override
    public void deleteByName(String name) {
        LinguisticQuantifier linguisticQuantifier = this.findByName(name);
        LinguisticQuantifierWrapper linguisticQuantifierWrapper = new LinguisticQuantifierWrapper();
        linguisticQuantifierWrapper.serialize(linguisticQuantifier);
        this.delete(linguisticQuantifierWrapper);
    }
}
    