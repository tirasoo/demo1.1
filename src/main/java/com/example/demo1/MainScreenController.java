package com.example.demo1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.example.demo1.Inventory.allParts;
import static com.example.demo1.Inventory.allProducts;
import static javafx.fxml.FXMLLoader.*;
import static javafx.fxml.FXMLLoader.load;
//import java.util.logging.Logger;


public class MainScreenController implements Initializable {
    public TableColumn<Object, Object> partIdCol;
    public TableColumn<Object, Object> partNameCol;
    public TableColumn<Object, Object> invLevelCol;
    public TableColumn<Object, Object> priceCostCol;
    public TableColumn<Object, Object> productIdCol;
    public TableColumn<Object, Object> productNameCol;
    public TableColumn InvLevelCol;
    public TableColumn<Object, Object> PriceCostCol;
    public TableColumn invLevCol2;
    Stage stage;
    Parent scene;
    @FXML
    private TableView<Part> partsMainTableView;
    @FXML
    private TableView<Product> productsMainTableView;
    @FXML
    private TableView<Part> addPartTableView;
    @FXML
    private TextField partSearchField;
    @FXML
    private TextField productSearchField;

    /**
     *
     * @param event
     * @throws IOException
     * RUNTIME ERRORS:
     * FUTURE ENHANCEMENTS:
     */
    @FXML
    public void onActionOpenAddParForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = load(getClass().getResource("AddParForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    //selection of part to be modified from partsMainTable
    public void onActionOpenModifyPartForm(ActionEvent event) throws IOException {
        Part SP = partsMainTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyPartForm.fxml"));
        scene = loader.load();
        ModifyPartController controller = loader.getController();
        controller.setparts(SP);
        stage.setScene(new Scene(scene));
        stage.show();
    }
    //selection of row or part and deletion of selected part
    public void onActionDeleteSelectedPart(ActionEvent event) {
        Part SP = partsMainTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Parts");
            alert.setHeaderText("Delete");
            alert.setContentText("Are you sure you want to delete this part");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK){
                allParts.remove(SP); //user chose OK,takes part off
            } else {
                //user chose cancel or closed the dialog box,nothing happens
            }
        }
    }
    public void onActionOpenModifyProductForm(ActionEvent event) throws IOException {
        Product SP = productsMainTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;

        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifyProductForm.fxml"));
        scene = loader.load();
        ModifyProductController controller = loader.getController();
        controller.setproducts(SP);
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionDeleteSelectedProduct() {
        Product SP = productsMainTableView.getSelectionModel().getSelectedItem();
        if (SP == null)
            return;
        else{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Products");
            alert.setHeaderText("Delete");
            alert.setContentText("Are you sure you want to delete this product");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()==ButtonType.OK){
                allProducts.remove(SP); //user chose OK,takes part off
            } else {
                //user chose cancel or closed the dialog box,nothing happens
            }
        }
    }
    public void onActionOpenAddProductForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = load(getClass().getResource("AddProductForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**
     * the method searches for a part by ID or Name in the partsTableView
     * @param event
     */
    public void onActionSearchPart(ActionEvent event) {
        String searchItem = partSearchField.getText();
        ObservableList<Part> foundParts = Inventory.lookupPart(searchItem);
        if(foundParts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Match Found");
            alert.setHeaderText("Unable to locate part");
            alert.showAndWait();
        } else {
            partsMainTableView.setItems(foundParts);
        }
    }
    /**
     * the method searches for a product by ID or Name in the productsTableView
     * @param event
     */
    public void onActionSearchProduct(ActionEvent event) {
        String searchprod = productSearchField.getText();
        ObservableList<Product> foundProducts = Inventory.lookupProduct(searchprod);
        if(foundProducts.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Match Found");
            alert.setHeaderText("Unable to locate product");
            alert.showAndWait();
        } else {
            productsMainTableView.setItems(foundProducts);
        }

    }
    public void onActionExitMainForm() {
        System.exit(0);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //associating tableview with list where data will be stored
        partsMainTableView.setItems(allParts);

        //assigning each column an attribute that you wish to display in a row
        partIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevelCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        priceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsMainTableView.setItems(allProducts);
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevCol2.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PriceCostCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}
