package pl.ug.edu.kglab.starproject.starproject.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.service.StarService;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/star")
public class StarRestController {

    private final StarService starService;

    public StarRestController(StarService starService) {
        this.starService = starService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Star> getById(@PathVariable Long id) {
        Optional<Star> star = starService.getById(id);
        return star.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public List<Star> getAll() {
        return starService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Star> add(@RequestBody Star star) {
        Star starAdded = starService.add(star);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(starAdded.getId())
                .toUri();

        return ResponseEntity.created(location).body(starAdded);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Star> put(@PathVariable Long id, @RequestBody Star star) {
        try {
            Star starUpdated = starService.update(id, star);
            return ResponseEntity.ok(starUpdated);

        } catch (IllegalAccessException e) {
            System.out.println("problem z fieldami");
            throw new RuntimeException(e);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            starService.deleteById(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
