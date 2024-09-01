package com.codex.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lname;

    @ManyToMany
    private List<Student> students = new ArrayList<Student>();

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", lname='" + lname + '\'' +
                ", students=" + students +
                '}';
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
