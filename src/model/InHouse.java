/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adam Combs
 */
public class InHouse extends Part {

    public InHouse(int id, String name, double price, int stock, int min, int max, int machineid) {
        super(id, name, price, stock, min, max);
        this.machineid = machineid;
    }
    
    private int machineid;
    
    public int getMachineId() {
        return machineid;
    }

    public void setMachineId(int machineID) {
        this.machineid = machineID;
    }
    
}
