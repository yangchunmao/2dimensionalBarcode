package com.ycm.eidea.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame {

    private static final long serialVersionUID = -96018887918900482L;
    private static final int DEFAULT_WIDTH = 980;
    private static final int DEFAULT_HEIGHT = 530;

    public Main() {
        setTitle("二维码工具");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((screenSize.width - this.getWidth()) / 2, (screenSize.height - this.getHeight()) / 2);

        // 分中， 右模型

        JPanel rightPanel = new JPanel(new BorderLayout());
        // JButton button1 = new JButton("测试");
        RightPanel paintPanel = new RightPanel();
        rightPanel.add(paintPanel, BorderLayout.CENTER);
        // rightPanel.add(button1);
        // 添加保存按钮
        JPanel savePanel = new JPanel();
        JButton saveButton = new JButton("保存二维码");
        
        
        savePanel.add(saveButton);
        rightPanel.add(savePanel, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        // 中间
        JPanel contentPanel = new JPanel();
        JTextArea jta = new JTextArea("请输入内容", 10, 50);
        jta.setLineWrap(true); // 设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK); // 设置组件的背景色
        jta.setFont(new Font("楷体", Font.BOLD, 16)); // 修改字体样式
        jta.setBackground(Color.YELLOW); // 设置按钮背景色
        JScrollPane jsp = new JScrollPane(jta); // 将文本域放入滚动窗口
        Dimension size = jta.getPreferredSize(); // 获得文本域的首选大小
        jsp.setBounds(110, 90, size.width, size.height);
        contentPanel.add(jsp);

        centerPanel.add(contentPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton button2 = new JButton("生成二维码");
        // 添加事件
        button2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            //    JOptionPane.showMessageDialog(centerPanel, jta.getText().trim());
               paintPanel.removeAll();
               paintPanel.setContents(jta.getText().trim());
               paintPanel.repaint();
            }
            
        });

        saveButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                BufferedWriter bw = null;
                JFileChooser chooser = new JFileChooser();

                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.setDialogTitle("Select path to save");

                int result = chooser.showSaveDialog(rightPanel);
                File file = null;
                String fileName = null;
                chooser.setSelectedFile(new File("新建.jpg"));
                if (result == chooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                }
                fileName = chooser.getName(file);
                if (fileName == null || fileName.trim().length() == 0) {
                    JOptionPane.showMessageDialog(rightPanel, "文件名为空！");
                }
                if (file.isFile()) {
                    fileName = file.getName();
                }
                file = chooser.getCurrentDirectory();// 获得当前目录
                String path = file.getPath() + java.io.File.separator + fileName;
                file = new File(path);

                if (file.exists()) {
                    int i = JOptionPane.showConfirmDialog(rightPanel, "该文件已经存在，确定要覆盖吗？");
                    if (i == JOptionPane.YES_OPTION)
                        ;
                    else
                        return;

                }
                try {
                    ImageIO.write(paintPanel.getQrCodeImage(), "jpg", file);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            } 
        });
        bottomPanel.add(button2);
        centerPanel.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.setPreferredSize(new Dimension(0, 160));

        add(centerPanel, BorderLayout.CENTER);

        add(rightPanel, BorderLayout.EAST);
        rightPanel.setPreferredSize(new Dimension(400, 0));
    }
    


}