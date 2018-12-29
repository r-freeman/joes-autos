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
public class Motorbike {
    private int id;
    private String make;
    private String model;
    private String engine;
    private String frame;
    private String colour;
    private int year;
    private double price;
    private int stock;
    
    /**
     * constructor with parameters
     * @param make
     * @param model
     * @param engine
     * @param frame
     * @param colour
     * @param year
     * @param price
     * @param stock 
     */
    Motorbike(String make, String model, String engine, String frame, String colour, int year, double price, int stock) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.frame = frame;
        this.colour = colour;
        this.year = year;
        this.price = price;
        this.stock = stock;
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
    
    public String getMake() {
        return make;
    }
    
    public void setMake(String make) {
        this.make = make;
    }
    
    public String getModel() {
        return model;
    }
    
    public void setModel(String model) {
        this.model = model;
    }
    
    public String getEngine() {
        return engine;
    }
    
    public void setEngine(String engine) {
        this.engine = engine;
    }
    
    public String getFrame() {
        return frame;
    }
    
    public void setFrame(String frame) {
        this.frame = frame;
    }
    
    public String getColour() {
        return colour;
    }
    
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getStock() {
        return stock;
    }
    
    public void setStock(int stock) {
        this.stock = stock;
    }
}
