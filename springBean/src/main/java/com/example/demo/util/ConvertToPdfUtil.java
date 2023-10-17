package com.example.demo.util;

import com.aspose.cells.Workbook;
import com.aspose.words.Document;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import cn.hutool.core.io.FileUtil;

/**
 * @author: create by AlefTao
 * @version: v1.0
 * @description: org.jeecg.common.poiextends
 * @date 23.1.28 16:02
 */
public class ConvertToPdfUtil {

    public static void main(String[] args) {
        //		excel2Pdf("F:\\aaa\\加班.xls","F:\\aaa\\加班.pdf") ;
        String path1 = "/Users/cuiyueyang/Desktop/1.docx";
        String path2 = "/Users/cuiyueyang/Desktop/1.pdf";
         convertToPdfAsAspose (path1,path2);
        // convertToPdfAsAspose("D:\\test\\1.xlsx", "D:\\test\\1new.pdf");
    }

    /**
     * 使用aspose转换成pdf文件
     *
     * @param inputFile
     * @param pdfFile
     * @return
     */
    public static boolean convertToPdfAsAspose(String inputFile, String pdfFile) {
        //后缀
        String suffix = FileUtil.getSuffix(inputFile);
        File file = new File(inputFile);
        if (!file.exists()) {
            return false;
        }
        if ("pdf".equalsIgnoreCase(suffix)) {
            return false;
        }

        //根据不同的文件转换成pdf文件
        if ("doc".equalsIgnoreCase(suffix) || "docx".equalsIgnoreCase(suffix) || "txt".equalsIgnoreCase(suffix)) {
            return doc2pdf(inputFile, pdfFile);
        } else if ("xls".equalsIgnoreCase(suffix) || "xlsx".equalsIgnoreCase(suffix)) {
            return excel2Pdf(inputFile, pdfFile);
        }
        // else if("xls".equalsIgnoreCase(suffix) || "xlsx".equalsIgnoreCase(suffix)) {
        //     return excel2Pdf(inputFile, pdfFile);
        // } else if (suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("jpg")
        //         || suffix.equalsIgnoreCase("jpeg")) {
        //     return img2PDF(inputFile, pdfFile);
        // }
        else {
            return false;
        }
    }

