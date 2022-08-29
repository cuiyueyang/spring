package com.example.demo.service.impl;

import com.example.demo.service.MainService;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>@date 2022/7/20 10:27</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Service("Demo1ServiceImpl")
public class Demo1ServiceImpl implements MainService {

    @Override
    public void handle(String str) {
        System.out.println("deom1" + str);
    }

}
