package pl.ug.edu.kglab.starproject.starproject.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.service.StarService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@Controller
public class StarWebController {

    private StarService starService;

    public StarWebController(StarService starService) {
        this.starService = starService;
    }

    @GetMapping("/star")
    public String persons(Model model) {
        model.addAttribute("allStarsFromDB", starService.getAll());

        return "star-all";
    }

    @GetMapping("/star/form/")
    public String starAdd(Model model) {
        model.addAttribute("starToAdd", new Star(
                "name",
                "type",
                0d,
                0d,
                0d,
                0
        ));
        return "star-form";
    }

    @GetMapping("/star/form/{id}")
    public String starEdit(@PathVariable Long id, Model model) {
        Optional<Star> star = starService.getById(id);
        if (star.isPresent()) {
            model.addAttribute("starToAdd", star.get());
            model.addAttribute("action", "update");
            return "star-form";

        } else {
            return "error";
        }
    }

    @PostMapping("/star")
    public String addNewStar(@Valid @ModelAttribute Star starToAdd, Model model) {

        starService.add(starToAdd);
        model.addAttribute("allStarsFromDB", starService.getAll());

        return "star-all";
    }

    @PostMapping("/star/{id}")
    public String editStar(@PathVariable Long id ,@Valid @ModelAttribute Star starToEdit, Model model) throws IllegalAccessException {
        starService.update(id, starToEdit);
        model.addAttribute("allStarsFromDB", starService.getAll());

        return "star-all";
    }

    @GetMapping("/star/{id}")
    public String deleteStar(@PathVariable Long id, Model model) {
        starService.deleteById(id);
        return "redirect:/star";
    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
    }
}
