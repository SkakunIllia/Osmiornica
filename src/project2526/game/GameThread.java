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

    private GameThread() {
        super();
        this.listeners = new ArrayList<>();
        this.timeInterval = 1_000;
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

    public synchronized void speedUp() {
        this.timeInterval = Math.max(500, this.timeInterval - 50);
    }

    @Override
    public void run() {
        this.isRunning = true;
        while (!this.isInterrupted()) {
            synchronized (this) {
                while (!this.isRunning) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        this.interrupt();
                    }
                }
            }
            for (TickListener l: this.listeners) {
                l.fireOnTick(new TickEvent(this));
            }
            try {
                Thread.sleep(this.timeInterval);
            } catch (InterruptedException e) {
                this.interrupt();
            }
        }
    }

    public synchronized void resumeThread() {
        this.isRunning = true;
        this.timeInterval = 1_000;
        this.notify();
    }

    @Override
    public synchronized void fireOnStartEvent(StartEvent e) {
        this.isRunning = true;
        this.timeInterval = 1_000;
        this.notify();
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        this.isRunning = false;
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        this.isRunning = false;
        this.timeInterval = 1_000;
    }
}