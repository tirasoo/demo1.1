package com.example.demo1;

/**
 * @author Tiras Ombasa
 * Student ID: 001244560
 */

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

/**
 * It is the controller class for the AddProduct.fxml file.
 *
 */
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
    @FXML
    private TextField partSearchField;

    /**
     * this method selects a part on the addPartTableView and copies/adds it to the associatedPartTableView
     * @param event
     * @throws IOException
     */
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

    /**
     * this method removes/deletes a part from the associatedPartTableView disassociating it from the product
     * @param event
     */
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

    /**
     * this method exits the add product form and returns to the main form
     * @param event
     * @throws IOException
     */
    public void Cancel(ActionEvent event) throws IOException {
                stage = (Stage)((Button)event.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene(scene));
                stage.show();
    }

    /**
     * this method adds a product to the productsMainTableView
     * @param event
     * @throws IOException
     */
    public void SaveProduct(ActionEvent event )throws IOException {
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
                //Add product-to the ProductTableView when save button on Add Product form is hit.
                Product product = new Product(Inventory.getNextProductId(), name, price, stock, min, max);
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
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in adding product");
            alert.setContentText("Check fields for correct input values");
            alert.showAndWait();
        }
    }
        /**
         * this method searches for a part by ID or name in the addPartTableView
         * @param actionEvent
         */
    public void searchPart(ActionEvent actionEvent) {
        String searchItem = partSearchField.getText();
        try {
            int id = Integer.parseInt(searchItem);
            Part p = Inventory.lookupPart(id);
            if (p != null) {
                addPartTableView.getSelectionModel().select(p);
            } else
                throw new NumberFormatException();
        } catch (NumberFormatException e) {
            ObservableList<Part> foundParts = Inventory.lookupPart(searchItem);
            if (foundParts.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                //alert.initModality(Modality.APPLICATION_MODAL);
                alert.setTitle("No Match Found");
                alert.setHeaderText("Unable to locate part");
                alert.showAndWait();
            } else {
                addPartTableView.setItems(foundParts);
            }
        }
    }

    /**
     * this method initializes the cells in the addPartTableView
     * @param url
     * @param resourceBundle
     */
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

    /**
     * this method creates a new stage/ initializes the addPartTableView and displays it.
     * @param stage
     * @throws IOException
     */
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

