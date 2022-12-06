package com.voronkov.Initializr.service;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.exceptions.ResourceNotFoundException;
import com.voronkov.Initializr.repo.ProductRepNew;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepNew productRepository;

    public Page<ProductDao> findAll(Integer minPrice, Integer maxPrice, String name, Integer page){
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
        return productRepository.findAll(spec, PageRequest.of(page-1,5));
    }

    public Optional<ProductDao> findById(Long id){
        return productRepository.findById(id);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }

    public ProductDao save(ProductDao productDaoCl){
       return productRepository.save(productDaoCl);
    }

    @Transactional
    public ProductDao update(ProductDto productDtoCl) {
        ProductDao p = productRepository.findById(productDtoCl.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Продукт не найден с таким ID "+productDtoCl.getId()));
        p.setPrice(productDtoCl.getPrice());
        p.setName(productDtoCl.getName());
        return p;
    }
}
