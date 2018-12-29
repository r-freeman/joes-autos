/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package joesautos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The model class interfaces with our database
 * @author ryan
 */
public class Model {
    private static Model instance = null;
    
    public static synchronized Model getInstance() {
        if( instance == null){
            instance = new Model();
        }
        
        return instance;
    }
    
    private List<Employee> employees;
    private Employee employee;
    private EmployeeTableGateway employeeGateway;
    
    private List<Motorbike> motorbikes;
    private Motorbike motorbike;
    private MotorbikeTableGateway motorbikeGateway;
    
    private List<Sale> sales;
    private Sale sale;
    private SaleTableGateway saleTableGateway;
    
    private List<TopSalesman> topSalesmen;
    
    private Model() {
        try {
            Connection conn = DBConnection.getInstance();
            
            // define gateways for interfacing with different tables
            this.motorbikeGateway = new MotorbikeTableGateway(conn);
            this.employeeGateway = new EmployeeTableGateway(conn);
            this.saleTableGateway = new SaleTableGateway(conn);
            
        }
        catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Gets an array of motorbikes from the database
     * @return 
     */
    public List<Motorbike> getMotorbikes() {
        try {
            this.motorbikes = motorbikeGateway.getMotorbikes();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Motorbike>(this.motorbikes);
    }
    
    /**
     * Gets a single motorbike with specific ID from the database
     * @param id
     * @return 
     */
    public Motorbike getMotorbikeById(int id) {
        try {
            this.motorbike = motorbikeGateway.getMotorbikeById(id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.motorbike;
    }
    
    /**
     * Gets an array of motorbikes with a specific frame
     * @param frame
     * @return 
     */
    public List<Motorbike> getMotorbikesByFrame(String frame) {
        try {
            this.motorbikes = motorbikeGateway.getMotorbikesByFrame(frame);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Motorbike>(this.motorbikes);
    }
    
    /**
     * Adds a motorbike to the databse
     * @param m 
     */
    public void addMotorbike(Motorbike m) {
        try {
            this.motorbikeGateway.addMotorbike(m);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Updates an existing motorbike in the DB
     * @param m 
     */
    public void updateMotorbike(Motorbike m) {
        try {
            this.motorbikeGateway.updateMotorbike(m);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes an existing motorbike with given ID
     * @param id 
     */
    public void deleteMotorbike(int id) {
        try {
            this.motorbikeGateway.deleteMotorbike(id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Returns an array of all employees
     * @return 
     */
    public List<Employee> getEmployees() {
        try {
            this.employees = employeeGateway.getEmployees();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Employee>(this.employees);
    }
    
    /**
     * Returns an employee with specific ID
     * @param id
     * @return 
     */
    public Employee getEmployeeById(int id) {
        try {
            this.employee = employeeGateway.getEmployeeById(id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.employee;
    }
    
    /**
     * Returns an array of employees with specific role
     * @param role
     * @return 
     */
    public List<Employee> getEmployeesByRole(String role) {
        try {
            this.employees = employeeGateway.getEmployeesByRole(role);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Employee>(this.employees);
    }
    
    /**
     * Returns an array of all sales
     * @return 
     */
    public List<Sale> getSales() {
        try {
            this.sales = saleTableGateway.getSales();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Sale>(this.sales);
    }
    
    /**
     * Returns an array of sales with specific status
     * @param status
     * @return 
     */
    public List<Sale> getSalesByStatus(String status) {
        try {
            this.sales = saleTableGateway.getSalesByStatus(status);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<Sale>(this.sales);
    }
    
    /**
     * Returns an array of sales made by a specific employee
     * @param employeeId
     * @return 
     */
    public List<Sale> getSalesByEmployeeId(Integer employeeId) {
        try {
            this.sales = saleTableGateway.getSalesByEmployeeId(employeeId);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>(this.sales);
    }
    
    /**
     * Returns the number of total sales
     * @return 
     */
    public double getTotalSumSales() {
        double sumSales = 0;
        try {
            sumSales = saleTableGateway.getTotalSumSales();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sumSales;
    }
    
    /**
     * Returns the number of motorbikes sold
     * @return 
     */
    public Integer getNumMotorbikesSold() {
        Integer numMotorbikes = 0;
        try {
            numMotorbikes = saleTableGateway.getNumMotorbikesSold();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return numMotorbikes;
    }
    
    /**
     * Returns an array of top salesmen
     * @return 
     */
    public List<TopSalesman> getTopSalesmen() {
        try {
            this.topSalesmen = saleTableGateway.getTopSalesmen();
        }
        catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<TopSalesman>(this.topSalesmen);
    }
    
    /**
     * Adds an employee to database
     * @param e 
     */
    public void addEmployee(Employee e) {
        try {
            this.employeeGateway.addEmployee(e);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    /**
     * Updates an existing employee
     * @param e 
     */
    public void updateEmployee(Employee e) {
        try {
            this.employeeGateway.updateEmployee(e);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes an employee with a specific ID
     * @param id 
     */
    public void deleteEmployee(int id) {
        try {
            this.employeeGateway.deleteEmployee(id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Adds a new sales to the database
     * @param sale 
     */
    public void addSale(Sale sale) {
        try{
            this.saleTableGateway.addSale(sale);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Deletes a sale with a specific ID
     * @param id 
     */
    public void deleteSale(int id) {
        try{
            this.saleTableGateway.deleteSale(id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
