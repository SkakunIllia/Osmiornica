package projekt2526.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public
    class Board
    implements KeyListener, TickListener {

    private final int[][] plansza;

    public Board() {
        this.plansza = new int[][]{
            {0, 0},
            {0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0}
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

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

    }
}
