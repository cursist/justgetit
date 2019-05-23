package be.vdab.justgetit.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "merk")
public class Merk implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    private long id;
    private String naam;
    private BigDecimal minimunMarge;

    protected Merk() {
    }

    public Merk(long id, String naam, BigDecimal minimunMarge) {
        this.id = id;
        this.naam = naam;
        this.minimunMarge = minimunMarge;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getMinimunMarge() {
        return minimunMarge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merk)) return false;
        Merk merk = (Merk) o;
        return getNaam().equals(merk.getNaam());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam());
    }

    @Override
    public String toString() {
        return  naam;
    }
}
