package be.vdab.justgetit.entities;

import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "categorieen")
public class Categorie implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    private long categorieId;
    @Column(name = "naam", unique = true)
    private String naam;
    @Version
    private long versie;

    protected Categorie() {
    }

    public Categorie(long categorieId, String naam) {
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
