package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * It creates a class called Inventory.
 */
public class Inventory {
        //class contents go here
        //class definition-->defining the methods and object's variables(instance variables/object fields/
        // object attributes)that  belong to them.

    /**
     *It creates an observable list of parts called allParts
     */
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();//allParts is the observable list where new parts objects will be put

    /**
     * It creates an observable list of parts called allProducts
     */
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();//allProducts is the list here thstwill represent the data which will be displayed.

    private static int nextPartId = 1;

    private static int nextProductId = 1;

    private static String text;

    /**
     * method gives the next part id
     * @return next part id
     */
    public static int getNextPartId() {
        return nextPartId++;
    }

    /**
     * this method will give the next product id
     * @return next product id
     */
    public static int getNextProductId() {
        return nextProductId++;
    }
    /**
     *
     * @param newPart accept a part object and add it to the allParts list
     */
    public static void addPart(Part newPart) //addPart function/method used to accept a part object and add it to the list
    {
        allParts.add(newPart);//called in addPart controller with inhouse or outsourced part
    }

    /**
     *
     * @param newProduct accept a product object and add it to the allProducts list
     */
    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);
    }

    /**
     *It looks up a part by its partId
     * @param partId
     * @return the part
     */
    public static Part lookupPart(int partId)//returns a part
    {
        Part temp = null;
        for (Part part : allParts){
            if (partId == part.getId()){
                temp = part;
            }
        }
        return temp;
    }

    /**
     * It looks up a product by its productId
     * @param productId
     * @return product
     */
    public static Product lookupProduct(int productId)
    {
        Product temp = null;
        for (Product product : allProducts){
            if (productId == product.getId()){
                temp = product;
            }
        }
        return temp;
    }

    /**
     * It returns a list of parts that match the search criteria
     * @param partName
     * @return list of found parts/observable list
     */
    public static ObservableList<Part> lookupPart(String partName)//returns observable list
    {
        ObservableList<Part> foundParts = FXCollections.observableArrayList();
        if(partName.length() == 0) {
            foundParts = allParts;
        }
        else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                    foundParts.add(allParts.get(i));
                }
            }
        }
        return foundParts;
    }

    /**
     * It returns a list of products that match the search criteria
     * @param productName
     * @return list of found products/observable list
     */
    public static ObservableList<Product> lookupProduct(String productName)
    {
        ObservableList<Product> foundProducts = FXCollections.observableArrayList();
        if(productName.length() == 0) {
            foundProducts = allProducts;
        }
        else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                    foundProducts.add(allProducts.get(i));
                }
            }
        }
        return foundProducts;
    }

    /**
     * method updates the part at the given index with the given part.
     * @param index
     * @param SP
     */
    public static void updatePart(int index, Part SP)
    {
        allParts.set(index, SP);
    }

    /**
     *method updates the product at the given index with the new product.
     * @param index
     * @param newProduct
     */
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }

    /**
     *the deletePart method takes the part object as parameter and uses the remove method
     *to remove the Part object(SP) from the List
     * @param SP
     * @return
     */
    public static boolean deletePart(Part SP)
    {
        return allParts.remove(SP);
    }

    /**
     *the deleteProduct method takes the product object as parameter and uses the remove method
     * to remove the Product object(SP)from the List
     * @parae  SP
     * @return boolean value
     */
    public static boolean deleteProduct(Product SP)

    {
        return allProducts.remove(SP);
    }

    /**
     *It returns an ObservableList of all the parts.
     * @return allParts list
     */
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }

    /**
     *It returns an ObservableList of all the products.
     * @return allProducts list
     */
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

    /**
     *It’s creating a public static method called setText that's setting the text variable
     * to the value of the parameter.
     * @param text
     */
    public static void setText(String text) {
        Inventory.text = text;
    }

    /**
     * It’s creating a static method called getText() that returns a String.
     * @return text
     */
    public static String getText() {
        return text;
    }
}

