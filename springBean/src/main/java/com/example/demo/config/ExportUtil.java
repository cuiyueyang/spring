package com.example.demo.config;

import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Map;

/**
 * <p>Description: </p>
 * <p>@date 2022/3/22 17:17</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/

public class ExportUtil {
    public static void export(Map<String, Object> map, String url, File tempFile) {

        try {
            XWPFDocument doc = WordExportUtil.exportWord07(url, map);
            FileOutputStream fos = new FileOutputStream(tempFile);
            doc.write(fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


