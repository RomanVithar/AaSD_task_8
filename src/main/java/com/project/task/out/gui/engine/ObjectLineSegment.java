package com.project.task.out.gui.engine;

/**
 * @author Roman Anokhin
 */
public class ObjectLineSegment {
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private Double Resistance;
    private Double Voltage;
    private Double Amperage;
    private String name;

    public ObjectLineSegment() {
        Resistance = null;
        Voltage = null;
        Resistance = null;
    }

    public ObjectLineSegment(int x1, int x2, int y1, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public ObjectLineSegment(int x1, int x2, int y1, int y2, Double weight) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.Resistance = weight;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Double getResistance() {
        return Resistance;
    }

    public void setResistance(Double resistance) {
        this.Resistance = resistance;
    }

    public Double getVoltage() {
        return Voltage;
    }

    public void setVoltage(Double voltage) {
        Voltage = voltage;
    }

    public Double getAmperage() {
        return Amperage;
    }

    public void setAmperage(Double amperage) {
        Amperage = amperage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
