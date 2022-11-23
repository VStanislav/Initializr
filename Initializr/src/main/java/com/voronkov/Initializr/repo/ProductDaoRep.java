package com.voronkov.Initializr.repo;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.Dao.ProductDaoCl;
import com.voronkov.Initializr.service.SessionFactoryUtils;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoRep implements ProductDao {

    private SessionFactoryUtils sessionFactoryUtils;

    @Autowired
    public ProductDaoRep(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public ProductDaoCl findById(Long id) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            ProductDaoCl product = session.get(ProductDaoCl.class, id);
            session.getTransaction().commit();
            return product;
        }
    }

    @Override
    public void saveOrUpdate(ProductDaoCl product) {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<ProductDaoCl> findAllProducts() {
        try (Session session = sessionFactoryUtils.getSession();) {
            session.beginTransaction();
            List<ProductDaoCl> products = session.createQuery("select p from ProductDaoCl p " +
                    "where p.id<10 ").getResultList();
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
}

//    @Override
//    public void updateByName(Long id, String newName) {
//        try (Session session = sessionFactoryUtils.getSession();) {
//            session.beginTransaction();
//
//            session.createQuery("update ProductDaoCl p set p.name=:name where p.id=:id")
//                    .setParameter("name",newName)
//                    .setParameter("id",id)
//                    .executeUpdate();
//
//        }
//    }

//    @Override
//    public ProductDaoCl findByName(String name) {
//        try (Session session = sessionFactoryUtils.getSession();) {
//            session.beginTransaction();
//            ProductDaoCl product = session.createQuery("select p from ProductDaoCl p where p.name= :name", ProductDaoCl.class)
//                    .setParameter("name", name)
//                    .getSingleResult();
//            session.getTransaction().commit();
//            return product;
//        }
//    }