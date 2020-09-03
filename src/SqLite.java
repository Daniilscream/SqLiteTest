import javax.swing.*;
import javax.swing.table.TableModel;
import java.sql.*;

public class SqLite {
    public static void main(String[] args) {
        try{
            Connection con = SQLiteConnection.getConnection();

            TableModel mod = new MyTableModel(con, "CarBrand");

            JTable jTable = new JTable(mod);

            jTable.setDefaultRenderer(Object.class, new MyTableRenderer());
            JScrollPane scroller = new JScrollPane(jTable, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            JFrame frame = new JFrame("Полученные данные");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(scroller);
            frame.pack();
            frame.setVisible(true);

            con.close();
        } catch (Exception e){e.printStackTrace();}
    }
}
