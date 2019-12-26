package com.ycm.eidea.util;

import java.awt.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 全局配置
 */
public final class Config {


     // 基本特性
    // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
    public static final ErrorCorrectionLevel ERROR_CORRECTION = ErrorCorrectionLevel.H;
    // 内容所使用字符集编码
    public static final String CHARACTER_SET = "utf-8";
    // 设置二维码边的空度，非负数
    public static final Integer MARGIN = 1;
    //编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
    //Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
    //MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E 1D,UPC/EAN extension,UPC_EAN_EXTENSION 
    public static final BarcodeFormat CODE_FORMAT = BarcodeFormat.QR_CODE;
    // 尺寸，大小
    public static final int SIZE = 400;
    // 文件后缀
    public static final String FILE_SUFFIX = "jpg";

    // 颜色
    // 前景色
    public static final Integer FOREGROUND_COLOR = Color.BLACK.getRGB();
    // 背景色
    public static final Integer BACKGROUND_COLOR = Color.RED.getRGB();
    // 透明背景 0:透明; 1:不透明
    public static final Integer TRANSPARENT_BACKGROUND = 0;


}