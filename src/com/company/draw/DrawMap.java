package com.company.draw;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawMap extends Canvas {
    private Image wall;
    private Image ground;
    private Image water;
    private String RESOURSES;
    private Path path;
    private JFrame frame;
    private int height = 30;
    private int width = 30;
    private Container container;
    private List<Map<String, Integer>> wallsLocation;
    private List<Map<String, Integer>> waterLocation;
    public DrawMap(JFrame jFrame) {
        RESOURSES = "src/resourses/maps/map.txt";
        path = Paths.get(RESOURSES);
        wallsLocation = new ArrayList<>();
        waterLocation = new ArrayList<>();
        frame = jFrame;
        frame.setResizable(false);
        container = frame.getContentPane();
        BufferedImage wall_img = null;
        BufferedImage ground_img = null;
        BufferedImage water_img = null;
        try {
            wall_img = ImageIO.read(new File("src/resourses/images/bricks-texture.png"));
            ground_img = ImageIO.read(new File("src/resourses/images/ground-texture.png"));
            water_img = ImageIO.read(new File("src/resourses/images/water-texture.jpg"));
            wall = wall_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ground = ground_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            water = water_img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void drawMap(){
        try {
            List<String> lines = Files.readAllLines(path);
            frame.setSize((lines.get(0).length() * width + 15), lines.size() * height + 38);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            String [] columns;
            Map<String, Integer> wallLoc = new HashMap<>();
            Map<String, Integer> waterLoc = new HashMap<>();
            for (int i = 0; i < lines.size(); i++) {
                columns = lines.get(i).split("");
                for (int j = 0; j < columns.length; j++){
                    JLabel label = new JLabel();
                    if (columns[j].equals("#")){
                        wallLoc.put("x", j * width);
                        wallLoc.put("y", i * height);
                        label.setIcon(new ImageIcon(wall));
                        wallsLocation.add(wallLoc);
                        wallLoc = new HashMap<>();
                    } else if (columns[j].equals("w")) {
                        waterLoc.put("x", j * width);
                        waterLoc.put("y", i * height);
                        label.setIcon(new ImageIcon(water));
                        waterLocation.add(waterLoc);
                        waterLoc = new HashMap<>();
                    } else {
                        label.setIcon(new ImageIcon(ground));
                    }
                    label.setBounds(j * width, i * height, width, height);
                    container.add(label);
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        frame.setLayout(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public List<Map<String, Integer>> getWallsLocation() {
        return wallsLocation;
    }

    public List<Map<String, Integer>> getWaterLocation() {
        return waterLocation;
    }
}
