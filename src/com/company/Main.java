package com.company;

import com.company.controller.TankController;
import com.company.draw.DrawMap;
import com.company.draw.DrawTank;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Танки");
        DrawTank drawTank = new DrawTank(frame);
        drawTank.drawTank();
        DrawMap drawMap = new DrawMap(frame);
        drawMap.drawMap();
        drawTank.setWallsLocation(drawMap.getWallsLocation());
        TankController tankController = new TankController(drawTank);
        frame.addKeyListener(tankController);
    }

}
