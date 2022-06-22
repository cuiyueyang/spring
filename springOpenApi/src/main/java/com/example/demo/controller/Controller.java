package com.example.demo.controller;

import com.example.demo.service.OpenService;
import com.example.demo.domain.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/22 10:55 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@RestController
@RequestMapping("open")
public class Controller {

    @Autowired
    private OpenService openService;

    @GetMapping("test")
    public String test() {
        List<StudentInfo> a = openService.test();
        return "success";
    }
}
