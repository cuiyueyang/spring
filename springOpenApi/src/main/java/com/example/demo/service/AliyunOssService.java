package com.example.demo.service;

import com.example.demo.common.ApiResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>Description: </p>
 * <p>@date 2023/1/4 14:00</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface AliyunOssService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    ApiResponseEntity uploadFile(MultipartFile file);

}
