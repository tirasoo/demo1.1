package com.example.demo1;

//Tiras Ombasa
//Student ID: 001244560

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    private javafx.stage.Stage Stage;
    @Override
    public void start(Stage stage) throws IOException {
        InHouse nut = new InHouse(Inventory.getNextPartId(), "nut", 40, 0, 100, 980, 1223);
        InHouse screw = new InHouse(Inventory.getNextPartId(), "screw", 80, 70, 400, 1100, 3234);
        InHouse clippers = new InHouse(Inventory.getNextPartId(), "clippers", 90, 100, 600, 1300, 3500);
        Outsourced chain = new Outsourced(Inventory.getNextPartId(), "chain", 50, 40, 120, 834, "ACE Hardware");
        Outsourced nail = new Outsourced(Inventory.getNextPartId(), "nail", 60, 50, 180, 734, "Home Depot");
        Outsourced handles = new Outsourced(Inventory.getNextPartId(), "handles", 50, 40, 120, 834, "ACE Hardware");

        Inventory.addPart(nut);
        Inventory.addPart(screw);
        Inventory.addPart(chain);
        Inventory.addPart(nail);
        Inventory.addPart(clippers);
        Inventory.addPart(handles);
        //add Products
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"Square Unix Bundle",249.99,10,50,100));
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"Shield",159.99,90,20,300));
        Inventory.addProduct(new Product(Inventory.getNextProductId(),"New Horizon",35.99,100,100,500));

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("MainForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("INVENTORY MANAGEMENT SYSTEM");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}