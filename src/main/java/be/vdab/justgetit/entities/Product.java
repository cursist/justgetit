package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity(name = "producten")
public class Product implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private long productId;
    @NotBlank
    private String naam;
    private String omschrijving;
    @Positive
    private BigDecimal inkoopprijs,verkoopprijs, minimumprijs;
    private int voorraad;
    private int besteld;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "merkId")
    private Merk merk;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategorieId")
    private Subcategorie subcategorie;
    @Version
    BigInteger versie;


    protected Product() {
    }

    public Product(String naam, BigDecimal inkoopPrijs,BigDecimal verkoopPrijs, BigDecimal minimumPrijs, int voorraad,
                   int besteld, Merk merk, Subcategorie subcategorie) {
        this.naam = naam;
        this.inkoopprijs = inkoopPrijs;
        this.verkoopprijs = verkoopPrijs;
        this.minimumprijs = minimumPrijs;
        this.voorraad = voorraad;
        this.besteld = besteld;
        this.merk = merk;
        this.subcategorie = subcategorie;
    }

    public long getId() {
        return productId;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
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

   
    public void setSubcategorie(Subcategorie subcategorie) {
        this.subcategorie = subcategorie;
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
