package com.example.demo.util;

import cn.hutool.core.img.ImgUtil;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2023/11/07 18:26</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignDocUtils {

    public static XWPFDocument signDocText(XWPFDocument document, String text, int row, int cell) throws Exception {
        List<XWPFTable> xwpfTables = document.getTables();
        //插入文字
        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
        Integer size = xwpfTableCell.getParagraphArray(0).getRuns().size();
        XWPFRun xwpfRun = xwpfTableCell.getParagraphArray(0).getRuns().get(size-1);
        xwpfRun.setText(text);
        return document;
    }

    public static XWPFDocument signDocPic(String docUrl, String signUrl, int row, int cell) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFTable> xwpfTables = document.getTables();
        //插入图片
        // 获取第一行第一列单元格
        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
        //读取图片文件
        URL img = new URL(signUrl);
        InputStream fileInputStream = img.openStream();
        // 添加图片到单元格
        XWPFRun run = xwpfTableCell.getParagraphArray(0).createRun();
        run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", 342400, 342400);
        return document;
    }

    public static XWPFDocument signDocPic3(String docUrl, String signUrl, int row, int cell) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
        for (XWPFParagraph xwpfParagraph : xwpfParagraphs) {
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                String text = xwpfRun.getText(0);
                if ("当事人或其代理人意见：".equals(text)) {
                    int position = text.indexOf("当事人或其代理人意见：");
                    if (position != -1) {
                        // 在插入点后面添加文本
                        String newText = text.substring(0, position) +"当事人或其代理人意见：cs22eeeeeeeeeeeeeeeeeeeeeeeeeeeeeEeeeqqqqqqqqqqqqqqqqqqqqqqq" + "          2023年11月9日 11时11分";
                        // 替换原来的文本为新的文本
                        xwpfRun.setText(newText, 0);
                    }
                }

                if ("当事人或其代理人签名：".equals(text)) {
                    //读取图片文件
                    URL img = new URL(signUrl);
                    InputStream fileInputStream = img.openStream();
                    xwpfRun.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", 684800, 684800);
                }
                System.out.println(text);
            }
        }
        return document;
    }

    public static XWPFDocument signDocPicTable(String docUrl, String signUrl, int row, int cell) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFTable> xwpfTables = document.getTables();
        //插入图片
        // 获取第一行第一列单元格
        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
        //读取图片文件
        URL img = new URL(signUrl);
        InputStream fileInputStream = img.openStream();
        // 添加图片到单元格
        XWPFRun run = xwpfTableCell.getParagraphArray(1).createRun();
        run.setText("cscs");
        run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", 342400, 342400);
        return document;
    }

    public static XWPFDocument signDocTextTable(XWPFDocument document, String text, int row, int cell) throws Exception {
        List<XWPFTable> xwpfTables = document.getTables();
        //插入文字
        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
        Integer size = xwpfTableCell.getParagraphArray(0).getRuns().size();
        XWPFRun xwpfRun;
        if (size == 0) {
            xwpfRun = xwpfTableCell.getParagraphArray(0).createRun();
        } else {
            xwpfRun = xwpfTableCell.getParagraphArray(0).getRuns().get(size-1);
        }
        xwpfRun.setText(text);
        return document;
    }

    public static XWPFDocument signDocPicText(String docUrl, String signUrl, String mark, int picWidth, int picHight) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
        for (int a = 0; a <xwpfParagraphs.size(); a++) {
            XWPFParagraph xwpfParagraph = xwpfParagraphs.get(a);
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                String text = xwpfRun.getText(0);
                if (mark.equals(text)) {
                    //读取图片文件
                    URL imgUrl = new URL(signUrl);
                    InputStream fileInputStream = imgUrl.openStream();
                    xwpfRun.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", picWidth, picHight);
                    xwpfRun.setText("cscs");
                }
            }
        }
        document = signDocTimeText(document, 30);
        return document;
    }

    public static XWPFDocument seeDocText(String docUrl) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
        for (int a = 0; a <xwpfParagraphs.size(); a++) {
            XWPFParagraph xwpfParagraph = xwpfParagraphs.get(a);
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                String text = xwpfRun.getText(0);
                if (StringUtils.isNotBlank(text)) {
                    if (text.contains("被询问人签字：")) {
                        int position = text.indexOf("被询问人签字：");
                        if (position != -1) {
                            // 在插入点后面添加文本
                            String newText = text.substring(0, position) + "被询问人签字：";
                            // 替换原来的文本为新的文本
                            xwpfRun.setText(newText, 0);
                        }
                        xwpfRun.setText("        时间");
                    }
                }
                System.out.println("1" + text);
            }
        }
        return document;
    }

    public static XWPFDocument seeDocTable(String docUrl) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);
        List<XWPFTable> xwpfTables = document.getTables();
        for (XWPFTable xwpfTable : xwpfTables) {
            int a = 0;
            List<XWPFTableRow> rows = xwpfTable.getRows();
            for (XWPFTableRow xwpfTableRow : rows) {
                List<XWPFTableCell> cells = xwpfTableRow.getTableCells();
                int b= 0;
                for (XWPFTableCell xwpfTableCell : cells) {
                    List<XWPFRun> runs = xwpfTableCell.getParagraphArray(0).getRuns();
                    Integer size = runs.size();
                    if (size > 0) {
                        XWPFRun xwpfRun = xwpfTableCell.getParagraphArray(0).getRuns().get(size-1);
                        String text = xwpfRun.getText(0);
                        System.out.println(a+","+b +"|"+text);
                    }
                    b++;
                }
                a++;
            }
        }
        return document;
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

    public static XWPFDocument signDocTimeText(XWPFDocument document, int size) {
        XWPFRun xwpfRun = document.getParagraphs().get(size).createRun();
        xwpfRun.setText("2023年11月11日 11时11分");
        return document;
    }

    public static XWPFDocument signDocTextText(XWPFDocument document, String remark, String mark) throws Exception {
        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
        for (int a = 0; a <xwpfParagraphs.size(); a++) {
            XWPFParagraph xwpfParagraph = xwpfParagraphs.get(a);
            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
            for (XWPFRun xwpfRun : xwpfRuns) {
                String text = xwpfRun.getText(0);
                if (mark.equals(text)) {
                    int position = text.indexOf(mark);
                    if (position != -1) {
                        // 在插入点后面添加文本
                        String newText = text.substring(0, position) + mark +remark;
                        // 替换原来的文本为新的文本
                        xwpfRun.setText(newText, 0);
                    }
                }
            }
        }
        return document;
    }

