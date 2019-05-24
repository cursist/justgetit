package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;


public interface BediendeRepository {
    //void zetProdutInCategorie(Product product, Categorie categorie);
    void zetProdutInSubCategorie(Product product, Subcategorie subCategorie);
}
