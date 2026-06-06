package project2526.main;

import project2526.pres.GameWindow;

import javax.swing.*;

public
    class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameWindow::new);
    }
}