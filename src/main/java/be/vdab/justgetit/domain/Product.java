package be.vdab.justgetit.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

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
    private Merk merk;
    private String omschrijving;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "subcategorieId")
    private Subcategorie subcategorie;
    private int voorraad;
    private int besteld;
    @Version
    private Timestamp versie;

    protected Product() {
    }

    public Product(String naam, BigDecimal inkoopprijs, BigDecimal verkoopprijs, BigDecimal minimumprijs, Merk merk, String omschrijving, Subcategorie  subcategorie, int voorraad, int besteld) {
        this.naam = naam;
        this.inkoopprijs = inkoopprijs;
        this.verkoopprijs = verkoopprijs;
        this.minimumprijs = minimumprijs;
        this.merk = merk;
        this.omschrijving = omschrijving;
        this.subcategorie = subcategorie;
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


    public String getOmschrijving() {
        return omschrijving;
    }


    public int getVoorraad() {
        return voorraad;
    }

    public int getBesteld() {
        return besteld;
    }

    public Merk getMerk() {
        return merk;
    }

    public Subcategorie getSubcategorie() {
        return subcategorie;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product)) return false;
        Product product = (Product) object;
        return getNaam().equals(product.getNaam()) &&
                getMerk().equals(product.getMerk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), getMerk());
    }

    @Override
    public String toString() {
        return "Product{" +
                "naam='" + naam + '\'' +
                ", merk=" + merk +
                '}';
    }
}

