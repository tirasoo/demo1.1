package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;

    @FXML
    public void onActionOpenAddParForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddParForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionOpenModifyPartForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyPartForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteSelectedPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionOpenModifyProductForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("ModifyProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionDeleteSelectedProduct() throws IOException {

    }

    public void onActionOpenAddProductForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionExitMainForm() {
        System.exit(0);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Load test data as per the webinar

        InHouse nut = new InHouse(1, "nut", 40, 0, 100, 980, 1223);
        InHouse screw = new InHouse(2, "screw", 80, 70, 400, 1100, 3234);
        Outsourced chain = new Outsourced(3, "chain", 50, 40, 120, 834, "ACE Hardware");
        Inventory.addPart(nut);
        Inventory.addPart(screw);
        Inventory.addPart(chain);

        /*partsMainTableView.setItems(Inventory.getAllParts());
    }*/
    }
}