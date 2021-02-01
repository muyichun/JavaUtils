package org.starry.bd.rrm.commons;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * 图片转换base64或base64转换成图片
 * @author Administrator
 *
 */
public class ImgToBase64Util {
	/**
	 * 将图片文件转化为字节数组字符串，并对其进行Base64编码处理   
	 * @param imgFile 图片路径
	 * @return
	 */
	public static String getImgStr(String imgFile){
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try 
        {
            in = new FileInputStream(imgFile);        
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
	}
	 /**
     * 对字节数组字符串进行Base64解码并生成图片
     * @param imgStr 图片数据
     * @param imgFilePath 保存图片全路径地址
     * @return
     */
    public static boolean generateImage(String imgStr,String imgFilePath){
        //
        if (imgStr == null) //图像数据为空
            return false;     
        try 
        {
            //Base64解码
            byte[] b = Base64.decodeBase64(imgStr);
            for(int i=0;i<b.length;++i)
            {
                if(b[i]<0)
                {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            OutputStream out = new FileOutputStream(imgFilePath);    
            out.write(b);
            out.flush();
            out.close();
            return true;
        } 
        catch (Exception e) 
        {
            return false;
        }
    }
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