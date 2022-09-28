package com.company;

import com.company.controller.TankController;
import com.company.draw.DrawMap;
import com.company.draw.DrawTank;
import com.company.draw.DrawWinner;

import javax.swing.*;

public class Main extends JFrame {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Танки");
        DrawTank drawTank = new DrawTank(frame);
        drawTank.drawTank();
        DrawMap drawMap = new DrawMap(frame);
        drawMap.drawMap();
        drawTank.setWallsLocation(drawMap.getWallsLocation());
        drawTank.setWatersLocation(drawMap.getWaterLocation());
        DrawWinner drawWinner = new DrawWinner(frame);
        TankController tankController = new TankController(drawTank);
        tankController.setDrawWinner(drawWinner);
        frame.addKeyListener(tankController);
    }

}
