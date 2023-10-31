package linkUtilTest;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.documents.HorizontalAlignment;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.TextBoxLineStyle;
import com.spire.doc.fields.TextBox;
import com.spire.doc.fields.TextRange;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;

public class InsertWordArtInWord {
    public static void main(String[] args) throws Exception{
//        test1();
        test2();
    }

    private static void test1() {
        //Create a Document instance
        Document doc = new Document();
        //Load a Word document
        doc.loadFromFile("/Users/cuiyueyang/Desktop/temp/word/temp1.docx");

        //Get the first section
        Section section = doc.getSections().get(0);
        //Add a paragraph to the section
        Paragraph paragraph = section.addParagraph();

        //Add a shape to the paragraph
//        ShapeObject shape = paragraph.appendShape(250, 70, ShapeType.Text_Ring_Outside);

        TextBox textBox = paragraph.appendTextBox(250, 70);
        textBox.getFormat().setLineStyle(TextBoxLineStyle.Thin_Thick);
        textBox.getFormat().setLineColor(Color.white);
        textBox.setVerticalPosition(20);
        textBox.setHorizontalPosition(80);
        Paragraph textboxPara1 = textBox.getBody().addParagraph();
        TextRange txtrg = textboxPara1.appendText("姓名_______学号_________班级__________");
        txtrg.getCharacterFormat().setFontName("等线");
        txtrg.getCharacterFormat().setFontSize(10);
        txtrg.getCharacterFormat().setTextColor(Color.black);
        textboxPara1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //Save the result document
        doc.saveToFile("/Users/cuiyueyang/Desktop/InsertWordArt.docx", FileFormat.Docx);
    }

    private static void test2() throws Exception{
        //Create a Document instance
        Document doc = new Document();

        String word1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/docx/1698630863769.docx?Expires=2013990857&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=oaYhD%2B1NOekuYF%2BNA0HdAZAKWsk%3D";
        URL file = new URL(word1);
        InputStream inputStream = file.openStream();
        doc.loadFromStream(inputStream, FileFormat.Docx);

        //Get the first section
        Section section = doc.getSections().get(0);
        //Add a paragraph to the section
        Paragraph paragraph = section.addParagraph();

        //Add a shape to the paragraph
//        ShapeObject shape = paragraph.appendShape(250, 70, ShapeType.Text_Ring_Outside);

        TextBox textBox = paragraph.appendTextBox(250, 70);
        textBox.getFormat().setLineStyle(TextBoxLineStyle.Thin_Thick);
        textBox.getFormat().setLineColor(Color.white);
        textBox.setVerticalPosition(20);
        textBox.setHorizontalPosition(80);
        Paragraph textboxPara1 = textBox.getBody().addParagraph();
        TextRange txtrg = textboxPara1.appendText("姓名_______学号_________班级__________");
        txtrg.getCharacterFormat().setFontName("等线");
        txtrg.getCharacterFormat().setFontSize(10);
        txtrg.getCharacterFormat().setTextColor(Color.black);
        textboxPara1.getFormat().setHorizontalAlignment(HorizontalAlignment.Center);

        //Save the result document
        doc.saveToFile("/Users/cuiyueyang/Desktop/InsertWordArt.docx", FileFormat.Docx);
    }

}