package pl.ug.edu.kglab.starproject.starproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ug.edu.kglab.starproject.starproject.domain.Zodiac;

import java.util.Optional;

public interface ZodiacRepository extends CrudRepository<Zodiac, Long> {
    Optional<Zodiac> findByName(String name);
}
