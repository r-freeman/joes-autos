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
 * Contains various SQL queries for carrying out CRUD operations on the Sale table
 * @author ryan
 */
public class SaleTableGateway {
    
    private static final String TABLE_NAME = "sales";
    private static final String COLUMN_ID = "sale_id";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_TOTAL = "total";
    private static final String COLUMN_COMMISSION = "commission";
    private static final String COLUMN_EMPLOYEE_ID = "employee_id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_MOTORBIKE_ID = "motorbike_id";
    private static final String COLUMN_SUM_SALES = "sum_sales";
    private static final String COLUMN_NUM_MOTORBIKES_SOLD = "num_motorbikes_sold";
    private static final String COLUMN_SALE_COUNT = "sale_count";
    private static final String COLUMN_TOTAL_SALES_VALUE = "total_sales_value";
    private static final String COLUMN_TOTAL_COMMISSION_EARNED = "total_commision_earned";
    
    private Connection mConnection;
    
    public SaleTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public List<Sale> getSales() throws SQLException {
        
        String query;
        Statement st;
        ResultSet rs;
        
        List<Sale> sales;
        Sale s;
        
        String status;
        
        double total, commission;
        int id, employeeId, motorbikeId;
        
        query = "SELECT * FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        sales = new ArrayList<Sale>();
        
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            employeeId = rs.getInt(COLUMN_EMPLOYEE_ID);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            s = new Sale(status, total, commission, employeeId, motorbikeId);
            s.setId(id);
            sales.add(s);
        }
        
        return sales;
    }
    
    public List<Sale> getSalesByStatus(String status) throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        List<Sale> sales;
        Sale s;
        
        String _status;
        
        double total, commission;
        
        int id, employeeId, motorbikeId;
        
        query = "SELECT * FROM " + TABLE_NAME + " WHERE status='" + status + "'";
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        sales = new ArrayList<Sale>();
        
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            _status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            employeeId = rs.getInt(COLUMN_EMPLOYEE_ID);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            s = new Sale(_status, total, commission, employeeId, motorbikeId);
            s.setId(id);
            sales.add(s);
        }
        
        return sales;
    }
    
    
    public List<Sale> getSalesByEmployeeId(Integer employeeId) throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        List<Sale> sales;
        Sale s;
        
        String status;
        
        double total, commission;
        
        int id, motorbikeId;
        
        query = "SELECT * FROM " + TABLE_NAME + " WHERE employee_id=" + employeeId;
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        sales = new ArrayList<Sale>();
        
        while (rs.next()) {
            id = rs.getInt(COLUMN_ID);
            status = rs.getString(COLUMN_STATUS);
            total = rs.getDouble(COLUMN_TOTAL);
            commission = rs.getDouble(COLUMN_COMMISSION);
            motorbikeId = rs.getInt(COLUMN_MOTORBIKE_ID);
            
            s = new Sale(status, total, commission, employeeId, motorbikeId);
            s.setId(id);
            sales.add(s);
        }
        
        return sales;
    }
    
    public int addSale(Sale sale) throws SQLException {
        String query, status;
        PreparedStatement st;
        
        double total, commission;
        
        int rows, employeeId, motorbikeId;
        
        status = sale.getStatus();
        total = sale.getTotal();
        commission = sale.getCommission();
        employeeId = sale.getEmployeeId();
        motorbikeId = sale.getMotorbikeId();
        
        query = "INSERT INTO sales (status, total, commission, employee_id, motorbike_id) VALUES (?, ?, ?, ?, ?)";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, status);
        st.setDouble(2, total);
        st.setDouble(3, commission);
        st.setInt(4, employeeId);
        st.setInt(5, motorbikeId);
        
        rows = st.executeUpdate();
        
        return rows;
    }
    
    public int deleteSale(int id) throws SQLException {
        String query;
        PreparedStatement st;
        
        int rows;
        
        query = "DELETE FROM sales WHERE sale_id=?";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setInt(1, id);
        
        rows = st.executeUpdate();
        
        return rows;
    }
    
    public double getTotalSumSales() throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        double sumSales = 0;
        
        query = "SELECT SUM(total) AS " + COLUMN_SUM_SALES + " FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        while (rs.next()) {
            sumSales = rs.getDouble(COLUMN_SUM_SALES);
        }
        
        return sumSales;
    }
        
    public Integer getNumMotorbikesSold() throws SQLException {
        String query;
        Statement st;
        ResultSet rs;
        
        Integer numMotorbikes = 0;
        
        query = "SELECT COUNT(sale_id) AS " + COLUMN_NUM_MOTORBIKES_SOLD + " FROM " + TABLE_NAME + " WHERE NOT status='void'";
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        while (rs.next()) {
            numMotorbikes = rs.getInt(COLUMN_NUM_MOTORBIKES_SOLD);
        }
        
        return numMotorbikes;
    }
    
    public List<TopSalesman> getTopSalesmen() throws SQLException {
        String query, firstName, lastName;
        Statement st;
        ResultSet rs;
        
        Integer employeeId, saleCount;
        double totalSalesValue, totalCommissionEarned;
        
        List<TopSalesman> topSalesmen;
        TopSalesman t;
        
        topSalesmen = new ArrayList<TopSalesman>();
        
        query = "SELECT e.employee_id, e.first_name, e.last_name, COUNT(s.sale_id)"
                + " as " + COLUMN_SALE_COUNT + ", SUM(s.total) as " 
                + COLUMN_TOTAL_SALES_VALUE + ", SUM(s.commission) as " + COLUMN_TOTAL_COMMISSION_EARNED
                + " FROM employees e "
                + "INNER JOIN " + TABLE_NAME +  " s "
                + "ON e.employee_id = s.employee_id "
                + "WHERE NOT s.status = 'void' "
                + "GROUP BY e.employee_id";
        
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        while(rs.next()) {
            employeeId = rs.getInt(COLUMN_EMPLOYEE_ID);
            firstName = rs.getString(COLUMN_FIRST_NAME);
            lastName = rs.getString(COLUMN_LAST_NAME);
            saleCount = rs.getInt(COLUMN_SALE_COUNT);
            totalSalesValue = rs.getDouble(COLUMN_TOTAL_SALES_VALUE);
            totalCommissionEarned = rs.getDouble(COLUMN_TOTAL_COMMISSION_EARNED);
            
            t = new TopSalesman(saleCount, totalSalesValue, totalCommissionEarned, "", 0.0, firstName, 
                lastName, "", "", "", "", "", "", "");
            t.setId(employeeId);
            topSalesmen.add(t);
        }
               
        return topSalesmen;
    }
}
