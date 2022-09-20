package com.company.model;

import java.awt.*;

public class Tank {
    private int locationX;
    private int locationY;
    private String direction;
    private int tankNumber;

    public Tank(int tankNumber, int locationX, int locationY, String direction) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.direction = direction;
        this.tankNumber = tankNumber;
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
}
