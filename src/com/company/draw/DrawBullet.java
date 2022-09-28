package com.company.draw;

import com.company.model.Bullet;
import com.company.model.Tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DrawBullet extends Canvas implements Runnable {
    private Image bullet;
    private JFrame frame;
    private int height = 30;
    private int width = 30;
    private Tank tank1;
    private Tank tank2;
    private Bullet bullet1;
    private List<Map<String, Integer>> wallsLocation;
    private JLabel bulletLabel;
    private DrawWinner drawWinner;
    private int speed = 100;

    public DrawBullet(JFrame jFrame, Tank tank1, Tank tank2) {
        bullet1 = new Bullet(tank1.getLocationX(), tank1.getLocationY(), tank1.getDirection(), 20);
        frame = jFrame;
        this.tank1 = tank1;
        this.tank2 = tank2;
    }

    public void fire() {
        BufferedImage bullet_img = null;

        if (bullet1.getDirection().equals("r")) {
            try {
                bullet_img = ImageIO.read(new File("src/resourses/images/bullet-texture-right.png"));
                bullet = bullet_img.getScaledInstance(6, 6, Image.SCALE_SMOOTH);
                bulletLabel = new JLabel();
                bulletLabel.setIcon(new ImageIcon(bullet));
                frame.add(bulletLabel, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveRight();
        } else if (bullet1.getDirection().equals("l")) {
            try {
                bullet_img = ImageIO.read(new File("src/resourses/images/bullet-texture-left.png"));
                bullet = bullet_img.getScaledInstance(6, 6, Image.SCALE_SMOOTH);
                bulletLabel  = new JLabel();
                bulletLabel.setIcon(new ImageIcon(bullet));
                bulletLabel.setHorizontalAlignment(JLabel.RIGHT);
                frame.add(bulletLabel, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveLeft();
        } else if (bullet1.getDirection().equals("t")) {
            try {
                bullet_img = ImageIO.read(new File("src/resourses/images/bullet-texture-top.png"));
                bullet = bullet_img.getScaledInstance(6, 6, Image.SCALE_SMOOTH);
                bulletLabel  = new JLabel();
                bulletLabel.setIcon(new ImageIcon(bullet));
                bulletLabel.setVerticalAlignment(JLabel.BOTTOM);
                bulletLabel.setHorizontalAlignment(JLabel.CENTER);
                frame.add(bulletLabel, 0);
                bullet1.setLocationX(bullet1.getLocationX() - 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveTop();
        } else if (bullet1.getDirection().equals("b")) {
            try {
                bullet_img = ImageIO.read(new File("src/resourses/images/bullet-texture-bottom.png"));
                bullet = bullet_img.getScaledInstance(6, 6, Image.SCALE_SMOOTH);
                bulletLabel  = new JLabel();
                bulletLabel.setIcon(new ImageIcon(bullet));
                bulletLabel.setVerticalAlignment(JLabel.TOP);
                bulletLabel.setHorizontalAlignment(JLabel.CENTER);
                frame.add(bulletLabel, 0);
                bullet1.setLocationX(bullet1.getLocationX() - 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            moveBottom();
        }
    }

    private synchronized void moveRight() {
        if (connectToTheTank(bullet1.getLocationX() + 30, bullet1.getLocationY())) {
        } else if (checkWall(bullet1.getLocationX() + 30, bullet1.getLocationY())) {
            bulletLabel.setBounds(bullet1.getLocationX() + 30, bullet1.getLocationY(), height, width);
            bullet1.setLocationX(bullet1.getLocationX() + 30);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveRight();
        } else {
            bulletLabel.setVisible(false);
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
        }
    }

    private synchronized void moveLeft() {
        if (connectToTheTank(bullet1.getLocationX() - 30, bullet1.getLocationY())) {
        } else if (checkWall(bullet1.getLocationX() - 30, bullet1.getLocationY())) {
            bulletLabel.setBounds(bullet1.getLocationX() - 30, bullet1.getLocationY(), height, width);
            bullet1.setLocationX(bullet1.getLocationX() - 30);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveLeft();
        } else {
            bulletLabel.setVisible(false);
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
        }
    }

    private synchronized void moveTop() {
        if (connectToTheTank(bullet1.getLocationX(), bullet1.getLocationY() - 30)) {
        } else if (checkWall(bullet1.getLocationX(), bullet1.getLocationY() - 30)) {
            bulletLabel.setBounds(bullet1.getLocationX(), bullet1.getLocationY() - 30, height, width);
            bullet1.setLocationY(bullet1.getLocationY() - 30);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveTop();
        } else {
            bulletLabel.setVisible(false);
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
        }
    }

    private synchronized void moveBottom() {
        if (connectToTheTank(bullet1.getLocationX(), bullet1.getLocationY() + 30)) {
        } else if (checkWall(bullet1.getLocationX(), bullet1.getLocationY() + 30)) {
            bulletLabel.setBounds(bullet1.getLocationX(), bullet1.getLocationY() + 30, height, width);
            bullet1.setLocationY(bullet1.getLocationY() + 30);
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            moveBottom();
        } else {
            bulletLabel.setVisible(false);
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
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

    public boolean connectToTheTank(int x, int y) {
        if (((x == tank2.getLocationX()) && (y == tank2.getLocationY())) || ((x + 5 == tank2.getLocationX()) && (y == tank2.getLocationY()))) {
            tank2.getDamage(bullet1.getDamage());
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
            bulletLabel.setVisible(false);
            if (tank2.getHP() <= 0) {
                if (tank2.getTankNumber() == 1) {
                    drawWinner.drawWinTwo();
                    System.out.println("Второй игрок победил!");
                } else if (tank2.getTankNumber() == 2) {
                    drawWinner.drawWinOne();
                    System.out.println("Первый игрок победил!");
                }
                return true;
            }
        } else if (((x == tank1.getLocationX()) && (y == tank1.getLocationY())) || ((x + 5 == tank1.getLocationX()) && (y == tank1.getLocationY()))) {
            tank1.getDamage(bullet1.getDamage());
            bulletLabel.setVisible(false);
            tank1.getBullets().remove(this);
            tank2.getBullets().remove(this);
            if (tank1.getHP() <= 0) {
                if (tank1.getTankNumber() == 1) {
                    drawWinner.drawWinTwo();
                    System.out.println("Второй игрок победил!");
                } else if (tank1.getTankNumber() == 2) {
                    drawWinner.drawWinOne();
                    System.out.println("Первый игрок победил!");
                }
                return true;
            }
        }

        return false;
    }

    @Override
    public void run() {
        fire();
    }

    public Bullet getBullet() {
        return bullet1;
    }

    public void setDrawWinner(DrawWinner drawWinner) {
        this.drawWinner = drawWinner;
    }
}
