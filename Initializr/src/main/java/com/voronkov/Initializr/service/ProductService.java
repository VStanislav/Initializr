package com.voronkov.Initializr.service;

import com.voronkov.Initializr.Dao.ProductDaoCl;
import com.voronkov.Initializr.repo.ProductDaoRep;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class ProductService {

    private ProductDaoRep productRepository;
    Integer finalMin;
    Integer finalMax;
    List<ProductDaoCl> productDaoClList;

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

    public void save(ProductDaoCl productDaoCl){
        productRepository.save(productDaoCl);
    }

    public List<ProductDaoCl> findProductsBetweenPrice(Integer min, Integer max) {
        System.out.println(min+"__"+max);
        return productRepository.findAllProducts().stream().
                filter(n -> n.getPrice() > min && n.getPrice() < max).collect(Collectors.toList());
    }
}
