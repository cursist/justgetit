package be.vdab.justgetit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Land implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private long id;
    @NotBlank
    @Column(name = "naam", unique = true)
    private String naam;

    protected Land() {}

    public Land(long id, String naam) {
        this.id = id;
        this.naam = naam;
    }

    public Land(String naam) {
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
        if (!(o instanceof Land)) return false;
        Land land = (Land) o;
        return getNaam().equalsIgnoreCase(land.getNaam());
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
