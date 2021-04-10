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
public class OutSourced extends Part{
    
    public OutSourced(int id, String name, double price, int stock, int min, int max, String companyname) {
        super(id, name, price, stock, min, max);
        this.companyname = companyname;
    }
    
    private String companyname;

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
    
}
