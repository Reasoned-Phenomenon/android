package com.example.mywidget;

import java.util.Date;

public class Emp {
    String fname;
    String lname;
    Date hiredate;

    @Override
    public String toString() {
        return "Emp{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", hiredate=" + hiredate +
                '}';
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }


}
