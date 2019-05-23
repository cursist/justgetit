package be.vdab.justgetit.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "subcategorieen")
public class Subcategorie implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    private long id;

    private BigDecimal minimummarge;
    private String naam;

    @Version
    private long versie;

    protected Subcategorie() {
    }

    public Subcategorie(long id, BigDecimal minimummarge, String naam) {
        this.id = id;
        this.minimummarge = minimummarge;
        this.naam = naam;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getMinimummarge() {
        return minimummarge;
    }

    public String getNaam() {
        return naam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subcategorie)) return false;
        Subcategorie that = (Subcategorie) o;
        return getNaam().equalsIgnoreCase(that.getNaam());
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
