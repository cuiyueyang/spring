package com.example.demo.mapper;

import com.example.demo.domain.StudentInfo;
import feign.Param;

import java.util.List;

/**
 * <p>Description: mapper数据层</p>
 * <p>Date: 2021/7/14 11:31 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public interface TestMapper {

    StudentInfo test007();

    /**
     * 批量增加
     * @param students
     */
    void batchSaveStudent(@Param("students") List<StudentInfo> students);

    /**
     * 单个循环更新
     * @param students
     */
    void batchUpdate1(@Param("students") List<StudentInfo> students);

    /**
     * 根据id确定更新
     * @param students
     */
    void batchUpdate2(List<StudentInfo> students);

    /**
     * 无则添加，有则更新
     * @param students
     */
    void batchUpdate3(List<StudentInfo> students);
}
