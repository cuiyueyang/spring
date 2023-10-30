package com.example.demo.util;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;
import com.spire.doc.Section;
import com.spire.doc.collections.ParagraphCollection;
import com.spire.doc.documents.Paragraph;
import com.spire.doc.documents.ShapeType;
import com.spire.doc.fields.ShapeObject;
 
import java.awt.*;
 
public class InsertWordArtInWord {
    public static void main(String[] args){

        Document doc = new Document();        //添加一个section
        Section section = doc.addSection();        //添加一个段落.
        Paragraph paragraph = section.addParagraph();        //向段落添加形状并指定形状大小和类型
        ShapeObject shape = paragraph.appendShape(400, 150, ShapeType.Text_Deflate_Bottom);        //设置形状的位置
        shape.setVerticalPosition(60);
        shape.setHorizontalPosition(60);        //设置艺术字的文本内容
        shape.getWordArt().setText("艺术字效果");        //设置艺术字的填充颜色和描边颜色
        shape.setFillColor(Color.CYAN);
        shape.setStrokeColor(Color.BLUE);        //将文档保存到文件.
        doc.saveToFile("/Users/cuiyueyang/Desktop/WordArt.docx", FileFormat.Docx_2013);



        //Create a Document instance
//        Document doc = new Document();
        //Load a Word document
//        doc.loadFromFile("/Users/cuiyueyang/Desktop/temp/word/temp2.docx");

        //Get the first section
//        Section section = doc.getSections().get(0);
        //Add a paragraph to the section
//        ParagraphCollection paragraphCollection = section.getParagraphs();
//        Paragraph paragraph = paragraphCollection.get(0);
//
//        //Add a shape to the paragraph
//        ShapeObject shape = paragraph.appendShape(400, 150, ShapeType.Text_Wave_3);
//
//        //Set the position of the shape
//        shape.setVerticalPosition(60);
//        shape.setHorizontalPosition(60);
//
//        //Set the text of WordArt
//        shape.getWordArt().setText("Happy Birthday");
//
////        //Set the fill color
//        shape.setFillColor(Color.orange);
////
////        //Set the border color of the text.
//        shape.setStrokeColor(Color.YELLOW);

        //Save the result document
//        doc.saveToFile("/Users/cuiyueyang/Desktop/送达回证-sign.docx", FileFormat.Docx);
    }
}