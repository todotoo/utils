package org.wg.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 生成二维码
 * Created by run on 2017/7/2.
 */
public class QrCodeGenerate {
    public static void main(String[] args) {
        //二维码图片保存后的绝对路径
        String imgPath = "E:/a/b.jpg";
        File qrCodeFile = new File(imgPath); //二维码图片文件
        if (!qrCodeFile.exists()) {
            qrCodeFile.mkdirs();
        }
        String spreadUrl = "https://www.baidu.com";

        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); // 内容所使用字符集编码
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(spreadUrl, BarcodeFormat.QR_CODE, 208, 208, hints);
            MatrixToImageWriter.writeToFile(bitMatrix, "jpg", qrCodeFile);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
