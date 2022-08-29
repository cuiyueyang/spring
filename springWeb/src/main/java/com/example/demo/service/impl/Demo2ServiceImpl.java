package com.example.demo.service.impl;

import com.example.demo.service.MainService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>@date 2022/7/20 13:59</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Service("Demo2ServiceImpl")
public class Demo2ServiceImpl implements MainService {

    @Override
    public void handle(String str) {
        System.out.println("deom2" + str);
    }

}
