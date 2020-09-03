import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MyTableRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        if(isSelected){setBackground(Color.ORANGE); setForeground(Color.BLACK);}
        else {setBackground(Color.WHITE); setForeground(Color.BLACK);}
        return this;
    }
}
