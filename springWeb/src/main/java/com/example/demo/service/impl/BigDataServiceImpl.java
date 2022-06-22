package com.example.demo.service.impl;

import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.BigDataService;
import com.example.demo.util.ThreadPool;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/18 10:13 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Slf4j
@Service
public class BigDataServiceImpl implements BigDataService {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String userName;
    @Value("${spring.datasource.password}")
    private String password;
    public static final Integer PAGE_SIZE = 200;
    @Autowired
    private TestDao testDao;
    @Resource
    private TestMapper testMapper;

    private static List<StudentInfo> studentInfoList = Lists.newArrayList();
    static {
        for (Integer i = 1; i <= 10000; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setStuNo(i.toString());
            studentInfo.setStuName(i.toString());
            studentInfo.setStuAge(i.longValue());
            studentInfo.setStuSex(1L);
            studentInfo.setRemove(0);
            studentInfoList.add(studentInfo);
        }
    }

    /**
     * jdbc PreparedStatement
     * 优点1：可读性和维护性更好
     * 优点2：性能表现,
     * 优点3：防止SQL注入式攻击
     */
    @Override
    public void jdbcTest() {
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);
            PreparedStatement pst = conn.prepareStatement("INSERT INTO student_info(`ID`, `STU_NO`, `STU_NAME`, `STU_AGE`, `STU_SEX`, `CREATE_TIME`, `UPDATE_TIME`, `REMOVE`) VALUES ('00001', 2016001, '陆林北', 16, 1, '2021-07-14 11:09:53', '2021-07-14 11:09:58', 0)");
            pst.execute();
            conn.commit();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 311ms
     */
    @Override
    public void jdbcBatchTest() {
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);
            PreparedStatement pst = null;
            for (int i = 1; i <= 10000; i++) {
                log.info("" + i);
                String id = "900000";
                pst = conn.prepareStatement("INSERT INTO student_info(`ID`,`STU_NO`, `STU_NAME`, `STU_AGE`, `STU_SEX`, `CREATE_TIME`, `UPDATE_TIME`, `REMOVE`) VALUES (?, 2016001, '陆林北', 16, 1, '2021-07-14 11:09:53', '2021-07-14 11:09:58', 0)");
                pst.setString(1, id + i);
                pst.execute();
            }
            log.info("commit");
            conn.commit();
            pst.close();
            conn.close();
            log.info("end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void jpaTest() {
        log.info("begin");
        testDao.saveAll(studentInfoList);
        log.info("end");
    }

    @Override
    public void threadPoolTest() {
        log.info("begin");
        ThreadPool threadPool = ThreadPool.getThreadPool();
        int pages = (studentInfoList.size() / PAGE_SIZE) + 1;
        int pageNumber = 0;
        while (pageNumber < pages) {
            int start = pageNumber * PAGE_SIZE;
            int end;
            if (pageNumber == pages - 1) {
                end = studentInfoList.size();
            } else {
                end = start + PAGE_SIZE;
            }
            List<StudentInfo> sublist = studentInfoList.subList(start, end);
            threadPool.execute(() -> testDao.saveAll(sublist));
            pageNumber++;
        }
        log.info("end");
    }

    @Override
    public void JdbcTemplateTest() {}

    /**
     * 1000 条耗时 53450 毫秒
     */
    @Override
    public void makeBigData(int num) {
        LocalDateTime beginTime = LocalDateTime.now();
        for (int i = 0; i < num; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setId(String.valueOf(i));
            studentInfo.setStuSex(1L);
            studentInfo.setStuNo(String.valueOf(i));
            studentInfo.setStuName("小顾"+i);
            testDao.save(studentInfo);
        }
        Duration duration = Duration.between(beginTime, LocalDateTime.now());
        Long time = duration.toMillis();
        System.out.println(time);
    }

    /**
     * saveAll 虽然能 分批次 批量处理 但是 还是存在多次连接数据库，更新前查询操作，比较慢
     * 1000条耗时 7608
     * 100000 条 316761ms
     */
    @Override
    public void makeBigData2(int num) {
        LocalDateTime beginTime = LocalDateTime.now();
        List<StudentInfo> students = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setId(String.valueOf(i));
            studentInfo.setStuSex(1L);
            studentInfo.setStuNo(String.valueOf(i));
            studentInfo.setStuName("小顾"+i);
            students.add(studentInfo);
        }
        testDao.saveAll(students);
        Duration duration = Duration.between(beginTime, LocalDateTime.now());
        Long time = duration.toMillis();
        System.out.println(time);
    }

    /**
     * mybati 只连接一次数据库，直接更新，较快
     * 1000条 耗时 1014
     * 100000条 13373ms
     */
    @Override
    public void makeBigData3(int num) {
        LocalDateTime beginTime = LocalDateTime.now();
        List<StudentInfo> students = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setId(String.valueOf(i));
            studentInfo.setStuNo(String.valueOf(i));
            studentInfo.setStuName("小顾"+i);
            studentInfo.setStuAge(16L);
            studentInfo.setStuSex(1L);
            studentInfo.setRemove(0);
            students.add(studentInfo);
        }
        testMapper.batchSaveStudent(students);
        Duration duration = Duration.between(beginTime, LocalDateTime.now());
        Long time = duration.toMillis();
        System.out.println(time);
    }

    /**
     * 1000条 1467ms
     * 100000条 86307ms
     */
    @Override
    public void makeBigData4(int num) {
        LocalDateTime beginTime = LocalDateTime.now();
        try {
            Connection conn = DriverManager.getConnection(url, userName, password);
            conn.setAutoCommit(false);
            PreparedStatement pst = null;
            for (int i = 1; i <= num; i++) {
                String id = "900000";
                pst = conn.prepareStatement("INSERT INTO student_info(`ID`,`STU_NO`, `STU_NAME`, `STU_AGE`, `STU_SEX`, `CREATE_TIME`, `UPDATE_TIME`, `REMOVE`) VALUES (?, 2016001, '陆林北', 16, 1, '2021-07-14 11:09:53', '2021-07-14 11:09:58', 0)");
                pst.setString(1, id + i);
                pst.execute();
            }
            conn.commit();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Duration duration = Duration.between(beginTime, LocalDateTime.now());
        Long time = duration.toMillis();
        System.out.println(time);
    }

    @Override
    public void updateBigData1(Integer num) {
        List<StudentInfo> students = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            StudentInfo studentInfo = new StudentInfo();
            studentInfo.setId(String.valueOf(i));
            studentInfo.setStuNo(String.valueOf(i));
            studentInfo.setStuName("小顾"+i);
            studentInfo.setStuAge(16L);
            studentInfo.setStuSex(1L);
            studentInfo.setRemove(0);
            students.add(studentInfo);
        }
        testMapper.batchUpdate1(students);
        testMapper.batchUpdate2(students);
        testMapper.batchUpdate3(students);
    }


}
