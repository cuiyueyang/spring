package com.example.demo.dao;

import com.example.demo.domain.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>Description: 数据层dao</p>
 * <p>Date: 2021/7/14 10:38 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */

public interface TestDao extends JpaRepository<StudentInfo, String> {

    @Query(value = "select * from student_info order by create_time desc limit 1 ",nativeQuery = true)
    StudentInfo selectDemo();

    /**
     *
     * @param stuNo
     * @return
     */
    @Query(value = "select * from student_info where stu_no = ?1 ",nativeQuery = true)
    List<StudentInfo> findByStuNo(String stuNo);

}
