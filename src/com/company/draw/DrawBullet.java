package com.company.draw;

import com.company.model.Bullet;
import com.company.model.Tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DrawBullet extends Canvas {
    private Image bullet;
    private JFrame frame;
    private int height = 30;
    private int width = 30;
    private Tank tank1;
    private JLabel tank1Label;
    private Bullet bullet1;
    private List<Map<String, Integer>> wallsLocation;
    private JLabel bulletLabel;

    public DrawBullet(JFrame jFrame, Tank tank1) {
        bullet1 = new Bullet(tank1.getLocationX(), tank1.getLocationY(), tank1.getDirection(), 1);
        frame = jFrame;
        this.tank1 = tank1;
        BufferedImage bullet_img = null;
        try {
            bullet_img = ImageIO.read(new File("src/resourses/images/bullet-texture.png"));
            bullet = bullet_img.getScaledInstance(6, 6, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fire() {
        bulletLabel  = new JLabel();
        bulletLabel.setIcon(new ImageIcon(bullet));
        frame.add(bulletLabel, 0);
        if (bullet1.getDirection().equals("r")) {
            moveRight();
        }
    }

    private synchronized void moveRight() {
        if (checkWall(bullet1.getLocationX() + 30, bullet1.getLocationY())) {
            bulletLabel.setBounds(bullet1.getLocationX() + 30, bullet1.getLocationY(), height, width);
            bullet1.setLocationX(bullet1.getLocationX() + 30);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveRight();
        }
    }


    public void setWallsLocation(List<Map<String, Integer>> wallsLocation) {
        this.wallsLocation = wallsLocation;
    }

    private boolean checkWall(int x, int y) {
        if (x < 0) {
            return false;
        }
        if (y < 0) {
            return false;
        }
        if (y > frame.getHeight() - 60) {
            return false;
        }
        if (x > frame.getWidth() - 30) {
            return false;
        }
        for (Map<String, Integer> i:wallsLocation) {
            if ((x == i.get("x")) && (y == i.get("y"))) {
                return false;
            }
        }
        return true;
    }
}
