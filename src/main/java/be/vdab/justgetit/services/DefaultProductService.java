package be.vdab.justgetit.services;

import be.vdab.justgetit.entities.Product;
import be.vdab.justgetit.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true, isolation = Isolation.READ_COMMITTED)
public class DefaultProductService implements ProductService {
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
    public List<Product> findByVerkoopprijsBetween(BigDecimal van, BigDecimal tot) {
        return productRepository.findByVerkoopprijsBetween(van, tot);
    }

    @Override
    public List<Product> findByBesteldIsNull() {
        return productRepository.findByBesteldIsNull();
    }

    @Override
    public List<Product> findByVoorraadLessThan(int aantal) {
        return productRepository.findByVoorraadLessThan(aantal);
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
    public List<Product>findByNaamContainingIgnoreCase(String zoekString){
        return productRepository.findByNaamContainingIgnoreCase(zoekString);
    }
}
