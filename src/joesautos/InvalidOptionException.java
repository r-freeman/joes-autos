/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package joesautos;

/**
 * Exception is thrown when the user enters an invalid option in the menu
 * @author ryan
 */
public class InvalidOptionException extends Exception{

    public InvalidOptionException() {
    }

    public InvalidOptionException(String message) {
        super(message);
    }

    public InvalidOptionException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidOptionException(Throwable cause) {
        super(cause);
    }

    public InvalidOptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
