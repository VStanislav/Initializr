package com.voronkov.Initializr.Dao;

import java.util.List;

public interface ProductDao {
    ProductDaoCl findById(Long id);
    void saveOrUpdate(ProductDaoCl product);
    void deleteById(Long id);
    List<ProductDaoCl> findAllProducts();
    //    void testCaching();
    //    void updateByName(Long id, String newName);
    //    ProductDaoCl findByName(String name);
}
