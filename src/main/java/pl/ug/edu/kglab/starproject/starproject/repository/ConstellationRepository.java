package pl.ug.edu.kglab.starproject.starproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ug.edu.kglab.starproject.starproject.domain.Constellation;

import java.util.Optional;

public interface ConstellationRepository extends CrudRepository<Constellation, Long> {
    Optional<Constellation> findByName(String name);
}
