/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.OutSourced;
import static model.Part.createPartID;

/**
 * FXML Controller class
 *
 * @author Adam Combs
 */
public class AddPartController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private RadioButton inHouseAddPartRadio;

    @FXML
    private RadioButton outsourcedAddPartRadio;

    @FXML
    private TextField nameAddPartTextField;

    @FXML
    private TextField invAddPartTextField;

    @FXML
    private TextField pricecostAddPartTextField;

    @FXML
    private TextField compNmMachIDAddPartTextField;

    @FXML
    private TextField maxAddPartTextField;

    @FXML
    private TextField minAddPartTextField;

    @FXML
    private Label compNmMachIDAddPartLabel;

    @FXML
    void onActionInHouseRBSelect(ActionEvent event) {
        compNmMachIDAddPartLabel.setText("Machine ID");
        compNmMachIDAddPartTextField.setPromptText("Mach ID");
        
    }

    @FXML
    void onActionOutSourceRBSelect(ActionEvent event) {
        compNmMachIDAddPartLabel.setText("Company Name");
        compNmMachIDAddPartTextField.setPromptText("Comp Nm");
    }
    
    @FXML
    void onActionCancelAddPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveAddPart(ActionEvent event) throws IOException {

        int id = createPartID();
        String name = nameAddPartTextField.getText();
        int stock = Integer.parseInt(invAddPartTextField.getText());        
        double price = Double.parseDouble(pricecostAddPartTextField.getText());
        int max = Integer.parseInt(maxAddPartTextField.getText());
        int min = Integer.parseInt(minAddPartTextField.getText());
        String companyname;
        int machineid;
        
        if(outsourcedAddPartRadio.isSelected()){
            companyname = compNmMachIDAddPartTextField.getText();
            Inventory.addPart(new OutSourced( id, name, price, stock, min, max, companyname));
        }
        else if(inHouseAddPartRadio.isSelected()){
            machineid = Integer.parseInt(compNmMachIDAddPartTextField.getText());
            Inventory.addPart(new InHouse(id, name, price, stock, min, max, machineid));
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
