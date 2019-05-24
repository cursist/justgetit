package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;


public interface BediendeRepository {
    //void zetProductInCategorie(Product product, Categorie categorie);
    int zetProductInSubCategorie(Product product, Subcategorie subCategorie);
}
