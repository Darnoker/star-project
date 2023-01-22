package pl.ug.edu.kglab.starproject.starproject.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Constellation {

    private Long id;
    private String name;
    private Zodiac zodiac;
    private List<Star> stars = new ArrayList<>();

    public Constellation(String name, List<Star> stars) {
        this.name = name;
        this.stars = stars;
    }

    public Constellation(String name, Zodiac zodiac) {
        this.name = name;
        this.zodiac = zodiac;
    }

    public Constellation(String name, Zodiac zodiac, List<Star> stars) {
        this.name = name;
        this.zodiac = zodiac;
        this.stars = stars;
    }

    public Constellation(String name) {
        this.name = name;
    }

    public Constellation() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "constellation")
    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    @OneToOne
    public Zodiac getZodiac() {
        return zodiac;
    }

    public void setZodiac(Zodiac zodiac) {
        this.zodiac = zodiac;
    }

    @Override
    public String toString() {
        return "Constellation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                '}';
    }
}