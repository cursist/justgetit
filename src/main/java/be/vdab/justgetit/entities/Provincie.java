package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "provincies")
public class Provincie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long provincieId;
    @NotBlank
    @Column(name="naam")
    private String naam;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="landId")
    private Land land;

    protected Provincie(){}

    public Provincie(String naam, Land land) {
        this.naam = naam;
        this.land = land;
    }

    public long getProvincieId() {
        return provincieId;
    }

    public String getNaam() {
        return naam;
    }

    public Land getLand() {
        return land;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Provincie)) return false;
        Provincie provincie = (Provincie) o;
        return getNaam().equalsIgnoreCase(provincie.getNaam()) &&
                getLand().equals(provincie.getLand());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getLand());
    }

    @Override
    public String toString() {
        return naam;
    }
}
