package project2526.pres.live_counter;

import project2526.game.*;

import javax.swing.*;
import java.awt.*;

public
    class SevenSegmentLiveCounter
    extends JTable
    implements ScoreListener, LiveListener {

    private final DigitLiveTableModel model;
    private int digitValue;

    public SevenSegmentLiveCounter() {
        super();
        this.digitValue = DigitLiveTableModel.THREE;

        this.model = new DigitLiveTableModel();
        this.setModel(this.model);
        this.model.updateNumber(this.digitValue);
        this.setDefaultRenderer(Boolean.class, new DigitLiveCellRenderer());

        int rowHeight = 15;
        int rowWidth = 15;
        int intercellSpacingWidth = 0;
        int intercellSpacingHeight = 0;

        this.setShowGrid(false);
        this.setIntercellSpacing(new Dimension(intercellSpacingWidth, intercellSpacingHeight));
        this.setBackground(Color.BLACK);
        this.setEnabled(false);
        this.setRowHeight(rowHeight);
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setPreferredWidth(rowWidth);
        }
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.digitValue = DigitLiveTableModel.THREE;
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        this.digitValue = DigitLiveTableModel.THREE;
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnMinusLive(LiveEvent e) {
        if (this.digitValue > DigitLiveTableModel.ZERO) {
            this.digitValue--;
            this.model.updateNumber(this.digitValue);
        }
    }
}