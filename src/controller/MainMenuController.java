/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 *
 * @author Adam Combs
 */
public class MainMenuController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    
    @FXML
    private TextField searchPartTextField;

    @FXML
    private TableView<Part> partTableMainMenu;

    @FXML
    private TableColumn<Part, Integer> partIDMainMenuCol;

    @FXML
    private TableColumn<Part, String> partNameMainMenuCol;

    @FXML
    private TableColumn<Part, Integer> partInvLevMainMenuCol;

    @FXML
    private TableColumn<Part, Double> partPPUMainMenuCol;

    @FXML
    private TextField searchProductTextField;

    @FXML
    private TableView<Product> productTableMainMenu;

    @FXML
    private TableColumn<Product, Integer> productIDMainMenuCol;

    @FXML
    private TableColumn<Product, String> productNameMainMenuCol;

    @FXML
    private TableColumn<Product, Integer> productInvLevMainMenuCol;

    @FXML
    private TableColumn<Product, Double> productPPUMainMenuCol;
    
     @FXML
    private Label hintMainMenuTextField;

    @FXML
    void onActionAddPartMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionAddProductMainMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionDeletePartMainMenu(ActionEvent event) {
        System.out.println("Part Deleted!");
        Part part = partTableMainMenu.getSelectionModel().getSelectedItem();
        System.out.println(part.getName());
        Inventory.deletePart(part);
    }

    @FXML
    void onActionDeleteProductMainMenu(ActionEvent event) {
        Product product = productTableMainMenu.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(product);
    }

    @FXML
    void onActionExitMainMenu(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onActionModifyPartMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();
        
        ModifyPartController MPartController = loader.getController();
        MPartController.transferPartInfo(partTableMainMenu.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }

    @FXML
    void onActionModifyProductMainMenu(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyProduct.fxml"));
        loader.load();
        
        ModifyProductController MProductController = loader.getController();
        MProductController.transferProductInfo(productTableMainMenu.getSelectionModel().getSelectedItem());
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }

    @FXML
    void onActionSearchPartMainMenu(ActionEvent event) {
        if(searchPartTextField.getText().isEmpty())
        {    
            partTableMainMenu.setItems(Inventory.getAllParts());
            hintMainMenuTextField.setText("");
        }    
        else
        {    
            partTableMainMenu.setItems(filterParts(searchPartTextField.getText()));
            hintMainMenuTextField.setText("Hint: Search with an empty search bar to restore all parts into view.");
        }    
    }

    @FXML
    void onActionSearchProductMainMenu(ActionEvent event) {
        if(searchProductTextField.getText().isEmpty())
        {    
            productTableMainMenu.setItems(Inventory.getAllProducts());
            hintMainMenuTextField.setText("");
        }    
        else
        {    
            productTableMainMenu.setItems(filterProducts(searchProductTextField.getText()));
            hintMainMenuTextField.setText("Hint: Search with an empty search bar to restore all items into table.");
        }  
    }
    
    public ObservableList<Part> filterParts(String search)
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
    
    public ObservableList<Product> filterProducts(String search)
    {
        if(!(Inventory.getAllFilteredProducts().isEmpty()))
            Inventory.getAllFilteredProducts().clear();
        
        for(Product product : Inventory.getAllProducts())
        {
            if(product.getName().contains(search))
                Inventory.getAllFilteredProducts().add(product);
        }
        return Inventory.getAllFilteredProducts();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        partTableMainMenu.setItems(Inventory.getAllParts());
        
        partIDMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvLevMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPPUMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        
        productTableMainMenu.setItems(Inventory.getAllProducts());
        
        productIDMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvLevMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPPUMainMenuCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    }    
    
}
