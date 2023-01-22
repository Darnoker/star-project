package pl.ug.edu.kglab.starproject.starproject.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.kglab.starproject.starproject.domain.Constellation;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.service.ConstellationService;
import pl.ug.edu.kglab.starproject.starproject.service.StarService;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/constellation")
public class ConstellationRestController {
    private final ConstellationService constellationService;

    public ConstellationRestController(ConstellationService constellationService) {
        this.constellationService = constellationService;
    }

    @GetMapping("/{id}")
    public Optional<Constellation> getById(@PathVariable Long id) {
        return constellationService.getById(id);
    }

    @GetMapping("/getAll")
    public List<Constellation> getAll() {
        return constellationService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Constellation> add(@RequestBody Constellation constellation) {
        Constellation constellationToBeAdded = constellationService.add(constellation);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(constellationToBeAdded.getId())
                .toUri();

        return ResponseEntity.created(location).body(constellationToBeAdded);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Constellation> put(@PathVariable Long id, @RequestBody Constellation constellation) {
        try {
            Constellation constellationToBeUpdated = constellationService.update(id, constellation);
            return ResponseEntity.ok(constellationToBeUpdated);

        } catch (IllegalAccessException e) {
            System.out.println("problem with fields");
            throw new RuntimeException(e);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            constellationService.deleteById(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
