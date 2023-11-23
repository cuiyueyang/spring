package com.example.demo.util;

import cn.hutool.core.io.FileUtil;
import com.aspose.words.Document;
import java.io.*;
import java.net.URL;

/**
 * @author: create by AlefTao
 * @version: v1.0
 * @description: org.jeecg.common.poiextends
 * @date 23.1.28 16:02
 */
public class ConvertToPdfUrlUtil {

    /**
     * 使用aspose转换成pdf文件
     *
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean convertToPdfAsAspose(String inputFile, String pdfFile) {
        return doc2pdf(inputFile, pdfFile);
    }

    /**
     * aspose.word包获取配置
     *
     * @return
     */
    public static boolean getWordLicense() {
        boolean result = false;
        try (InputStream is = ConvertToPdfUrlUtil.class.getClassLoader().getResourceAsStream("license/license.xml")) {
            com.aspose.words.License aposeLic = new com.aspose.words.License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * word文档转pdf
     *
     * @param inPath
     * @param outPath
     */
    public static boolean doc2pdf(String inPath, String outPath) {
        if (!getWordLicense()) {
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            return false;
        }
        FileOutputStream os = null;
        try {
            // 新建一个空白pdf文档
            File file = new File(outPath);
            os = new FileOutputStream(file);
            // Address是将要被转化的word文档

            URL inFile = new URL(inPath);
            InputStream inputStream = inFile.openStream();
            Document doc = new Document(inputStream);

            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(os, com.aspose.words.SaveFormat.PDF);
            // EPUB, XPS, SWF 相互转换
            os.close();
        } catch (Exception e) {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
