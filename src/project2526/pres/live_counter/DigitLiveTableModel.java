package project2526.pres.live_counter;

import javax.swing.table.AbstractTableModel;

public
    class DigitLiveTableModel
    extends AbstractTableModel {

    private static final boolean[][][] DIGITS = {
        {
            {true,  true,  true},
            {true,  false, true},
            {true,  false, true},
            {true,  false, true},
            {true,  true,  true}
        },
        {
            {false, false, true},
            {false, false, true},
            {false, false, true},
            {false, false, true},
            {false, false, true}
        },
        {
            {true, true, true},
            {false, false, true},
            {true, true, true},
            {true, false, false},
            {true, true, true}
        },
        {
            {true, true, true},
            {false, false, true},
            {true, true, true},
            {false, false, true},
            {true, true, true}
        },
        {
            {false, false, false},
            {false, false, false},
            {false, false, false},
            {false, false, false},
            {false, false, false}
        }
    };

    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int EMPTY = 4;

    private boolean[][] currentDigit;

    public DigitLiveTableModel() {
        super();
        this.currentDigit = DigitLiveTableModel.DIGITS[0];
    }

    public void updateNumber(int newNumber) {
        this.currentDigit = DigitLiveTableModel.DIGITS[newNumber];
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return DigitLiveTableModel.DIGITS[0].length;
    }

    @Override
    public int getColumnCount() {
        return DigitLiveTableModel.DIGITS[0][0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.currentDigit[rowIndex][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Boolean.class;
    }
}