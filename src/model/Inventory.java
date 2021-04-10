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
public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static ObservableList<Part> allFilteredParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allFilteredProducts = FXCollections.observableArrayList();
    
    public static void addPart(Part part)
    {
        allParts.add(part);
    }
    
    public static void addProduct(Product product)
    {
        allProducts.add(product);
    }
    
    public static ObservableList<Part> getAllParts()
    {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts()
    {
        return allProducts;
    }
    
    public static void deletePart(Part part)
    {
        allParts.remove(part);
    }
    
    public static void deleteProduct(Product product)
    {
        allProducts.remove(product);
    }
    
    public static ObservableList<Part> getAllFilteredParts()
    {
        return allFilteredParts;
    }
    
    public static ObservableList<Product> getAllFilteredProducts()
    {
        return allFilteredProducts;
    }

    public static void updatePart(int index, Part updatepart)
    {
        int i = -1;
        for(Part part: Inventory.getAllParts())
        {
            i++;
            if(index == Inventory.getAllParts().indexOf(part))
            {
                Inventory.getAllParts().set(index, updatepart);
            }    
        }
    }
    
    public static void updateProduct(int index, Product updateproduct)
    {
        int i = -1;
        for(Product product: Inventory.getAllProducts())
        {
            i++;
            if(index == Inventory.getAllProducts().indexOf(product))
            {
                Inventory.getAllProducts().set(index, updateproduct);
                System.out.println(i);
            }    
        }
    }
    /*
    public Part lookupPart(int partID){
        
        return part;
    }
    
    public Part lookupPart(String name){
        
        return part,
    }
    
    public Product lookupPart(int productID){
        
        return product;
    }
    
    public Product lookupPart(String productName){
        
        return product;
    }
    */
    

    

        
   
}
