package com.example.demo.util;

import cn.hutool.core.img.ImgUtil;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

/**
 * @program: SignPdf
 * @description:
 * @author: syske
 * @create: 2019-12-03 22:54
 */

@Slf4j
public class SignPdfUtil {
    // 秘钥密码
    public static String PASSWORD = "123456";
    // 秘钥文件路径
    public static String KEY_STORE_PATH = "D:/";

    private SignPdfUtil() {
    }


    public static byte[] rotate90AW(String imgFile) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        URL urlfile = new URL(imgFile);
        InputStream inStream = urlfile.openStream();
        BufferedImage bi = ImageIO.read(inStream);
        int width = bi.getWidth();
        int height = bi.getHeight();

        BufferedImage biFlip = new BufferedImage(height, width, bi.getType());

        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                biFlip.setRGB(j, width - 1 - i, bi.getRGB(i, j));

        ImageIO.write(biFlip, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
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


    /**
     * 查找关键字定位
     *
     * @param numberOfPages
     * @param keyWords      关键字
     * @param reader
     * @return
     * @throws IOException
     */
    private static KeyWordInfo getKeyWordLocation(Integer numberOfPages,
                                                  final String keyWords, PdfReader reader) throws IOException {
        PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(reader);

        final KeyWordInfo keyWordInfo = new KeyWordInfo();


//        TextExtractionStrategy strategy = pdfReaderContentParser.processContent(numberOfPages, new SimpleTextExtractionStrategy());
//        sb.append(strategy.getResultantText());
//        System.out.println(sb.toString());

        RenderListener renderListener = pdfReaderContentParser.processContent(numberOfPages,
                new RenderListener() {
                    StringBuffer sb = new StringBuffer();

                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        String text = textRenderInfo.getText(); // 整页内容
                        sb.append(text);
                        if (sb.toString().contains(keyWords)) {
                            Rectangle2D.Float boundingRectange = textRenderInfo.getBaseline().getBoundingRectange();
                            float leftY = (float) boundingRectange.getMinY() - 1;
                            float rightY = (float) boundingRectange.getMaxY() + 1;

                            log.info(boundingRectange.x + "--" + boundingRectange.y + "---");

                            keyWordInfo.setHeight(rightY - leftY);
                            keyWordInfo.setWidth((rightY - leftY) * keyWords.length());
                            keyWordInfo.setX(boundingRectange.x);
                            keyWordInfo.setY(boundingRectange.y + 20);
                            sb = new StringBuffer();
                        }

                    }

                    @Override
                    public void renderImage(ImageRenderInfo arg0) {
                    }

                    @Override
                    public void endTextBlock() {
                    }

                    @Override
                    public void beginTextBlock() {
                    }
                });
        return keyWordInfo;
    }

    private static class KeyWordInfo {
        //x轴
        private float x;
        //y轴
        private float y;
        private double width;
        private double height;

        public float getX() {
            return x;
        }

