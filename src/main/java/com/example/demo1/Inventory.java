package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
        //class contents go here
        //class definition-->defining the methods and object's variables(instance variables/object fields/
        // object attributes)that  belong to them.

    private static ObservableList<Part> allParts = FXCollections.observableArrayList();//allParts is the observable list where new parts objects will be put
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) //addPart function/method used to accept a part object and add it to the list
    {
        allParts.add(newPart);//called in addPart controller with inhouse or outsourced part

    }

    public static void addProduct(Product newProduct)
    {
        allProducts.add(newProduct);

    }

    public static Part lookupPart(int partId)
    {
        return null;
    }

    public static Product lookupProduct(int productId)
    {
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName)
    {
        return null;
    }

    public static ObservableList<Product> lookupProduct(String productName)
    {
        return null;
    }

    public static void updatePart(int index, Part selectedPart)
    {

    }

    public static void updateProduct(int index, Product newProduct)
    {

    }

    public static boolean deletePart(Part selectedPart)
    {
        return false;
    }

    public static boolean deleteProduct(Product selectedProduct)
    {
        return false;
    }

    public static ObservableList<Part> getAllParts()
    {
        return null;
    }

    public static ObservableList<Product> getAllProducts()
    {
        return null;
    }
}

