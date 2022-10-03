package com.company.controller;

import com.company.draw.DrawBullet;
import com.company.draw.DrawTank;
import com.company.draw.DrawWinner;
import com.company.model.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankController implements KeyListener {
    private final DrawTank drawTank1;
    private DrawWinner drawWinner;

    public TankController(DrawTank drawTank1) {
        this.drawTank1 = drawTank1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (!drawTank1.getTank1().getDirection().equals("t")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-top.png", "t", drawTank1.tank1Label, drawTank1.tank1);
                } else {
                    drawTank1.moveTank(drawTank1.tank1.getLocationX(), drawTank1.tank1.getLocationY() - 30, drawTank1.tank1Label, drawTank1.tank1);
                }
                break;
            case KeyEvent.VK_D:
                if (!drawTank1.getTank1().getDirection().equals("r")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-right.png", "r", drawTank1.tank1Label, drawTank1.tank1);
                } else {
                    drawTank1.moveTank(drawTank1.tank1.getLocationX() + 30, drawTank1.tank1.getLocationY(), drawTank1.tank1Label, drawTank1.tank1);
                }
                break;
            case KeyEvent.VK_S:
                if (!drawTank1.getTank1().getDirection().equals("b")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-bottom.png", "b", drawTank1.tank1Label, drawTank1.tank1);
                } else {
                    drawTank1.moveTank(drawTank1.tank1.getLocationX(), drawTank1.tank1.getLocationY() + 30, drawTank1.tank1Label, drawTank1.tank1);
                }
                break;
            case KeyEvent.VK_A:
                if (!drawTank1.getTank1().getDirection().equals("l")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-left.png", "l", drawTank1.tank1Label, drawTank1.tank1);
                } else {
                    drawTank1.moveTank(drawTank1.tank1.getLocationX() - 30, drawTank1.tank1.getLocationY(), drawTank1.tank1Label, drawTank1.tank1);
                }
                break;
            case KeyEvent.VK_SPACE:
                if (drawTank1.getTank1().getBullets().size() <= 4) {
                    DrawBullet drawBullet = new DrawBullet(drawTank1.getFrame(), drawTank1.getTank1(), drawTank1.getTank2());
                    drawBullet.setDrawWinner(drawWinner);
                    Thread bulletThread = new Thread(drawBullet);
                    drawBullet.setWallsLocation(drawTank1.getWallsLocation());
                    bulletThread.start();
                    drawTank1.getTank1().addBullet(drawBullet);
                }
                break;
            case KeyEvent.VK_UP:
                if (!drawTank1.getTank2().getDirection().equals("t")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-top2.png", "t", drawTank1.tank2Label, drawTank1.tank2);
                } else {
                    drawTank1.moveTank(drawTank1.tank2.getLocationX(), drawTank1.tank2.getLocationY() - 30, drawTank1.tank2Label, drawTank1.tank2);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!drawTank1.getTank2().getDirection().equals("r")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-right2.png", "r", drawTank1.tank2Label, drawTank1.tank2);
                } else {
                    drawTank1.moveTank(drawTank1.tank2.getLocationX() + 30, drawTank1.tank2.getLocationY(), drawTank1.tank2Label, drawTank1.tank2);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!drawTank1.getTank2().getDirection().equals("b")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-bottom2.png", "b", drawTank1.tank2Label, drawTank1.tank2);
                } else {
                    drawTank1.moveTank(drawTank1.tank2.getLocationX(), drawTank1.tank2.getLocationY() + 30, drawTank1.tank2Label, drawTank1.tank2);
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!drawTank1.getTank2().getDirection().equals("l")){
                    drawTank1.turnTank("src/resourses/images/tank-1-texture-left2.png", "l", drawTank1.tank2Label, drawTank1.tank2);
                } else {
                    drawTank1.moveTank(drawTank1.tank2.getLocationX() - 30, drawTank1.tank2.getLocationY(), drawTank1.tank2Label, drawTank1.tank2);
                }
                break;
            case KeyEvent.VK_ENTER:
                if (drawTank1.getTank2().getBullets().size() <= 4) {
                    DrawBullet drawBullet2 = new DrawBullet(drawTank1.getFrame(), drawTank1.getTank2(), drawTank1.getTank1());
                    drawBullet2.setDrawWinner(drawWinner);
                    Thread bulletThread2 = new Thread(drawBullet2);
                    drawBullet2.setWallsLocation(drawTank1.getWallsLocation());
                    bulletThread2.start();
                    drawTank1.getTank2().addBullet(drawBullet2);
                }
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void setDrawWinner(DrawWinner drawWinner) {
        this.drawWinner = drawWinner;
    }
}
