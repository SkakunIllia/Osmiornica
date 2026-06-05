package project2526.pres;

import project2526.game.Board;
import project2526.game.GameThread;
import project2526.game.PlusOneEvent;
import project2526.game.StartEvent;

import javax.swing.*;
import java.awt.*;

public
    class GameWindow
    extends JFrame {

    public GameWindow() {
        JPanel panel = new JPanel(new BorderLayout());

        SevenSegmentDigit ones = new SevenSegmentDigit();
        SevenSegmentDigit tens = new SevenSegmentDigit();
        SevenSegmentDigit hundreds = new SevenSegmentDigit();

        Board board = new Board();
        this.addKeyListener(board);

        GameThread thread = GameThread.getThread();

        hundreds.addScoreListener(thread);

        board.addScoreListener(ones);
        board.addScoreListener(thread);

        thread.addTickListener(board);

        ones.addScoreListener(tens);
        tens.addScoreListener(hundreds);

        JPanel pointsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pointsPanel.add(hundreds);
        pointsPanel.add(tens);
        pointsPanel.add(ones);

        panel.add(pointsPanel, BorderLayout.PAGE_END);
        this.add(panel);

        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
