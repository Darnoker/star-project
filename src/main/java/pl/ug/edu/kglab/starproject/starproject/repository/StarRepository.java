package pl.ug.edu.kglab.starproject.starproject.repository;

import org.springframework.data.repository.CrudRepository;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface StarRepository extends CrudRepository<Star, Long> {
    List<Star> findAllByAge(Integer age);
    List<Star> findAllByTypeAndAge(String type, Integer age);
    List<Star> findAllByTypeOrAge(String type, Integer age);

    @Query("SELECT s FROM Star s WHERE s.radius < 75")
    List<Star> manualFindByRadiusLessThan75();
}