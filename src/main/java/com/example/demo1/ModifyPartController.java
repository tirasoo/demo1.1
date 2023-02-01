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

import static java.lang.Integer.parseInt;

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
    private int partIndex;

    //creation of an object then addition of the object to the observable list.
    //save part
    public void onActionSavePart(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idTxt.getText());
        String name = nameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = parseInt(minTxt.getText());
        int machineId = 0;
        String companyName = "";

        //create  functionality of InHouse and Outsourced  button on modify part form
        if (inHouseBtn.isSelected()) {
            machineId = parseInt(machineIdTxt.getText());
            Inventory.updatePart(partIndex,new InHouse(id, name, price, stock, min, max, machineId));
        }
        else {
            companyName = machineIdTxt.getText();
            Inventory.updatePart(partIndex,new Outsourced(id, name, price, stock, min, max, companyName));
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
    //the method transfers the data of the selected part in the partsTableView to the Modify Part table
    public void setparts(Part sp) {
        this.sp = sp;
        partIndex = Inventory.getAllParts().indexOf(sp);
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
