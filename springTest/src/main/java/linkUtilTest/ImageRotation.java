package linkUtilTest;

import java.awt.*;
import java.awt.geom.AffineTransform;  
import java.awt.image.BufferedImage;  
import java.io.File;  
import javax.imageio.ImageIO;  
  
public class ImageRotation {  
    public static void main(String[] args) {  
        try {  
            // 读取原始图片  
            BufferedImage originalImage = ImageIO.read(new File("/Users/cuiyueyang/Desktop/temp/sign2.jpg"));
              
            // 创建一个旋转矩阵  
            double angleInRadians = Math.toRadians(270); // 旋转45度
            AffineTransform at = new AffineTransform();
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();
            at.rotate(angleInRadians, height/3.3, width/3.3);
              
            // 创建一个新的图像，用于绘制旋转后的图像  
            BufferedImage rotatedImage = new BufferedImage(height, width, originalImage.getType());
              
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
}