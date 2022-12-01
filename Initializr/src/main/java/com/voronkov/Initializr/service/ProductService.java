package com.voronkov.Initializr.service;

import com.voronkov.Initializr.Dao.ProductDaoCl;
import com.voronkov.Initializr.repo.ProductDaoRep;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public class ProductService {

    private ProductDaoRep productRepository;

    public ProductService(ProductDaoRep productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDaoCl> getAllProducts(){
        return productRepository.findAllProducts();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void changeScore(Long id, Integer delta) {
        ProductDaoCl product = productRepository.findById(id);
        product.setPrice(product.getPrice()+delta);
        productRepository.saveOrUpdate(product);
    }
}
