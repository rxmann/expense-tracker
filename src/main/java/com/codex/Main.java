package com.codex;

import com.codex.model.Laptop;
import com.codex.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {


        Laptop laptop = new Laptop();
        laptop.setLname("Dell");
        Laptop lapto = new Laptop();
        lapto.setLname("Acer");

        Student s = new Student();
        s.setName("hugo");
        s.setMarks(77);
        s.getLaptops().add(laptop);
        s.getLaptops().add(lapto);

        lapto.getStudents().add(s);

        // Create Configuration instance
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);

        // Create StandardServiceRegistry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        // Create SessionFactory
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        Session session = null;
        Transaction transaction = null;

        try {
            // Open a new session
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            // persist to db
            session.save(laptop);
            session.save(lapto);
            session.save(s);
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }

    }
}