        public void setX(float x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(float y) {
            this.y = y;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }

    public enum PageStyle {
        PAGE_STYLE_LANDSCAPE, // 横向
        PAGE_STYLE_PORTRAIT // 纵向
    }


    /**
     * @param x         以左下角为原点的x坐标
     * @param y         左下角为原点的y坐标
     * @param imgHeight 带有签名文字的图片宽度
     * @param imgWidth  带有签名文字的图片高度
     * @param rotation  带有签名文字的图片的旋转角度，逆时针旋转，如果不旋转则为0
     * @param pageNum   需要签名的页码数组，从1开始
     * @return 返回签好名的PDF文件字节码
     */

    public static byte[] signCoordinate(String pdfUrl, String signUrl, int x, int y, float imgWidth, float imgHeight, float rotation, int... pageNum) throws Exception {
        URL urlfile = new URL(pdfUrl);
        InputStream pdf = urlfile.openStream();

        URL signFile = new URL(signUrl);
        InputStream sign = signFile.openStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImgUtil.scale(sign, byteArrayOutputStream, 0.5f);
        byte[] signImgByte = byteArrayOutputStream.toByteArray();
        //转换为透明底色的png
        signImgByte = transferAlpha(signImgByte);
        return addImgToDoc(pdf, signImgByte, x, y, imgWidth, imgHeight, rotation, pageNum);
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
//            byte[] imgBytes = new byte[imgInput.available()];
//            IOUtils.read(imgInput, imgBytes);

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
                img.setRotationDegrees(rotation + reader.getPageRotation(pn)); // 旋转角度
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
    public static byte[] signAddText(byte[] url, String contentText, Integer page, Integer x, Integer y, String remark)
            throws Exception {
        PdfReader reader = new PdfReader(url);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader, byteArrayOutputStream);
        PdfContentByte content;
        ////HYShuSongErKW
        BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//            BaseFont base = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        PdfGState gs = new PdfGState();
        //content = stamper.getOverContent(i);// 在内容上方加水印
        content = stamper.getUnderContent(page);//在内容下方加水印
        gs.setFillOpacity(0.2f);
        content.beginText();
        //字体大小
        content.setFontAndSize(base, 12);
        //content.setTextMatrix(70, 200);
        //内容居中，横纵坐标，偏移量
        content.showTextAligned(Element.ALIGN_RIGHT, contentText, x, y, 0);
        // //添加图片
        // Image image = Image.getInstance("D:\\测试图片.jpg");
        //
        // /*
        //   img.setAlignment(Image.LEFT | Image.TEXTWRAP);
        //   img.setBorder(Image.BOX); img.setBorderWidth(10);
        //   img.setBorderColor(BaseColor.WHITE); img.scaleToFit(100072);//大小
        //   img.setRotationDegrees(-30);//旋转
        //  */
        // //图片的位置（坐标）
        // image.setAbsolutePosition(520, 786);
        // // image of the absolute
        // image.scaleToFit(200, 200);
        // image.scalePercent(15);//依照比例缩放
        // content.addImage(image);
//            content.setColorFill(BaseColor.BLACK);
        content.endText();
        PdfContentByte content2;
        content2 = stamper.getUnderContent(page);//在内容下方加水印
        content2.beginText();
        //字体大小
        content2.setFontAndSize(base, 12);
        //content.setTextMatrix(70, 200);
        //内容居中，横纵坐标，偏移量
        Calendar cal = Calendar.getInstance();
        // 当前月
        int month = cal.get(Calendar.MONTH) + 1;
        // 当前日
        int day = cal.get(Calendar.DATE);
        // 当前小时
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        // 当前分钟
        int minute = cal.get(Calendar.MINUTE);
        //填充月份
        content2.showTextAligned(Element.ALIGN_RIGHT, month + "", x + 30, y, 0);
        //填充日期
        content2.showTextAligned(Element.ALIGN_RIGHT, day + "", x + 60, y, 0);
        //填充小时
        content2.showTextAligned(Element.ALIGN_RIGHT, hour + "", x + 30, y - 23, 0);
        //填充分钟
        content2.showTextAligned(Element.ALIGN_RIGHT, minute + "", x + 60, y - 23, 0);
        //填充备注
        content2.showTextAligned(Element.ALIGN_LEFT, remark, x - 240, y - 230, 0);

        content2.endText();
        stamper.close();
        //关闭打开的原来PDF文件，不执行reader.close()删除不了（必须先执行stamper.close()，否则会报错）
        reader.close();
        //删除原来的PDF文件
        // File targetTemplePDF = new File(inputPDFFilePath);
        // targetTemplePDF.delete();
        //inputPDFFile.close();
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 在询问笔录2.pdf上拼接签名图片及签名时间（文案）
     *
     * @param pdfUrl
     * @param signImgUrl
     * @return
     */
    public static byte[] signImgAndText(String pdfUrl, String signImgUrl) throws Exception {

        URL url = new URL(pdfUrl);
        InputStream inputStream = url.openStream();

        URL urlImg = new URL(signImgUrl);
        InputStream urlImgStream = urlImg.openStream();
        PdfReader pdfReader = new PdfReader(inputStream);
        //获取pdf总页数
        int pages = pdfReader.getNumberOfPages();
        byte[] signImgByte = urlTobyte(signImgUrl);
        //对图片进行等比例缩放
        //ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //ImgUtil.scale(urlImgStream, byteArrayOutputStream, 0.5f);
        //byte[] signImgByte = byteArrayOutputStream.toByteArray();
        //设置固定长宽
        signImgByte = resizeImage(signImgByte, 40, 90);
        //转换为透明底色的png
        signImgByte = transferAlpha(signImgByte);
        //获取pdf
        byte[] pdfByte = urlTobyte(pdfUrl);
        for (int i = 0; i < pages; i++) {
            pdfByte = findKeyWordLocation(pdfByte, signImgByte, "被询问人签字", i + 1);
        }
        return pdfByte;
    }


    /**
     * 根据关键字定位签名
     *
     * @param signPdfSrc    签名的PDF文件
     * @param signImage     签名图片完整路径
     * @param keyWords      关键字
     * @param numberOfPages 签名页码，如果是最后一页则传null
     * @return
     */
    public static byte[] findKeyWordLocation(byte[] signPdfSrc,
                                             byte[] signImage, String keyWords,
                                             Integer numberOfPages) throws Exception {
        PdfReader pdfReader = null;
        PdfReader textPdfReader = null;
        try {
            pdfReader = new PdfReader(signPdfSrc); // 也可以输入流的方式构建
            Image image = Image.getInstance(signImage);
            float llx = 0f;
            float lly = 0f;
            float signImageWidth = image.getWidth();
            float signImageHeight = image.getHeight();
            float signImageHeightSocale = 85 / signImageWidth * signImageHeight;
            if (keyWords != null && !keyWords.isEmpty()) {
                //找到 签名 插入的位置
                KeyWordInfo keyWordInfo = getKeyWordLocation(numberOfPages, keyWords, pdfReader);
                Rectangle pageSize = pdfReader.getPageSize(numberOfPages);
                float width = pageSize.getWidth();
                llx = keyWordInfo.getX() + (float) keyWordInfo.getWidth() * keyWords.length();
                lly = keyWordInfo.getY() - signImageHeightSocale / 2;

            } else {
                throw new Exception("坐标和关键字不能同时为空！");
            }

            float urx = llx - 50;
            float ury = lly + 60;
            int xLocation = Math.round(urx);
            int yLocation = Math.round(ury);
            int[] pages = {numberOfPages};
            //签名图片拼接
            byte[] bytes = addImgToDoc(new ByteArrayInputStream(signPdfSrc), signImage, Math.round(urx), Math.round(ury), 40, 90, 90, pages);

            //对拼接签名图片后的pdf拼接签名时间
            textPdfReader = new PdfReader(bytes);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfStamper stamper = new PdfStamper(textPdfReader, byteArrayOutputStream);
            int total = textPdfReader.getNumberOfPages() + 1;
            PdfContentByte content;
            ////HYShuSongErKW
            BaseFont base = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
//            BaseFont base = BaseFont.createFont("STSong-Light", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            PdfGState gs = new PdfGState();
            //content = stamper.getOverContent(i);// 在内容上方加水印
            content = stamper.getUnderContent(numberOfPages);//在内容下方加水印
            gs.setFillOpacity(0.2f);
            content.beginText();
            //字体大小
            content.setFontAndSize(base, 12);
            content.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
            content.setLineWidth(0.1);
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            // 当前月
            int month = cal.get(Calendar.MONTH) + 1;
            // 当前日
            int day = cal.get(Calendar.DATE);
            // 当前小时
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            // 当前分钟
            int minute = cal.get(Calendar.MINUTE);
            //content.setTextMatrix(70, 200);
            //内容居中，横纵坐标，偏移量
            content.showTextAligned(Element.ALIGN_RIGHT, year + "", xLocation + 115, yLocation + 15, 0);
            // //添加图片
            // Image image = Image.getInstance("D:\\测试图片.jpg");
            //
            // /*
            //   img.setAlignment(Image.LEFT | Image.TEXTWRAP);
            //   img.setBorder(Image.BOX); img.setBorderWidth(10);
            //   img.setBorderColor(BaseColor.WHITE); img.scaleToFit(100072);//大小
            //   img.setRotationDegrees(-30);//旋转
            //  */
            // //图片的位置（坐标）
            // image.setAbsolutePosition(520, 786);
            // // image of the absolute
            // image.scaleToFit(200, 200);
            // image.scalePercent(15);//依照比例缩放
            // content.addImage(image);
//            content.setColorFill(BaseColor.BLACK);
            content.endText();
            PdfContentByte content2;
            content2 = stamper.getUnderContent(numberOfPages);//在内容下方加水印
            content2.beginText();
            //字体大小
            content2.setFontAndSize(base, 12);
            content2.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
            content2.setLineWidth(0.1);


            //content.setTextMatrix(70, 200);
            //内容居中，横纵坐标，偏移量
            //填充固定文案
            content2.showTextAligned(Element.ALIGN_LEFT, "以上笔录我已看过，与我说的一致，无异议。", xLocation , yLocation + 55, 0);
            //填充月份
            content2.showTextAligned(Element.ALIGN_RIGHT, month + "", xLocation + 140, yLocation + 15, 0);
//            //填充日期
            content2.showTextAligned(Element.ALIGN_RIGHT, day + "", xLocation + 163, yLocation + 15, 0);
//            //填充小时
            content2.showTextAligned(Element.ALIGN_RIGHT, hour + "", xLocation + 187, yLocation + 15, 0);
//            //填充分钟
            content2.showTextAligned(Element.ALIGN_RIGHT, minute + "", xLocation + 211, yLocation + 15, 0);
            content2.endText();
            stamper.close();
            //关闭打开的原来PDF文件，不执行reader.close()删除不了（必须先执行stamper.close()，否则会报错）
            pdfReader.close();
            textPdfReader.close();
            //删除原来的PDF文件
            // File targetTemplePDF = new File(inputPDFFilePath);
            // targetTemplePDF.delete();
            //inputPDFFile.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            log.error("签章失败", e);
            throw new Exception("签章失败", e);
        } finally {
        }
    }


    public static byte[] urlTobyte(String url) throws MalformedURLException {
        URL ur = new URL(url);
        BufferedInputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new BufferedInputStream(ur.openStream());
            out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        byte[] content = out.toByteArray();
        return content;
    }


    /**
     * 通过BufferedImage图片流调整图片大小
     */
    public static byte[] resizeImage(byte[] byteImage, int targetWidth, int targetHeight) throws IOException {
        BufferedImage originalImage = bytesToBufferedImage(byteImage);
        java.awt.Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, java.awt.Image.SCALE_AREA_AVERAGING);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        byte[] bytes = imageToBytes(outputImage);
        return bytes;
    }

    /**
     * BufferedImage图片流转byte[]数组
     */
    public static byte[] imageToBytes(BufferedImage bImage) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(bImage, "jpg", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    /**
     * byte[]数组转BufferedImage图片流
     */
    private static BufferedImage bytesToBufferedImage(byte[] ImageByte) {
        ByteArrayInputStream in = new ByteArrayInputStream(ImageByte);
        BufferedImage image = null;
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}