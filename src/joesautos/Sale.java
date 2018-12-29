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
public class Sale {
    private int id;
    private String status;
    private double total, commission;
    private int employeeId;
    private int motorbikeId;

    /**
     * constructor with parameters
     * @param status
     * @param total
     * @param commission
     * @param employeeId
     * @param motorbikeId 
     */
    Sale(String status, double total, double commission, int employeeId, int motorbikeId) {
        this.status = status;
        this.total = total;
        this.commission = commission;
        this.employeeId = employeeId;
        this.motorbikeId = motorbikeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public double getCommission() {
        return commission;
    }
    
    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getMotorbikeId() {
        return motorbikeId;
    }

    public void setMotorbikeId(int motorbikeId) {
        this.motorbikeId = motorbikeId;
    }
}
