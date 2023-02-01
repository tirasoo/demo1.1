package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.demo1.Inventory.allParts;
import static com.example.demo1.Inventory.getNextPartId;
import static java.lang.Integer.parseInt;


public class AddProductController implements Initializable {
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
    private ActionEvent actionEvent;
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

    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private EventObject event;


    //the onActionAddPart method copies the selected part on the
    // addPartTableView to the lower table(associatedPartTableView)
    public void onActionAddPart(ActionEvent event) throws IOException {
        Part SP = addPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        associatedParts.add(SP);
        associatedPartTableView.setItems(associatedParts);

       // associatedPartTableView.setItems(allParts);
//
        //assigning each column an attribute that you wish to display in a row
        PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

//        else {
//            if (!associatedParts.contains(SP)) {
//                associatedParts.add(SP);
//                associatedPartTableView.setItems(associatedParts);
//
        }

    //removes selected part from the bottom table(associatedPartTableView)-disassociating or removing a part from a product
    public void removeAssociatedPart(ActionEvent event) {
        Part SP = associatedPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Associated Part");
            alert.setHeaderText("Remove");
            alert.setContentText("Are you sure you want to remove this associated part");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK){
                associatedParts.remove(SP); //user chose OK,takes part off
            } else {
                //user chose cancel or closed the dialog box
            }
        }
    }
    public void Cancel(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene(scene));
                stage.show();
    }
    public void SaveProduct(ActionEvent event )throws IOException {
        String name = nameTxt.getText();
        int stock = parseInt(invTxt.getText());
        double price = Double.parseDouble(priceTxt.getText());
        int max = parseInt(maxTxt.getText());
        int min = parseInt(minTxt.getText());
        //Add product-to the ProductTableView when save button on Add Product form is hit.
        Product product = new Product(Inventory.getNextProductId(),name,price,stock,min,max);
        //   product.setId(parseInt(this.idTxt.getText()));
//        product.setName(this.nameTxt.getText());
//        product.setStock(parseInt(this.invTxt.getText()));
//        product.setMin(parseInt(this.minTxt.getText()));
//        product.setMax(parseInt(this.maxTxt.getText()));
//        product.setPrice(Double.parseDouble(this.priceTxt.getText()));
        //product.addAssociatedPart((Part) associatedParts);
        Inventory.addProduct(product);
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void searchPart(ActionEvent actionEvent) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //associating tableview with list(Observable List) where data will be stored
        addPartTableView.setItems(allParts);

        //assigning each column on TableView an attribute that you wish to display in a row
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        //associating tableview with list where data will be stored
//        associatedPartTableView.setItems(allParts);
//
//        //assigning each column an attribute that you wish to display in a row
//        PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
//        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
//        InvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
//        PriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    public void start(Stage stage) throws IOException {
        InHouse nut = new InHouse(getNextPartId(), "nut", 40, 0, 100, 980, 1223);
        InHouse screw = new InHouse(getNextPartId(), "screw", 80, 70, 400, 1100, 3234);
        InHouse clippers = new InHouse(getNextPartId(), "clippers", 90, 100, 600, 1300, 3500);
        Outsourced chain = new Outsourced(getNextPartId(), "chain", 50, 40, 120, 834, "ACE Hardware");
        Outsourced nail = new Outsourced(getNextPartId(), "nail", 60, 50, 180, 734, "Home Depot");
        Outsourced handles = new Outsourced(getNextPartId(), "handles", 50, 40, 120, 834, "ACE Hardware");

        Inventory.addPart(nut);
        Inventory.addPart(screw);
        Inventory.addPart(chain);
        Inventory.addPart(nail);
        Inventory.addPart(clippers);
        Inventory.addPart(handles);
    }
}

