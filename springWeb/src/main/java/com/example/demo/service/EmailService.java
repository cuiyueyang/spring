package com.example.demo.service;

import com.example.demo.common.ApiResponseEntity;

/**
 * <p>Description: 发送邮件service</p>
 * <p>@date 2022/3/28 10:18</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface EmailService {

    /**
     * 发送邮件
     * @return
     */
    ApiResponseEntity sendSimpleEmail();

    /**
     * 发送html邮件
     * @return
     */
    ApiResponseEntity sendHtmlEmail();

    /**
     * 发送带附件的邮件
     * @return
     */
    ApiResponseEntity sendAttachmentsMail();

    /**
     * 发送带静态资源的邮件
     * @return
     */
    ApiResponseEntity sendInlineMail();
}
