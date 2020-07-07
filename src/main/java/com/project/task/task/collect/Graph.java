package com.project.task.task.collect;

import java.util.ArrayList;

/**
 * @author Roman Anokhin
 */
public class Graph<V, B> {
    public ArrayList<V> circles;
    public ArrayList<B> segments;
    // Вроде источник тока, ниже вроде сточник тока
    private int indexPositive;
    private int indexNegative;

    public Graph() {
        circles = new ArrayList<V>();
        segments = new ArrayList<B>();
    }

    public int getIndexPositive() {
        return indexPositive;
    }

    public void setIndexPositive(int indexPositive) {
        this.indexPositive = indexPositive;
    }

    public int getIndexNegative() {
        return indexNegative;
    }

    public void setIndexNegative(int indexNegative) {
        this.indexNegative = indexNegative;
    }
}
