package be.vdab.justgetit.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name ="Producten")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal inkoopprijs;
    private BigDecimal verkoopprijs;
    private BigDecimal minimumprijs;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "merkId")
    private Set<merk>merken;
    private String omschrijving;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subCategorieId")
    private Set<subCategorie> subCategorieen;
    private int voorraad;
    private int besteld;
    @Version
    private Timestamp versie;

    protected Product() {
    }

    public Product(String naam, BigDecimal inkoopprijs, BigDecimal verkoopprijs, BigDecimal minimumprijs, Set<merk> merken, String omschrijving, Set<subCategorie> subCategorieen, int voorraad, int besteld) {
        this.naam = naam;
        this.inkoopprijs = inkoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.minimumprijs = minimumprijs;
        this.merken = new LinkedHashSet<>();
        this.omschrijving = omschrijving;
        this.subCategorieen = new LinkedHashSet<>();
        this.voorraad = voorraad;
        this.besteld = besteld;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getInkoopprijs() {
        return inkoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }

    public BigDecimal getMinimumprijs() {
        return minimumprijs;
    }

    public Set<merk> getMerken() {
        return Collections.unmodifiableSet(merken);
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public Set<subCategorie> getSubCategorieen() {
        return Collections.unmodifiableSet(subCategorieen);
    }

    public int getVoorraad() {
        return voorraad;
    }

    public int getBesteld() {
        return besteld;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product)) return false;
        Product product = (Product) object;
        return getNaam().equals(product.getNaam()) &&
                getMerken().equals(product.getMerken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getMerken());
    }

    @Override
    public String toString() {
        return "Product{" +
                "naam='" + naam + '\'' +
                ", merken=" + merken +
                '}';
    }
}

