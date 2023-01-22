package pl.ug.edu.kglab.starproject.starproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.ug.edu.kglab.starproject.starproject.domain.Constellation;
import pl.ug.edu.kglab.starproject.starproject.domain.Star;
import pl.ug.edu.kglab.starproject.starproject.domain.Zodiac;
import pl.ug.edu.kglab.starproject.starproject.service.ConstellationService;
import pl.ug.edu.kglab.starproject.starproject.service.StarService;
import pl.ug.edu.kglab.starproject.starproject.service.ZodiacService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class StarProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarProjectApplication.class, args);
	}

	@Bean
	CommandLineRunner setUpApp(StarService starService, ZodiacService zodiacService, ConstellationService constellationService) {
		return(args -> {
            Star rigel = new Star(
                    "Rigel",
                    "Blue Supergiant",
                    21d,
                    78.9,
                    12.100,
                    8
            );
            Star arcturus = new Star(
                    "Arcturus",
                    "Red Giant",
                    1.08,
                    25.4,
                    4286d,
                    7
            );
            Star aldebaran = new Star(
                    "Aldebaran",
                    "Red Giant",
                    1.16,
                    45.1,
                    3.900,
                    6
            );

            Star canisMajoris = new Star(
                    "VY Canis Majoris",
                    "Red Hypergiant",
                    17d,
                    1420d,
                    3490d,
                    8
            );

            Zodiac z1 = new Zodiac("Capricornus");
            Zodiac z2 = new Zodiac("Aquarius");
            Zodiac z3 = new Zodiac("Pisces");
            Zodiac z4 = new Zodiac("Aries");
            Zodiac z5 = new Zodiac("Taurus");
            Zodiac z6 = new Zodiac("Gemini");
            Zodiac z7 = new Zodiac("Cancer");
            Zodiac z8 = new Zodiac("Leo");
            Zodiac z9 = new Zodiac("Virgo");
            Zodiac z10 = new Zodiac("Libra");
            Zodiac z11 = new Zodiac("Scorpius");
            Zodiac z12 = new Zodiac("Sagittarius");

            Constellation c1  = new Constellation("Canis Major");
			Constellation c2  = new Constellation("Boötes");
			Constellation c3  = new Constellation("Orion");
			Constellation c4  = new Constellation("Taurus");


            c1.setStars(new ArrayList<>(List.of(canisMajoris)));
            c2.setStars(new ArrayList<>(List.of(arcturus)));
            c3.setStars(new ArrayList<>(List.of(rigel)));
            c4.setStars(new ArrayList<>(List.of(aldebaran)));

            aldebaran.setConstellation(c4);
            rigel.setConstellation(c3);
            arcturus.setConstellation(c2);
            canisMajoris.setConstellation(c1);

            starService.add(rigel);
            starService.add(arcturus);
            starService.add(aldebaran);
            starService.add(canisMajoris);

            zodiacService.add(z1);
            zodiacService.add(z2);
            zodiacService.add(z3);
            zodiacService.add(z4);
            zodiacService.add(z5);
            zodiacService.add(z6);
            zodiacService.add(z7);
            zodiacService.add(z8);
            zodiacService.add(z9);
            zodiacService.add(z10);
            zodiacService.add(z11);
            zodiacService.add(z12);

            Optional<Zodiac> zodiac = zodiacService.getByName("Taurus");
            zodiac.ifPresent(c4::setZodiac);

            constellationService.add(c1);
            constellationService.add(c2);
            constellationService.add(c3);
            constellationService.add(c4);
		});
	}
}
