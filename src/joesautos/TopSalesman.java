/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautos;

/**
 * Extends the Saleman class in order to create a model for the data returned
 * from the top salesmen DB query
 * @author ryan
 */
public class TopSalesman extends Salesman {
    
    private Integer saleCount;
    private Double totalSalesValue;
    private Double totalCommissionEarned;

    /**
     * contructor with parameters
     * @param saleCount
     * @param totalSalesValue
     * @param totalCommissionEarned
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
    TopSalesman(Integer saleCount, double totalSalesValue, double totalCommissionEarned, String role, double commissionRate, String firstName, 
                String lastName, String email, String telephone, String address, String town, String city, String country, String postCode) {
        super(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
        this.saleCount = saleCount;
        this.totalSalesValue = totalSalesValue;
        this.totalCommissionEarned = totalCommissionEarned;
    }

    /**
     * getters and setters
     * @return 
     */
    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    public Double getTotalSalesValue() {
        return totalSalesValue;
    }

    public void setTotalSalesValue(Double totalSalesValue) {
        this.totalSalesValue = totalSalesValue;
    }

    public Double getTotalCommissionEarned() {
        return totalCommissionEarned;
    }

    public void setTotalCommissionEarned(Double totalCommissionEarned) {
        this.totalCommissionEarned = totalCommissionEarned;
    }

}
