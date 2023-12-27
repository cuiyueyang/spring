package com.example.demo.util;

import cn.hutool.core.img.ImgUtil;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

/**
 * @program: SignPdf
 * @description:
 * @author: syske
 * @create: 2019-12-03 22:54
 */

@Slf4j
public class SignPdfUtil3 {

    public static byte[] getByte(String pdfUrl) throws Exception {
        URL urlfile = new URL(pdfUrl);
        InputStream inputStream = urlfile.openStream();
        byte[] data = null;
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            int bytesRead;
            // 从输入流中读取字节并写入到输出流中
            while ((bytesRead = inputStream.read()) != -1) {
                outputStream.write(bytesRead);
            }
            // 将输出流转换为byte数组
            data = outputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭输入流
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return data;
    }

    public static byte[] signCoordinate(byte[] byteArray, String signUrl, int x, int y, float imgWidth, float imgHeight, float rotation, int... pageNum) throws Exception {
        InputStream pdf = new ByteArrayInputStream(byteArray);
        URL signFile = new URL(signUrl);
        InputStream sign = signFile.openStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImgUtil.scale(sign, byteArrayOutputStream, 0.5f);
        byte[] signImgByte = byteArrayOutputStream.toByteArray();
        //转换为透明底色的png
        signImgByte = transferAlpha(signImgByte);
        return addImgToDoc(pdf, signImgByte, x, y, imgWidth, imgHeight, rotation, pageNum);
    }

    public static byte[] transferAlpha(byte[] bytes) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ByteArrayInputStream is = new ByteArrayInputStream(bytes);
        try {
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
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 将一个图片添加到PDF文件上，类似签名
     */
    public static byte[] addImgToDoc(InputStream docInput, byte[] imgInput, int x, int y, float imgWidth, float imgHeight, float rotation, int[] pageNum) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            PdfReader reader = new PdfReader(docInput);
            PdfStamper stamp = new PdfStamper(reader, baos);
            stamp.setRotateContents(false);
            Image img = Image.getInstance(imgInput);
            img.setAbsolutePosition(x, y);
            for (int pn : pageNum) {
                int rr = reader.getPageRotation(pn) / 90;
                if (rr % 2 == 1) {
                    float t = imgWidth;
                    imgWidth = imgHeight;
                    imgHeight = t;
                }
                img.scaleAbsoluteWidth(imgWidth);
                img.scaleAbsoluteHeight(imgHeight);
                // 旋转角度
                img.setRotationDegrees(rotation + reader.getPageRotation(pn));
                PdfContentByte over = stamp.getOverContent(pn);
                over.addImage(img);
            }
            stamp.close();
            reader.close();
        } catch (Exception e) {
            throw new Exception("PDF签名失败");
        }
        return baos.toByteArray();
    }

    /**
     * 根据坐标在pdf上添加文字
     *
     * @param contentText 添加的文字
     * @param page        添加的第几页
     * @param x           添加位置x坐标
     * @param y           添加位置y坐标
     * @return
     * @throws Exception
     */
    public static byte[] signAddText(byte[] url, String contentText, Integer page, Integer x, Integer y)
            throws Exception {
        PdfReader reader = new PdfReader(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, byteArrayOutputStream);
        PdfContentByte content;
        ////HYShuSongErKW
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
        PdfGState gs = new PdfGState();
        //在内容下方加水印
        content = stamper.getUnderContent(page);
        gs.setFillOpacity(0.2f);
        content.beginText();
        //字体大小
        content.setFontAndSize(base, 12);
        //内容居中，横纵坐标，偏移量
        content.showTextAligned(Element.ALIGN_LEFT, contentText, x, y, 0);
        content.endText();
        PdfContentByte content2;
        //在内容下方加水印
        content2 = stamper.getUnderContent(page);
        content2.beginText();
        //字体大小
        content2.setFontAndSize(base, 12);
        //内容居中，横纵坐标，偏移量
        Calendar cal = Calendar.getInstance();
        content2.endText();
        stamper.close();
        //关闭打开的原来PDF文件，不执行reader.close()删除不了（必须先执行stamper.close()，否则会报错）
        reader.close();
        return byteArrayOutputStream.toByteArray();
    }


}