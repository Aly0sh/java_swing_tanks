package com.company;

import com.company.controller.Tank1Controller;
import com.company.controller.Tank2Controller;
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
        Tank1Controller tankController = new Tank1Controller(drawTank);
        Tank2Controller tank2Controller = new Tank2Controller(drawTank);
        tankController.setDrawWinner(drawWinner);
        tank2Controller.setDrawWinner(drawWinner);
        frame.addKeyListener(tankController);
        frame.addKeyListener(tank2Controller);
    }

}
