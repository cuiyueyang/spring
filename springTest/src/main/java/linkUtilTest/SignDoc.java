package linkUtilTest;

import com.example.demo.util.SignDocUtil;
import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.collections.SectionCollection;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextWrappingStyle;
import com.spire.doc.fields.DocPicture;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/27 11:22</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class SignDoc {

    public static void main(String[] args) throws Exception{
//        test1();
        test2();
    }

    private static void test1() throws Exception {
        // 创建 Document 类的对象
        Document doc = new Document();
        // 从磁盘载入 Word 文件
        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
        String word2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698650295402.docx?Expires=2014010290&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=0wyjoLNU3GRYjrWmMx6jyjH2ZWE%3D";
        URL file = new URL(word2);
        InputStream inputStream = file.openStream();
        doc.loadFromStream(inputStream, FileFormat.Docx);


        // 创建 DocPicture 类的对象
        DocPicture picture = new DocPicture(doc);
        String image = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        URL signFile = new URL(image);
        InputStream sign = signFile.openStream();
        picture.loadImage(sign);


        // 设置图片大小
        //示例：80
        picture.setWidth(50);
        //示例：50
        picture.setHeight(110);
        // 将图片文本环绕方式设置为四周环绕
        picture.setTextWrappingStyle(TextWrappingStyle.Square);
        // 将图片插入到第几段  1：表示第一段
        doc.getSections().get(0).getParagraphs().get(1).getChildObjects().insert(0, picture);
        // 设置图片的位置
        //示例：110.0F 水平位置
        picture.setHorizontalPosition(170);
        //示例：110.0F 垂直位置
        picture.setVerticalPosition(350);
        // 保存文档
        doc.saveToFile("/Users/cuiyueyang/Desktop/送达回证-sign.docx", FileFormat.Docx);
    }

    private static void test2() throws Exception {
        // 创建 Document 类的对象
        Document doc = new Document();
        // 从磁盘载入 Word 文件
        // 从磁盘载入 Word 文件
        File file = new File("/Users/cuiyueyang/Desktop/temp/word/temp2.docx");
        doc.loadFromFile(file.getAbsolutePath());
        // 创建 DocPicture 类的对象
        DocPicture picture = new DocPicture(doc);
        // 从磁盘加载图片
         String images= "/Users/cuiyueyang/Desktop/temp/picture/sign1.jpg";
        picture.loadImage(images);


        // 设置图片大小
        //示例：80
        picture.setWidth(50);
        //示例：50
        picture.setHeight(70);
        //旋转角度
        // 将图片文本环绕方式设置为四周环绕
        picture.setTextWrappingStyle(TextWrappingStyle.In_Front_Of_Text);
        // 将图片插入到第几段  1：表示第一段
        SectionCollection sectionCollection = doc.getSections();
        Section section = sectionCollection.get(0);
        ParagraphCollection paragraphCollection = section.getParagraphs();
        Paragraph paragraph = paragraphCollection.get(0);
        paragraph.getChildObjects().insert(0, picture);
//        doc.getSections().get(0).getParagraphs().get(1).getChildObjects().insert(0, picture);
        // 设置图片的位置
        //示例：110.0F 水平位置
        picture.setHorizontalPosition(120);
        //示例：110.0F 垂直位置
        picture.setVerticalPosition(360);
        // 保存文档
        doc.saveToFile("/Users/cuiyueyang/Desktop/送达回证-sign.docx", FileFormat.Docx);
    }

}
