package com.example.demo.service;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/18 10:13 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public interface BigDataService {
    void jdbcTest();

    void jdbcBatchTest();

    void jpaTest();

    void threadPoolTest();

    void JdbcTemplateTest();

    void makeBigData(int num);

    void makeBigData2(int num);

    void makeBigData3(int num);

    void makeBigData4(int num);

    void updateBigData1(Integer num);
}
