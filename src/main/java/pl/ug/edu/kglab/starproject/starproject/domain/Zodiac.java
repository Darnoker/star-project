package pl.ug.edu.kglab.starproject.starproject.domain;

import javax.persistence.*;

@Entity
public class Zodiac {
    private Long id;
    private String name;

    public Zodiac(String name) {
        this.name = name;
    }

    public Zodiac() {
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

    @Override
    public String toString() {
        return "Zodiac{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
