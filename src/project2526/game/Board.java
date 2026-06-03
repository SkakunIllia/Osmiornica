package project2526.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public
    class Board
    implements KeyListener, TickListener, ScoreListener {

    private final int[][] plansza;
    private final int[][] pathCoordinates;
    private int currentPosition;
    private int movingPositionBuffer;
    private int lives;
    private boolean isTreasureTaken;

    public Board() {
        this.currentPosition = 0;
        this.movingPositionBuffer = 0;
        this.lives = 3;

        this.isTreasureTaken = false;

        this.plansza = new int[][]{
            {0, 0},
            {0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0}
        };

        this.pathCoordinates = new int[][]{
            {0, 0},
            {0, 1},
            {1, 0},
            {2, 0},
            {3, 0},
            {4, 0},
            {5, 0},
            {5, 1},
            {5, 2},
            {4, 3},
            {3, 4},
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_D -> this.movingPositionBuffer = 1;
            case KeyEvent.VK_A -> this.movingPositionBuffer = -1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_S) {
            GameThread thread = GameThread.getThread();
            if (!thread.isAlive()) {
                thread.start();
            } else {
                thread.resumeThread();
            }
        }
    }

    @Override
    public void fireOnTick(TickEvent e) {
        this.updatePosition();
    }

    private void updatePosition() {
        if (this.movingPositionBuffer != 0) {
            if (this.movingPositionBuffer > 0) {
                this.currentPosition++;
                this.movingPositionBuffer--;
            } else {
                this.currentPosition--;
                this.movingPositionBuffer++;
            }
        }

        this.currentPosition = Math.max(Math.min(6, this.currentPosition), 0);
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.currentPosition = 0;
        this.movingPositionBuffer = 0;
        this.lives = 3;

        this.isTreasureTaken = false;
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {

    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {

    }
}
