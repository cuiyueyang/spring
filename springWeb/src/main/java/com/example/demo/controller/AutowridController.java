package com.example.demo.controller;

import com.example.demo.service.MainService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 注入练习</p>
 * <p>@date 2022/7/20 14:04</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api("注入练习")
@RestController
@RequestMapping("/autowird")
public class AutowridController {

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/test1")
    public void handle(String str){
        MainService ruleService = applicationContext.getBean(str, MainService.class);
        ruleService.handle("111");
    }

}
