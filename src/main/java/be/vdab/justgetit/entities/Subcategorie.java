package be.vdab.justgetit.entities;

import be.vdab.justgetit.valueobjects.SubcategorieEigenschappen;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "subcategorieen")
public class Subcategorie implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    private long subcategorieId;
    @Column(name = "naam", unique = true)
    private String naam;
    private BigDecimal minimumMargePercent;
    private BigDecimal minimumMargeBedrag;
    @ManyToOne(optional =false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categorieId")
    private Categorie categorie;
    @ElementCollection
    @CollectionTable(name = "subcategorieeigenschappen",
            joinColumns = @JoinColumn(name = "subcategorieId"))
    private Set<SubcategorieEigenschappen> eigenschappen;

    @Version
    private long versie;

    protected Subcategorie() {
    }


    public Subcategorie( String naam, BigDecimal minimumMargePercent,
                        BigDecimal minimumMargeBedrag, Categorie categorie) {
        this.naam = naam;
        this.minimumMargePercent = minimumMargePercent;
        this.minimumMargeBedrag = minimumMargeBedrag;
        this.categorie = categorie;
        this.eigenschappen = new LinkedHashSet<>();
    }



    public long getId() {
        return subcategorieId;
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

    public Categorie getCategorie() {
        return categorie;
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
