package com.codex;

import com.codex.model.Alien1;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        // Create Configuration instance
        // Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class).addAnnotatedClass(Laptop.class);
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien1.class);

        // Create StandardServiceRegistry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        // Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();

        session.beginTransaction();
        session2.beginTransaction();
        //************************** QUERY HERE ********************************************************************
        Alien1 a = (Alien1) session.get(Alien1.class, 2);
        System.out.println(a.toString());
        Alien1 a1 = (Alien1) session2.get(Alien1.class, 2);
        System.out.println(a1.toString());


        //**********************************************************************************************************
        session.getTransaction().commit();
        session2.getTransaction().commit();

        session.close();
        session2.close();
        sessionFactory.close();

    }
}