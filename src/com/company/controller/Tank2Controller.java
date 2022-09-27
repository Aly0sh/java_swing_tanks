package com.company.controller;

import com.company.draw.DrawBullet;
import com.company.draw.DrawTank;
import com.company.draw.DrawWinner;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Tank2Controller implements KeyListener {
    private DrawTank drawTank1;
    private DrawWinner drawWinner;

    public Tank2Controller(DrawTank drawTank1) {
        this.drawTank1 = drawTank1;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (!drawTank1.getTank2().getDirection().equals("t")){
                    drawTank1.turnTop2();
                } else {
                    drawTank1.moveTop2();
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (!drawTank1.getTank2().getDirection().equals("r")){
                    drawTank1.turnRight2();
                } else {
                    drawTank1.moveRight2();
                }
                break;
            case KeyEvent.VK_DOWN:
                if (!drawTank1.getTank2().getDirection().equals("b")){
                    drawTank1.turnBottom2();
                } else {
                    drawTank1.moveBottom2();
                }
                break;
            case KeyEvent.VK_LEFT:
                if (!drawTank1.getTank2().getDirection().equals("l")){
                    drawTank1.turnLeft2();
                } else {
                    drawTank1.moveLeft2();
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