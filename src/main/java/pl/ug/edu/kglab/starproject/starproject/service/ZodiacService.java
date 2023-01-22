package pl.ug.edu.kglab.starproject.starproject.service;

import org.springframework.stereotype.Service;
import pl.ug.edu.kglab.starproject.starproject.domain.Zodiac;
import pl.ug.edu.kglab.starproject.starproject.repository.ZodiacRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ZodiacService {
    private ZodiacRepository zodiacRepository;

    public ZodiacService(ZodiacRepository zodiacRepository) {
        this.zodiacRepository = zodiacRepository;
    }

    public Optional<Zodiac> getById(Long id) {
        return zodiacRepository.findById(id);
    }

    public Optional<Zodiac> getByName(String name) {
        return zodiacRepository.findByName(name);
    }

    public List<Zodiac> getAll() {
        return (List<Zodiac>) zodiacRepository.findAll();
    }

    public Zodiac add(Zodiac ZodiacToAdd) {
        return zodiacRepository.save(ZodiacToAdd);
    }

    public Zodiac update(Long id, Zodiac zodiac) throws IllegalAccessException {
        Optional<Zodiac> optionalZodiac = zodiacRepository.findById(id);
        Zodiac zodiacToUpdate;
        if(optionalZodiac.isPresent()) {
            zodiacToUpdate = optionalZodiac.get();
        } else {
            throw new EntityNotFoundException("didn't found Zodiac");
        }

        Class cls = zodiac.getClass();
        Field[] fields = cls.getDeclaredFields();

        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            Object value = fields[i].get(zodiac);
            if (value != null) {
                fields[i].set(zodiacToUpdate, value);
            }
        }
        return zodiacRepository.save(zodiacToUpdate);
    }

    public void deleteById(Long id) {
        if (!zodiacRepository.existsById(id)) {
            throw new EntityNotFoundException("didn't found Zodiac to delete");
        }

        zodiacRepository.deleteById(id);
    }
}
