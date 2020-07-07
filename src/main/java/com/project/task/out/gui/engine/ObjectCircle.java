package com.project.task.out.gui.engine;

import java.awt.*;

/**
 * @author Roman Anokhin
 */
public class ObjectCircle {
    private int x;
    private int y;
    private int w;
    private int h;
    private Color color;
    private Double powerSupplyA;
    private Double powerSupplyU;


    public ObjectCircle() {
        color = Color.black;
        powerSupplyA = 0d;
        powerSupplyU = 0d;

    }

    public ObjectCircle(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        color = Color.black;
        powerSupplyA = 0d;
        powerSupplyU = 0d;
    }

    public ObjectCircle(int x, int y, int w, int h, Double powerSupplyA, Double powerSupplyU) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        color = Color.black;
        if(powerSupplyA != 0 || powerSupplyU != 0){
            this.powerSupplyA = powerSupplyA;
            this.powerSupplyU =powerSupplyU;
        }else {
            this.powerSupplyA = 0d;
            this.powerSupplyU = 0d;
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public Double getPowerSupplyA() {
        return powerSupplyA;
    }

    public void setPowerSupplyA(Double powerSupplyA) {
        this.powerSupplyA = powerSupplyA;
    }

    public Double getPowerSupplyU() {
        return powerSupplyU;
    }

    public void setPowerSupplyU(Double powerSupplyU) {
        this.powerSupplyU = powerSupplyU;
    }
}
