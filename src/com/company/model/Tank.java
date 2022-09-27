package com.company.model;

import com.company.draw.DrawBullet;

import java.util.ArrayList;
import java.util.List;

public class Tank {
    private int locationX;
    private int locationY;
    private String direction;
    private int tankNumber;
    private List<DrawBullet> bullets;
    private int HP;

    public Tank(int tankNumber, int locationX, int locationY, String direction, int HP) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.direction = direction;
        this.tankNumber = tankNumber;
        this.HP = HP;
        bullets = new ArrayList<>();
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getTankNumber() {
        return tankNumber;
    }

    public void setTankNumber(int tankNumber) {
        this.tankNumber = tankNumber;
    }

    public void addBullet(DrawBullet bullet) {
        bullets.add(bullet);
    }

    public List<DrawBullet> getBullets() {
        return bullets;
    }

    public void getDamage(int damage) {
        this.HP -= damage;
    }

    public int getHP() {
        return HP;
    }
}
