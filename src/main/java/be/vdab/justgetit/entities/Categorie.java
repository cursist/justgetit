package be.vdab.justgetit.entities;

import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "categorieen")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categorieId;
    @Column(name = "naam", unique = true)
    private String naam;

    protected Categorie() {
    }

    public Categorie(String naam) {
        this.naam = naam;
    }

    protected Categorie(long categorieId, String naam) {
        this.categorieId = categorieId;
        this.naam = naam;
    }

    public long getId() {
        return categorieId;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categorie)) return false;
        Categorie categorie = (Categorie) o;
        return getNaam().equalsIgnoreCase(categorie.getNaam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam());
    }

    @Override
    public String toString() {
        return naam;
    }
}
