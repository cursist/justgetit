package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;


public interface BediendeRepository {
    void zetProductInSubCategorie(Product product, Subcategorie subcategorie);
}
