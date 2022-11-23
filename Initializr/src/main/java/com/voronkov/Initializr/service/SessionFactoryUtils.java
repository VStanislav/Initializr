package com.voronkov.Initializr.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.Column;

@Component
public class SessionFactoryUtils {
    private SessionFactory factory;

    @PostConstruct
    public void init(){
        System.out.println("init factory");
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }
    @PreDestroy
    public void shutdown(){
        System.out.println("dest factory");
        if (factory!=null){
            factory.close();
        }
    }

    public Session getSession(){
        return factory.getCurrentSession();
    }
}
