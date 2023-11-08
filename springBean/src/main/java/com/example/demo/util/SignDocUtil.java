package com.example.demo.util;

import com.spire.doc.*;
import com.spire.doc.Document;
import com.spire.doc.collections.DocumentObjectCollection;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.collections.TextBoxCollection;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextFormField;
import com.spire.doc.fields.TextRange;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.STSchemeColorVal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/30 09:56</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Slf4j
public class SignDocUtil {

    /**
     * 设置签名图片
     * @param docUrl
     * @param signUrl
     * @param x
     * @param y
     * @param imgWidth
     * @param imgHeight
     * @return
     * @throws Exception
     */
    public static Document signPic(String docUrl, String signUrl, int x, int y, float imgWidth, float imgHeight) throws Exception {
        Document doc = new Document();
        // 从磁盘载入 Word 文件
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        doc.loadFromStream(inputStream, FileFormat.Docx);

        // 创建 DocPicture 类的对象
        DocPicture picture = new DocPicture(doc);
        // 从磁盘加载图片
        String images= signUrl;
        URL signFile = new URL(images);
        InputStream sign = signFile.openStream();
        picture.loadImage(sign);
        // 设置图片大小
        //示例：80
        picture.setWidth(imgWidth);
        //示例：50
        picture.setHeight(imgHeight);
        //旋转角度
        // 将图片文本环绕方式设置为四周环绕
        picture.setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
        // 将图片插入到第几段  1：表示第一段
        doc.getSections().get(0).getParagraphs().get(0).getChildObjects().insert(0, picture);
        // 设置图片的位置
        //示例：110.0F 水平位置
        picture.setHorizontalPosition(x);
        //示例：110.0F 垂直位置
        picture.setVerticalPosition(y);
        return doc;
    }

    public static Document signTextTemp(String docUrl, String signUrl) throws Exception {
        Document doc = new Document();
        // 从磁盘载入 Word 文件
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        doc.loadFromStream(inputStream, FileFormat.Docx);

//        String text = doc.getText();
//        System.out.println(text);

//        TextBoxCollection textBoxCollection = doc.getTextBoxes();
//        for (int a = 0; a < textBoxCollection.getCount(); a++) {
//            TextBox textBox = textBoxCollection.get(a);
//            System.out.println(textBox.getBody());
//        }

        SectionCollection sectionCollection = doc.getSections();
        for (int a = 0; a <sectionCollection.getCount(); a++){
            Section section = sectionCollection.get(a);
            ParagraphCollection paragraphCollection = section.getParagraphs();
            for (int b=0; b < paragraphCollection.getCount(); b++) {
                DocumentObjectCollection documentObjectCollection = paragraphCollection.get(b).getChildObjects();
                for (int c=0; c<documentObjectCollection.getCount(); c++) {
                    DocumentObject documentObject = documentObjectCollection.get(c);
                    DocumentObjectType documentObjectType = documentObject.getDocumentObjectType();
                    if (DocumentObjectType.Text_Range.equals(documentObjectType)) {
                        TextRange textRange = (TextRange) documentObject;
                        System.out.println(textRange.getText());
                    } else if (DocumentObjectType.Text_Box.equals(documentObjectType)) {
                        TextBox textBox = (TextBox) documentObject;
                        System.out.println(textBox);
                    } else if (DocumentObjectType.Table.equals(documentObjectType)) {
                        Table table = (Table) documentObject;
                        TableCell tableCell = table.get(1,1);
                        System.out.println(1);
                    } else if (DocumentObjectType.Any.equals(documentObjectType)) {
                        System.out.println(1);
                    } else if (DocumentObjectType.Body.equals(documentObjectType)) {
                        System.out.println(1);
                    } else if (DocumentObjectType.Bookmark_End.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Bookmark_Start.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Break.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Check_Box.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Comment.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Comment_End.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Comment_Mark.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Control_Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Custom_Xml.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Document.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Drop_Down_Form_Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Embeded_Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Field_Mark.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Footnote.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Header_Footer.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Merge_Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Office_Math.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Ole_Object.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Paragraph.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Permission_End.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Permission_Start.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Picture.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Ruby.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.SDT_Block_Content.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.SDT_Cell_Content.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.SDT_Inline_Content.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.SDT_Row_Content.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Section.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Seq_Field.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Shape.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Shape_Group.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Shape_Line.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Shape_Path.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Shape_Rect.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Smart_Tag.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Structure_Document_Tag.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Structure_Document_Tag_Cell.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Structure_Document_Tag_Inline.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Structure_Document_Tag_Row.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Sub_Document.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Symbol.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.System.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Table_Cell.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Table_Row.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.TOC.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Undefined.equals(documentObjectType)) {
                        System.out.println(1);
                    }else if (DocumentObjectType.Xml_Para_Item.equals(documentObjectType)) {
                        System.out.println(1);
                    }
                }
            }
        }

        return doc;
    }

