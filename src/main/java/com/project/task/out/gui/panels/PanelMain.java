package com.project.task.out.gui.panels;

import javax.swing.*;
import java.awt.*;

/**
 * @author Roman Anokhin
 */
public class PanelMain extends JScrollPane {
    public PanelMain(Component view, int vsbPolicy, int hsbPolicy) {
        super(view, vsbPolicy, hsbPolicy);
        super.setViewportBorder(BorderFactory.createLineBorder(Color.YELLOW));
        super.setWheelScrollingEnabled(true);
    }
}
