package com.example.demo.util;

import org.apache.poi.xwpf.usermodel.*;

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
                    URL img = new URL(signUrl);
                    InputStream fileInputStream = img.openStream();
                    xwpfRun.setText("cscs");
                    xwpfRun.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", picWidth, picHight);
                }
            }
        }
        document = signDocTimeText(document, 30);
        return document;
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
