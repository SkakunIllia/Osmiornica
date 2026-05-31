package projekt2526.game;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public
    class GameThread
    extends Thread {

    private static GameThread THREAD;
    private boolean isRunning = false;
    private final List<TickListener> listeners;
    private int timeInterval;

    private GameThread() {
        this.listeners = new CopyOnWriteArrayList<>();
        this.timeInterval = 1_000;
    }

    public static synchronized  GameThread getThread() {
        if (GameThread.THREAD == null) {
            GameThread.THREAD = new GameThread();
        }

        return GameThread.THREAD;
    }

    public void addTickListener(TickListener l) {
        this.listeners.add(l);
    }

    public void removeTickListener(TickListener l) {
        this.listeners.remove(l);
    }

    @Override
    public void run() {
        this.isRunning = true;
        TickEvent event = new TickEvent();
        while (!this.isInterrupted()) {
            if (this.isRunning) {
                for (TickListener l: this.listeners) {
                    l.fireOnTick(event);
                }

                try {
                    Thread.sleep(this.timeInterval);
                } catch (InterruptedException e) {
                    this.interrupt();
                }

                this.timeInterval = Math.max(100, this.timeInterval - 50);

            } else {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    this.interrupt();
                }
            }
        }
    }

    public void resumeThread() {
        this.isRunning = true;
    }
}
