package com.codex.model;


import javax.persistence.*;

@Entity
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String lname;

    @ManyToOne
    private Alien alien;

    @Override
    public String toString() {
        return "Laptop{" +
                "id=" + id +
                ", lname='" + lname + '\'' +
                ", alien=" + alien +
                '}';
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

    public Alien getAlien() {
        return alien;
    }

    public void setAlien(Alien alien) {
        this.alien = alien;
    }
}
