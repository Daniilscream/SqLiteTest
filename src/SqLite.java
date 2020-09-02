import java.sql.*;

public class SqLite {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet res = null;
        try{
            Driver d = (Driver) Class.forName("org.sqlite.JDBC").newInstance(); //org.sqlite.JDBC
            String url = "jdbc:sqlite:bd\\CarShop.db";
            con = DriverManager.getConnection(url);

            String sql = "SELECT * FROM spr_Model";
            stmt = con.createStatement();

            res = stmt.executeQuery(sql);

            while (res.next()){
                System.out.println(res.getString("name_en")+ " - "+ res.getObject("name_ru"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(res!=null)res.close();
                if(stmt!=null)stmt.close();
                if(con!=null)con.close();
            } catch (Exception e){}
        }
    }
}
