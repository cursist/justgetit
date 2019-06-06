package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.repositories.ProductRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBediendeService implements BediendeService {

    private final ProductRepository productService;
    private final SubcategorieRepository subcategorieService;
    private final EntityManager manager;

    public DefaultBediendeService(ProductRepository productService, SubcategorieRepository subcategorieService, EntityManager manager) {
        this.productService = productService;
        this.subcategorieService = subcategorieService;
        this.manager = manager;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void zetProductInSubCategorie(long productid, long subcategorieid) {
        Product product = productService.findById(productid).get();
        product.setSubcategorie(subcategorieService.findById(subcategorieid).get());
        productService.save(product);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public long bepaalMinimaleVerkoopprijs(long productid) {

        return manager.createNamedQuery("Product.bepaalMinimaleVerkoopprijs")
                .setParameter("pid", productid)
                .getFirstResult();
    }


}

