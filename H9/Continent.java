package org.example;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "continent")
@NamedQuery(name = "Continent.findByName", query = "SELECT c FROM Continent c WHERE c.name LIKE :name")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "continent")
    private List<Country> countries;

    public Continent() {
    }

    public Continent(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}