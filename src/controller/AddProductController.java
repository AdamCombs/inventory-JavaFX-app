/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import static model.Product.createProductID;

/**
 * FXML Controller class
 *
 * @author Adam Combs
 */
public class AddProductController implements Initializable {

    Stage stage;
    Parent scene;
    ObservableList<Part> tempList = FXCollections.observableArrayList();
    
    @FXML
    private TextField nameAddProductTextField;

    @FXML
    private TextField invAddProductTextField;

    @FXML
    private TextField priceAddProductTextField;

    @FXML
    private TextField maxAddProductTextField;

    @FXML
    private TextField minAddProductTextField;

    @FXML
    private TextField searchAddProductTextField;

    @FXML
    private TableView<Part> topTableAddProduct;

    @FXML
    private TableColumn<Part, Integer> partIDAddProductTopCol;

    @FXML
    private TableColumn<Part, String> partNameAddProductTopCol;

    @FXML
    private TableColumn<Part, Integer> invLevAddProductTopCol;

    @FXML
    private TableColumn<Part, Integer> ppuAddProductTopCol;

    @FXML
    private TableView<Part> botTableAddProduct;

    @FXML
    private TableColumn<Part, Integer> partIDAddProductBotCol;

    @FXML
    private TableColumn<Part, String> partNameAddProductBotCol;

    @FXML
    private TableColumn<Part, Integer> invLevAddProductBotCol;

    @FXML
    private TableColumn<Part, Integer> ppuAddProductBotCol;
    
    @FXML
    private Label hintAddProductLabel;

    @FXML
    void onActionAddProduct(ActionEvent event) throws IOException {
        tempList.add(topTableAddProduct.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionCancelProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {
        tempList.remove(botTableAddProduct.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        String name = nameAddProductTextField.getText();
        int stock = Integer.parseInt(invAddProductTextField.getText());        
        double price = Double.parseDouble(priceAddProductTextField.getText());
        int max = Integer.parseInt(maxAddProductTextField.getText());
        int min = Integer.parseInt(minAddProductTextField.getText());
        
        Product tempProduct = new Product( createProductID(), name, price, stock, min, max);
        tempProduct.setAssociatedParts(tempList);
        
        if(tempList.isEmpty() == true)
        {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Invalid Product!");
            errorAlert.setContentText("Each product must contain atleast one part.");
            errorAlert.showAndWait();
        }
        else if(tempProduct.getPriceOfAssociatedParts(tempProduct) > tempProduct.getPrice())
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid Product!");
            errorAlert.setContentText("Price of product must be higher than combined price of associated parts!");
            errorAlert.showAndWait();
        }
        else if(tempList.isEmpty() == false)
        {
            Inventory.addProduct(tempProduct);
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSearchAddProduct(ActionEvent event) {
        if(searchAddProductTextField.getText().isEmpty())
        {    
            topTableAddProduct.setItems(Inventory.getAllParts());
            hintAddProductLabel.setText("");
        }    
        else
        {    
            topTableAddProduct.setItems(filter(searchAddProductTextField.getText()));
            hintAddProductLabel.setText("Hint: Search with an empty search bar to restore all parts into view.");
        }  
    }
    
    public ObservableList<Part> filter(String search)
    {
        if(!(Inventory.getAllFilteredParts().isEmpty()))
            Inventory.getAllFilteredParts().clear();
        
        for(Part part : Inventory.getAllParts())
        {
            if(part.getName().contains(search))
                Inventory.getAllFilteredParts().add(part);
        }
        return Inventory.getAllFilteredParts();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        topTableAddProduct.setItems(Inventory.getAllParts());
        
        partIDAddProductTopCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAddProductTopCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevAddProductTopCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppuAddProductTopCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        botTableAddProduct.setItems(tempList);
        
        partIDAddProductBotCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameAddProductBotCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevAddProductBotCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppuAddProductBotCol.setCellValueFactory(new PropertyValueFactory<>("price"));        
    }    
    
}
