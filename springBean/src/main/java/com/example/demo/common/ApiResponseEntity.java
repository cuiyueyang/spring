package com.example.demo.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * <p>Description: 统一返回对象</p>
 * <p>Date: 2021/7/29 10:38 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@ApiModel(value = "api接口返回统一对象")
@Data
public class ApiResponseEntity<T> {
    @ApiModelProperty(value = "返回状态码")
    private int code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "返回状态")
    private Boolean success;

    @ApiModelProperty(value = "数据实体")
    private T data;

    public ApiResponseEntity() {
    }

    public ApiResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
        if (code == 200) {
            success = true;
        } else {
            success = false;
        }
    }

    public ApiResponseEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        if (code == 200) {
            success = true;
        } else {
            success = false;
        }
    }

    public static ApiResponseEntity success() {
        ApiResponseEntity response = new ApiResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        response.code = 200;
        response.success = true;
        return response;
    }

    public static ApiResponseEntity success(Object data) {
        ApiResponseEntity response = new ApiResponseEntity(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
        response.data = data;
        response.code = 200;
        response.success = true;
        return response;
    }

    public static ApiResponseEntity success(String msg) {
        ApiResponseEntity response = new ApiResponseEntity(HttpStatus.OK.value(), msg);
        response.data = null;
        response.code = 200;
        response.success = true;
        return response;
    }

    public static ApiResponseEntity success(String msg, Object data) {
        ApiResponseEntity response = new ApiResponseEntity(HttpStatus.OK.value(), msg);
        response.data = data;
        response.code = 200;
        response.success = true;
        return response;
    }

    public static ApiResponseEntity fail(String msg) {
        ApiResponseEntity response = new ApiResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.OK.getReasonPhrase());
        response.msg = msg;
        response.success = false;
        return response;
    }

    public static ApiResponseEntity fail(HttpStatus status, String msg) {
        ApiResponseEntity response = new ApiResponseEntity(status.value(), status.getReasonPhrase());
        response.msg = msg;
        response.success = false;
        return response;
    }
}
