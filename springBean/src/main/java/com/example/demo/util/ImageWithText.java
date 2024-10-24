package com.example.demo.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
 
public class ImageWithText {

    private static byte[] writDate(byte[] signFileByteArr) throws IOException {
        // 读取图片
        ByteArrayInputStream inputStream = new ByteArrayInputStream(signFileByteArr);
        BufferedImage image = ImageIO.read(inputStream);

        //创建日期图片
        BufferedImage textImage = new BufferedImage(image.getWidth(), 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = textImage.createGraphics();
        //这两步很重要 否则背景黑色
        g2d.setBackground(Color.WHITE);
        g2d.fillRect(0, 0, image.getWidth(), 50);
        // 设置文字颜色和字体
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("宋体", Font.PLAIN, 50));
        // 在新图片上绘制日期文字
        g2d.drawString(DateUtil.today(), 290, 40);
        g2d.dispose();

        // 创建一个新的图片，将原始图片和文字图片合并
        BufferedImage combinedImage = new BufferedImage(image.getWidth(), image.getHeight() + 50, BufferedImage.TYPE_INT_RGB);
        Graphics2D combinedGraphics = combinedImage.createGraphics();
        //这两步很重要 否则背景黑色
        combinedGraphics.setBackground(Color.WHITE);
        combinedGraphics.fillRect(0, 0, image.getWidth(), image.getHeight() + 50);
        // 绘制原始图片
        combinedGraphics.drawImage(image, 0, 0, null);
        // 绘制文字图片，位于原始图片下方
        combinedGraphics.drawImage(textImage, 0, image.getHeight(), null);

        // 释放资源
        combinedGraphics.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "PNG", outputStream);
        return outputStream.toByteArray();

    }

    private static byte[] writDate2(byte[] signFileByteArr) throws IOException {
        // 读取图片
        ByteArrayInputStream inputStream = new ByteArrayInputStream(signFileByteArr);
        BufferedImage image = ImageIO.read(inputStream);

        //创建日期图片
        BufferedImage textImage = new BufferedImage(image.getWidth(), 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = textImage.createGraphics();
        //这两步很重要 否则背景黑色
        g2d.setBackground(Color.GREEN);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, image.getWidth(), 25);
        // 设置文字颜色和字体
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("宋体", Font.PLAIN, 25));
//        // 在新图片上绘制日期文字
        g2d.drawString("2024年9月19日 12:02:02 桐乡市xxxxxxxxxx路口", 0, 20);
        g2d.dispose();

        // 创建一个新的图片，将原始图片和文字图片合并
        BufferedImage combinedImage = new BufferedImage(image.getWidth(), image.getHeight() + 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D combinedGraphics = combinedImage.createGraphics();
        //这两步很重要 否则背景黑色
        combinedGraphics.setBackground(Color.GREEN);
        combinedGraphics.fillRect(0, 0, image.getWidth(), image.getHeight() + 25);
        // 绘制原始图片
        combinedGraphics.drawImage(image, 0, 0, null);
        // 绘制文字图片，位于原始图片下方
        combinedGraphics.drawImage(textImage, 0, image.getHeight(), null);
        // 释放资源
        combinedGraphics.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(combinedImage, "PNG", outputStream);
        return outputStream.toByteArray();

    }

    public static void main(String[] args) throws IOException {
        System.out.println(System.currentTimeMillis());
        String url = "http://192.168.90.111:3100/aila-dev/aila/uavms-1.png";
        //将url读取为数组
        byte[] signFileByteArr = ImageUrlToByteArray.convertImageUrlToByteArray(url);
        String imagePath = "/Users/cuiyueyang/Desktop/temp/picture/uavms-1.png";
        //读取 图片 转化为 byte[]
//        byte[] signFileByteArr = FileUtil.readBytes(new File(imagePath));
        byte[] temp2 = writDate2(signFileByteArr);
        FileUtil.writeBytes(temp2, "/Users/cuiyueyang/Desktop/uavms-2.png");
        System.out.println(System.currentTimeMillis());
    }



}