package linkUtilTest;

import org.apache.commons.compress.utils.Lists;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>Description: </p>
 * <p>Date: 2023/11/15 09:55</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class UnZipTest2 {

    public static void main(String[] args) throws Exception{

        String pic1 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/95201699510728989.jpeg?Expires=2014870729&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=ZeedvnskKSxIQCDIp6RfYmyW69U%3D";
        String pic2 = "http://linkcld-tcsp.oss-cn-hangzhou.aliyuncs.com/aila/95201699510728989.jpeg?Expires=2014870729&OSSAccessKeyId=LTAI5tJZijZ5wB2PDNGthdV7&Signature=ZeedvnskKSxIQCDIp6RfYmyW69U%3D";
        List<String> picList = Lists.newArrayList();
        picList.add(pic1);
        picList.add(pic2);
        downloadZip(picList);
    }

    private static void downloadZip(List<String> picList) throws Exception{
        String baseDir = "/Users/cuiyueyang/Desktop/cs.zip";
        File toFile = new File(baseDir);
        int a = 0;
        try (FileOutputStream outputStream = new FileOutputStream(toFile);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32());
             ZipOutputStream zipOut = new ZipOutputStream(checkedOutputStream)) {
            for (String pic : picList) {
                URL img = new URL(pic);
                InputStream fileInputStream = img.openStream();
                compressFile(fileInputStream, zipOut, String.valueOf(a));
                ++a;
            }
        }
    }

    private static void compressFile(InputStream file, ZipOutputStream zipOut, String baseDir) throws IOException {
        try (BufferedInputStream bis = new BufferedInputStream(file)) {
            ZipEntry entry = new ZipEntry(baseDir + ".jpg");
            zipOut.putNextEntry(entry);
            int count;
            byte[] data = new byte[1024];
            while ((count = bis.read(data, 0, 1024)) != -1) {
                zipOut.write(data, 0, count);
            }
        }
    }

}
