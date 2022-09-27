package com.company.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DrawWinner {
    private Image playerOneWin;
    private Image playerTwoWin;
    private JFrame frame;
    private JLabel label;

    public DrawWinner(JFrame frame) {
        BufferedImage player1_img = null;
        BufferedImage player2_img = null;
        this.frame = frame;
        try {
            player1_img = ImageIO.read(new File("src/resourses/images/player-1-win.png"));
            playerOneWin = player1_img.getScaledInstance(400, 350, Image.SCALE_SMOOTH);
            player2_img = ImageIO.read(new File("src/resourses/images/player-2-win.png"));
            playerTwoWin = player2_img.getScaledInstance(400, 350, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        label = new JLabel();
        label.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
    }

    public void drawWinOne() {
        label.setIcon(new ImageIcon(playerOneWin));
        label.setVisible(true);
        frame.add(label, 0);
        SwingUtilities.updateComponentTreeUI(frame);
    }

    public void drawWinTwo() {
        label.setIcon(new ImageIcon(playerTwoWin));
        label.setVisible(true);
        frame.add(label, 0);
        SwingUtilities.updateComponentTreeUI(frame);
    }

}
