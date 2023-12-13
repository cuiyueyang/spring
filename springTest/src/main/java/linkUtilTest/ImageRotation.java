package linkUtilTest;

import java.awt.*;
import java.awt.geom.AffineTransform;  
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ImageRotation {  
    public static void main(String[] args) {  
        try {
//            String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
//            rotatedImage(image);

            // 读取原始图片  
            BufferedImage originalImage = ImageIO.read(new File("/Users/cuiyueyang/Desktop/temp/sign2.jpg"));
            // 获取图像的透明通道
            int transparency = originalImage.getRGB(0, 0);
            transparency = (transparency & 0x00ffffff) == 0x00000000 ? 0xff : 0x00;

            // 创建一个旋转矩阵
            double angleInRadians = Math.toRadians(270); // 旋转45度
            AffineTransform at = new AffineTransform();
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            at.rotate(angleInRadians, height/5, width/3);
              
            // 创建一个新的图像，用于绘制旋转后的图像  
            BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
            rotatedImage.setRGB(0, 0, transparency | (transparency << 8) | (transparency << 16) | (transparency << 24));
            // 获取图形上下文，并绘制旋转后的图像
            Graphics2D g2d = rotatedImage.createGraphics();  
            g2d.drawImage(originalImage, at, null);  
            g2d.dispose();  
              
            // 保存旋转后的图像  
            ImageIO.write(rotatedImage, "jpg", new File("/Users/cuiyueyang/Desktop/sign1.jpg"));
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
    }

    public static byte[] transferAlpha(byte[] bytes) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        try {
//            is = new FileInputStream(file);
            // 如果是MultipartFile类型，那么自身也有转换成流的方法：is = file.getInputStream();
            BufferedImage bi = ImageIO.read(is);
            java.awt.Image image = (java.awt.Image) bi;
            ImageIcon imageIcon = new ImageIcon(image);
            BufferedImage bufferedImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
                    BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g2D = (Graphics2D) bufferedImage.getGraphics();
            g2D.drawImage(imageIcon.getImage(), 0, 0, imageIcon.getImageObserver());
            int alpha = 0;
            for (int j1 = bufferedImage.getMinY(); j1 < bufferedImage.getHeight(); j1++) {
                for (int j2 = bufferedImage.getMinX(); j2 < bufferedImage.getWidth(); j2++) {
                    int rgb = bufferedImage.getRGB(j2, j1);

                    int R = (rgb & 0xff0000) >> 16;
                    int G = (rgb & 0xff00) >> 8;
                    int B = (rgb & 0xff);
                    if (((255 - R) < 30) && ((255 - G) < 30) && ((255 - B) < 30)) {
                        rgb = ((alpha + 1) << 24) | (rgb & 0x00ffffff);
                    }

                    bufferedImage.setRGB(j2, j1, rgb);

                }
            }
            g2D.drawImage(bufferedImage, 0, 0, imageIcon.getImageObserver());
//            ImageIO.write(bufferedImage, "png", new File("/Users/luohaojie/Desktop/sign/2.png"));// 直接输出文件
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return byteArrayOutputStream.toByteArray();
    }

    private static InputStream rotatedImage(String signUrl) throws Exception {
        String images= signUrl;
        URL signFile = new URL(images);
        BufferedImage originalImage = ImageIO.read(signFile);
        double angleInRadians = Math.toRadians(270);
        AffineTransform at = new AffineTransform();
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        at.rotate(angleInRadians, height/5, width/3);
        // 创建一个新的图像，用于绘制旋转后的图像
        BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
        // 获取图形上下文，并绘制旋转后的图像
        Graphics2D g2d = rotatedImage.createGraphics();
        g2d.drawImage(originalImage, at, null);
        g2d.dispose();
        ImageIO.write(rotatedImage, "jpg", new File("/Users/cuiyueyang/Desktop/sign1.jpg"));
        File file = new File("/Users/cuiyueyang/Desktop/sign1.jpg");
        InputStream inputStream = new FileInputStream(file);
        return inputStream;
    }


    public static InputStream bufferedImageToInputStream(BufferedImage bufferedImage) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", os);
        return new ByteArrayInputStream(os.toByteArray());
    }

}