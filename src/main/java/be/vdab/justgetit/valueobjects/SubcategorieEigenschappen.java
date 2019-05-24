package be.vdab.justgetit.valueobjects;

import be.vdab.justgetit.entities.Subcategorie;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Embeddable
@Table(name = "subcategorieeigenschappen")
public class SubcategorieEigenschappen {
    @Id
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
        if (!(o instanceof SubcategorieEigenschappen)) return false;
        SubcategorieEigenschappen that = (SubcategorieEigenschappen) o;
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

