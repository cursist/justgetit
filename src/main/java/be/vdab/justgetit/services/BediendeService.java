package be.vdab.justgetit.services;

        import be.vdab.justgetit.entities.Product;
        import be.vdab.justgetit.entities.Subcategorie;

public interface BediendeService {
    //void zetProductInCategorie(Product product, Categorie categorie);
    int zetProductInSubCategorie(Product product, Subcategorie subCategorie);
}
