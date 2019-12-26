package com.ycm.eidea.qrcode;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.common.collect.Maps;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ycm.eidea.conf.DefaultConfigured;
import com.github.binarywang.utils.qrcode.BufferedImageLuminanceSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QRCodeFactory extends DefaultConfigured implements QRCode {

    private final Logger logger = LoggerFactory.getLogger(QRCodeFactory.class);

    public static QRCode getInstance() {
        return new QRCodeFactory();
    }

    @Override
    public BitMatrix createQRCodeMatrix(String contents) throws WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, getConf().getErrorCorrection());
        hints.put(EncodeHintType.CHARACTER_SET, getConf().getCharacterSet());
        hints.put(EncodeHintType.MARGIN, getConf().getMargin());

        return new MultiFormatWriter().encode(contents, getConf().getCodeFormat(), getConf().getSize(),
                getConf().getSize(), hints);
    }

    /**
     * bitMatrix 转化为 BufferedImage
     * 
     * @param bitMatrix
     * @return
     */
    private BufferedImage toBufferedImage(BitMatrix bitMatrix) {
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y,
                        bitMatrix.get(x, y) ? getConf().getForegroundColor()
                                : (getConf().getTransparentBackground() == 1 ? getConf().getBackgroundColor()
                                        : new Color(255, 255, 255, 0).getRGB()));
            }
        }
        return image;
    }

    /**
     * 二维码添加logo图片
     * 
     * @param matrixImage
     * @param logoUri
     * @return
     * @throws IOException
     */
    private BufferedImage setMatrixLogo(BufferedImage matrixImage, String logoUri) throws IOException {

        Graphics2D g2 = matrixImage.createGraphics();
        int width = matrixImage.getWidth();
        int height = matrixImage.getHeight();

        BufferedImage logo = ImageIO.read(new File(logoUri));

        // logo 图片的尺寸 1/5, 要根据二维码的纠错级别进行自动变更
        int size = 0;
        if (getConf().getErrorCorrection().equals(ErrorCorrectionLevel.H)) {
            size = 5;
        } else if (getConf().getErrorCorrection().equals(ErrorCorrectionLevel.Q)) {
            size = 8;
        } else if (getConf().getErrorCorrection().equals(ErrorCorrectionLevel.M)) {
            size = 10;
        } else {
            size = 15;
        }

        int logoWidth = width / size;
        int logoHeight = height / size;
        // logo 图片起始位置，此目的是为logo居中显示
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;
        g2.drawImage(logo, x, y, logoWidth, logoHeight, null);

        // 给logo画边框
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.WHITE);
        g2.draw(new RoundRectangle2D.Float(x, y, logoWidth, logoHeight, 20, 20));
        g2.dispose();
        matrixImage.flush();
        return matrixImage;
    }

    @Override
    public BufferedImage createQRCodeToBufferedImage(String contents) throws WriterException {
        BitMatrix bitMatrix = createQRCodeMatrix(contents);
        return toBufferedImage(bitMatrix);
    }

    @Override
    public BufferedImage createQRCodeAddLogo(String contents, String logoUri) throws WriterException, IOException {
        return setMatrixLogo(createQRCodeToBufferedImage(contents), logoUri);
    }

    @Override
    public void createQRCode(String contents) throws WriterException, IOException {
        createQRCode(contents, null, null);
    }

    @Override
    public void createQRCode(String contents, String outFileUri) throws WriterException, IOException {
        createQRCode(contents, outFileUri, null);
    }

    @Override
    public void createQRCode(String contents, String outFileUri, String logoUri) throws WriterException, IOException {

        Path path = Paths.get(".");
        if (outFileUri != null) {
            path = Paths.get(outFileUri);
        }
        File tempFile = Files.createTempFile(path, "qrcode_", "." + getConf().getFileSuffix()).toFile();
        logger.debug("tempFile absolute path: {} ", tempFile.getAbsoluteFile());

        BufferedImage image = toBufferedImage(createQRCodeMatrix(contents));
        if (logoUri != null) {
            image = setMatrixLogo(image, logoUri);
        }
        ImageIO.write(image, getConf().getFileSuffix(), tempFile);
        logger.debug("二维码生成成功，文件在 {}", tempFile.getAbsoluteFile());
    }

    @Override
    public String decodeQRCode(BufferedImage qrcodeImage) throws NotFoundException {
        BinaryBitmap binaryBitmap = new BinaryBitmap(
            new HybridBinarizer(new BufferedImageLuminanceSource(qrcodeImage)));
        EnumMap<DecodeHintType, Object> hints = Maps.newEnumMap(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, getConf().getCharacterSet());
        return new MultiFormatReader().decode(binaryBitmap, hints).getText();
    }

    @Override
    public String decodeQRCode(File file) throws NotFoundException, IOException {
        return decodeQRCode(ImageIO.read(file));
    }




   

}