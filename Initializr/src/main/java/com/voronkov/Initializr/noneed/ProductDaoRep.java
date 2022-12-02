package com.voronkov.Initializr.noneed;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.service.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoRep implements com.voronkov.Initializr.noneed.ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public ProductDaoRep(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public ProductDao findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            ProductDao product = session.get(ProductDao.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void saveOrUpdate(ProductDao product) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<ProductDao> findAllProducts() {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            List<ProductDao> products = session.createQuery("select p from ProductDaoCl p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.createQuery("delete from ProductDaoCl p where p.id=:id")
                            .setParameter("id",id)
                                    .executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void save(ProductDao product) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }
}