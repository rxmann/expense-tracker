package com.codex;

import com.codex.model.hqlmodel.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create Configuration instance
        // Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class).addAnnotatedClass(Laptop.class);
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);

        // Create StandardServiceRegistry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        // Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = sessionFactory.openSession();

        session.beginTransaction();
        //************************** QUERY HERE ********************************************************************

        // Query using HQL
        Query<Object[]> query2 = session.createQuery("SELECT s.rollno, s.name, s.marks FROM Student s", Object[].class);

        List<Object[]> stds = query2.list();
        for (Object[] std: stds) {
            Integer rollno = (Integer) std[0];
            String name = (String) std[1];
            Integer marks = (Integer) std[2];
            System.out.printf("Name: %s | Roll No: %d | Marks: %d\n", name, rollno, marks);
        }

        //**********************************************************************************************************
        session.getTransaction().commit();

        session.close();

    }
}