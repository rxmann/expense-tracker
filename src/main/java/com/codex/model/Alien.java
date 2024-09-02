package com.codex.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity(name="aliens")
public class Alien {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int aid;
    public String aname;

    @OneToMany(mappedBy = "alien", fetch = FetchType.EAGER)
    private Collection<Laptop> laptops = new ArrayList<Laptop>();

    @Override
    public String toString() {
        return "Alien{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", laptops=" + laptops +
                '}';
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Collection<Laptop> getLaptops() {
        return laptops;
    }

    public void setLaptops(Collection<Laptop> laptops) {
        this.laptops = laptops;
    }
}
