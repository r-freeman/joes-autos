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
 * Contains various SQL queries for carrying out CRUD operations on the motorbike table
 * @author ryan
 */
public class MotorbikeTableGateway {
    private static final String TABLE_NAME = "motorbikes";
    private static final String COLUMN_ID = "motorbike_id";
    private static final String COLUMN_MAKE = "make";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_ENGINE = "engine";
    private static final String COLUMN_TYPE = "frame";
    private static final String COLUMN_COLOUR = "colour";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_STOCK = "stock";
    
    private Connection mConnection;
    
    public MotorbikeTableGateway(Connection connection) {
        mConnection = connection;
    }
    
    public List<Motorbike> getMotorbikes() throws SQLException {
        
        String query;
        Statement st;
        ResultSet rs;
        List<Motorbike> motorbikes;
        
        String make, model, engine, frame, colour;
        int id, year, stock;
        double price;
        
        Motorbike m;
        
        query = "SELECT * FROM " + TABLE_NAME;
        st = this.mConnection.createStatement();
        
        rs = st.executeQuery(query);
        
        motorbikes = new ArrayList<Motorbike>();
        
        while(rs.next()){
            id = rs.getInt(COLUMN_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            frame = rs.getString(COLUMN_TYPE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getFloat(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            
            m = new Motorbike(make, model, engine, frame, colour, year, price, stock);
            m.setId(id);
            motorbikes.add(m);
        }
        
        return motorbikes;
    }
    
    public Motorbike getMotorbikeById(int id) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        
        String make, model, engine, frame, colour;
        int _id, year, stock;
        double price;
        
        Motorbike m = null;
        
        query = "SELECT * FROM motorbikes WHERE motorbike_id=?";
        
        st = this.mConnection.prepareStatement(query);
        st.setInt(1, id);
        
        rs = st.executeQuery();
        
        while(rs.next()){
            _id = rs.getInt(COLUMN_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            frame = rs.getString(COLUMN_TYPE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getFloat(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            
            m = new Motorbike(make, model, engine, frame, colour, year, price, stock);
            m.setId(_id);
        }
        
        return m;
    }
    
    public int addMotorbike(Motorbike m) throws SQLException {
        String query;
        PreparedStatement st;
        
        String make, model, engine, frame, colour;
        int year, stock, rows;
        double price;
        
        make = m.getMake();
        model = m.getModel();
        engine = m.getEngine();
        frame = m.getFrame();
        colour = m.getColour();
        year = m.getYear();
        price = m.getPrice();
        stock = m.getStock();
        
        query = "INSERT INTO motorbikes (make, model, engine, frame, colour, year, price, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, make);
        st.setString(2, model);
        st.setString(3, engine);
        st.setString(4, frame);
        st.setString(5, colour);
        st.setInt(6, year);
        st.setDouble(7, price);
        st.setInt(8, stock);
        
        rows = st.executeUpdate();
        
        return rows;
    }
    
    public int updateMotorbike(Motorbike m) throws SQLException {
        String query;
        PreparedStatement st;
        
        String make, model, engine, frame, colour;
        int id, year, stock, rows;
        double price;
        
        id = m.getId();
        make = m.getMake();
        model = m.getModel();
        engine = m.getEngine();
        frame = m.getFrame();
        colour = m.getColour();
        year = m.getYear();
        price = m.getPrice();
        stock = m.getStock();
        
        query = "UPDATE motorbikes SET make=?, model=?, engine=?, frame=?, colour=?, year=?, price=?, stock=? WHERE motorbike_id=?";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, make);
        st.setString(2, model);
        st.setString(3, engine);
        st.setString(4, frame);
        st.setString(5, colour);
        st.setInt(6, year);
        st.setDouble(7, price);
        st.setInt(8, stock);
        st.setInt(9, id);
        
        rows = st.executeUpdate();
        
        return rows;
    }
    
    public int deleteMotorbike(int id) throws SQLException {
        String query;
        PreparedStatement st;
        
        int rows;
        
        query = "DELETE FROM motorbikes WHERE motorbike_id=?";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setInt(1, id);
        
        rows = st.executeUpdate();
        
        return rows;
    }
    
    public List<Motorbike> getMotorbikesByFrame(String frame) throws SQLException {
        String query;
        PreparedStatement st;
        ResultSet rs;
        List<Motorbike> motorbikes;
        
        String make, model, engine, _frame, colour;
        int id, year, stock;
        double price;
        
        Motorbike m;
        
        query = "SELECT * FROM motorbikes WHERE frame=?";
        
        st = this.mConnection.prepareStatement(query);
        
        st.setString(1, frame);
        
        motorbikes = new ArrayList<Motorbike>();
        
        rs = st.executeQuery();
        
        while(rs.next()){
            id = rs.getInt(COLUMN_ID);
            make = rs.getString(COLUMN_MAKE);
            model = rs.getString(COLUMN_MODEL);
            engine = rs.getString(COLUMN_ENGINE);
            _frame = rs.getString(COLUMN_TYPE);
            colour = rs.getString(COLUMN_COLOUR);
            year = rs.getInt(COLUMN_YEAR);
            price = rs.getDouble(COLUMN_PRICE);
            stock = rs.getInt(COLUMN_STOCK);
            
            m = new Motorbike(make, model, engine, _frame, colour, year, price, stock);
            m.setId(id);
            motorbikes.add(m);
        }
        
        return motorbikes;
    }
}
