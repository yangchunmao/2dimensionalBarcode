package com.ycm.eidea;

import javax.swing.JFrame;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ycm.eidea.gui.Main;
import com.ycm.eidea.gui.v2.MainFrame;
import com.ycm.eidea.qrcode.*;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * 
     * @param args The arguments of the program.
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");

        // String logoUri = "D:\\tryhard\\eidea\\2dimensionalBarcode\\下载.jpg";
        // QRCode qrCode = QRCodeFactory.getInstance();
        // qrCode.getConf().setSize(600).setErrorCorrection(ErrorCorrectionLevel.H);
        // qrCode.createQRCode("www.baidu.com", 
        //     "D:\\tryhard\\eidea\\2dimensionalBarcode\\", logoUri);

        JFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    
}
