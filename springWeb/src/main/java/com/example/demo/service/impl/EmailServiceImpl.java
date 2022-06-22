package com.example.demo.service.impl;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * <p>Description: 发送邮件service</p>
 * <p>@date 2022/3/28 10:19</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender jms;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public ApiResponseEntity sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo("1343606980@qq.com"); // 接收地址
            message.setSubject("一封简单的邮件"); // 标题
            message.setText("使用Spring Boot发送简单邮件。"); // 内容
            jms.send(message);
            return ApiResponseEntity.success("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.fail("发送失败！：e.getMessage()");
        }
    }

    @Override
    public ApiResponseEntity sendHtmlEmail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("1343606980@qq.com"); // 接收地址
            helper.setSubject("一封HTML格式的邮件"); // 标题
            // 带HTML格式的内容
            StringBuffer sb = new StringBuffer("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>");
            helper.setText(sb.toString(), true);
            jms.send(message);
            return ApiResponseEntity.success("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.fail("发送失败！：e.getMessage()");
        }
    }

    @Override
    public ApiResponseEntity sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("1343606980@qq.com"); // 接收地址
            helper.setSubject("一封带附件的邮件"); // 标题
            helper.setText("详情参见附件内容！"); // 内容
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("springWeb/src/main/resources/templates/easyPoi导入测试.xlsx"));
            helper.addAttachment("easyPoi导入测试.xlsx", file);
            jms.send(message);
            return ApiResponseEntity.success("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.fail("发送失败！：e.getMessage()");
        }
    }

    @Override
    public ApiResponseEntity sendInlineMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("1343606980@qq.com"); // 接收地址
            helper.setSubject("一封带静态资源的邮件"); // 标题
            helper.setText("<html><body>博客图：<img src='cid:img'/></body></html>", true); // 内容
            // 传入附件
            FileSystemResource file = new FileSystemResource(new File("springWeb/src/main/resources/img/bj1.jpg"));
            helper.addInline("img", file);
            jms.send(message);
            return ApiResponseEntity.success("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponseEntity.fail("发送失败！：e.getMessage()");
        }
    }
}
