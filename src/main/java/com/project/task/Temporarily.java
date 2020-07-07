package com.project.task;

import com.project.task.out.gui.Window;

import javax.swing.*;

/**
 * @author Roman Anokhin
 */
public class Temporarily {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Window window = new Window();
                window.setVisible(true);
                window.addPowerSupply(50,300,0d,220d);
                window.addPowerSupply(900,300,0d,220d);
            }
        });

    }
}
