package com.voronkov.Initializr.service;


import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import org.springframework.data.jpa.domain.Specification;


public class ProductSpecifications {
    public static Specification<ProductDao> greaterThanOrEqualTo(Integer price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price);
    }
    public static Specification<ProductDao> lessThanOrEqualTo(Integer price){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price);
    }
    public static Specification<ProductDao> likeName(String name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"),String.format("%%%s%%",name));
    }
    public static Specification<ProductDao> likeDiler(String diler){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("diler"),String.format("%%%s%%",diler));
    }
}
