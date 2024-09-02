package com.codex.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;



@Entity
@Table(name = "alien")
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Alien1 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;
    private String aname;
    private String color;

    @Override
    public String toString() {
        return "Alien1{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
