package pl.ug.edu.kglab.starproject.starproject.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Constellation {

    private Long id;
    private String name;
    private List<Star> stars = new ArrayList<>();

    public Constellation(String name, List<Star> stars) {
        this.name = name;
        this.stars = stars;
        System.out.println(this);
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

    @Override
    public String toString() {
        return "Constellation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stars=" + stars +
                '}';
    }
}