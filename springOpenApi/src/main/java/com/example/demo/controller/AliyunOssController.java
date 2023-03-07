package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.AliyunOssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>Description: 阿里云oss</p>
 * <p>@date 2023/1/4 13:57</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api(value = "阿里云oss")
@RequestMapping("/api/aliyunOss")
@RestController
public class AliyunOssController {

    @Autowired
    private AliyunOssService aliyunOssService;

    @ApiOperation("上传附件")
    @PostMapping("/upload")
    public ApiResponseEntity uploadFile(MultipartFile file) {
        return  aliyunOssService.uploadFile(file);
    }

}
