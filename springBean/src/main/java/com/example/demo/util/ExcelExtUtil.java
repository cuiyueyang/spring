package com.example.demo.util;

import java.io.InputStream;

/**
 * @author: create by AlefTao
 * @version: v1.0
 * @description: org.jeecg.common.poiextends
 * @date 23.1.29 9:45
 */
public class ExcelExtUtil {

    /**
     * aspose.word包获取配置
     *
     * @return
     */
    public static boolean getExcelLicense() {
        boolean result = false;
        try (InputStream is = ConvertToPdfUtil.class.getClassLoader().getResourceAsStream("license/license.xml")) {
            com.aspose.cells.License aposeLic = new com.aspose.cells.License();
            aposeLic.setLicense(is);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
