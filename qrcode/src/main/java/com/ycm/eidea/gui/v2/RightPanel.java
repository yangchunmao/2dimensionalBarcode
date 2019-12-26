package com.ycm.eidea.gui.v2;

import javax.swing.*;
import java.awt.*;

public class RightPanel extends JPanel {


    public RightPanel () {

        this.setLayout(new BorderLayout());

        JButton button = new JButton("保存");
        add(button);
        

        this.setPreferredSize(new Dimension(400, 0));
    }
}