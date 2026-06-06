package project2526.game;

import java.util.ArrayList;
import java.util.List;

public
    class GameThread
    extends Thread
    implements ScoreListener {

    private static GameThread THREAD;
    private boolean isRunning = false;
    private final List<TickListener> listeners;
    private int timeInterval;
    private int currentPoints;

    private GameThread() {
        super();
        this.listeners = new ArrayList<>();
        this.timeInterval = 1_000;
        this.currentPoints = 0;
    }

    public static synchronized GameThread getThread() {
        if (GameThread.THREAD == null) {
            GameThread.THREAD = new GameThread();
        }

        return GameThread.THREAD;
    }

    public synchronized void addTickListener(TickListener l) {
        this.listeners.add(l);
    }

    public synchronized void removeTickListener(TickListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void run() {
        this.isRunning = true;
        TickEvent event = new TickEvent(this);
        while (!this.isInterrupted()) {
            if (this.currentPoints > 999) {
                this.stopGame();
            }
            if (this.isRunning) {
                for (TickListener l: this.listeners) {
                    l.fireOnTick(event);
                }

                try {
                    Thread.sleep(this.timeInterval);
                } catch (InterruptedException e) {
                    this.interrupt();
                }

                this.timeInterval = Math.max(500, this.timeInterval - 50);

            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    this.interrupt();
                }
            }
        }
    }

    public synchronized void resumeThread() {
        this.isRunning = true;
        this.currentPoints = 0;
        this.timeInterval = 1_000;
        this.notify();
    }

    public synchronized void stopGame() {
        this.isRunning = false;
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.currentPoints = 0;
        this.isRunning = true;
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        this.currentPoints++;
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        this.currentPoints = 0;
        this.isRunning = false;
        this.timeInterval = 1_000;
    }
}