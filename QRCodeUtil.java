package org.starry.sleep.commons;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * 二维码生成工具类
 */
public class QRCodeUtil {
    /**
     *
     * @param codeUrl,x,y,pictureFormat
     * @return 图片的Base64字符串
     * @throws Exception
     */
    public static String genQRCode(String codeUrl,int x,int y,String pictureFormat) throws Exception{
        String image = null;
        OutputStream stream = null;
        if(StringUtils.isNotBlank(codeUrl)){
            String code_url = codeUrl;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            if(x<=0||y<=0){
                x=y=200;
            }
            BitMatrix m = writer.encode(code_url, BarcodeFormat.QR_CODE, x,y);
            if(StringUtils.isBlank(pictureFormat)){
                pictureFormat="png";
            }
            MatrixToImageWriter.writeToStream(m, pictureFormat, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            image = Base64.encodeBase64String(bytes);
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        }
        return image;
    }
}
