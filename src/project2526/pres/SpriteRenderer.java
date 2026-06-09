package project2526.pres;

import project2526.game.Board;
import project2526.game.TickEvent;
import project2526.game.TickListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public
    class SpriteRenderer
    extends JPanel
    implements TickListener {

    private final Board board;
    private BufferedImage boatImg;
    private BufferedImage octopusImg;
    private BufferedImage chestImg;
    private BufferedImage diverBoatWithTreasureImg;
    private BufferedImage diverChestImg;
    private BufferedImage diverWaterImg;
    private BufferedImage tentacleImg;
    private BufferedImage plant;

    public SpriteRenderer(Board board) {
        super();
        this.setPreferredSize(new Dimension(500, 500));
        this.board = board;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        try {
            this.octopusImg = ImageIO.read(new File("src/project2526/resources/octopus.png"));
            this.tentacleImg = ImageIO.read(new File("src/project2526/resources/tentacle.png"));
            this.boatImg = ImageIO.read(new File("src/project2526/resources/boat.png"));
            this.chestImg = ImageIO.read(new File("src/project2526/resources/chest.png"));
            this.diverChestImg = ImageIO.read(new File("src/project2526/resources/diver_chest.png"));
            this.diverBoatWithTreasureImg = ImageIO.read(new File("src/project2526/resources/diver_boat_with_treasure.png"));
            this.diverWaterImg = ImageIO.read(new File("src/project2526/resources/diver_water.png"));
            this.plant = ImageIO.read(new File("src/project2526/resources/plant.png"));
        } catch (IOException _) {}
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        int cellSize = this.getWidth() / this.board.getPlansza().length;

        g2d.drawImage(this.plant, 0, cellSize * 5, cellSize, cellSize, null);
        g2d.drawImage(this.plant, cellSize * 5, cellSize * 5, cellSize, cellSize, null);
        g2d.drawImage(this.plant, cellSize * 5, cellSize * 4, cellSize, cellSize, null);
        g2d.drawImage(this.plant, cellSize * 4, cellSize * 5, cellSize, cellSize, null);

        g2d.drawImage(this.boatImg, 0, 0, cellSize * 2, cellSize, null);
        g2d.drawImage(this.chestImg, cellSize * 4, cellSize * 4, cellSize, cellSize, null);
        g2d.drawImage(this.octopusImg, (int)(cellSize * 1.5), 0, cellSize * 6, cellSize * 4, null);

        int[][] plansza = this.board.getPlansza();
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza[i].length; j++) {
                if (plansza[i][j] == 1) {
                    g2d.drawImage(this.tentacleImg, cellSize * j, cellSize * i, cellSize, cellSize, null);
                }
            }
        }

        int[] currentPosition = this.board.getCurrentPosition();
        int currentY = currentPosition[0];
        int currentX = currentPosition[1];
        boolean isTreasureTaken = this.board.isTreasureTaken();

        if (isTreasureTaken) {
            if (currentX == 0) {
                g2d.drawImage(this.diverBoatWithTreasureImg, cellSize * currentX, cellSize * currentY, cellSize, cellSize, null);
            } else {
                g2d.drawImage(this.diverWaterImg, cellSize * currentX, cellSize * currentY, cellSize, cellSize, null);
            }
        } else {
            if (currentX == Board.PATH_COORDINATES[Board.END][0] && currentY == Board.PATH_COORDINATES[Board.END][1]) {
                g2d.drawImage(this.diverChestImg, cellSize * currentX, cellSize * currentY, cellSize, cellSize, null);
            } else {
                g2d.drawImage(this.diverWaterImg, cellSize * currentX, cellSize * currentY, cellSize, cellSize, null);
            }
        }
    }

    @Override
    public void fireOnTick(TickEvent e) {
        this.repaint();
    }
}