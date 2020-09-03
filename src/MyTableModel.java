import javax.swing.table.AbstractTableModel;
import java.sql.*;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    private Object[][] contents;
    private String[] columnNames;
    private Class[] columnClasses;

    public MyTableModel(Connection con, String qqq) throws SQLException {
        super();
        getTableContents(con, qqq);
    }

    private void getTableContents(Connection con, String qqq) throws SQLException {
        DatabaseMetaData meta = con.getMetaData();

        ResultSet rs = meta.getColumns(null, null, qqq, null);

        ArrayList colNamesList = new ArrayList();
        ArrayList colTypesList = new ArrayList();

        while (rs.next()) {
            colNamesList.add(rs.getString("COLUMN_NAME"));

            int dbType = rs.getInt("DATA_TYPE");

            switch (dbType) {
                case Types.INTEGER:
                    colTypesList.add(Integer.class);
                    break;
                case Types.FLOAT:
                    colTypesList.add(Float.class);
                    break;
                case Types.DOUBLE:
                case Types.REAL:
                    colTypesList.add(Double.class);
                    break;
                case Types.DATE:
                case Types.TIME:
                case Types.TIMESTAMP:
                    colTypesList.add(java.sql.Date.class);
                    break;
                default:
                    colTypesList.add(String.class);
                    break;
            }
            ;
        }
        columnNames = new String[colNamesList.size()];
        colNamesList.toArray(columnNames);

        columnClasses = new Class[colTypesList.size()];
        colTypesList.toArray(columnClasses);

        Statement statement = con.createStatement();
        rs = statement.executeQuery("SELECT * FROM " + qqq);

        ArrayList rowList = new ArrayList();

        while(rs.next()){
            ArrayList cellList = new ArrayList();
            for (int i = 0; i < columnClasses.length; i++) {
                Object cellValue = null;
                if(columnClasses[i]==String.class) cellValue = rs.getString(columnNames[i]);
                else if(columnClasses[i]==Integer.class) cellValue = new Integer(rs.getInt(columnNames[i]));
                else if(columnClasses[i]==Float.class) cellValue = new Float(rs.getInt(columnNames[i]));
                else if(columnClasses[i]==Double.class) cellValue = new Double(rs.getDouble(columnNames[i]));
                else if(columnClasses[i]==java.sql.Date.class) cellValue = rs.getDate(columnNames[i]);
                else System.out.println("Мозг вскипел, чё за тип...." + columnNames[i]);

                cellList.add(cellValue);
            }

            Object[] cells =cellList.toArray();
            rowList.add(cells);
        }
        contents = new Object[rowList.size()][];
        for (int i = 0; i < contents.length; i++) {
            contents[i] = (Object[]) rowList.get(i);
        }
        rs.close();
        statement.close();
    }

    @Override
    public int getRowCount() {
        return contents.length;
    }

    @Override
    public int getColumnCount() {
        if(contents.length==0)return 0;
        else return contents[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return contents[rowIndex][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
}
