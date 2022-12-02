package com.voronkov.Initializr.service;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.repo.ProductRepNew;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepNew productRepository;

    public ProductService(ProductRepNew productRepository) {
        this.productRepository = productRepository;
    }

    public Page<ProductDao> find(Integer minPrice, Integer maxPrice, String name, String diler, Integer page){
        Specification<ProductDao> spec = Specification.where(null);
        if (minPrice!=null){
            spec=spec.and(ProductSpecifications.greaterThanOrEqualTo(minPrice));
        }
        if (maxPrice!=null){
            spec=spec.and(ProductSpecifications.lessThanOrEqualTo(maxPrice));
        }
        if (name!=null){
            spec=spec.and(ProductSpecifications.likeName(name));
        }
        if (diler!=null){
            spec=spec.and(ProductSpecifications.likeDiler(diler));
        }
        return productRepository.findAll(spec, PageRequest.of(page-1,5));
    }

    public ProductDao findById(Long id){
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public void save(ProductDao productDaoCl){
        productRepository.save(productDaoCl);
    }

}
