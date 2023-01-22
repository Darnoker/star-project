package pl.ug.edu.kglab.starproject.starproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ug.edu.kglab.starproject.starproject.domain.CelestialBody;

public interface CelestialBodyRepository extends CrudRepository<CelestialBody, Long> {
}
