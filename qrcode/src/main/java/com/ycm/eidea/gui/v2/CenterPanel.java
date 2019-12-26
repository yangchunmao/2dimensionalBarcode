package com.ycm.eidea.gui.v2;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {
    

    public CenterPanel() {
        this.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        JTextArea jta = new JTextArea("请输入内容", 10, 50);
        jta.setLineWrap(true); // 设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK); // 设置组件的背景色
        jta.setFont(new Font("楷体", Font.BOLD, 16)); // 修改字体样式
        jta.setBackground(Color.WHITE); // 设置按钮背景色
        JScrollPane jsp = new JScrollPane(jta); // 将文本域放入滚动窗口
        Dimension size = jta.getPreferredSize(); // 获得文本域的首选大小
        jsp.setBounds(110, 150, size.width, size.height);
        contentPanel.add(jsp);

        add(contentPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton createButton = new JButton("生成二维码");
        buttonPanel.add(createButton);
        buttonPanel.setPreferredSize(new Dimension(0, 160));

        add(buttonPanel, BorderLayout.SOUTH);
        
    }
}