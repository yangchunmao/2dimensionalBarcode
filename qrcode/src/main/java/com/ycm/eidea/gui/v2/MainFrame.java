 package com.ycm.eidea.gui.v2;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.*;


public class MainFrame extends JFrame {


    private static final long serialVersionUID = 2407358431515996399L;
    private static final int DEFAULT_WIDTH = 980;
    private static final int DEFAULT_HEIGHT = 530;
    
    public MainFrame() {
        setTitle("二维码工具");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        JPanel centerPanel = new CenterPanel();
        JPanel rightPanel = new RightPanel();
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
    }
}