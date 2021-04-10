/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acjavafxapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import static model.Part.createPartID;
import model.Product;
import static model.Product.createProductID;

/**
 *
 * Name: Adam Combs
 * Student ID: 001037922
 * email: acomb41@gmail.com
 * Course: Software I C482
 * 
 * 
 */
public class ACJavaFXApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Random preloaded parts to demonstrate funtionality.
        
        InHouse inHouse1 = new InHouse( createPartID(), "Buttress", 69.99, 100, 10, 120, 75509);
        OutSourced outSourced1 = new OutSourced( createPartID(), "Intercostel", 129.29, 25, 5, 30, "Bullocks Machining");
        InHouse inHouse2 = new InHouse( createPartID(), "Empennage", 2078.99, 3, 1, 4, 75509);
        OutSourced outSourced2 = new OutSourced( createPartID(), "Fuselage", 3004.29, 25, 5, 30, "Aerospace Magicians");
        OutSourced outSourced3 = new OutSourced( createPartID(), "Wing Assembly", 1200.29, 25, 5, 30, "Perfect Combo Boys");
        
        
        Inventory.addPart(inHouse1);
        Inventory.addPart(outSourced1);
        Inventory.addPart(inHouse2);
        Inventory.addPart(outSourced2);
        Inventory.addPart(outSourced3);
        
        Product product1 = new Product( createProductID(), "C130 Hercules", 3000019.20, 2, 1, 3);
        Product product2 = new Product( createProductID(), "F22 Raptor", 27830.45, 3, 2, 4);
        Product product3 = new Product( createProductID(), "G650", 12950.20, 2, 1, 3);
        
        product1.addAssociatedPart(inHouse1);
        product1.addAssociatedPart(outSourced2);
        product1.addAssociatedPart(outSourced3);
        
        product2.addAssociatedPart(outSourced1);
        product2.addAssociatedPart(outSourced1);
        product2.addAssociatedPart(outSourced3);
        
        product3.addAssociatedPart(inHouse2);
        
        Inventory.addProduct(product1);
        Inventory.addProduct(product2);
        Inventory.addProduct(product3);
        
        launch(args);
    }
    
}
