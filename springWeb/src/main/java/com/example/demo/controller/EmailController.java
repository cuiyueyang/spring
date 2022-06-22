package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.EmailService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 发送邮件</p>
 * <p>@date 2022/1/4 14:35</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api("发送邮件")
@RestController
@RequestMapping("email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "发送邮件", notes = "", httpMethod = "GET")
    @RequestMapping("sendSimpleEmail")
    public ApiResponseEntity sendSimpleEmail() {
        return emailService.sendSimpleEmail();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "发送html邮件", notes = "", httpMethod = "GET")
    @RequestMapping("sendHtmlEmail")
    public ApiResponseEntity sendHtmlEmail() {
        return emailService.sendHtmlEmail();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "发送带附件的邮件", notes = "", httpMethod = "GET")
    @RequestMapping("sendAttachmentsMail")
    public ApiResponseEntity sendAttachmentsMail() {
        return emailService.sendAttachmentsMail();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "发送带静态资源的邮件", notes = "", httpMethod = "GET")
    @RequestMapping("sendInlineMail")
    public ApiResponseEntity sendInlineMail() {
        return emailService.sendInlineMail();
    }

}
