package be.vdab.justgetit.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "subcategorieen")
public class Subcategorie implements Serializable {
    private static final long serialVersionUID = 1l;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long subcategorieId;
    @Column(name = "naam", unique = true)
    private String naam;
    private BigDecimal minimumMargePercent;
    private BigDecimal minimumMargeBedrag;
    @ManyToOne(optional =false, fetch = FetchType.LAZY)
    @JoinColumn(name = "categorieId")
    private Categorie categorie;

    @Version
    private long versie;

    protected Subcategorie() {
    }

    public Subcategorie(String naam, Categorie categorie) {
        this.naam = naam;
        this.categorie = categorie;
    }

    protected Subcategorie( String naam, BigDecimal minimumMargePercent,
                        BigDecimal minimumMargeBedrag, Categorie categorie) {
        this.naam = naam;
        this.minimumMargePercent = minimumMargePercent;
        this.minimumMargeBedrag = minimumMargeBedrag;
        this.categorie = categorie;
    }

    protected Subcategorie(long subcategorieId, String naam, BigDecimal minimumMargePercent, BigDecimal minimumMargeBedrag, Categorie categorie) {
        this.subcategorieId = subcategorieId;
        this.naam = naam;
        this.minimumMargePercent = minimumMargePercent;
        this.minimumMargeBedrag = minimumMargeBedrag;
        this.categorie = categorie;
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

    public void setMinimumMargePercent(BigDecimal minimumMargePercent) {
        this.minimumMargePercent = minimumMargePercent;
    }

    public void setMinimumMargeBedrag(BigDecimal minimumMargeBedrag) {
        this.minimumMargeBedrag = minimumMargeBedrag;
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
