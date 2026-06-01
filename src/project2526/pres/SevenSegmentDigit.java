package project2526.pres;

import project2526.game.PlusOneEvent;
import project2526.game.ResetEvent;
import project2526.game.ScoreListener;
import project2526.game.StartEvent;

import javax.swing.*;
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
        this.digitValue = 0;
        this.listeners = new ArrayList<>();

        this.model = new DigitTableModel();
        this.setModel(this.model);
    }

    public void addScoreListener(ScoreListener l) {
        this.listeners.add(l);
    }

    public void removeScoreListener(ScoreListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.digitValue = 0;
        StartEvent event = new StartEvent(this);
        for (ScoreListener l : this.listeners) {
            l.fireOnStartEvent(event);
        }
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        if (++this.digitValue > 9) {
            PlusOneEvent event = new PlusOneEvent(this);
            for (ScoreListener l: this.listeners) {
                l.fireOnPlusOneEvent(event);
            }
            this.digitValue = 0;
        }
        this.model.updateNumber(this.digitValue);
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        this.digitValue = 0;
        ResetEvent event = new ResetEvent(this);
        for (ScoreListener l: this.listeners) {
            l.fireOnResetEvent(event);
        }
        this.model.updateNumber(DigitTableModel.EMPTY);
    }
}