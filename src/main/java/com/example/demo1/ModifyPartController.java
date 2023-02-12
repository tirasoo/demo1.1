package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */


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

import static java.lang.Integer.parseInt;

/**
 *It is the controller class for the ModifyPart.fxml file.
 */
public class ModifyPartController implements Initializable {
    public ToggleGroup modifyPartToggleGrp;
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
    public Part sp; //field of the selected part
    public int index;

    //creation of an object then addition of the object to the observable list.
    //save part

    /**
     * the method saves the part on the partsMainTableView after modifications.
     * @param event
     * @throws IOException
     */
    public void onActionSavePart(ActionEvent event) throws IOException {
        try {
            int stock = Integer.parseInt(invTxt.getText());
            int max = Integer.parseInt(maxTxt.getText());
            int min = Integer.parseInt(minTxt.getText());
            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in max and min entries");
                alert.setContentText("max value should be greater than min value");
                alert.showAndWait();
            } else if (stock < min || stock > max) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error in Inventory Field");
                alert.setContentText("Inventory field must be between max and min values");
                alert.showAndWait();
            } else {
                String name = nameTxt.getText();
                double price = Double.parseDouble(priceTxt.getText());
                int machineId = 0;
                String companyName = "";

                //create  functionality of InHouse and Outsourced  button on add part form
                if (inHouseBtn.isSelected()) {
                    machineId = Integer.parseInt(machineIdTxt.getText());
                    Inventory.updatePart(this.index, new InHouse(Inventory.getNextPartId(), name, price, stock, min, max, machineId));
                } else {
                    companyName = machineIdTxt.getText();
                    Inventory.updatePart(this.index,new Outsourced(Inventory.getNextPartId(), name, price, stock, min, max, companyName));
                }
                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene(scene));
                stage.show();
            }
        }
        catch (Exception e){
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in modifying part");
            alert.setContentText("Check fields for correct input values");
            alert.showAndWait();
        }

    }

    /**
     * the method exits the Modify Part form and returns to the Main form
     * @param event
     * @throws IOException
     */
    public void onActionCancelPart(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * initializes the modify part table
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

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

    /**
     * the method transfers the data of the selected part in the partsTableView to the Modify Part form
     * @param sp
     */
    //
    public void setparts(int index,Part sp) {
        this.sp = sp;
        this.index =index;
        //partIndex = Inventory.getAllParts().indexOf(sp);
        idTxt.setText(String.valueOf(sp.getId()));
        nameTxt.setText(sp.getName());
        invTxt.setText(Integer.toString(sp.getStock()));
        priceTxt.setText(Double.toString(sp.getPrice()));
        maxTxt.setText(Integer.toString(sp.getMax()));
        minTxt.setText(Integer.toString(sp.getMin()));
        if(sp instanceof InHouse ){
            inHouseBtn.setSelected(true);
            this.inHouseBtn.setText("Machine ID");
            machineIdTxt.setText(Integer.toString(((InHouse)sp).getMachineId()));
        }
        else{
            Outsourced os = (Outsourced) sp;
           outsourcedBtn.setSelected(true);
            this.outsourcedBtn.setText("Company Name");
            machineIdTxt.setText(os.getCompanyName());
        }
    }

}
