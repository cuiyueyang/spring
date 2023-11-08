package com.example.demo.util;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;

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

//    public static XWPFDocument signDocText(String docUrl, String text, int row, int cell) throws Exception {
//        URL file = new URL(docUrl);
//        InputStream inputStream = file.openStream();
//        XWPFDocument document = new XWPFDocument(inputStream);
//        List<XWPFTable> xwpfTables = document.getTables();
//        //插入文字
//        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
//        Integer size = xwpfTableCell.getParagraphArray(0).getRuns().size();
//        XWPFRun xwpfRun = xwpfTableCell.getParagraphArray(0).getRuns().get(size-1);
//        xwpfRun.setText(text);
//        return document;
//    }
//
//    public static XWPFDocument signDocPic(XWPFDocument document, String signUrl, int row, int cell) throws Exception {
//        List<XWPFTable> xwpfTables = document.getTables();
//        //插入图片
//        // 获取第一行第一列单元格
//        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(row).getCell(cell);
//        //读取图片文件
//        URL img = new URL(signUrl);
//        InputStream fileInputStream = img.openStream();
//        // 添加图片到单元格
//        XWPFRun run = xwpfTableCell.getParagraphArray(0).createRun();
//        run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "cs", 685800, 685800);
//        return document;
//    }

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
        run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "", 685800, 685800);
        return document;
    }

}
