package project2526.pres;

import project2526.game.PlusOneEvent;
import project2526.game.ResetEvent;
import project2526.game.ScoreListener;
import project2526.game.StartEvent;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public
    class SevenSegmentDigit
    extends JTable
    implements ScoreListener {

    private final List<ScoreListener> listeners;
    private final DigitTableModel model;
    private int digitValue;

    public SevenSegmentDigit() {
        super();
        this.digitValue = DigitTableModel.ZERO;
        this.listeners = new ArrayList<>();

        this.model = new DigitTableModel();
        this.setModel(this.model);
        this.setDefaultRenderer(Boolean.class, new DigitCellRenderer());

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

    public void addScoreListener(ScoreListener l) {
        this.listeners.add(l);
    }

    public void removeScoreListener(ScoreListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.digitValue = DigitTableModel.ZERO;
        StartEvent event = new StartEvent(this);
        for (ScoreListener l : this.listeners) {
            l.fireOnStartEvent(event);
        }
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        if (++this.digitValue > DigitTableModel.NINE) {
            PlusOneEvent event = new PlusOneEvent(this);
            for (ScoreListener l: this.listeners) {
                l.fireOnPlusOneEvent(event);
            }
            this.digitValue = DigitTableModel.ZERO;
        }
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        this.digitValue = DigitTableModel.ZERO;
        ResetEvent event = new ResetEvent(this);
        for (ScoreListener l: this.listeners) {
            l.fireOnResetEvent(event);
        }
        this.model.updateNumber(DigitTableModel.ZERO);
    }
}