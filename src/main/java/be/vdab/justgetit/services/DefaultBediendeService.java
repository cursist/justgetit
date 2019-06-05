package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.entities.Subcategorie;
import be.vdab.justgetit.repositories.ProductRepository;
import be.vdab.justgetit.repositories.SubcategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class DefaultBediendeService implements BediendeService {

    private final ProductRepository productService;
    private final SubcategorieRepository subcategorieService;

    public DefaultBediendeService(ProductRepository productService, SubcategorieRepository subcategorieService) {
        this.productService = productService;
        this.subcategorieService = subcategorieService;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void zetProductInSubCategorie(long productid, long subcategorieid) {
        Product product = productService.findById(productid).get();
        Subcategorie subcategorie = subcategorieService.findById(subcategorieid).get();
        product.setSubcategorie(subcategorie);
        productService.save(product);
    }

}

