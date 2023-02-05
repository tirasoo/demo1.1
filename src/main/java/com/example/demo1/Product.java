package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * It creates a class called Product.
 */
public class Product {
        //class contents go here
        //class definition-->defining the object's variables(instance variables/object fields/object attributes)
        // that belong to them
        private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
        private int id;
        private String name;
        private double price;
        private int stock;
        private int min;
        private int max;

    /**
     * It creates a constructor for the Product class->constructor definition.
     *
     * @param id
     * @param name
     * @param price
     * @param stock
     * @param min
     * @param max
     */

        public Product(int id, String name, double price, int stock, int min, int max) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.min = min;
            this.max = max;
        }

    public Product() {

    }

    /**
         * @return the id
         */
        public int getId()
        {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(int id)
        {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName()
        {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name)
        {
            this.name = name;
        }

        /**
         * @return the price
         */
        public double getPrice()
        {
            return price;
        }

        /**
         * @param price the price to set
         */
        public void setPrice(double price)
        {
            this.price = price;
        }

        /**
         * @return the stock
         */
        public int getStock()
        {
            return stock;
        }

        /**
         * @param stock the stock to set
         */
        public void setStock(int stock)
        {
            this.stock = stock;
        }

        /**
         * @return the min
         */
        public int getMin()
        {
            return min;
        }

        /**
         * @param min the min to set
         */
        public void setMin(int min)
        {
            this.min = min;
        }
        /**
         * @return the max
         */
        public int getMax()
        {
            return max;
        }

        /**
         * @param max the max to set
         */
        public void setMax(int max)
        {
            this.max = max;
        }

    /**
     *The method adds the Part object passed as a parameter to the associatedParts ArrayList.
     * @param part
     */
    public void addAssociatedPart(Part part)
        {
            associatedParts.add(part);
        }


    /**
     * The method takes one parameter, selectedAssociatedPart, which is of type Part and return boolean
     * @param selectedAssociatedPart
     * @return boolean
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart)
        {
            return false;
        }

    /**
     * The getAllAssociatedParts() method is returning an ObservableList of Part objects.
     * @return associatedParts
     */
    public  ObservableList<Part> getAllAssociatedParts()
        {
            return associatedParts;
        }
    }








