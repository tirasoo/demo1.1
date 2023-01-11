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

public class ModifyProductController implements Initializable {
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
    private TableView<?> modifyProductTableView1;
    @FXML
    private TableView<?> modifyProductTableView2;
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

    public void onActionAddPart(ActionEvent event) {

    }
    public void removeAssociatedPart(ActionEvent event) {

    }
    public void displayMainForm(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    public void onActionSavePart(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