    public static void signTextTemp2(String docUrl, String signUrl) throws Exception {
        URL file = new URL(docUrl);
        InputStream inputStream = file.openStream();
        XWPFDocument document = new XWPFDocument(inputStream);

        List<XWPFTable> xwpfTables = document.getTables();
        //插入文字
        XWPFTableCell xwpfTableCell = xwpfTables.get(0).getRow(9).getCell(1);
        Integer size = xwpfTableCell.getParagraphArray(0).getRuns().size();
        XWPFRun xwpfRun = xwpfTableCell.getParagraphArray(0).getRuns().get(size-1);
        xwpfRun.setText("cs");

        //插入图片
        // 获取第一行第一列单元格
        XWPFTableCell cell = xwpfTables.get(0).getRow(6).getCell(1);
        // 读取图片文件
        URL img = new URL(signUrl);
        InputStream fileInputStream = img.openStream();
////        File file1 = new File("/Users/cuiyueyang/Desktop/temp/picture");
//        FileInputStream fileInputStream = new FileInputStream("/Users/cuiyueyang/Desktop/temp/picture/sign1.jpg");

        // 添加图片到单元格
        XWPFRun run = cell.getParagraphArray(0).createRun();
//        run.setText("图片添加到单元格");
        XWPFPicture xwpfPicture = run.addPicture(fileInputStream, XWPFDocument.PICTURE_TYPE_JPEG, "cs", 685800, 685800);
//        xwpfPicture.getCTPicture().getSpPr().addNewLn().addNewSolidFill().addNewSchemeClr().setVal(STSchemeColorVal.Enum.forString("tx1"));
        // 保存修改后的文档
        FileOutputStream outputStream = new FileOutputStream(new File("/Users/cuiyueyang/Desktop/cs.docx"));
        document.write(outputStream);
        outputStream.close();
    }


    /**
     * 设置签名备注
     * @param doc
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     * @throws Exception
     */
    public static Document signText(Document doc, String text, int x, int y, float width, float height) throws Exception {
        //Get the first section
        Section section = doc.getSections().get(0);
        //Add a paragraph to the section
        Paragraph paragraph = section.addParagraph();
        TextBox textBox = paragraph.appendTextBox(width, height);
        textBox.getFormat().setLineStyle(TextBoxLineStyle.Thin_Thick);
        textBox.getFormat().setLineColor(Color.white);
        textBox.getFormat().setHorizontalPosition(x);
        textBox.getFormat().setVerticalPosition(y);
        textBox.getFormat().setVerticalOrigin(VerticalOrigin.Page);
        textBox.setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
        Paragraph textboxPara1 = textBox.getBody().addParagraph();
        TextRange txtrg = textboxPara1.appendText(text);
        txtrg.getCharacterFormat().setFontName("等线");
        txtrg.getCharacterFormat().setFontSize(10);
        txtrg.getCharacterFormat().setTextColor(Color.black);
        textboxPara1.getFormat().setHorizontalAlignment(HorizontalAlignment.Left);
        return doc;
    }


    public static List<String> getWordKey(String templateUri) {
        String buffer = "";
        List<String> keyListFromString = null;
        String localPath = "/Users/cuiyueyang/Desktop/1698630863769.docx";
        try {
            OPCPackage opcPackage = POIXMLDocument.openPackage(localPath);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            buffer = extractor.getText();
            keyListFromString = getKeyListFromString(buffer, "收件人与受", "达人的关系");
            opcPackage.close();
            return keyListFromString;
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return keyListFromString;
    }

    /**
     * 查询一个字符串再另一个字符串中出现的下标
     *
     * @param str
     * @param key
     * @return
     */
    public static List<Integer> searchAllIndex(String str, String key) {
        java.util.List<Integer> allIndex = new ArrayList<Integer>();
        int a = str.indexOf(key);
        while (a != -1) {
            allIndex.add(a);
            a = str.indexOf(key, a + 1);
        }
        return allIndex;
    }

    /**
     * 根据关键字 获取字符串中参数
     *
     * @param string
     * @param keyStart 如'${ * @param keyEnd 如 }'
     * @return
     */
    public static List<String> getKeyListFromString(String string, String keyStart, String keyEnd) {
        // 返回数据
        List<String> allStringList = new ArrayList<String>();
        // 判断不为空
        if (StringUtils.isNotBlank(string)) {
            string = string.replaceAll("\\\\s*", "");
            // 开始keyIndex集合
            List<Integer> firstIndex = searchAllIndex(string, keyStart);
            // 结束keyIndex集合
            List<Integer> endIndex = searchAllIndex(string, keyEnd);
            // 不为空
            if (CollectionUtils.isNotEmpty(firstIndex)) {
                // 循环
                for (int i = 0; i < firstIndex.size(); i++) {
                    // 截取关键字部分
                    String temp = string.substring(firstIndex.get(i) + keyStart.length(), endIndex.get(i));
                    // 添加到返回数据中
                    allStringList.add(temp);
                }
            }
        }
        return allStringList;
    }



}
