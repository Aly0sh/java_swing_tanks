package com.company.model;

public class Bullet {
    private int locationX;
    private int locationY;
    private String direction;
    private int damage;

    public Bullet(int locationX, int locationY, String direction, int damage) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.direction = direction;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
