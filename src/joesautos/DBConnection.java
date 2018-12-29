/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package joesautos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ryan
 */
public class DBConnection {
    private static Connection sConnection;
    
    /**
     * Returns an instance of the database connection
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection getInstance() throws ClassNotFoundException, SQLException {
        String host, db, user, password;
        
        host = "192.168.64.2";
        db = "joesAUTOS";
        user = "dbuser";
        password = "dbpassword";
        
        if (sConnection == null || sConnection.isClosed()) {
            String url = "jdbc:mysql://" + host + "/" + db;
            Class.forName("com.mysql.jdbc.Driver");
            sConnection = DriverManager.getConnection(url, user, password);
        }
        
        return sConnection;
    }
}