//    public static XWPFDocument signDocPicText(String docUrl, String signUrl, int row, int cell) throws Exception {
//        URL file = new URL(docUrl);
//        InputStream inputStream = file.openStream();
//        XWPFDocument document = new XWPFDocument(inputStream);
//
//        List<XWPFParagraph> xwpfParagraphs = document.getParagraphs();
//        int x = 0;
//        for (int a = 0; a <xwpfParagraphs.size(); a++) {
//            System.out.println(a);
//            XWPFParagraph xwpfParagraph = xwpfParagraphs.get(a);
//            List<XWPFRun> xwpfRuns = xwpfParagraph.getRuns();
//            for (XWPFRun xwpfRun : xwpfRuns) {
//                String text = xwpfRun.getText(0);
//                if ("当事人或其代理人意见：".equals(text)) {
//                    int position = text.indexOf("当事人或其代理人意见：");
//                    if (position != -1) {
//                        // 在插入点后面添加文本
//                        String newText = text.substring(0, position) +"当事人或其代理人意见：cs22eeeeeeeeeeeeeeeeeeeeeeeeeeeeeEeeeqqqqqqqqqqqqqqqqqqqqqqq" + "          2023年11月9日 11时11分";
//                        // 替换原来的文本为新的文本
//                        xwpfRun.setText(newText, 0);
//                    }
//                }
//                if ("当事人或其代理人签名：".equals(text)) {
//                    //读取图片文件
//                    URL img = new URL(signUrl);
//                    InputStream fileInputStream = img.openStream();
//                    xwpfRun.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", 684800, 684800);
//                }
//                System.out.println(text);
//            }
//        }
//        XWPFRun xwpfRun = xwpfParagraphs.get(30).createRun();
//        xwpfRun.setText("                2023年11月11日 11时11分");
//        return document;
//    }

}
