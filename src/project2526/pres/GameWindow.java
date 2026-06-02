package project2526.pres;

import project2526.game.PlusOneEvent;
import project2526.game.StartEvent;

import javax.swing.*;
import java.awt.*;

public
    class GameWindow
    extends JFrame {

    public GameWindow() {
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new BorderLayout());

        SevenSegmentDigit ones = new SevenSegmentDigit();
        SevenSegmentDigit tens = new SevenSegmentDigit();
        SevenSegmentDigit hundreds = new SevenSegmentDigit();

        ones.addScoreListener(tens);
        tens.addScoreListener(hundreds);

        JPanel pointsPanel = new JPanel(new FlowLayout());

        pointsPanel.add(hundreds);
        pointsPanel.add(tens);
        pointsPanel.add(ones);

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btn = new JButton("+1");

        btn.addActionListener(e -> ones.fireOnPlusOneEvent(new PlusOneEvent(this)));

        southPanel.add(pointsPanel);
        panel.add(southPanel, BorderLayout.EAST);
        this.add(panel, BorderLayout.PAGE_END);
        this.add(btn, BorderLayout.PAGE_START);

        this.setSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
