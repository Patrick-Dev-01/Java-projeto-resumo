package patrickdev01.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    String url;
    String user;
    String password;

    public Database(){
        this.url = "jdbc:mysql://localhost:3306/Bank";
        this.user = "root";
        this.password = "";
    }
    public Connection connect(){
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(this.url, this.user, this.password);
        }

        catch(SQLException ex){
            System.out.println("Error ocurred while to connect database: " + ex);
            ex.printStackTrace();
        }

        return connection;
    }
}
