package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "gemeenten")
public class Gemeente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long gemeenteId;
    @NotBlank
    @Column(name="naam")
    private String naam;

    private String postcode;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="provincieId")
    private Provincie provincie;

    protected Gemeente(){}

    public Gemeente(@NotBlank String naam, String postcode, Provincie provincie) {
        this.naam = naam;
        this.postcode = postcode;
        this.provincie = provincie;
    }

    public long getGemeenteId() {
        return gemeenteId;
    }

    public String getNaam() {
        return naam;
    }

    public String getPostcode() {
        return postcode;
    }

    public Provincie getProvincie() {
        return provincie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gemeente)) return false;
        Gemeente gemeente = (Gemeente) o;
        return getNaam().equalsIgnoreCase(gemeente.getNaam()) &&
                getProvincie().equals(gemeente.getProvincie());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getProvincie());
    }

    @Override
    public String toString() {
        return naam;
    }
}
