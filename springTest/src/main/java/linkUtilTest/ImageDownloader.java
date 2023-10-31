package linkUtilTest;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
public class ImageDownloader {
    public static void main(String[] args) throws IOException {
        // 图片链接列表
        ArrayList<String> imageUrls = new ArrayList<>();
        String image1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        String image2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        String image3 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        String image4 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/tcsp/jpg/1698630915112.jpg?Expires=2013990906&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=pVXAMefbkPpjp4RqX%2BBKSwcYWl0%3D";
        imageUrls.add(image1);
        imageUrls.add(image2);
        imageUrls.add(image3);
        imageUrls.add(image4);
        // 下载图片并保存到本地临时目录
        File dir = new File("/Users/cuiyueyang/Desktop/CS");
        if (!dir.exists()) {
            dir.mkdir();
        }
        ArrayList<File> imageFiles = new ArrayList<>();
        for (int i = 0; i < imageUrls.size(); i++) {
            String imageUrl = imageUrls.get(i);
            String fileName = "image" + i + ".jpg";
            URL url = new URL(imageUrl);
            FileOutputStream outputStream = new FileOutputStream(new File(dir, fileName));
            InputStream inputStream = url.openStream();
            byte[] buffer = new byte[4096];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
            imageFiles.add(new File(dir, fileName));
        }
        // 打包为zip文件
        byte[] buffer = new byte[4096];
        String zipFileName = "/Users/cuiyueyang/Desktop/CS/images.zip";
        ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFileName));
        for (int i = 0; i < imageFiles.size(); i++) {
            File imageFile = imageFiles.get(i);
            FileInputStream inputStream = new FileInputStream(imageFile);
            String fileName = imageFile.getName();
            ZipEntry entry = new ZipEntry(fileName);
            zipOutputStream.putNextEntry(entry);
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                zipOutputStream.write(buffer, 0, length);
            }
            inputStream.close();
            //删除使用过的临时图片
            imageFile.delete();
        }
        //删除使用过的临时zip
        File zip = new File(zipFileName);
//        zip.delete();
        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }
}