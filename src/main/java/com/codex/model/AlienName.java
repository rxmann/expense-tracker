package com.codex.model;


import jakarta.persistence.*;

@Embeddable
public class AlienName {

    public String fname;
    public String lname;
    public String mname;

    public String getLname() {
        return lname;
    }

    @Override
    public String toString() {
        return "AlienName{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
