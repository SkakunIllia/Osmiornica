package project2526.pres;

import javax.swing.table.AbstractTableModel;

public
    class DigitTableModel
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
            {true, false, true},
            {true, false, true},
            {true, true, true},
            {false, false, true},
            {false, false, true}
        },
        {
            {true, true, true},
            {true, false, false},
            {true, true, true},
            {false, false, true},
            {true, true, true}
        },
        {
            {true, true, true},
            {true, false, true},
            {true, true, true},
            {true, false, true},
            {true, true, true}
        },
        {
            {true, true, true},
            {true, false, true},
            {false, false, true},
            {false, false, true},
            {false, false, true}
        },
        {
            {true, true, true},
            {true, false, true},
            {true, true, true},
            {true, false, true},
            {true, true, true}
        },
        {
            {true, true, true},
            {true, false, true},
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
    public static final int EMPTY = 10;
    private boolean[][] currentDigit;

    public DigitTableModel() {
        super();
        this.currentDigit = DigitTableModel.DIGITS[0];
    }

    public void updateNumber(int newNumber) {
        this.currentDigit = DigitTableModel.DIGITS[newNumber];
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return DigitTableModel.DIGITS[0].length;
    }

    @Override
    public int getColumnCount() {
        return DigitTableModel.DIGITS[0][0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.currentDigit[rowIndex][columnIndex];
    }
}