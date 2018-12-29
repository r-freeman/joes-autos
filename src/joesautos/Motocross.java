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
public class Motocross extends Motorbike{
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
    Motocross(String make, String model, String engine, String frame, String colour, int year, double price, int stock) {
        super(make, model, engine, frame, colour, year, price, stock);
    }   
}
