package pl.ug.edu.kglab.starproject.starproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.service.StarService;

@SpringBootApplication
public class StarProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner setUpApp(StarService starService) {
		return(args -> {
            starService.add(new Star(
                    "Rigel",
                    "Blue Supergiant",
                    21d,
                    78.9,
                    12.100,
                    8
            ));
            starService.add(new Star(
                    "Arcturus",
                    "Red Giant",
                    1.08,
                    25.4,
                    4286d,
                    7
            ));
            starService.add(new Star(
                    "Red  Giant Star",
                    "Red Giant",
                    1.08,
                    25.4,
                    4286d,
                    8
            ));

            starService.add(new Star(
                    "VY Canis Majoris",
                    "Red Hypergiant",
                    17d,
                    1420d,
                    3490d,
                    8
            ));

		});
	}

}
