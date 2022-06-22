package com.example.demo.service;

import com.example.demo.common.ApiResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>Description: 导出相关</p>
 * <p>@date 2022/3/28 10:33</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface ExportService {

    void exportZhuZhuangTu(HttpServletResponse response);

    void exportJudgeReport();

    void exportOnline();

    ApiResponseEntity exportLocal();

    void simpleWordExport();

    void wdWordExport();

    ApiResponseEntity importExcel(MultipartFile file);
}
