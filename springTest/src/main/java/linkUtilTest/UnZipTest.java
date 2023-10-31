package linkUtilTest;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.service.spi.ServiceException;
import java.io.*;
import java.nio.file.Paths;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * <p>Description: </p>
 * <p>Date: 2023/10/31 15:56</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public class UnZipTest {

    //compress方法需要传入2个参数，是两个地址
    //第一个地址是目标打包文件的地址，第二个是zip包输出的地址
    public static void main(String[] args) throws IOException {
        String hallFilePath = "/Users/cuiyueyang/Desktop/temp/picture";
        compress(Paths.get(hallFilePath).toString(), hallFilePath + ".zip");
    }

    //由此开始是所有相关的工具方法
    public static void compress(String fromPath, String toPath) throws IOException {
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);
        if (!fromFile.exists()) {
            throw new ServiceException(fromPath + "不存在！");
        }
        try (FileOutputStream outputStream = new FileOutputStream(toFile); CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new CRC32()); ZipOutputStream zipOutputStream = new ZipOutputStream(checkedOutputStream)) {
            String baseDir = "";
            compress(fromFile, zipOutputStream, baseDir);
        }
    }

    private static void compress(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (file.isDirectory()) {
            compressDirectory(file, zipOut, baseDir);
        } else {
            compressFile(file, zipOut, baseDir);
        }
    }

    private static void compressFile(File file, ZipOutputStream zipOut, String baseDir) throws IOException {
        if (!file.exists()) {
            return;
        }
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zipOut.putNextEntry(entry);
            int count;
            byte[] data = new byte[1024];
            while ((count = bis.read(data, 0, 1024)) != -1) {
                zipOut.write(data, 0, count);
            }
        }
    }

    private static void compressDirectory(File dir, ZipOutputStream zipOut, String baseDir) throws IOException {
        File[] files = dir.listFiles();
        if (files != null && ArrayUtils.isNotEmpty(files)) {
            for (File file : files) {
                compress(file, zipOut, baseDir + dir.getName() + File.separator);
            }
        }
    }

}
