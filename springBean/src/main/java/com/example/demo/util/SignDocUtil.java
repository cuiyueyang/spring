package com.example.demo.util;

import cn.hutool.core.img.ImgUtil;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.*;
import com.spire.doc.fields.DocPicture;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextRange;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/30 09:56</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
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

}
