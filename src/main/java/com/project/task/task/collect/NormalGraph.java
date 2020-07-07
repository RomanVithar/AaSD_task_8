package com.project.task.task.collect;

import com.project.task.out.gui.engine.ObjectCircle;
import com.project.task.out.gui.engine.ObjectLineSegment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Roman Anokhin
 */

public class NormalGraph {
    public class Node implements Comparable {
        //Индекс ObjectLine в Graph
        int id;
        Double U;
        Double I;
        Double R;
        ArrayList<Node> neighbours;

        public Node() {
            neighbours = new ArrayList<Node>();
        }

        public void addNeighbour(Node neighbour) {
            neighbours.add(neighbour);
        }

        @Override
        public int compareTo(Object o) {
            Node node = (Node) o;
            return id - node.id;
        }
    }

    private ArrayList<Node> vertexes;
    private Node start;
    private Node end;
    private Double pwSupplyU;
    private Double pwSupplyI = 0d;


    public NormalGraph() {
        vertexes = new ArrayList<Node>();
    }

    //Начал оставлять коментарии, пришлось это сделать так как программа эта пишется как-то небыстро.
    //Я уже 3 раз заново анализирую свой код и мне надоело
    //Функция которая делает из графа, удобного для вывода удобный граф для анализа
    public void convertToNormal(Graph<ObjectCircle, ObjectLineSegment> graph) {
        //список вершин в нормальном графе
        vertexes = new ArrayList<Node>();
        //цикл проходит по ненорм графу и закидывает все рёбра в вершины норм графа(и их параметры)
        for (int i = 0; i < graph.segments.size(); i++) {
            Node node = new Node();
            node.I = graph.segments.get(i).getAmperage();
            node.U = graph.segments.get(i).getVoltage();
            node.R = graph.segments.get(i).getResistance();
            node.id = i;
            vertexes.add(node);
        }
        // проходим по вершинам и смотрим соседей соседей определяем по координатом с некой погрешностью
        for (Node item : vertexes) {
            for (Node n : vertexes) {
                if (n != item) {
                    if ((Math.abs(graph.segments.get(n.id).getX1() - graph.segments.get(item.id).getX2()) < 40
                            && Math.abs(graph.segments.get(n.id).getY1() - graph.segments.get(item.id).getY2()) < 40)
                            || (Math.abs(graph.segments.get(n.id).getX2() - graph.segments.get(item.id).getX1()) < 40
                            && Math.abs(graph.segments.get(n.id).getY2() - graph.segments.get(item.id).getY1()) < 40)) {
                        item.neighbours.add(n);
                    }
                }
            }
        }
        //Находим две точки с входным и выходным напряжением и присваеваем start и end
        boolean switcher = true;
        for (int i = 0; i < graph.circles.size(); i++) {
            if (graph.circles.get(i).getPowerSupplyU() != 0d) {
                for (int j = 0; j < graph.segments.size(); j++) {
                    if ((Math.abs(graph.segments.get(j).getX1() - graph.circles.get(i).getX()) < 40
                            && Math.abs(graph.segments.get(j).getY1() - graph.circles.get(i).getY()) < 40)
                            || (Math.abs(graph.segments.get(j).getX2() - graph.circles.get(i).getX()) < 40
                            && Math.abs(graph.segments.get(j).getY2() - graph.circles.get(i).getY()) < 40)) {
                        for (Node item : vertexes) {
                            if (item.id == j) {
                                if (switcher) {
                                    start = item;
                                    switcher = false;
                                } else {
                                    end = item;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void convertFromNormal(Graph<ObjectCircle, ObjectLineSegment> graph) {
        for (Node item : vertexes) {
            if (item.id != -1) {
                graph.segments.get(item.id).setResistance(item.R);
                graph.segments.get(item.id).setAmperage(item.I);
                graph.segments.get(item.id).setVoltage(item.U);
            }
        }
        graph.circles.get(graph.getIndexNegative()).setPowerSupplyA(pwSupplyI);
    }

//    public void measurePar(Graph<ObjectCircle, ObjectLineSegment> graph) {
//        convertToNormal(graph);
//        Node curr = start;
//        double fullR;
//        fullR = curr.neighbours.get(0).R;
//        for (int i = 1; i < curr.neighbours.size(); i++) {
//            fullR = fullR * curr.neighbours.get(i).R / (fullR + curr.neighbours.get(i).R);
//        }
//        pwSupplyI = pwSupplyU / fullR;
//        for (Node item : curr.neighbours) {
//            item.I = pwSupplyU * item.R;
//            item.U = pwSupplyU;
//        }
//        convertFromNormal(graph);
//    }

    public void measurePos(Graph<ObjectCircle, ObjectLineSegment> graph) {
        convertToNormal(graph);
        Node before = start;
        Node curr = start;
        double fullR = 0d;
        while (curr != end) {
            fullR += curr.R;
            for (Node item : curr.neighbours) {
                if (item != before) {
                    before = curr;
                    curr = item;
                }
            }
        }
        fullR += curr.R;
        pwSupplyI = pwSupplyU / fullR;
        before = start;
        curr = start;
        while (curr != end) {
            curr.I = pwSupplyI;
            curr.U = pwSupplyU / curr.R;
            for (Node item : curr.neighbours) {
                if (item != before) {
                    before = curr;
                    curr = item;
                }
            }
        }
        curr.I = pwSupplyI;
        curr.U = pwSupplyU / curr.R;
        convertFromNormal(graph);
    }

    public void setPuwerU(Double indexU) {
        this.pwSupplyU = indexU;
    }
}
