package com.voronkov.Initializr.model;

import com.voronkov.Initializr.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public List<Product> getProductList() {
        return productList;
    }

    public ProductRepository() {
        for (int i = 0; i < 5; i++) {
            productList.add(new Product("Product "+i,(int)(Math.random()*100),i));
        }
    }

    public Product getProductById(int id){
        if (id<0 || id>=productList.size()){
            System.out.println("Wrong ID");
            return null;
        } else return productList.get(id);
    }

    public void setNewProduct(Product product){
        productList.add(product);
    }


}
