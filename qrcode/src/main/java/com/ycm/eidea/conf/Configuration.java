package com.ycm.eidea.conf;

import java.util.Hashtable;
import java.util.Map.Entry;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.ycm.eidea.util.Config;


public class Configuration {

    // 基本特性
    // 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）
    public static final String ERROR_CORRECTION = "ERROR_CORRECTION";
    // 内容所使用字符集编码
    public static final String CHARACTER_SET = "CHARACTER_SET";
    // 设置二维码边的空度，非负数
    public static final String MARGIN = "MARGIN";
    // 编码类型，目前zxing支持：Aztec 2D,CODABAR 1D format,Code 39 1D,Code 93 1D ,Code 128 1D,
    // Data Matrix 2D , EAN-8 1D,EAN-13 1D,ITF (Interleaved Two of Five) 1D,
    // MaxiCode 2D barcode,PDF417,QR Code 2D,RSS 14,RSS EXPANDED,UPC-A 1D,UPC-E
    // 1D,UPC/EAN extension,UPC_EAN_EXTENSION
    public static final String CODE_FORMAT = "CODE_FORMAT";
    // 尺寸，大小
    public static final String SIZE = "SIZE";
    // 生成的图片的格式
    public static final String FILE_SUFFIX = "FILE_SUFFIX";

    // 颜色
    // 前景色
    public static final String FOREGROUND_COLOR = "FOREGROUND_COLOR";
    // 背景色
    public static final String BACKGROUND_COLOR = "BACKGROUND_COLOR";
    // 透明背景
    public static final String TRANSPARENT_BACKGROUND = "TRANSPARENT_BACKGROUND";
    // 美化器

    protected Hashtable<String, Object> data = new Hashtable<>();

    public Configuration set(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public String getString(String key) {
        return get(key);
    }

    public <T> T get(String key) {
        return (T) data.get(key);
    }

    public <T> T getOrDefault(String key, T defaultValue) {
        Object value = data.get(key);
        if (null == value) {
            return defaultValue;
        } else {
            return (T) value;
        }
    }

    public ErrorCorrectionLevel getErrorCorrection(){
        return get(ERROR_CORRECTION);
    }

    public String getCharacterSet(){
        return get(CHARACTER_SET);
    }

    public Integer getMargin() {
        return get(MARGIN);
    }

    public BarcodeFormat getCodeFormat() {
        return get(CODE_FORMAT);
    }

    public Integer getSize() {
        return get(SIZE);
    }

    public String getFileSuffix() {
        return get(FILE_SUFFIX);
    }

    public Integer getForegroundColor() {
        return get(FOREGROUND_COLOR);
    }

    public Integer getBackgroundColor() {
        return get(BACKGROUND_COLOR);
    }

    public Integer getTransparentBackground() {
        return get(TRANSPARENT_BACKGROUND);
    }


    public Configuration setSize(Integer size) {
        set(SIZE, size);
        return this;
    }

    public Configuration setErrorCorrection(ErrorCorrectionLevel level) {
        set(ERROR_CORRECTION, level);
        return this;
    }

    protected Configuration copy() {
        Configuration conf = new Configuration();
        conf.data = (Hashtable<String, Object>) data.clone();
        return conf;
    }

    private static Configuration defaultConf = null;

    private static Object configLock = new Object();

    public static Configuration getDefault() {
        if (defaultConf == null) {
            synchronized (configLock) {
                if (defaultConf == null) {
                    defaultConf = new Configuration();
                    defaultConf.set(ERROR_CORRECTION, Config.ERROR_CORRECTION);
                    defaultConf.set(CHARACTER_SET, Config.CHARACTER_SET);
                    defaultConf.set(MARGIN, Config.MARGIN);
                    defaultConf.set(CODE_FORMAT, Config.CODE_FORMAT);
                    defaultConf.set(SIZE, Config.SIZE);
                    defaultConf.set(FILE_SUFFIX, Config.FILE_SUFFIX);
                    // 颜色
                    defaultConf.set(FOREGROUND_COLOR, Config.FOREGROUND_COLOR);
                    defaultConf.set(BACKGROUND_COLOR, Config.BACKGROUND_COLOR);
                    defaultConf.set(TRANSPARENT_BACKGROUND, Config.TRANSPARENT_BACKGROUND);
                }
            }
        }
        return defaultConf;
    }

    public static Configuration copyDefault() {
        return getDefault().copy();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Configuration:\n ");
        for (Entry<String, Object> entry : data.entrySet()) {
            sb.append("\t").append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}