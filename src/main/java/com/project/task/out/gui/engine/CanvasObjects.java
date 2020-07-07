package com.project.task.out.gui.engine;

import com.project.task.task.collect.Graph;

import java.awt.*;

/**
 * @author Roman Anokhin
 */
public class CanvasObjects {
    private Graph<ObjectCircle,ObjectLineSegment> graph;
    private int isSeeked;
    private ObjectLineSegment bufferLine;


    public CanvasObjects() {
        bufferLine = new ObjectLineSegment();
        isSeeked = 0;
        graph = new Graph<ObjectCircle, ObjectLineSegment>();
    }
    private boolean costil = true;
    public void addObjectCircle(ObjectCircle oc) {
        graph.circles.add(oc);
        if(oc.getPowerSupplyA()!=0){
            if(costil) {
                graph.setIndexPositive(graph.circles.size());
                costil = false;
            }else{
                graph.setIndexNegative(graph.circles.size());
                costil = true;
            }
        }
    }

    public void addObjectLineSegment(ObjectLineSegment oc) {
        graph.segments.add(oc);
    }

    public ObjectCircle getCircleAt(int index) {
        return graph.circles.get(index);
    }

    public ObjectLineSegment getSegmentAt(int index) {
        return graph.segments.get(index);
    }

    public int segmentSize() {
        return graph.segments.size();
    }

    public int circleSize() {
        return graph.circles.size();
    }

    public void tieVertex(int x, int y) {
        if (isSeeked == 1) {
            boolean check = false;
            for (ObjectCircle item : graph.circles) {
                if (Math.pow(x - item.getX(), 2) + Math.pow(y - item.getY(), 2) <= Math.pow(item.getW(), 2)) {
                    item.setColor(Color.green);
                    bufferLine.setX2(item.getX() + item.getW() / 2);
                    bufferLine.setY2(item.getY() + item.getW() / 2);
                    check = true;
                    break;
                }
            }
            if (check) {
                bufferLine.setName("R_" + (graph.segments.size()));
                graph.segments.add(bufferLine);
                bufferLine = new ObjectLineSegment();
                isSeeked = 3;
            }


        }
        if (isSeeked == 0) {
            boolean check = false;
            for (ObjectCircle item : graph.circles) {
                if (Math.pow(x - item.getX(), 2) + Math.pow(y - item.getY(), 2) <= Math.pow(item.getW(), 2)) {
                    item.setColor(Color.green);
                    bufferLine.setX1(item.getX() + item.getW() / 2);
                    bufferLine.setY1(item.getY() + item.getW() / 2);
                    check = true;
                    break;
                }
            }
            if (check) {
                isSeeked = 1;
            }
        }
        if (isSeeked == 3) {
            isSeeked = 0;
        }
    }

    public ObjectLineSegment isEdge(int x, int y) {
        for (ObjectLineSegment item : graph.segments) {
            int x1 = item.getX1();
            int x2 = item.getX2();
            int y1 = item.getY1();
            int y2 = item.getY2();
            if ((x2 >= x && x >= x1) || (x1 >= x && x >= x2)) {
                if (Math.abs((x - x1) * (y2 - y1) - (y - y1) * (x2 - x1)) < 5000) {
                    return item;
                }
            }
        }
        return null;
    }

    public Graph<ObjectCircle, ObjectLineSegment> getGraph() {
        return graph;
    }
}
