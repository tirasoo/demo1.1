package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.demo1.Inventory.allParts;

public class AddProductController implements Initializable {
    public TableView addProductTableView1;
    public TableView<Part> addPartTableView;
    public TableColumn<Object, Object> partIdCol;
    public TableColumn<Object, Object> partNameCol;
    public TableColumn<Object, Object> invLevelCol;
    public TableColumn<Object, Object> priceCostCol;
    public TableView<Part> associatedPartTableView;
    public TableColumn PartIdCol;
    public TableColumn PartNameCol;
    public TableColumn InvLevelCol;
    public TableColumn PriceCostCol;
    Stage stage;
    Parent scene;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField priceTxt;
    public void onActionAddPart(ActionEvent event) throws IOException {
        int id=Integer.parseInt(idTxt.getText());
        String name=nameTxt.getText();
        double price=Double.parseDouble(priceTxt.getText());
        int max=Integer.parseInt(maxTxt.getText());
        int min=Integer.parseInt(minTxt.getText());
        invTxt.getText();
        int stock = 0;
        //create Product object and add that object to the end of the Inventory list
        Inventory.addProduct(new Product(id,name,price,stock,min,max));
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    //removes selected part from the bottom table-disassociating or removing a part from a product
    public void removeAssociatedPart(ActionEvent event)
    {
        Part SP = associatedPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        allParts.remove(SP);
    }
    public void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionSaveProduct(ActionEvent event) throws IOException{
        String name = nameTxt.getText();
        int stock = Integer.parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = Integer.parseInt(maxTxt.getText());
        int min = Integer.parseInt(minTxt.getText());
        
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //associating tableview with list where data will be stored
        addPartTableView.setItems(allParts);

        //assigning each column an attribute that you wish to display in a row
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //associating tableview with list where data will be stored
        associatedPartTableView.setItems(allParts);

        //assigning each column an attribute that you wish to display in a row
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }


}

