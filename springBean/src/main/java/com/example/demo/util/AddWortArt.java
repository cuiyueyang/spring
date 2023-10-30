package com.example.demo.util;

import com.spire.doc.*;

import com.spire.doc.documents.Paragraph;

import com.spire.doc.documents.ShapeType;

import com.spire.doc.fields.ShapeObject;

 

import java.awt.*;

 

public class AddWortArt {

    public static void main(String[]args){

        //创建word文档

        Document doc = new Document();

 

        //添加一个section

        Section section = doc.addSection();

 

        //添加一个段落到section

        Paragraph paragraph = section.addParagraph();

 

        //添加一个shape,并设置其大小和样式

        ShapeObject shape = paragraph.appendShape(250, 70, ShapeType.Text_Arch_Up_Curve);

 

        //设置shape的位置

        shape.setVerticalPosition(80);

        shape.setHorizontalPosition(100);

 

        //写入艺术字文本，并设置字体填充色、字体边框颜色

        shape.getWordArt().setText("添加艺术字");

        shape.setFillColor(Color.PINK);

        shape.setStrokeColor(Color.cyan);

 

        //保存文档

        doc.saveToFile("/Users/cuiyueyang/Desktop/addwordart.docx", FileFormat.Docx_2013);

    }

}