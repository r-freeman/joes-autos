/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package joesautos;

import java.util.Scanner;

/**
 * Abstract Employee class
 * @author ryan
 */
public class Employee {
    private int id;
    private String role;
    private double commissionRate;
    private String firstName;
    private String lastName;
    private String email;
    private String telephone;
    private String address;
    private String town;
    private String city;
    private String country;
    private String postCode;

    /**
     * constructor with parameters 
     * @param role
     * @param commission
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
    Employee(String role, double commissionRate, String firstName, String lastName, String email, String telephone, String address, String town, String city, String country, String postCode) {
        this.role = role;
        this.commissionRate = commissionRate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.address = address;
        this.town = town;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }
    
    /**
     * getters and setters
     * @return 
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }  
}
