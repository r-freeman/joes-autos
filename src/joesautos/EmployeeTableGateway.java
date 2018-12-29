/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package joesautos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Our interface for employee table in database
 * @author ryan
 */
public class EmployeeTableGateway {
    private static final String TABLE_NAME = "employees";
    private static final String COLUMN_ID = "employee_id";
    private static final String COLUMN_ROLE = "role";
    private static final String COLUMN_COMMISSION_RATE = "commission_rate";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TELEPHONE = "telephone";
    private static final String COLUMN_ADDRESS = "address";
    private static final String COLUMN_TOWN = "town";
    private static final String COLUMN_CITY = "city";
    private static final String COLUMN_COUNTRY = "country";
    private static final String COLUMN_POST_CODE = "post_code";

    private Connection mConnection;

    /**
     * Initialize the connection to db
     * @param connection 
     */
    public EmployeeTableGateway(Connection connection) {
        mConnection = connection;
    }

    /**
     * Returns an ArrayList of all employees
     * @return
     * @throws SQLException 
     */
    public List<Employee> getEmployees() throws SQLException {

        String query;
        Statement st;
        ResultSet rs;
        List<Employee> employees;

        String role, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        
        double commissionRate;
        
        int id;

        Employee e;

        query = "SELECT * FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();

        rs = st.executeQuery(query);

        employees = new ArrayList<Employee>();

        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            role = rs.getString(COLUMN_ROLE);
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            email = rs.getString(COLUMN_EMAIL);
            telephone = rs.getString(COLUMN_TELEPHONE);
            address = rs.getString(COLUMN_ADDRESS);
            town = rs.getString(COLUMN_TOWN);
            city = rs.getString(COLUMN_CITY);
            country = rs.getString(COLUMN_COUNTRY);
            postCode = rs.getString(COLUMN_POST_CODE);

            e = new Employee(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
            e.setId(id);
            employees.add(e);
        }

        return employees;
    }
    
    /**
     * Returns an Employee with a specific id
     * @param id
     * @return
     * @throws SQLException 
     */
    public Employee getEmployeeById(int id) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        
        String role, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        
        double commissionRate;
        
        int _id;
        
        Employee e = null;
        
        query = "SELECT * FROM employees WHERE employee_id=?";
        
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, id);
       
        rs = st.executeQuery();
        
        while(rs.next()){
            _id = rs.getInt(COLUMN_ID);
            role = rs.getString(COLUMN_ROLE);
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            email = rs.getString(COLUMN_EMAIL);
            telephone = rs.getString(COLUMN_TELEPHONE);
            address = rs.getString(COLUMN_ADDRESS);
            town = rs.getString(COLUMN_TOWN);
            city = rs.getString(COLUMN_CITY);
            country = rs.getString(COLUMN_COUNTRY);
            postCode = rs.getString(COLUMN_POST_CODE);
            
            e = new Employee(role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
            e.setId(_id);
        }
        
        return e;
    }

    /**
     * Insert an employee into the database
     * @param e
     * @return
     * @throws SQLException 
     */
    public int addEmployee(Employee e) throws SQLException {
        String query;
        PreparedStatement st;

        String role, firstName, lastName, email, telephone,
                address, town, city, country, postCode;

        double commissionRate;
        
        int rows;

        role = e.getRole();
        commissionRate = e.getCommissionRate();
        firstName = e.getFirstName();
        lastName = e.getLastName();
        email = e.getEmail();
        telephone = e.getTelephone();
        address = e.getAddress();
        town = e.getTown();
        city = e.getCity();
        country = e.getCountry();
        postCode = e.getPostCode();

        query = "INSERT INTO employees (role, commission_rate, first_name, last_name, email, telephone, address, town, city, country, post_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        st = this.mConnection.prepareStatement(query);

        st.setString(1, role);
        st.setDouble(2, commissionRate);
        st.setString(3, firstName);
        st.setString(4, lastName);
        st.setString(5, email);
        st.setString(6, telephone);
        st.setString(7, address);
        st.setString(8, town);
        st.setString(9, city);
        st.setString(10, country);
        st.setString(11, postCode);

        rows = st.executeUpdate();

        return rows;
    }

    /**
     * Update an existing employee
     * @param e
     * @return
     * @throws SQLException 
     */
    public int updateEmployee(Employee e) throws SQLException {
        String query;
        PreparedStatement st;

        String role, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        int id, rows;
        
        double commissionRate;

        id = e.getId();
        role = e.getRole();
        commissionRate = e.getCommissionRate();
        firstName = e.getFirstName();
        lastName = e.getLastName();
        email = e.getEmail();
        telephone = e.getTelephone();
        address = e.getAddress();
        town = e.getTown();
        city = e.getCity();
        country = e.getCountry();
        postCode = e.getPostCode();

        query = "UPDATE customers SET role=?, commission_rate=?, first_name=?, last_name=?, email=?, telephone=?, address=?, town=?, city=?, country=?, post_code=? WHERE employee_id=?";

        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, role);
        st.setDouble(2, commissionRate);
        st.setString(3, firstName);
        st.setString(4, lastName);
        st.setString(5, email);
        st.setString(6, telephone);
        st.setString(7, address);
        st.setString(8, town);
        st.setString(9, city);
        st.setString(10, country);
        st.setString(11, postCode);
        st.setInt(12, id);

        rows = st.executeUpdate();

        return rows;
    }

    /**
     * Delete an employee with a given id
     * @param id
     * @return
     * @throws SQLException 
     */
    public int deleteEmployee(int id) throws SQLException {
        String query;
        PreparedStatement st;

        int rows;

        query = "DELETE FROM employees WHERE employee_id=?";

        st = this.mConnection.prepareStatement(query);

        st.setInt(1, id);

        rows = st.executeUpdate();

        return rows;
    }

    /**
     * Returns an ArrayList of employees with specified role
     * @param role
     * @return
     * @throws SQLException 
     */
    public List<Employee> getEmployeesByRole(String role) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Employee> employees;
        
        String _role, firstName, lastName, email, telephone,
                address, town, city, country, postCode;
        
        double commissionRate;
        
        int id;
        
        Employee e;
        
        query = "SELECT * FROM employees WHERE role=?";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, role);
        
        employees = new ArrayList<Employee>();
        
        rs = st.executeQuery();
        
        while(rs.next()){
            id = rs.getInt(COLUMN_ID);
            _role = rs.getString(COLUMN_ROLE);
            commissionRate = rs.getDouble(COLUMN_COMMISSION_RATE);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            email = rs.getString(COLUMN_EMAIL);
            telephone = rs.getString(COLUMN_TELEPHONE);
            address = rs.getString(COLUMN_ADDRESS);
            town = rs.getString(COLUMN_TOWN);
            city = rs.getString(COLUMN_CITY);
            country = rs.getString(COLUMN_COUNTRY);
            postCode = rs.getString(COLUMN_POST_CODE);
            
            e = new Employee(_role, commissionRate, firstName, lastName, email, telephone, address, town, city, country, postCode);
            
            e.setId(id);            
            employees.add(e);
        }
        
        return employees;
    } 
}
