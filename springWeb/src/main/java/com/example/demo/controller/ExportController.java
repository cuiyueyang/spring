package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.ExportService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * <p>Description: 导出相关</p>
 * <p>@date 2022/3/22 17:19</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api("导出相关")
@Slf4j
@RequestMapping("excel")
@RestController
public class ExportController {

    @Autowired
    private ExportService exportService;

    @ApiOperation("将数据设置为柱状图图片，导出到word")
    @PostMapping("/exportZhuZhuangTu")
    public void exportZhuZhuangTu(HttpServletResponse response) throws IOException {
        exportService.exportZhuZhuangTu(response);
    }

    @GetMapping("/exportJudgeReport")
    @ApiOperation("导出研判报告")
    public void exportJudgeReport(){
        exportService.exportJudgeReport();
    }

    @GetMapping("/exportOnline")
    @ApiOperation("easyPoi导出excel")
    public void exportOnline(){
        exportService.exportOnline();
    }

    @GetMapping("/exportLocal")
    @ApiOperation("easyPoi 导出excel 本地测试")
    public ApiResponseEntity exportLocal(){
        return exportService.exportLocal();
    }

    @GetMapping("/simpleWordExport")
    @ApiOperation("easyPoi 导出 doc")
    public void simpleWordExport(){
        exportService.simpleWordExport();
    }

    @GetMapping("/wdWordExport")
    @ApiOperation("导出文档格式的word")
    public void wdWordExport(){
        exportService.wdWordExport();
    }

    @PostMapping("importExcel")
    @ApiOperation("easyPoi 导入")
    public ApiResponseEntity importExcel(@RequestParam("file") MultipartFile file){
        return exportService.importExcel(file);
    }

}


