package com.codex;

import com.codex.model.hqlmodel.Student;
import org.hibernate.SQLQuery;
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

        // Get One Statement
        Query<Object[]> query3 = session.createQuery("SELECT s.rollno, s.name, s.marks FROM Student s WHERE s.rollno = 9", Object[].class);
        Object[] stdRes = query3.uniqueResult();
        System.out.println(stdRes[0] + " : " + stdRes[1] + " : " + stdRes[2]);


        // Get All statement
        Query<Object[]> query2 = session.createQuery("SELECT s.rollno, s.name, s.marks FROM Student s where marks >= 90", Object[].class);

        List<Object[]> stds = query2.list();
        for (Object[] std: stds) {
            Integer rollno = (Integer) std[0];
            String name = (String) std[1];
            Integer marks = (Integer) std[2];
            System.out.printf("Name: %s | Roll No: %d | Marks: %d\n", name, rollno, marks);
        }

        // Patch statement
        String hql_patch = "UPDATE Student set marks = :newMarks where rollno = :rollNumber";
        Query<?> query=  session.createQuery(hql_patch);
        query.setParameter("newMarks", 99);
        query.setParameter("rollNumber", 9);

        Integer res = query.executeUpdate();
        System.out.println(res);


        // CustomSQL
        Query<Student> squery = session.createNativeQuery("select * from student where marks = 99", Student.class);
        Student stt = squery.uniqueResult();
        System.out.println(stt);




        //**********************************************************************************************************
        session.getTransaction().commit();

        session.close();

    }
}