package project2526.pres;

import project2526.game.Board;
import project2526.game.GameThread;
import project2526.pres.live_counter.SevenSegmentLiveCounter;

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

        SevenSegmentLiveCounter lives = new SevenSegmentLiveCounter();

        Board board = new Board();
        this.addKeyListener(board);

        board.addLiveListener(lives);
        board.addScoreListener(lives);

        GameThread thread = GameThread.getThread();

        hundreds.addScoreListener(thread);

        board.addScoreListener(ones);

        thread.addTickListener(board);

        ones.addScoreListener(tens);
        tens.addScoreListener(hundreds);

        JLabel pointsLabel = new JLabel("Points: ");
        pointsLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel pointsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        pointsPanel.add(pointsLabel);
        pointsPanel.add(hundreds);
        pointsPanel.add(tens);
        pointsPanel.add(ones);

        JPanel livesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel livesLabel = new JLabel("Lives: ");
        livesLabel.setFont(new Font("Arial", Font.BOLD, 20));

        livesPanel.add(livesLabel);
        livesPanel.add(lives);

        JPanel wrapper = new JPanel(new GridBagLayout());
        SpriteRenderer spriteRenderer = new SpriteRenderer(board);

        thread.addTickListener(spriteRenderer);

        wrapper.add(spriteRenderer);

        panel.add(wrapper, BorderLayout.CENTER);
        panel.add(pointsPanel, BorderLayout.PAGE_END);
        panel.add(livesPanel, BorderLayout.LINE_END);
        this.add(panel);

        this.setSize(new Dimension(700, 700));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}