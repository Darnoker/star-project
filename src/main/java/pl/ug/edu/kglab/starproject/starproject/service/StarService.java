package pl.ug.edu.kglab.starproject.starproject.service;

import org.springframework.stereotype.Service;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.repository.ConstellationRepository;
import pl.ug.edu.kglab.starproject.starproject.repository.StarRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StarService {

    final StarRepository starRepository;
    final ConstellationRepository constellationRepository;

    public StarService(StarRepository starRepository, ConstellationRepository constellationRepository) {
        this.starRepository = starRepository;
        this.constellationRepository = constellationRepository;
    }

    public Optional<Star> getById(Long id) {
        return starRepository.findById(id);
    }

    public List<Star> getAll() {
        return (List<Star>) starRepository.findAll();
    }

    public Star add(Star starToAdd) {
        return starRepository.save(starToAdd);
    }

    public Star update(Long id, Star star) throws IllegalAccessException {
        if (!starRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't find star");
        }
        Star starToUpdate = starRepository.findById(id).get();

        Class cls = star.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(star);
            if (value != null) {
                fields[i].set(starToUpdate, value);
            }
        }
        return starRepository.save(starToUpdate);
    }

    public void deleteById(Long id) {
        if (!starRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't found star to delete");
        }

        starRepository.deleteById(id);
    }

}