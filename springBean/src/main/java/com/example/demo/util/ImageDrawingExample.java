package com.example.demo.util;

import javax.imageio.ImageIO;
import java.awt.*;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import java.io.IOException;  
  
public class ImageDrawingExample {  
    public static void main(String[] args) {  
        try {  
            // 创建一个宽高都为200的BufferedImage，类型为TYPE_INT_ARGB（带有alpha通道）  
            BufferedImage textImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);  
  
            // 获取Graphics2D对象以在textImage上绘制  
            Graphics2D g2d = textImage.createGraphics();  
  
            // 使用黑色填充整个图像（设置背景色）  
            g2d.setColor(Color.BLACK);  
            g2d.fillRect(0, 0, textImage.getWidth(), textImage.getHeight());  
  
            // 设置文本颜色为白色  
            g2d.setColor(Color.WHITE);  
  
            // 设置字体（可选）  
            g2d.setFont(new Font("Serif", Font.PLAIN, 24));  
  
            // 在图像上绘制文本  
            g2d.drawString("Hello, World!", 50, 50);  
  
            // 释放Graphics2D资源  
            g2d.dispose();  
  
            // 保存图像到文件（可选）  
            ImageIO.write(textImage, "PNG", new File("/Users/cuiyueyang/Desktop/uavms-3.png"));
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}