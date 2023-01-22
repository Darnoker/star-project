package pl.ug.edu.kglab.starproject.starproject.service;

import org.springframework.stereotype.Service;
import pl.ug.edu.kglab.starproject.starproject.domain.Constellation;
import pl.ug.edu.kglab.starproject.starproject.repository.ConstellationRepository;
import pl.ug.edu.kglab.starproject.starproject.repository.StarRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ConstellationService {

    final ConstellationRepository constellationRepository;

    public ConstellationService(StarRepository starRepository, ConstellationRepository constellationRepository) {
        this.constellationRepository = constellationRepository;
    }

    public Optional<Constellation> getById(Long id) {
        if (!constellationRepository.existsById(id)) {
            throw new EntityNotFoundException("get by id error");
        }

        return constellationRepository.findById(id);

    }

    public List<Constellation> getAll() {
        return (List<Constellation>) constellationRepository.findAll();
    }

    public Constellation add(Constellation constellation) {
        return constellationRepository.save(constellation);
    }

    public Constellation update(Long id, Constellation constellation) throws IllegalAccessException {
        if (!constellationRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't find star");
        }
        Constellation constellationToUpdate = constellationRepository.findById(id).get();

        Class cls = constellation.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(constellation);
            if (value != null) {
                fields[i].set(constellationToUpdate, value);
            }
        }
        return constellationRepository.save(constellationToUpdate);
    }

    public void deleteById(Long id) {
        if (!constellationRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't found star to delete");
        }

        constellationRepository.deleteById(id);
    }
}