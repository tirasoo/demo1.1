package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * this method creates a new class called MainApplication that extends the Application class.
 */
public class MainApplication extends Application {
    private javafx.stage.Stage Stage;

    /**
     * this method creates a new stage and sets the title to "INVENTORY MANAGEMENT SYSTEM".
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        InHouse nut = new InHouse(Inventory.getNextPartId(), "nut", 40, 150, 100, 980, 1223);
        Inventory.addPart(nut); //create a new object and add it to the list
        InHouse screw = new InHouse(Inventory.getNextPartId(), "screw", 80, 470, 400, 1100, 3234);
        Inventory.addPart(screw);
        InHouse clippers = new InHouse(Inventory.getNextPartId(), "clippers", 90, 700, 600, 1300, 3500);
        Inventory.addPart(clippers);
        Outsourced chain = new Outsourced(Inventory.getNextPartId(), "chain", 50, 400, 120, 834, "ACE Hardware");
        Inventory.addPart(chain);
        Outsourced nail = new Outsourced(Inventory.getNextPartId(), "nail", 60, 500, 180, 734, "Home Depot");
        Inventory.addPart(nail);
        Outsourced handles = new Outsourced(Inventory.getNextPartId(), "handles", 50, 450, 120, 834, "ACE Hardware");
        Inventory.addPart(handles);

        //add Products
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"Square Unix Bundle",249.99,10,50,100));
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"Shield",159.99,90,20,300));
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"New Horizon",35.99,100,100,500));

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("INVENTORY MANAGEMENT SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * this is the main method of a Java program and launches the application
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}