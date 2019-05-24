package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
@Table(name = "subcategorieeigenschappen")
public class SubcategorieEigenschap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subcategorieEigenschapId;
    private String naam;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategorieId")
    @NotBlank
    private Subcategorie subcategorie;

    public String getNaam() {
        return naam;
    }

    public Subcategorie getSubcategorie() {
        return subcategorie;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public void setSubcategorie(Subcategorie subcategorie) {
        this.subcategorie = subcategorie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SubcategorieEigenschap)) return false;
        SubcategorieEigenschap that = (SubcategorieEigenschap) o;
        return subcategorieEigenschapId == that.subcategorieEigenschapId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subcategorieEigenschapId);
    }

    @Override
    public String toString() {
        return naam;
    }
}

