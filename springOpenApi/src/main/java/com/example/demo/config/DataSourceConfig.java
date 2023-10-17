package com.example.demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
/**
 * @author dalaoyang
 * @Description
 * @project springboot_learn
 * @package com.dalaoyang.config
 * @email yangyang@dalaoyang.cn
 * @date 2018/4/10
 */
@Configuration
public class DataSourceConfig {
    @Primary
    @Bean(name = "testDataSource")
    @Qualifier("testDataSource")
    @ConfigurationProperties(prefix="spring.datasource.db1")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "test2DataSource")
    @Qualifier("test2DataSource")
    @ConfigurationProperties(prefix="spring.datasource.db2")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
