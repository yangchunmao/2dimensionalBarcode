package com.ycm.eidea.qrcode;

import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ycm.eidea.conf.Configured;

public interface QRCode extends Configured {

    /**
     * 生成二维码，写入临时的文件，文件路径为 .
     * @param contents  内容
     * @throws WriterException
     * @throws IOException
     */
    void createQRCode(String contents) throws WriterException, IOException;

    /**
     * 生成二维码,写入自定义目录下的临时的文件
     * @param content
     * @param outFileUri
     * @throws WriterException
     * @throws IOException
     */
    void createQRCode(String contents, String outFileUri) throws WriterException, IOException;

    /**
     * 生成带logo的二维码，写入自定义目录下的临时的文件
     * @param contents
     * @param outFileUri
     * @param logoUri
     * @throws WriterException
     * @throws IOException
     */
    void createQRCode(String contents, String outFileUri, String logoUri) throws WriterException, IOException;

    /**
     * 生成二维码的bit矩阵
     * @param contents  内容
     * @return 
     * @throws WriterException 
     */
    BitMatrix createQRCodeMatrix(String contents) throws WriterException;
    
    /**
     * 生成二维码的BufferedImage
     * @param contents 内容
     * @return
     * @throws WriterException
     */
    BufferedImage createQRCodeToBufferedImage(String contents) throws WriterException;

    /**
     * 生成带Logo的二维码的BufferedImage
     * @param contents
     * @param logoUri
     * @return
     * @throws WriterException
     * @throws IOException
     */
    BufferedImage createQRCodeAddLogo(String contents, String logoUri) throws WriterException, IOException;

    /**
     * 解析二维码
     * @param qrcodeImage
     * @return
     * @throws NotFoundException
     */
    String decodeQRCode(BufferedImage qrcodeImage) throws NotFoundException;

    /**
     * 解析二维码
     * @param file 文件
     * @return
     * @throws NotFoundException
     */
    String decodeQRCode(File file) throws NotFoundException, IOException ;
}