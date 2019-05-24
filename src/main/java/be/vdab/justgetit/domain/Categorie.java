package be.vdab.justgetit.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categorieen" )
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    private String naam;

    protected Categorie() {}

    public Categorie(String naam) {
        this.naam = naam;
    }

    public long getId() {
        return id;
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
        return id + naam;
    }
}
