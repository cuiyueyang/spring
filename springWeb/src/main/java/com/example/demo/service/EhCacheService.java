package com.example.demo.service;

import com.example.demo.domain.StudentInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

/**
 * <p>Description: </p>
 * <p>@date 2021/12/27 10:36</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@CacheConfig(cacheNames = "studentInfo")
public interface EhCacheService {

    StudentInfo queryById(String stuNo);

    void update(StudentInfo studentInfo);

    @Cacheable(key = "#p0")
    StudentInfo queryByIdCache(String id);

    @CachePut(key = "#p0.id")
    void updateCache(StudentInfo studentInfo);
}
