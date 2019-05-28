package be.vdab.justgetit.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "producten")
//@NamedQuery(name = "Product.findByVoorraadLessThan",query = "select p from Product p where p.voorraad < :aantal order by p.naam")
public class Product implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    private long productId;
    @NotBlank
    private String naam;
    private String omschrijving;
    @Positive
    private BigDecimal inkoopprijs,verkoopprijs,minumumprijs;
    private int voorraad;
    private int besteld;
    @Positive
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "merkId")
    private Merk merk;
    @Positive
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subcategorieId")
    private Subcategorie subcategorie;

    @Version
    private long versie;

    protected Product() {
    }

    public Product(String naam, BigDecimal inkoopPrijs,BigDecimal verkoopPrijs, BigDecimal minumumprijs, int voorraad,
                   int besteld, Merk merk, Subcategorie subcategorie) {
        this.naam = naam;
        this.inkoopprijs = inkoopPrijs;
        this.verkoopprijs = verkoopPrijs;
        this.minumumprijs = minumumprijs;
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

    public BigDecimal getInkoopPrijs() {
        return inkoopprijs;
    }

    public BigDecimal getVerkoopPrijs() {
        return verkoopprijs;
    }

    public BigDecimal getMinumumprijs() {
        return minumumprijs;
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
