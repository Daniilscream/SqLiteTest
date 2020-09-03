import com.sun.source.tree.BreakTree;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLiteConnection {

    private static Connection con;

    public static Connection getConnection() {
        try {
            Driver d = (Driver) Class.forName("org.sqlite.JDBC").newInstance(); //org.sqlite.JDBC
            String url = "jdbc:sqlite:bd\\CarShop.db";
            if(con==null) con = DriverManager.getConnection(url);
            return con;
        } catch (SQLException e){
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE,null,e);
        }
        catch (ClassNotFoundException e){ Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE,null,e);}
        catch (InstantiationException e){ Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE,null,e);}
        catch (IllegalAccessException e){ Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE,null,e);}
        return null;
    }

}
