package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
public class WatermarkExample {
    public static void main(String[] args) {
        String imagePath = "/Users/cuiyueyang/Desktop/temp/picture/uavms-1.png"; // 图片路径
        String outputPath = "/Users/cuiyueyang/Desktop/uavms-1.png"; // 输出图片路径
        String watermarkText = "备注文字"; // 要添加的备注文字
 
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            int imageWidth = originalImage.getWidth();
            int imageHeight = originalImage.getHeight();
 
            BufferedImage newImage = new BufferedImage(imageWidth, imageHeight + 30, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = newImage.createGraphics();
 
            // 绘制原始图片
            g2d.drawImage(originalImage, 0, 0, null);
 
            // 设置文字颜色和字体
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.PLAIN, 18));
 
            // 在图片下方添加文字
            g2d.drawString(watermarkText, 10, imageHeight + 15); // 10和15是文字位置的x和y坐标
 
            g2d.dispose();
 
            // 保存新图片
            ImageIO.write(newImage, "jpg", new File(outputPath));
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}