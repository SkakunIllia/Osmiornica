package project2526.pres.live_counter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public
    class DigitLiveCellRenderer
    extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        this.setText("");
        if (value != null) {
            boolean isTurnedOn = (Boolean) value;
            this.setBackground(isTurnedOn ? Color.RED : Color.BLACK);
        }
        return this;
    }
}