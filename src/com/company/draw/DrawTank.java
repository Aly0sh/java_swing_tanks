package com.company.draw;

import com.company.model.Tank;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class DrawTank extends Canvas {
    private Image tank;
    private String RESOURSES;
    private Path path;
    private JFrame frame;
    private int height = 30;
    private int width = 30;
    private Tank tank1;
    private JLabel tank1Label;
    private Tank tank2;
    private JLabel tank2Label;
    private List<Map<String, Integer>> wallsLocation;

    public DrawTank(JFrame jFrame) {
        RESOURSES = "src/resourses/maps/map.txt";
        path = Paths.get(RESOURSES);
        frame = jFrame;
    }

    public void drawTank(){
        try {
            List<String> lines = Files.readAllLines(path);
            String [] columns;
            for (int i = 0; i < lines.size(); i++) {
                columns = lines.get(i).split("");
                for (int j = 0; j < columns.length; j++){
                    if (columns[j].equals("1")){
                        BufferedImage tank_img = null;
                        try {
                            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-right.png"));
                            tank = tank_img.getScaledInstance(width, height - 10, Image.SCALE_SMOOTH);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tank1Label = new JLabel();
                        tank1Label.setIcon(new ImageIcon(tank));
                        tank1Label.setBounds(j * width, i * height, width, height);
                        tank1 = new Tank(1, j * width, i * height, "r");
                        frame.add(tank1Label);
                    }
                    else if (columns[j].equals("2")){
                        BufferedImage tank_img = null;
                        try {
                            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-right2.png"));
                            tank = tank_img.getScaledInstance(width, height - 10, Image.SCALE_SMOOTH);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        tank2Label = new JLabel();
                        tank2Label.setIcon(new ImageIcon(tank));
                        tank2Label.setBounds(j * width, i * height, width, height);
                        tank2 = new Tank(2, j * width, i * height, "r");
                        frame.add(tank2Label);
                    }

                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void moveRight(){
        if (checkWall(tank1.getLocationX() + 30, tank1.getLocationY())) {
            tank1Label.setBounds(tank1.getLocationX() + 30, tank1.getLocationY(), width, height);
            tank1.setLocationX(tank1.getLocationX() + 30);
        }
    }

    public void moveLeft(){
        if (checkWall(tank1.getLocationX() - 30, tank1.getLocationY())) {
            tank1Label.setBounds(tank1.getLocationX() - 30, tank1.getLocationY(), width, height);
            tank1.setLocationX(tank1.getLocationX() - 30);
        }
    }

    public void moveTop() {
        if (checkWall(tank1.getLocationX(), tank1.getLocationY() - 30)) {
            tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY() - 30, width, height);
            tank1.setLocationY(tank1.getLocationY() - 30);
        }
    }

    public void moveBottom() {
        if (checkWall(tank1.getLocationX(), tank1.getLocationY() + 30)) {
            tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY() + 30, width, height);
            tank1.setLocationY(tank1.getLocationY() + 30);
        }
    }

    public void turnTop(){
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-top.png"));
            if (tank1.getDirection().equals("r") || tank1.getDirection().equals("l")) {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX() + 5, tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX() + 5);
            } else {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX());
            }
            tank1.setLocationY(tank1.getLocationY());
            tank1Label.setIcon(new ImageIcon(tank));
            tank1.setDirection("t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnBottom(){
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-bottom.png"));
            if (tank1.getDirection().equals("r") || tank1.getDirection().equals("l")){
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX() + 5, tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX() + 5);
            } else {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX());
            }
            tank1.setLocationY(tank1.getLocationY());
            tank1Label.setIcon(new ImageIcon(tank));
            tank1.setDirection("b");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnRight() {
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-right.png"));
            if (tank1.getDirection().equals("t") || tank1.getDirection().equals("b")){
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX() - 5, tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX() - 5);
            } else {
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX());
            }
            tank1.setLocationY(tank1.getLocationY());
            tank1Label.setIcon(new ImageIcon(tank));
            tank1.setDirection("r");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnLeft() {
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-left.png"));
            if (tank1.getDirection().equals("t") || tank1.getDirection().equals("b")){
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX() - 5, tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX() - 5);
            } else {
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank1Label.setBounds(tank1.getLocationX(), tank1.getLocationY(), height, width);
                tank1.setLocationX(tank1.getLocationX());
            }
            tank1.setLocationY(tank1.getLocationY());
            tank1Label.setIcon(new ImageIcon(tank));
            tank1.setDirection("l");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveRight2(){
        if (checkWall(tank2.getLocationX() + 30, tank2.getLocationY())) {
            tank2Label.setBounds(tank2.getLocationX() + 30, tank2.getLocationY(), width, height);
            tank2.setLocationX(tank2.getLocationX() + 30);
        }
    }

    public void moveLeft2(){
        if (checkWall(tank2.getLocationX() - 30, tank2.getLocationY())) {
            tank2Label.setBounds(tank2.getLocationX() - 30, tank2.getLocationY(), width, height);
            tank2.setLocationX(tank2.getLocationX() - 30);
        }
    }

    public void moveTop2() {
        if (checkWall(tank2.getLocationX(), tank2.getLocationY() - 30)) {
            tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY() - 30, width, height);
            tank2.setLocationY(tank2.getLocationY() - 30);
        }
    }

    public void moveBottom2() {
        if (checkWall(tank2.getLocationX(), tank2.getLocationY() + 30)) {
            tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY() + 30, width, height);
            tank2.setLocationY(tank2.getLocationY() + 30);
        }
    }

    public void turnTop2(){
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-top2.png"));
            if (tank2.getDirection().equals("r") || tank2.getDirection().equals("l")) {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX() + 5, tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX() + 5);
            } else {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX());
            }
            tank2.setLocationY(tank2.getLocationY());
            tank2Label.setIcon(new ImageIcon(tank));
            tank2.setDirection("t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnBottom2(){
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-bottom2.png"));
            if (tank2.getDirection().equals("r") || tank2.getDirection().equals("l")){
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX() + 5, tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX() + 5);
            } else {
                tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX());
            }
            tank2.setLocationY(tank2.getLocationY());
            tank2Label.setIcon(new ImageIcon(tank));
            tank2.setDirection("b");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnRight2() {
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-right2.png"));
            if (tank2.getDirection().equals("t") || tank2.getDirection().equals("b")){
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX() - 5, tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX() - 5);
            } else {
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX());
            }
            tank2.setLocationY(tank2.getLocationY());
            tank2Label.setIcon(new ImageIcon(tank));
            tank2.setDirection("r");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnLeft2() {
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File("src/resourses/images/tank-1-texture-left2.png"));
            if (tank2.getDirection().equals("t") || tank2.getDirection().equals("b")){
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX() - 5, tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX() - 5);
            } else {
                tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                tank2Label.setBounds(tank2.getLocationX(), tank2.getLocationY(), height, width);
                tank2.setLocationX(tank2.getLocationX());
            }
            tank2.setLocationY(tank2.getLocationY());
            tank2Label.setIcon(new ImageIcon(tank));
            tank2.setDirection("l");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public Tank getTank1() {
        return tank1;
    }

    public void setWallsLocation(List<Map<String, Integer>> wallsLocation) {
        this.wallsLocation = wallsLocation;
    }

    public List<Map<String, Integer>> getWallsLocation() {
        return wallsLocation;
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
            if (((x - 5 == i.get("x")) && (y == i.get("y"))) || ((x == i.get("x")) && (y == i.get("y")))) {
                return false;
            }
        }

        return true;
    }

    public Tank getTank2() {
        return tank2;
    }
}
