package com.ycm.eidea.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

public class LeftPanel extends JPanel {

    private static final long serialVersionUID = 3808786794731535371L;


    public LeftPanel() {
        setLayout(new BorderLayout());

        JPanel textPanel = new JPanel(new BorderLayout());
        JTextArea textArea = new JTextArea(2, 8);
        textPanel.add(textArea);
        add(textPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("生成二维码");
        buttonPanel.add(createButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

}