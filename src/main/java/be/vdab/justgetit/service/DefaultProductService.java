package be.vdab.justgetit.service;

import be.vdab.justgetit.domain.Merk;
import be.vdab.justgetit.domain.Product;
import be.vdab.justgetit.domain.Subcategorie;
import be.vdab.justgetit.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.OverridesAttribute;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true, isolation = Isolation.READ_COMMITTED)
public class DefaultProductService implements ProductService{
    private ProductRepository productRepository;

    public DefaultProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public List<Product>findAll(){
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Merk> findByMerkId(long merkId) {
        return productRepository.findByMerkId(merkId);
    }

    @Override
    public List<Subcategorie> findBySubcategorieId(long subcategorieId) {
        return productRepository.findBySubcategorieId(subcategorieId);
    }

    @Override
    public List<Product> findByVerkoopprijsBetween(BigDecimal van, BigDecimal tot) {
        return productRepository.findByVerkoopprijsBetween(van, tot);
    }

    @Override
    public List<Product> findByBesteldIsNull() {
        return productRepository.findByBesteldIsNull();
    }

    @Override
    public List<Product> findByVoorraadKleinerDan(BigDecimal aantal) {
        return productRepository.findByVoorraadKleinerDan(aantal);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }
    @Override
    public List<Product>findByNaamContaining(String zoekString){
        return productRepository.findByNaamContaining(zoekString);
    }
}
