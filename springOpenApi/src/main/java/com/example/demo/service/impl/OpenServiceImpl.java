package com.example.demo.service.impl;

import com.example.demo.db1.CsDao;
import com.example.demo.db2.Cs2Dao;
import com.example.demo.service.OpenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/22 17:09 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Service
@Slf4j
public class OpenServiceImpl implements OpenService {

    @Autowired
    private CsDao csDao;
    @Autowired
    private Cs2Dao cs2Dao;

    @Override
    public void test() {
        System.out.println(csDao.count());
        System.out.println(cs2Dao.count());
    }

}
