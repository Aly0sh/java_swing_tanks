package com.company.controller;

import com.company.draw.DrawBullet;
import com.company.draw.DrawTank;
import com.company.model.Tank;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TankController implements KeyListener {
    private DrawTank drawTank1;

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
                    drawTank1.turnTop();
                } else {
                    drawTank1.moveTop();
                }
                break;
            case KeyEvent.VK_D:
                if (!drawTank1.getTank1().getDirection().equals("r")){
                    drawTank1.turnRight();
                } else {
                    drawTank1.moveRight();
                }
                break;
            case KeyEvent.VK_S:
                if (!drawTank1.getTank1().getDirection().equals("b")){
                    drawTank1.turnBottom();
                } else {
                    drawTank1.moveBottom();
                }
                break;
            case KeyEvent.VK_A:
                if (!drawTank1.getTank1().getDirection().equals("l")){
                    drawTank1.turnLeft();
                } else {
                    drawTank1.moveLeft();
                }
                break;
            case KeyEvent.VK_SPACE:
                DrawBullet drawBullet = new DrawBullet(drawTank1.getFrame(), drawTank1.getTank1());
                drawBullet.setWallsLocation(drawTank1.getWallsLocation());
                drawBullet.fire();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
