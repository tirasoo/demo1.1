package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
        //class contents go here
        //class definition-->defining the methods and object's variables(instance variables/object fields/
        // object attributes)that  belong to them.

    public static ObservableList<Part> allParts = FXCollections.observableArrayList();//allParts is the observable list where new parts objects will be put
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();//allProducts is the list here thstwill represent the data which will be displayed.
    private static int nextPartId = 1;
    private static int nextProductId = 1;
    private static String text;

    public static int getNextPartId() {
        return nextPartId++;
    }

    public static int getNextProductId() {
        return nextProductId++;
    }

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
        Part temp = null;
        for (Part part : allParts){
            if (partId == part.getId()){
                temp = part;
            }
        }
        return temp;
    }
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
    public static ObservableList<Part> lookupPart(String partName)
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
    public static void updatePart(int index, Part selectedPart)
    {
        allParts.set(index, selectedPart);
    }
    public static void updateProduct(int index, Product newProduct)
    {
        allProducts.set(index, newProduct);
    }
    public static boolean deletePart(Part selectedPart)
    {
        return allParts.remove(selectedPart);
    }
    public static boolean deleteProduct(Product selectedProduct)
    {
        return allProducts.remove(selectedProduct);
    }

    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }

    public static void setText(String text) {
        Inventory.text = text;
    }

    public static String getText() {
        return text;
    }
}

