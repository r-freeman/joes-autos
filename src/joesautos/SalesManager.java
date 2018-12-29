/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ryan
 */
public class SalesManager extends Employee {
    /**
     * constructor with parameters
     * @param role
     * @param commissionRate
     * @param firstName
     * @param lastName
     * @param email
     * @param telephone
     * @param address
     * @param town
     * @param city
     * @param country
     * @param postCode 
     */
    SalesManager(String role, double commissionRate, String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode) {
        super(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
    }
    
    public double getTotalSumSales(Model model) {
        return model.getTotalSumSales();
    }
    
    public Integer getNumMotorbikesSold(Model model) {
        return model.getNumMotorbikesSold();
    }
    
    public List<TopSalesman> getTopSalesmen(Model model) {
        return new ArrayList<>(model.getTopSalesmen());
    }
}
