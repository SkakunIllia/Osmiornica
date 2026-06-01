package projekt2526.game;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public
    class SevenSegmentDigit
    extends JPanel
    implements ScoreListener {

    private final List<ScoreListener> listeners;

    public SevenSegmentDigit() {
        super();
        this.listeners = new ArrayList<>();
    }

    public void addScoreListener(ScoreListener l) {
        this.listeners.add(l);
    }

    public void removeScoreListener(ScoreListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        for (ScoreListener l: this.listeners) {
            l.fireOnStartEvent(e);
        }
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        for (ScoreListener l: this.listeners) {
            l.fireOnPlusOneEvent(e);
        }
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        for (ScoreListener l: this.listeners) {
            l.fireOnResetEvent(e);
        }
    }
}