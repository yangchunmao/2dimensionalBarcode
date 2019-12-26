package com.ycm.eidea.gui;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

import com.google.zxing.WriterException;
import com.ycm.eidea.qrcode.QRCode;
import com.ycm.eidea.qrcode.QRCodeFactory;

public class RightPanel extends JPanel {

    private static final long serialVersionUID = -136940735761136185L;

    private String contents = null;

    private QRCode qrCode;

    private BufferedImage qrCodeImage = null;

    
    public RightPanel() {
        qrCode = QRCodeFactory.getInstance();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(contents != null) {
            try {
                qrCodeImage = qrCode.createQRCodeToBufferedImage(contents);
                g2.drawImage(qrCodeImage, null, 0, 0);
                g2.dispose();
            } catch (WriterException e) {
            }
        }else{
            Rectangle2D rect = new Rectangle2D.Double(0, 0, qrCode.getConf().getSize(), qrCode.getConf().getSize());
            g2.setColor(Color.WHITE);
            g2.fill(rect);
        } 
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public BufferedImage getQrCodeImage() {
        return qrCodeImage;
    }

    public void setQrCodeImage(BufferedImage qrCodeImage) {
        this.qrCodeImage = qrCodeImage;
    }
}