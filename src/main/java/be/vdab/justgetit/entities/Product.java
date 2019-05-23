package be.vdab.justgetit.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1l;

    @Id
    private long id;

    private String naam,omschrijving;
    private BigDecimal inkoopPrijs,verkoopPrijs,minumumprijs;
    private int voorraad;

    private Merk merk;

    private Subcategorie subcategorie;

    @Version
    private long versie;

    protected Product() {
    }

    public Product(long id, String naam, String omschrijving,
                   BigDecimal inkoopPrijs, BigDecimal verkoopPrijs,
                   BigDecimal minumumprijs, int voorraad,
                   Subcategorie subcategorie, Merk merk) {
        this.id = id;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.inkoopPrijs = inkoopPrijs;
        this.verkoopPrijs = verkoopPrijs;
        this.minumumprijs = minumumprijs;
        this.voorraad = voorraad;
        this.subcategorie = subcategorie;
        this.merk = merk;
    }

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public BigDecimal getInkoopPrijs() {
        return inkoopPrijs;
    }

    public BigDecimal getVerkoopPrijs() {
        return verkoopPrijs;
    }

    public BigDecimal getMinumumprijs() {
        return minumumprijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getNaam().equals(product.getNaam()) &&
                merk.equals(product.merk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNaam(), merk);
    }

    @Override
    public String toString() {
        return  naam;
    }
}
