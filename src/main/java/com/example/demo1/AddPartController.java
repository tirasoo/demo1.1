package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class AddPartController implements Initializable {
    Stage stage;
    Parent scene;
    public RadioButton inHouseBtn;
    public RadioButton outsourcedBtn;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField machineIdTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    @FXML
    public ToggleGroup addPartToggleGrp;
    @FXML
    private Label labelTxt;

    //creation of an object then addition of the object to the observable list.
    //save part
    public void onActionSavePart(ActionEvent event) throws IOException {
        String name = nameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        int machineId = 0;
        String companyName = "";

        //create  functionality of InHouse and Outsourced  button on add part form
        if (inHouseBtn.isSelected()) {
            machineId = Integer.parseInt(machineIdTxt.getText());
            Inventory.addPart(new InHouse(Inventory.getNextPartId(), name, price, stock, min, max, machineId));
        }
        else {
            companyName = machineIdTxt.getText();
            Inventory.addPart(new Outsourced(Inventory.getNextPartId(), name, price, stock, min, max, companyName));
        }
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionCancelPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }
    public void ifButtonSelected(ActionEvent event)
    {
        if (inHouseBtn.isSelected()){
            labelTxt.setText("Machine ID");
        }
        else {
            labelTxt.setText("Company Name");
        }
    }
}










