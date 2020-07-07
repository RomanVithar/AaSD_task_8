package com.project.task.out.gui.panels;

import javax.swing.*;

/**
 * @author Roman Anokhin
 */
public class PanelInstruction extends JPanel {
    private JLabel label = new JLabel("Программа предназначена для нахождения сил токов и напряжений для последовательного соединения.");

    public PanelInstruction() {
        super.add(label);
    }
}
