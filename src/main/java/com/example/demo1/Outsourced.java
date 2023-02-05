package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */

/**
 * creates an outsourced class which is a subclass of the part class
 */
public class Outsourced extends Part {


    //class contents go here
    //Class definition-->defining the object's variables(instance variables/object field/object attributes)
    // that belong to them.
    private String companyName;

    /**
     * It creates a constructor for the outsourced class-constructor definition.
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     * @param companyName
     */
//Constructor definition:
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * @param companyName the name to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    /**
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
}



