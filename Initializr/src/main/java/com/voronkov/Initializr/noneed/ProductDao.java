package com.voronkov.Initializr.noneed;

import java.util.List;

public interface ProductDao{
    com.voronkov.Initializr.Dao.ProductDao findById(Long id);
    void saveOrUpdate(com.voronkov.Initializr.Dao.ProductDao product);
    void deleteById(Long id);
    List<com.voronkov.Initializr.Dao.ProductDao> findAllProducts();
    void save(com.voronkov.Initializr.Dao.ProductDao product);

}
