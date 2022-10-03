package com.company.draw;

import com.company.model.Bullet;
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
    public Tank tank1;
    public JLabel tank1Label;
    public Tank tank2;
    public JLabel tank2Label;
    private List<Map<String, Integer>> wallsLocation;
    private List<Map<String, Integer>> watersLocation;

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
                        tank1 = new Tank(1, j * width, i * height, "r", 100);
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
                        tank2 = new Tank(2, j * width, i * height, "r", 100);
                        frame.add(tank2Label);
                    }

                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void moveTank(int x, int y, JLabel tankLabel, Tank tank){
        if (checkWall(x, y)) {
            checkBullet();
            tankLabel.setBounds(x, y, width, height);
            tank.setLocationX(x);
            tank.setLocationY(y);
        }
    }

    public void turnTank(String texture, String direction, JLabel tankLabel, Tank _tank){
        BufferedImage tank_img = null;
        try {
            tank_img = ImageIO.read(new File(texture));
            if (direction.equals("t") || direction.equals("b")) {
                if (_tank.getDirection().equals("r") || _tank.getDirection().equals("l")) {
                    tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                    tankLabel.setBounds(_tank.getLocationX() + 5, _tank.getLocationY(), height, width);
                    _tank.setLocationX(_tank.getLocationX() + 5);
                } else {
                    tank = tank_img.getScaledInstance(height - 10, width, Image.SCALE_SMOOTH);
                    tankLabel.setBounds(_tank.getLocationX(), _tank.getLocationY(), height, width);
                    _tank.setLocationX(_tank.getLocationX());
                }
                _tank.setLocationY(_tank.getLocationY());
                tankLabel.setIcon(new ImageIcon(tank));
                _tank.setDirection(direction);
            } else {
                if (_tank.getDirection().equals("t") || _tank.getDirection().equals("b")){
                    tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                    tankLabel.setBounds(_tank.getLocationX() - 5, _tank.getLocationY(), height, width);
                    _tank.setLocationX(_tank.getLocationX() - 5);
                } else {
                    tank = tank_img.getScaledInstance(height, width - 10, Image.SCALE_SMOOTH);
                    tankLabel.setBounds(_tank.getLocationX(), _tank.getLocationY(), height, width);
                    _tank.setLocationX(_tank.getLocationX());
                }
                _tank.setLocationY(_tank.getLocationY());
                tankLabel.setIcon(new ImageIcon(tank));
                _tank.setDirection(direction);
            }
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

    public void setWatersLocation(List<Map<String, Integer>> watersLocation) {
        this.watersLocation = watersLocation;
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

        for (Map<String, Integer> i:watersLocation) {
            if (((x - 5 == i.get("x")) && (y == i.get("y"))) || ((x == i.get("x")) && (y == i.get("y")))) {
                return false;
            }
        }

        return true;
    }

    public Tank getTank2() {
        return tank2;
    }

    private void checkBullet() {
        for (int i = 0; i < tank1.getBullets().size(); i++) {
            if (tank1.getBullets().get(i).connectToTheTank(tank1.getBullets().get(i).getBullet().getLocationX(), tank1.getBullets().get(i).getBullet().getLocationY())){
                break;
            }
        }
        for (int i = 0; i < tank2.getBullets().size(); i++) {
            if (tank2.getBullets().get(i).connectToTheTank(tank2.getBullets().get(i).getBullet().getLocationX(), tank2.getBullets().get(i).getBullet().getLocationY())){
                break;
            }
        }
    }
}
