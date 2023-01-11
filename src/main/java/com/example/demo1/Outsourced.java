package com.example.demo1;

import java.util.IdentityHashMap;

public class Outsourced extends Part {


    //class contents go here
    //Class definition-->defining the object's variables(instance variables/object field/object attributes)
    // that belong to them.
    private String companyName;

    //Constructor definition:
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @param companyName the name to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }
}



