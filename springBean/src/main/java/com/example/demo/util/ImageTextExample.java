package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
public class ImageTextExample {
    public static void main(String[] args) {
        String text = "Hello, World!";
        int fontSize = 30;
        String imagePath = "/Users/cuiyueyang/Desktop/temp/picture/uavms-1.png"; // 图片路径
        String outputPath = "/Users/cuiyueyang/Desktop/uavms-1.png"; // 输出图片路径
 
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            Graphics2D g2d = (Graphics2D) image.getGraphics();
 
            // 设置文字颜色和字体
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, fontSize));
 
            // 获取文字的尺寸
            FontMetrics metrics = g2d.getFontMetrics();
            Rectangle2D textRect = metrics.getStringBounds(text, g2d);
 
            // 计算文字放置的位置，使其居中
            int textX = (image.getWidth() - (int) textRect.getWidth()) / 2;
            int textY = (image.getHeight() - (int) textRect.getHeight()) / 2;

            // 在图片上绘制文字
            g2d.drawString(text, textX, textY);
 
            // 释放图形上下文使用的系统资源
            g2d.dispose();
 
            // 保存新的图片
            ImageIO.write(image, "jpg", new File(outputPath));
 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}