    /**
     * aspose.word包获取配置
     *
     * @return
     */
    public static boolean getWordLicense() {
        boolean result = false;
        try (InputStream is = ConvertToPdfUtil.class.getClassLoader().getResourceAsStream("license/license.xml")) {
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
            Document doc = new Document(inPath);
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

    /**
     * word文档转pdf
     */
    public static boolean doc2pdf(InputStream inputStream, OutputStream outputStream) {
        if (!getWordLicense()) {
            // 验证License 若不验证则转化出的pdf文档会有水印产生
            return false;
        }
        try {
            Document doc = new Document(inputStream);
            // 全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF,
            doc.save(outputStream, com.aspose.words.SaveFormat.PDF);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * asponse:excel转pdf
     *
     * @param excelPath
     * @param pdfPath
     */
    public static boolean excel2Pdf(String excelPath, String pdfPath) {
        // 验证License
        if (!ExcelExtUtil.getExcelLicense()) {
            return false;
        }
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            File excelFile = new File(excelPath);
            if (excelFile.exists()) {
                fileInputStream = new FileInputStream(excelFile);
                Workbook workbook = new Workbook(fileInputStream);
                File pdfFile = new File(pdfPath);
                fileOutputStream = new FileOutputStream(pdfFile);
                workbook.save(fileOutputStream, com.aspose.cells.SaveFormat.PDF);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * asponse:excel转pdf
     */
    public static boolean excel2Pdf(InputStream inputStream, OutputStream outputStream) {
        // 验证License
        if (!ExcelExtUtil.getExcelLicense()) {
            return false;
        }
        try {
            Workbook workbook = new Workbook(inputStream);
            workbook.save(outputStream, com.aspose.cells.SaveFormat.PDF);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // public static boolean getPptLicense() {
    //     boolean result = false;
    //     InputStream is = null ;
    //     try {
    //         is = Word2Pdf.class.getClassLoader().getResourceAsStream("license.xml"); // license.xml应放在..\WebRoot\WEB-INF\classes路径下
    //         com.aspose.slides.License aposeLic = new com.aspose.slides.License();
    //         aposeLic.setLicense(is);
    //         result = true;
    //         is.close();
    //     } catch (Exception e) {
    //         if(is != null) {
    //             try {
    //                 is.close() ;
    //             } catch (IOException e1) {
    //                 // TODO Auto-generated catch block
    //                 e1.printStackTrace();
    //             }
    //         }
    //         e.printStackTrace();
    //     }
    //     return result;
    // }

    /**
     * aspose:ppt转pdf
     * @param inPath
     * @param outPath
     */
    // public static void ppt2pdf(String inPath,String outPath) {
    //
    //     // 验证License
    //     if (!getPptLicense()) {
    //         return;
    //     }
    //     FileOutputStream fileOS = null ;
    //     try {
    //         long old = System.currentTimeMillis();
    //         File file = new File(outPath);// 输出pdf路径
    //         Presentation pres = new Presentation(inPath);//输入pdf路径
    //         fileOS = new FileOutputStream(file);
    //         pres.save(fileOS, com.aspose.slides.SaveFormat.Pdf);
    //         fileOS.close();
    //
    //         long now = System.currentTimeMillis();
    //         System.out.println("共耗时：" + ((now - old) / 1000.0) + "秒\n\n" + "文件保存在:" + file.getPath()); //转化过程耗时
    //     } catch (Exception e) {
    //         if(fileOS != null) {
    //             try {
    //                 fileOS.close();
    //             } catch (IOException e1) {
    //                 e1.printStackTrace();
    //             }
    //         }
    //         e.printStackTrace();
    //     }
    // }

    //img转pdf
    // public static boolean img2PDF(String imgFilePath, String pdfFilePath){
    //     File file = new File(imgFilePath);
    //     if (file.exists()) {
    //         com.lowagie.text.Document document = new com.lowagie.text.Document();
    //         FileOutputStream fos = null;
    //         try {
    //             fos = new FileOutputStream(pdfFilePath);
    //             PdfWriter.getInstance(document, fos);
    //
    //             // 添加PDF文档的某些信息，比如作者，主题等等
    //             document.addAuthor("newprint");
    //             document.addSubject("test pdf.");
    //             // 设置文档的大小
    //             document.setPageSize(PageSize.A4);
    //             // 打开文档
    //             document.open();
    //             // 写入一段文字
    //             // document.add(new Paragraph("JUST TEST ..."));
    //             // 读取一个图片
    //             Image image = Image.getInstance(imgFilePath);
    //             float imageHeight = image.getScaledHeight();
    //             float imageWidth = image.getScaledWidth();
    //             int i = 0;
    //             while (imageHeight > 500 || imageWidth > 500) {
    //                 image.scalePercent(100 - i);
    //                 i++;
    //                 imageHeight = image.getScaledHeight();
    //                 imageWidth = image.getScaledWidth();
    //                 System.out.println("imageHeight->" + imageHeight);
    //                 System.out.println("imageWidth->" + imageWidth);
    //             }
    //
    //             image.setAlignment(Image.ALIGN_CENTER);
    //             // //设置图片的绝对位置
    //             // image.setAbsolutePosition(0, 0);
    //             // image.scaleAbsolute(500, 400);
    //             // 插入一个图片
    //             document.add(image);
    //         } catch (DocumentException de) {
    //             System.out.println(de.getMessage());
    //         } catch (IOException ioe) {
    //             System.out.println(ioe.getMessage());
    //         }
    //         document.close();
    //         try {
    //             fos.flush();
    //             fos.close();
    //         } catch (IOException e) {
    //             e.printStackTrace();
    //         }
    //
    //         return true;
    //     } else {
    //         return false;
    //     }
    // }

}
