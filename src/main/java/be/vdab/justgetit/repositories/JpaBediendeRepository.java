package be.vdab.justgetit.repositories;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaBediendeRepository implements BediendeRepository{

    private EntityManager manager;

    public JpaBediendeRepository(EntityManager manager){
        this.manager = manager;
    }

    @Override
    public int zetProductInSubCategorie(Product product, Subcategorie subcategorie) {
        return manager.createNamedQuery("Product.zetProductInSubcategorie")
                .setParameter("subcategorie", subcategorie.getId())
                .executeUpdate();
    }

}
