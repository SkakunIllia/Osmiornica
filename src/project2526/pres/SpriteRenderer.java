package project2526.pres;

import project2526.game.Board;
import project2526.game.TickEvent;
import project2526.game.TickListener;

import javax.swing.*;
import java.awt.*;

public
    class SpriteRenderer
    extends JPanel
    implements TickListener {

    private final Board board;
    private final Image boatImg;
    private final Image octopusImg;
    private final Image chestImg;
    private final Image diverBoatWithTreasureImg;
    private final Image diverBoatWithoutTreasureImg;
    private final Image diverChestImg;
    private final Image diverLeftImg;
    private final Image diverRightImg;
    private final Image diverWaterImg;
    private final Image tentacleImg;

    public SpriteRenderer(Board board) {
        super();
        this.board = board;
        this.setBackground(Color.WHITE);

        this.octopusImg = new ImageIcon("src/project2526/resources/octopus.png").getImage();
        this.tentacleImg = new ImageIcon("src/project2526/resources/tentacle.png").getImage();
        this.boatImg = new ImageIcon("src/project2526/resources/boat.png").getImage();
        this.chestImg = new ImageIcon("src/project2526/resources/chest.png").getImage();
        this.diverChestImg = new ImageIcon("src/project2526/resources/diver_chest.png").getImage();
        this.diverBoatWithTreasureImg = new ImageIcon("src/project2526/resources/diver_boat_with_treasure.png").getImage();
        this.diverBoatWithoutTreasureImg = new ImageIcon("src/project2526/resources/diver_boat_without_treasure.png").getImage();
        this.diverLeftImg = new ImageIcon("src/project2526/resources/diver_left.png").getImage();
        this.diverRightImg = new ImageIcon("src/project2526/resources/diver_right.png").getImage();
        this.diverWaterImg = new ImageIcon("src/project2526/resources/diver_water.png").getImage();
    }

    @Override
    public void fireOnTick(TickEvent e) {
        this.repaint();
    }
}