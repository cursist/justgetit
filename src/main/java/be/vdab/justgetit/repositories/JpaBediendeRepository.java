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
    public void zetProductInSubCategorie(Product product, Subcategorie subcategorie) {
        Product prod = manager.find(Product.class, product.getId());
        prod.setSubcategorie(subcategorie);
/*        Product prod = product;
        prod.setSubcategorie(subcategorie);
        manager.persist(prod);*/
    }

}
