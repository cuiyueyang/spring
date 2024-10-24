package com.example.demo.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;  
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;  
  
public class ImageUrlToByteArray {  
  
    public static byte[] convertImageUrlToByteArray(String imageUrl) throws IOException {  
        URL url = new URL(imageUrl);  
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();  
        httpURLConnection.setRequestMethod("GET");  
        httpURLConnection.connect();  
  
        try (InputStream inputStream = httpURLConnection.getInputStream();  
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {  
  
            byte[] buffer = new byte[4096];  
            int bytesRead;  
  
            // 读取图片数据到字节数组中  
            while ((bytesRead = inputStream.read(buffer)) != -1) {  
                byteArrayOutputStream.write(buffer, 0, bytesRead);  
            }  
  
            // 返回包含图片数据的字节数组  
            return byteArrayOutputStream.toByteArray();  
        }  
    }  
  
    public static void main(String[] args) {
        String imageUrl = "http://192.168.90.111:3100/aila-dev/aila/uavms-1.png";
        try {
            byte[] imageBytes = convertImageUrlToByteArray(imageUrl);  
            // 现在你可以使用imageBytes进行进一步的处理，比如保存到文件或发送到服务器等  
            System.out.println("图片数据已转换为字节数组，长度为：" + imageBytes.length);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
}