package com.project.task.out.gui;

import com.project.task.out.gui.engine.Conditions;
import com.project.task.out.gui.panels.DrawZone;
import com.project.task.out.gui.panels.PanelInstruction;
import com.project.task.out.gui.panels.PanelMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Roman Anokhin
 */
public class Window extends JFrame {
    private JButton btnModeCreate = new JButton("Create");
    private JButton btnModeRemove = new JButton("Init");
    private JButton btnModeWay = new JButton("Way");
    private JButton btnModeClr = new JButton("Clear");

    private JButton btnPar = new JButton("Parallel");
    private JButton btnPos = new JButton("Seek");

    private Container container;
    private JToolBar tb;
    private JTabbedPane tabPane;
    private DrawZone label;
    private PanelMain pnlMain;
    private Label condLabel;

    private int powerX1;
    private int powerY1;
    private int powerX2;
    private int powerY2;
    private Double powerU1;
    private Double powerU2;
    private Double powerI1;
    private Double powerI2;
    private int powerNum = 0;


    public Window() throws HeadlessException {
        super("Task 8");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        tb = new JToolBar();
        tabPane = new JTabbedPane();
        label = new DrawZone();
        label.setEnabled(true);
        pnlMain = new PanelMain(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        PanelInstruction pnlInstruction = new PanelInstruction();

        container = this.getContentPane();
        container.setLayout(new BorderLayout());
        container.add(tabPane, BorderLayout.CENTER);
        container.add(tb, BorderLayout.SOUTH);


        tabPane.add("Редактор", pnlMain);
        tabPane.add("Инструкция", pnlInstruction);

        tb.add(btnModeCreate);
        tb.add(btnModeRemove);
        tb.add(btnModeWay);
        tb.add(btnModeClr);
       // tb.add(btnPar);
        tb.add(btnPos);
        condLabel = new Label();
        condLabel.setText("Режим: создание");
        tb.add(condLabel);

        btnModeCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                label.setConditions(Conditions.CREATE);
                condLabel.setText("Режим: создание");
            }
        });
        btnModeRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                label.setConditions(Conditions.ENABLE);
                condLabel.setText("Режим: иницилизация");

            }
        });
        btnModeWay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                label.setConditions(Conditions.WAY);
                condLabel.setText("Режим: путь");

            }
        });
        btnModeClr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                label.clear();
                label.addPowerSupply(powerX1, powerY1, powerI1, powerU1);
                label.addPowerSupply(powerX2, powerY2, powerI2, powerU2);
            }
        });
//        btnPar.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(java.awt.event.ActionEvent e) {
//                label.calcPar();
//            }
//        });
        btnPos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                label.calcPos();
            }
        });
        super.setBounds(400, 100, 1000, 700);
    }

    public void addVertex(int x, int y) {
        label.addVertex(x, y);
    }

    public void addPowerSupply(int x, int y, Double amp, Double vol) {
        if (powerNum == 0) {
            powerX1 = x;
            powerY1 = y;
            powerI1 = amp;
            powerU1 = vol;
            powerNum++;
        } else {
            powerX2 = x;
            powerY2 = y;
            powerI2 = amp;
            powerU2 = vol;
            powerNum--;
        }
        label.addPowerSupply(x, y, amp, vol);
    }
}
