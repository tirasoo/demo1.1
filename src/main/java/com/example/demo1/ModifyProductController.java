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
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.TableView;

import static com.example.demo1.Inventory.allParts;
import static com.example.demo1.Inventory.allProducts;
import static java.lang.Integer.parseInt;

/**
 *It is the controller class for the ModifyProduct.fxml file.
 */
public class ModifyProductController implements Initializable {
    public TextField searchPartField;
    Stage stage;
    Parent scene;

    @FXML
    private TableColumn<?, ?> InvLevelCol;
    @FXML
    private TableColumn<?, ?> PartIdCol;
    @FXML
    private TableColumn<?, ?> PartNameCol;
    @FXML
    private TableColumn<?, ?> PriceCostCol;
    @FXML
    private TextField idTxt;
    @FXML
    private TableColumn<?, ?> invLevelCol;
    @FXML
    private TextField invTxt;
    @FXML
    private TextField maxTxt;
    @FXML
    private TextField minTxt;
    @FXML
    private TableView<Part> addPartTableView;
    @FXML
    private TableView<Part> associatedPartTableView;
    @FXML
    private TextField nameTxt;
    @FXML
    private TableColumn<?, ?> partIdCol;
    @FXML
    private TableColumn<?, ?> partNameCol;
    @FXML
    private TableColumn<?, ?> priceCostCol;
    @FXML
    private TextField priceTxt;
    @FXML
    private TextField partSearchField;

    public Product sp; //field of the selected product from the productsTableView
    private int productIndex;
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private EventObject event;
    private static int selectedProductIndex;

    /**
     * the method adds a selected part to the associatedPartTableView from the addPartTableView
     * @param event
     */
    public void onActionAddPart(ActionEvent event) {
        Part SP = (Part) addPartTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        else {
            if (!associatedParts.contains(SP)) {
                associatedParts.add(SP);
                associatedPartTableView.setItems(associatedParts);
            }
        }
    }

    /**
     * the method exits the modify product form back to the main form
     * @param event
     * @throws IOException
     */
    public void onActionCancel(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * the method saves a product to the productsMainTableView after modifications
     * @param event
     * @throws IOException
     */
    public void onActionSaveProduct(ActionEvent event) throws IOException {
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
            }
            //Add product-to the ProductTableView when save button on Add Product form is hit.
            else {
                Product product = new Product();
                //   product.setId(parseInt(this.idTxt.getText()));
                product.setName(this.nameTxt.getText());
                product.setStock(parseInt(this.invTxt.getText()));
                product.setMin(parseInt(this.minTxt.getText()));
                product.setMax(parseInt(this.maxTxt.getText()));
                product.setPrice(Double.parseDouble(this.priceTxt.getText()));
                for(Part part: associatedParts) {
                    product.addAssociatedPart(part);

                }
                //product.addAssociatedPart((Part) associatedParts);
                Inventory.updateProduct(productIndex, product);

                stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("MainForm.fxml"));
                Parent mp = loader.load();
                stage.setTitle("Inventory Management System");
                stage.setScene(new Scene(mp));
                stage.show();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error in modifying product");
            alert.setContentText("Check fields for correct input values");
            alert.showAndWait();
        }
    }
    /**
     * the method transfers the data of the selected product in the productsTableView to the Modify Product table for
     * modification
     * @param sp
     */
    public void setproducts(Product sp) {
        this.sp = sp;
        productIndex = Inventory.getAllProducts().indexOf(sp);
        idTxt.setText(String.valueOf(sp.getId()));
        nameTxt.setText(sp.getName());
        invTxt.setText(Integer.toString(sp.getStock()));
        priceTxt.setText(Double.toString(sp.getPrice()));
        maxTxt.setText(Integer.toString(sp.getMax()));
        minTxt.setText(Integer.toString(sp.getMin()));
        associatedParts.setAll(sp.getAllAssociatedParts());
        associatedPartTableView.setItems(sp.getAllAssociatedParts());
    }

    /**
     * this method initializes the addPartTableView and the associatedPartTableView tables
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
       associatedPartTableView.setItems(associatedParts);
//
//        //assigning each column an attribute that you wish to display in a row
        PartIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
       InvLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
       PriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    /**
     * this method removes/deletes a part from the associatedPartTableView disassociating it from the product
     * @param event
     */
    public void onActionRemoveAssPart(ActionEvent event) {
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
                //user chose cancel or closed the dialog box,nothing happens
            }
        }
    }

    /**
     * this method searches for a part by ID or name in the addPartTableView
     * @param event
     */
    public void searchPart(ActionEvent event) {
        String searchItem = partSearchField.getText();
        try {
            int id = Integer.parseInt(searchItem);
            Part p = Inventory.lookupPart(id);
            if (p != null) {
                addPartTableView.getSelectionModel().select(p);
            } else
                throw new NumberFormatException();
        }
        catch (NumberFormatException e) {
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
}
