package com.project.task.out.gui.panels;

import com.project.task.out.gui.engine.CanvasObjects;
import com.project.task.out.gui.engine.Conditions;
import com.project.task.out.gui.engine.ObjectCircle;
import com.project.task.out.gui.engine.ObjectLineSegment;
import com.project.task.task.collect.NormalGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * @author Roman Anokhin
 */
public class DrawZone extends JLabel implements Scrollable, MouseListener {
    private CanvasObjects canvas;
    private Conditions conditions;
    private ArrayList<Integer> listGreen = new ArrayList<Integer>();
    private Double indexA;
    private Double indexU;

    public DrawZone() {
        super.addMouseListener(this);
        conditions = Conditions.CREATE;
        canvas = new CanvasObjects();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(7000, 7000);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return getPreferredSize();
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 100;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(8.0f));
        g2.setColor(Color.BLACK);

        for (int i = 0; i < canvas.circleSize(); i++) {
            g2.setColor(Color.BLACK);
            if (canvas.getCircleAt(i).getColor() == Color.GREEN) {
                g2.setColor(Color.GREEN);
                listGreen.add(i);
            }
            g2.drawOval(canvas.getCircleAt(i).getX(),
                    canvas.getCircleAt(i).getY(),
                    canvas.getCircleAt(i).getH(),
                    canvas.getCircleAt(i).getW());
        }
        for (int i = 0; i < canvas.segmentSize(); i++) {
            g2.setColor(Color.BLACK);
            g2.drawLine(canvas.getSegmentAt(i).getX1(),
                    canvas.getSegmentAt(i).getY1(),
                    canvas.getSegmentAt(i).getX2(),
                    canvas.getSegmentAt(i).getY2());
        }
        if (listGreen.size() > 2) {
            for (int i : listGreen) {
                canvas.getCircleAt(i).setColor(Color.black);
            }
            listGreen = new ArrayList<Integer>();
        }
        if (indexA != 0) {
            g2.setFont(new Font("", Font.BOLD,20));
            g2.drawString("Сила тока источника питания:     " + (indexA), 0, 40);
        }
        if (indexU != 0) {
            g2.setFont(new Font("", Font.BOLD,20));
            g2.drawString("Напряжение источника питания: " + (indexU), 0, 20);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK && conditions == Conditions.CREATE) {
            addVertex(e.getX(), e.getY());
        }
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK && conditions == Conditions.WAY) {
            canvas.tieVertex(e.getX(), e.getY());
            super.repaint();
        }
        if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) == MouseEvent.BUTTON3_MASK) {
            nextConditions();
        }
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == MouseEvent.BUTTON1_MASK && conditions == Conditions.ENABLE) {
//            NormalGraph ng = new NormalGraph();
//            ng.measure(canvas.getGraph());
            ObjectLineSegment lineSegment = canvas.isEdge(e.getX(), e.getY());
            if (lineSegment != null) {
                String inputResistance = JOptionPane.showInputDialog(
                        this.getParent(),
                        "<html>" +
                                "<h2>" + lineSegment.getName() + "</h2>" +
                                "<p> Сопротивление = " + lineSegment.getResistance() + "</p>" +
                                "<p> Напряжение = " + lineSegment.getVoltage() + "</p>" +
                                "<p> Сила тока = " + lineSegment.getAmperage() + "</p>" +
                                "<p> Введите сопротивление:</p>" +
                                "</html>");
                try {
                    if (!inputResistance.equals("")) {
                        lineSegment.setResistance(Double.parseDouble(inputResistance));
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(
                            this.getParent(),
                            "<html>" +
                                    "<h2>Сопротивление должно быть вещественым числом!</h2>" +
                                    "<h4>Знак разделитель - '.'</h4>" +
                                    "</html>");
                }
            }
        }
    }
//    public void calcPar(){
//        NormalGraph ng = new NormalGraph();
//        ng.setPuwerU(indexU);
//        ng.measurePar(canvas.getGraph());
//    }
    public void calcPos(){
        NormalGraph ng = new NormalGraph();
        ng.setPuwerU(indexU);
        ng.measurePos(canvas.getGraph());
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void nextConditions() {
        switch (conditions) {
            case CREATE:
                conditions = Conditions.ENABLE;
                break;
            case ENABLE:
                conditions = Conditions.WAY;
                break;
            case WAY:
                conditions = Conditions.CREATE;
                break;
        }
    }

    public void setConditions(Conditions conditions) {
        this.conditions = conditions;
    }

    public void clear() {
        canvas = new CanvasObjects();
        repaint();
    }

    public void addVertex(int x, int y) {
        canvas.addObjectCircle(new ObjectCircle(x - 25, y - 25, 50, 50));
        super.repaint();
    }

    public void addPowerSupply(int x, int y, Double amp, Double vol) {
        canvas.addObjectCircle(new ObjectCircle(x - 25, y - 25, 50, 50, amp, vol));
        indexA = amp;
        indexU = vol;
        super.repaint();
    }
}
