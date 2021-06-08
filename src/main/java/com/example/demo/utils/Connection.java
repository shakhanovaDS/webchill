package com.example.demo.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class Connection {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws Exception {
        if (sessionFactory == null)
            sessionFactory = createSessionFactory();
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() throws Exception {
        try {
            return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

}