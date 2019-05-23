package be.vdab.justgetit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "merken")
public class Merk implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    private long merkId;
    @Column(name = "naam", unique = true)
    private String naam;
    private BigDecimal minimunMargePercent;
    private BigDecimal minimunMargeBedrag;

    public Merk() {
    }

    public Merk(long merkId, String naam, BigDecimal minimunMargePercent,
                BigDecimal minimunMargeBedrag) {
        this.merkId = merkId;
        this.naam = naam;
        this.minimunMargePercent = minimunMargePercent;
        this.minimunMargeBedrag = minimunMargeBedrag;
    }

    public long getId() {
        return merkId;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getMinimunMargePercent() {
        return minimunMargePercent;
    }

    public BigDecimal getMinimunMargeBedrag() {
        return minimunMargeBedrag;
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
