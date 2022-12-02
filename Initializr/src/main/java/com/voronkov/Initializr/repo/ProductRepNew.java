package com.voronkov.Initializr.repo;


import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepNew extends JpaRepository<ProductDao,Long>, JpaSpecificationExecutor<ProductDao> {

}
