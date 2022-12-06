package com.voronkov.Initializr.converter;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDao dtoToDao(ProductDto productDto){
        return new ProductDao(productDto.getId(), productDto.getName(), productDto.getPrice());
    }

    public ProductDto daoToDto(ProductDao productDao){
        return new ProductDto(productDao.getId(), productDao.getName(), productDao.getPrice());
    }
}
