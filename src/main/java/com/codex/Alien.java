package com.codex;

import jakarta.persistence.*;

@Entity(name="aliens")
public class Alien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int aid;
    public AlienName aname;
    @Column(name="color")
    public String color;


    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname=" + aname +
                ", color='" + color + '\'' +
                '}';
    }

    public void setAname(AlienName aname) {
        this.aname = aname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
