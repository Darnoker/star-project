package pl.ug.edu.kglab.starproject.starproject.service;

import pl.ug.edu.kglab.starproject.starproject.domain.CelestialBody;
import pl.ug.edu.kglab.starproject.starproject.domain.Zodiac;
import pl.ug.edu.kglab.starproject.starproject.repository.CelestialBodyRepository;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

public class CelestialBodyService {

    private CelestialBodyRepository celestialBodyRepository;

    public CelestialBodyService(CelestialBodyRepository celestialBodyRepository) {
        this.celestialBodyRepository = celestialBodyRepository;
    }

    public Optional<CelestialBody> getById(Long id) {
        if (!celestialBodyRepository.existsById(id)) {
            throw new EntityNotFoundException("get by id error");
        }

        return celestialBodyRepository.findById(id);
    }

    public List<CelestialBody> getAll() {
        return (List<CelestialBody>) celestialBodyRepository.findAll();
    }

    public CelestialBody add(CelestialBody CelestialBody) {
        return celestialBodyRepository.save(CelestialBody);
    }

    public CelestialBody update(Long id, CelestialBody celestialBody) throws IllegalAccessException {
        Optional<CelestialBody> optionalCelestialBody = celestialBodyRepository.findById(id);
        CelestialBody celestialBodyToUpdate;
        if(optionalCelestialBody.isPresent()) {
            celestialBodyToUpdate = optionalCelestialBody.get();
        } else {
            throw new EntityNotFoundException("didn't found Zodiac");
        }

        Class cls = celestialBody.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(celestialBody);
            if (value != null) {
                fields[i].set(celestialBodyToUpdate, value);
            }
        }
        return celestialBodyRepository.save(celestialBodyToUpdate);
    }

    public void deleteById(Long id) {
        if (!celestialBodyRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't found star to delete");
        }

        celestialBodyRepository.deleteById(id);
    }
}
