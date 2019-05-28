package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "subcategorieeigenschappen")
public class SubcategorieEigenschap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subcategorieEigenschapId;

    @NotBlank
    private String naam;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subcategorieId")
    @NotNull
    private Subcategorie subcategorie;

    protected SubcategorieEigenschap() {}

    public SubcategorieEigenschap(String naam, Subcategorie subcategorie) {
        this.naam = naam;
        this.subcategorie = subcategorie;
    }

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

