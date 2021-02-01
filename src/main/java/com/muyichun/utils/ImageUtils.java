package com.muyichun.utils;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageUtils {

	public static void main(String[] args) {
		String profilePath = "/Users/muyichun/Downloads/xxx/touxiang1.jpeg"; 
		String backPath = "/Users/muyichun/Downloads/xxx/1.jpeg";
		String outFile = "/Users/muyichun/Downloads/xxx/ok.png";
//		zoomImage(profilePath, outFile,500,500);
//		overlapImage(backPath, profilePath, outFile);
	}
	/**https://www.zxgj.cn/g/tupiancaijian 在线获取图片的像素
	 * 图片合成
	 * @param bigPath    打底图片
	 * @param smallPath  覆盖小图片
	 * @param outFile 	 输出图片
	 */
	public static final void overlapImage(String bigPath, String smallPath, String outFile) {
		int img_x = 813;
		int img_y = 243;
		int str_x = 209;
		int str_y = 310;
		try {
			BufferedImage big = ImageIO.read(new File(bigPath));
			BufferedImage small = ImageIO.read(new File(smallPath));
			Graphics2D g = big.createGraphics();
			//合成图片
			g.drawImage(small, img_x, img_y, small.getWidth(), small.getHeight(), null);
			//合成文字
			g.setColor(Color.black);
			g.setFont(new Font("楷体",Font.PLAIN,39));
			g.drawString("哈哈哈",str_x,str_y);
			g.dispose();
			ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    /*
     * 图片缩放,w，h为缩放的目标宽度和高度
     * src为源文件目录，dest为缩放后保存目录
     */
    public static void zoomImage(String src,String dest,int w,int h) {
		try {
			double wr = 0, hr = 0;
			File srcFile = new File(src);
			File destFile = new File(dest);

			BufferedImage bufImg;
			bufImg = ImageIO.read(srcFile);
			Image Itemp = bufImg.getScaledInstance(w, h, bufImg.SCALE_SMOOTH);// 设置缩放目标图片模板
			
			wr = w * 1.0 / bufImg.getWidth(); // 获取缩放比例
			hr = h * 1.0 / bufImg.getHeight();
			
			AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
			Itemp = ato.filter(bufImg, null);
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile); // 写入缩减后的图片
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /*
     * 图片按比率缩放
     * size为文件大小
     */
    public static void zoomImage(String src,String dest,Integer size) throws Exception {
        File srcFile = new File(src);
        File destFile = new File(dest);
        
        long fileSize = srcFile.length();
        if(fileSize < size * 1024)   //文件大于size k时，才进行缩放
            return;
        
        Double rate = (size * 1024 * 0.5) / fileSize; // 获取长宽缩放比例
        
        BufferedImage bufImg = ImageIO.read(srcFile);
        Image Itemp = bufImg.getScaledInstance(bufImg.getWidth(), bufImg.getHeight(), bufImg.SCALE_SMOOTH);
            
        AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(rate, rate), null);
        Itemp = ato.filter(bufImg, null);
        try {
            ImageIO.write((BufferedImage) Itemp,dest.substring(dest.lastIndexOf(".")+1), destFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
