package linkUtilTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * <p>Description: </p>
 * <p>Date: 2023/11/24 09:56</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class ImageTest {

    public static void main(String[] args) throws Exception {
        File imageFile = new File("/Users/cuiyueyang/Desktop/temp/sign2.jpg");
        BufferedImage image = ImageIO.read(imageFile);
        // 获取透明通道
        int transparency = image.getRGB(0, 0);
        transparency = (transparency & 0x00ffffff) == 0x00000000 ? 0xff : 0x00;
        // 创建旋转后的图像
        int width = image.getWidth();
        int height = image.getHeight();
        BufferedImage rotatedImage = new BufferedImage(height, width, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        // 设置渲染规则和旋转角度
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.rotate(Math.toRadians(270), height / 5, width / 3); // 以图片中心为旋转中心旋转45度

        // 将原始图像绘制到新图像上，并设置透明底色
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        rotatedImage.setRGB(0, 0, transparency | (transparency << 8) | (transparency << 16) | (transparency << 24)); // 设置透明底色

        // 将旋转后的图像写入输出文件
        File outputFile = new File("/Users/cuiyueyang/Desktop/sign2.jpg");
        ImageIO.write(rotatedImage, "png", outputFile); // 将旋转后的图像写入输出文件（PNG格式）
    }

}
