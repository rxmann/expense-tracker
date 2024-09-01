package com.codex;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {
    public static void main(String[] args) {

//        AlienName an = new AlienName();
//        an.setFname("dxman");
//        an.setLname("bark");

        Alien alien = new Alien();
//        alien.setColor("blue");
//        alien.setAname(an);

        // Create Configuration instance
        Configuration configuration = new Configuration().configure().addAnnotatedClass(Alien.class);

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
            // Commit transaction
            transaction.commit();

            // save
            if (alien != null) {
                session.save(alien);
            }
            // get
//            alien = session.get(Alien.class, 1);


        } catch (Exception e) {
            if (transaction != null) {
                // Rollback transaction on error
                transaction.rollback();
            }
            // Handle the exception (log it, rethrow it, etc.)
            e.printStackTrace();
        } finally {
            if (session != null) {
                // Ensure session is closed
                session.close();
            }
            if (sessionFactory != null) {
                // Ensure sessionFactory is closed
                sessionFactory.close();
            }
        }

    }
}