package pl.ug.edu.kglab.starproject.starproject.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class Star {

    public Star() {
    }

    public Star(String name, String type, Double mass, Double radius, Double temperature, Integer age) {
        this.name = name;
        this.type = type;
        this.mass = mass;
        this.radius = radius;
        this.temperature = temperature;
        this.age = age;
    }

    public Star(Long id, String name, String type, Double mass, Double radius, Double temperature, Integer age) {
        this(name, type, mass, radius, temperature, age);
        this.id = id;
        System.out.println(this);
    }

    private Long id;
    @NotNull
    @Size(min = 1)
    private String name;
    @NotNull
    @Size(min = 1, max = 20)
    private String type;
    @NotNull
    private Double mass;
    @NotNull
    private Double radius;
    @NotNull
    private Double temperature;
    private Integer age;

    private Constellation constellation;
    private List<CelestialBody> celestialBodies;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    @ManyToOne(cascade = CascadeType.PERSIST)
    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    @ManyToMany(cascade = CascadeType.PERSIST)
    public List<CelestialBody> getCelestialBodies() {
        return celestialBodies;
    }

    public void setCelestialBodies(List<CelestialBody> celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    @Override
    public String toString() {
        return "Star{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", mass=" + mass +
                ", radius=" + radius +
                ", temperature=" + temperature +
                ", age=" + age +
                '}';
    }
}
