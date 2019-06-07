package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "merken")
public class Merk implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long merkId;
    @Column(name = "naam", unique = true)
    @NotBlank
    private String naam;
    private BigDecimal minimumMargeBedrag;
    private BigDecimal minimumMargePercent;

    protected Merk() {
    }

    public Merk(String naam) {
        this.naam = naam;
    }

    protected Merk(long merkId, String naam, BigDecimal minimumMargePercent,
                BigDecimal minimumMargeBedrag) {
        this.merkId = merkId;
        this.naam = naam;
        this.minimumMargePercent = minimumMargePercent;
        this.minimumMargeBedrag = minimumMargeBedrag;
    }

    public long getId() {
        return merkId;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getMinimumMargePercent() {
        return minimumMargePercent;
    }

    public BigDecimal getMinimumMargeBedrag() {
        return minimumMargeBedrag;
    }

    public void setMinimumMargePercent(BigDecimal minimunMargePercent) {
        this.minimumMargePercent = minimunMargePercent;
    }

    public void setMinimumMargeBedrag(BigDecimal minimunMargeBedrag) {
        this.minimumMargeBedrag = minimunMargeBedrag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Merk)) return false;
        Merk merk = (Merk) o;
        return getNaam().equalsIgnoreCase(merk.getNaam());
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
