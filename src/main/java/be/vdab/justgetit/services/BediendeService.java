package be.vdab.justgetit.services;

        import be.vdab.justgetit.entities.Product;
        import be.vdab.justgetit.entities.Subcategorie;

public interface BediendeService {
    void zetProductInSubCategorie(Product product, Subcategorie subcategorie);
}
