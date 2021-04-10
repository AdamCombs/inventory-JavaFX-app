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

/**
 * FXML Controller class
 *
 * @author Adam Combs
 */
public class ModifyProductController implements Initializable {
    
    Stage stage;
    Parent scene;
    int index;
    ObservableList<Part> tempList = FXCollections.observableArrayList();


    @FXML
    private TextField idModifyProductTextField;

    @FXML
    private TextField nameModifyProductTextField;

    @FXML
    private TextField invModifyProductTextField;

    @FXML
    private TextField priceModifyProductTextField;

    @FXML
    private TextField maxModifyProductTextField;

    @FXML
    private TextField minModifyProductTextField;

    @FXML
    private TextField searchModifyProductTextField;

    @FXML
    private TableView<Part> topTableModifyProduct;

    @FXML
    private TableColumn<Part, Integer> partIDModifyProductTopCol;

    @FXML
    private TableColumn<Part, String> partNameModifyProductTopCol;

    @FXML
    private TableColumn<Part, Integer> invLevModifyProductTopCol;

    @FXML
    private TableColumn<Part, Integer> ppuModifyProductTopCol;

    @FXML
    private TableView<Part> botTableModifyProduct;

    @FXML
    private TableColumn<Part, Integer> partIDModifyProductBotCol;

    @FXML
    private TableColumn<Part, String> partNameModifyProductBotCol;

    @FXML
    private TableColumn<Part, Integer> invLevModifyProductBotCol;

    @FXML
    private TableColumn<Part, Integer> ppuModifyProductBotCol;
    
    @FXML
    private Label hintModifyProductLabel;

    @FXML
    void onActionAddModifyProduct(ActionEvent event) {
        tempList.add(topTableModifyProduct.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionCancelModifyProduct(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeleteModifyProduct(ActionEvent event) {
        tempList.remove(botTableModifyProduct.getSelectionModel().getSelectedItem());
    }

    @FXML
    void onActionSaveModifyProduct(ActionEvent event) throws IOException {
        int id = Integer.parseInt(idModifyProductTextField.getText());
        String name = nameModifyProductTextField.getText();
        int stock = Integer.parseInt(invModifyProductTextField.getText());        
        double price = Double.parseDouble(priceModifyProductTextField.getText());
        int max = Integer.parseInt(maxModifyProductTextField.getText());
        int min = Integer.parseInt(minModifyProductTextField.getText());
            
        Product tempProduct = new Product( id, name, price, stock, min, max);
        tempProduct.setAssociatedParts(tempList);
        
        if(tempList.isEmpty() == true)
        {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
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
            System.out.print(index);
            Inventory.updateProduct(index, tempProduct);
            System.out.println("Product Updated?");
            System.out.println(tempProduct.getPriceOfAssociatedParts(tempProduct));
        
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onActionSearchModifyProduct(ActionEvent event) {
        if(searchModifyProductTextField.getText().isEmpty())
        {    
            topTableModifyProduct.setItems(Inventory.getAllParts());
            hintModifyProductLabel.setText("");
        }    
        else
        {    
            topTableModifyProduct.setItems(filter(searchModifyProductTextField.getText()));
            hintModifyProductLabel.setText("Hint: Search with an empty search bar to restore all parts into view.");
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
    
    public void transferProductInfo(Product product){
        idModifyProductTextField.setText(Integer.toString(product.getId()));
        nameModifyProductTextField.setText(product.getName());
        invModifyProductTextField.setText(Integer.toString(product.getStock()));
        priceModifyProductTextField.setText(Double.toString(product.getPrice()));
        maxModifyProductTextField.setText(Integer.toString(product.getMax()));
        minModifyProductTextField.setText(Integer.toString(product.getMin()));
        
        tempList.setAll(product.getAllAssociatedParts());
        botTableModifyProduct.setItems(tempList);
        
        partIDModifyProductBotCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameModifyProductBotCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevModifyProductBotCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppuModifyProductBotCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        index = Inventory.getAllProducts().indexOf(product);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        topTableModifyProduct.setItems(Inventory.getAllParts());
        
        partIDModifyProductTopCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameModifyProductTopCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        invLevModifyProductTopCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ppuModifyProductTopCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }    
    
}
