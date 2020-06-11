package pl.jkkk.task2.logic.service.pollution;

import org.springframework.stereotype.Service;
import pl.jkkk.task2.logic.exception.PollutionNotFoundException;
import pl.jkkk.task2.logic.model.Pollution;
import pl.jkkk.task2.logic.repository.PollutionRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PollutionServiceImpl implements PollutionService {

    /*------------------------ FIELDS REGION ------------------------*/
    private final PollutionRepository pollutionRepository;

    /*------------------------ METHODS REGION ------------------------*/
    public PollutionServiceImpl(PollutionRepository pollutionRepository) {
        this.pollutionRepository = pollutionRepository;
    }

    @Override
    public Pollution findById(Long id) {
        Optional<Pollution> pollution = pollutionRepository.findById(id);

        if (!pollution.isPresent()) {
            throw new PollutionNotFoundException();
        }

        return pollution.get();
    }

    @Override
    public List<Pollution> findAll() {
        return StreamSupport
                .stream(pollutionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Pollution save(Pollution object) {
        return pollutionRepository.save(object);
    }

    @Override
    public List<Pollution> saveAll(Collection<Pollution> collection) {
        return StreamSupport
                .stream(pollutionRepository.saveAll(collection).spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        pollutionRepository.deleteById(id);
    }

    @Override
    public void delete(Pollution object) {
        pollutionRepository.delete(object);
    }

    @Override
    public void deleteAll() {
        pollutionRepository.deleteAll();
    }
}
