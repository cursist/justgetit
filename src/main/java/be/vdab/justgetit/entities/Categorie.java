package be.vdab.justgetit.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categorieen" )
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long categorieId;
    @Column(name = "naam", unique = true)
    private String naam;

    protected Categorie() {}

    public Categorie(String naam) {
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
        if (o == null || getClass() != o.getClass()) return false;
        Categorie categorie = (Categorie) o;
        return naam.equals(categorie.naam);
    }

    @Override
    public int hashCode() {
        return naam.hashCode();
    }

    @Override
    public String toString() {
        return categorieId + naam;
    }
}
