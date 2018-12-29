/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautos;

/**
 *
 * @author ryan
 */
public class Salesman extends Employee {
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
    Salesman(String role, double commissionRate, String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode) {
        super(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
    }
    
    public double getSaleTotal(final double VAT, double price) {
        return price * (1 + VAT);
    }
    
    public double getSaleCommission(double price) {
        return (price * this.getCommissionRate()) / 100;
    }
}
