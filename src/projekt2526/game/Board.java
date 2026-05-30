package projekt2526.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public
    class Board
    implements KeyListener {

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

    }
}
