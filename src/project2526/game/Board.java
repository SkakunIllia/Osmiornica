package project2526.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public
    class Board
    implements KeyListener, TickListener, ScoreListener {

    private final int[][] plansza;
    private static final int[][] PATH_COORDINATES = {
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

    private final static int BEGINNING = 0;
    private final static int END = Board.PATH_COORDINATES.length - 1;

    private int currentPosition;
    private int movingPositionBuffer;
    private int lives;

    private boolean isTreasureTaken;

    private final List<ScoreListener> listeners;

    public Board() {
        this.currentPosition = Board.BEGINNING;
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

        this.listeners = new ArrayList<>();
    }

    public void addScoreListener(ScoreListener l) {
        this.listeners.add(l);
    }

    public void removeScoreListener(ScoreListener l) {
        this.listeners.remove(l);
    }

    public int[][] getPlansza() {
        return this.plansza;
    }

    public boolean isTreasureTaken() {
        return this.isTreasureTaken;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
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
        this.checkTreasure();
        this.updateTentacles();
        this.checkCollision();
    }

    private void updateTentacles() {
        for (int i = 1; i < this.plansza.length; i++) {
            int headIndex = this.plansza[i].length;
            for (int j = 0; j < this.plansza[i].length; j++) {
                if (this.plansza[i][j] == 1) {
                    headIndex = j;
                    break;
                }
            }
            boolean extend = Math.random() > 0.5;

            if (extend) {
                if (headIndex > 0) {
                    this.plansza[i][headIndex - 1] = 1;
                }
            } else {
                if (headIndex < this.plansza[i].length) {
                    this.plansza[i][headIndex] = 0;
                }
            }
        }
    }

    private void checkCollision() {
        int row = PATH_COORDINATES[this.currentPosition][0];
        int col = PATH_COORDINATES[this.currentPosition][1];
        if (row > 0 && this.plansza[row][col] != 0) {
            this.lives--;
            this.isTreasureTaken = false;
            this.currentPosition = Board.BEGINNING;
            if (this.lives <= 0) {
                this.fireOnResetEvent(new ResetEvent(this));
            }
        }
    }

    private void checkTreasure() {
        if (!this.isTreasureTaken && this.currentPosition == Board.END) {
            this.isTreasureTaken = true;
            this.fireOnPlusOneEvent(new PlusOneEvent(this));
        } else if (this.isTreasureTaken && this.currentPosition == Board.BEGINNING) {
            for (int i = 0; i < 3; i++) {
                this.fireOnPlusOneEvent(new PlusOneEvent(this));
            }
            this.isTreasureTaken = false;
        }
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

        this.currentPosition = Math.max(Math.min(Board.END, this.currentPosition), Board.BEGINNING);
    }

    @Override
    public void fireOnStartEvent(StartEvent e) {
        this.currentPosition = 0;
        this.movingPositionBuffer = 0;
        this.lives = 3;

        this.isTreasureTaken = false;

        for (ScoreListener l: this.listeners) {
            l.fireOnStartEvent(new StartEvent(this));
        }
    }

    @Override
    public void fireOnPlusOneEvent(PlusOneEvent e) {
        for (ScoreListener l: this.listeners) {
            l.fireOnPlusOneEvent(new PlusOneEvent(this));
        }
    }

    @Override
    public void fireOnResetEvent(ResetEvent e) {
        for (ScoreListener l: this.listeners) {
            l.fireOnResetEvent(new ResetEvent(this));
        }
    }
}