/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Adam Combs
 */
public class Product {
    
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;
    
    public static int idProductCounter = 1;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }
    
    public void addAssociatedPart(Part part)
    {
        associatedParts.add(part);
    }
    
    public ObservableList<Part> getAllAssociatedParts()
    {
        return associatedParts;
    }
    
    public void setAssociatedParts(ObservableList<Part> associatedParts) 
    {
        this.associatedParts = associatedParts;
    }
    
    public void deleteAssociatedPart(Part associatedPart)
    {
        associatedParts.remove(associatedPart);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
    
    public static int createProductID() 
    {
        return idProductCounter++;
    }
    
    public double getPriceOfAssociatedParts(Product product)
    {
        double cost = 0.0;
        int index = -1;
        for(Part part : product.getAllAssociatedParts()){
            index++;
            if(index == product.getAllAssociatedParts().indexOf(product));
                cost = product.getAllAssociatedParts().get(index).getPrice() + cost;
            }
        return cost;
    }
}
