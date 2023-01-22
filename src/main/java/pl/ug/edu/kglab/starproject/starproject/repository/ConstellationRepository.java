package pl.ug.edu.kglab.starproject.starproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ug.edu.kglab.starproject.starproject.domain.Constellation;

public interface ConstellationRepository extends CrudRepository<Constellation, Long> {
}
