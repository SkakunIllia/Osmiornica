package projekt2526.game;

public
    class GameThread
    extends Thread {

    private static GameThread THREAD;
    private boolean isRunning = false;

    private GameThread() {
    }

    public static synchronized  GameThread getThread() {
        if (GameThread.THREAD == null) {
            GameThread.THREAD = new GameThread();
        }

        return GameThread.THREAD;
    }

    @Override
    public void run() {
        this.isRunning = true;
        // ...
    }
}
