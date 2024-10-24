package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.config.RedisUtil;
import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import com.example.demo.service.RedisService;
import com.example.demo.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>Description: redis测试</p>
 * <p>Date: 2021/8/17 11:31 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TestDao testDao;
    /**
     * redis过期时间 3天
     */
    private static final int SECOND = 259200;
    private static final int OneDaySecond = 86400;
    /**
     * redis缓存结果状态
     */
    private static final String SUCCESS = "OK";

    /**
     * 缓存穿透，缓存击穿，缓存雪崩
     * 缓存穿透：(不存在对应的缓存)key对应的数据在数据源并不存在，每次请求都会请求数据源。利用这种形式不断访问可能会压垮数据源。
     * 缓存击穿：(存在但已过期)key对应的数据在数据源存在，但是过期了，直接请求数据源。大量请求进入后造成数据库压力。
     * 缓存雪崩：(缓存集中失效) 缓存服务器重启或者大量缓存集中在某一个时间段失效。
     */

    @Override
    public String studentRedis(String stuNo) {
        if (StringUtils.isNotBlank(stuNo)) {
            String jsonValue = redisUtil.get(stuNo);
            if (StringUtils.isBlank(jsonValue)) {
                List<StudentInfo> studentInfos = Lists.newArrayList();
                StudentInfo studentInfo = new StudentInfo();
                studentInfos.add(studentInfo);
                if (CollectionUtils.isNotEmpty(studentInfos)) {
                    jsonValue = JsonMapper.defaultMapper().toJson(studentInfos);
                    int invalidTime = getInvalidTime(stuNo);
                    String redisResult = redisUtil.setex(stuNo, invalidTime, jsonValue);
                    if (SUCCESS.equals(redisResult)) {
                        log.info("缓存成功,失效时间为：{}", invalidTime);
                    } else {
                        log.error("缓存失败");
                    }
                }
            } else {
                return jsonValue;
            }
        }
        return "success";
    }

    private static int getInvalidTime(String key) {
        int i = key.hashCode();
        //这里过期时间分三种，3天 4天 5天
        return (Math.abs(i) % 3) * OneDaySecond + SECOND;
    }

}
