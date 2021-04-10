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
import model.Part;
import model.InHouse;
import model.Inventory;
import model.OutSourced;

/**
 * FXML Controller class
 *
 * @author Adam Combs
 */
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;
    int index;
    
    @FXML
    private RadioButton inHouseModifyPartRadio;

    @FXML
    private RadioButton outsourcedModifyPartRadio;

    @FXML
    private TextField idModifyPartTextField;
    
    @FXML
    private TextField nameModifyPartTextField;

    @FXML
    private TextField invModifyPartTextField;

    @FXML
    private TextField pricecostModifyPartTextField;

    @FXML
    private TextField compNmMachIDModifyPartTextField;

    @FXML
    private TextField maxModifyPartTextField;

    @FXML
    private TextField minModifyPartTextField;

    @FXML
    private Label compNmMachIDModifyPartLabel;
    
    @FXML
    void inHouseRBSelect(ActionEvent event) {
        compNmMachIDModifyPartLabel.setText("Machine ID");
        compNmMachIDModifyPartTextField.setPromptText("Mach ID");
    }
    
    @FXML
    void outsourcedRBSelect(ActionEvent event) {
        compNmMachIDModifyPartLabel.setText("Company Name");
        compNmMachIDModifyPartTextField.setPromptText("Comp Nm");
    }
    
    @FXML
    void onActionCancelModifyPart(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onActionSaveModifyPart(ActionEvent event) throws IOException {
        
        int id = Integer.parseInt(idModifyPartTextField.getText());
        String name = nameModifyPartTextField.getText();
        int stock = Integer.parseInt(invModifyPartTextField.getText());        
        double price = Double.parseDouble(pricecostModifyPartTextField.getText());
        int max = Integer.parseInt(maxModifyPartTextField.getText());
        int min = Integer.parseInt(minModifyPartTextField.getText());
        String companyname = compNmMachIDModifyPartTextField.getText();;
        int machineid;
        
        if(outsourcedModifyPartRadio.isSelected()){
            companyname = compNmMachIDModifyPartTextField.getText();
            Inventory.updatePart(index, new OutSourced( id, name, price, stock, min, max, companyname));
        }
        else if(inHouseModifyPartRadio.isSelected()){
            machineid = Integer.parseInt(compNmMachIDModifyPartTextField.getText());
            Inventory.updatePart(index, new InHouse(id, name, price, stock, min, max, machineid));
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.showAndWait();
    }
    
    public void transferPartInfo(Part part){
        idModifyPartTextField.setText(Integer.toString(part.getId()));
        nameModifyPartTextField.setText(part.getName());
        invModifyPartTextField.setText(Integer.toString(part.getStock()));
        pricecostModifyPartTextField.setText(Double.toString(part.getPrice()));
        maxModifyPartTextField.setText(Integer.toString(part.getMax()));
        minModifyPartTextField.setText(Integer.toString(part.getMin()));
        
        index = Inventory.getAllParts().indexOf(part);
        
        if(part instanceof InHouse)
        {
            inHouseModifyPartRadio.setSelected(true);
            compNmMachIDModifyPartTextField.setText(Integer.toString(((InHouse) part).getMachineId()));
        }
        else{
            outsourcedModifyPartRadio.setSelected(true);
            compNmMachIDModifyPartTextField.setText(((OutSourced) part).getCompanyname());
        }    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
