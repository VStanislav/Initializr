package com.voronkov.Initializr.validators;

import com.voronkov.Initializr.dto.ProductDto;
import com.voronkov.Initializr.exceptions.ValidationException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto){
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice()<1){
            errors.add("Price can't be low that 1");
        }
        if (productDto.getName().isBlank()){
            errors.add("Name can't be empty");
        }
        if (!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
