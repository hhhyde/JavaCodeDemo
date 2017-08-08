package com.example.webmvc.dao.config;

import java.beans.PropertyVetoException;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * 配置数据源
 */
@Configuration
public class DBconfig {
    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DruidDataSource dataSource() throws PropertyVetoException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("db.url"));
        dataSource.setUsername(env.getProperty("db.account"));
        dataSource.setPassword(env.getProperty("db.passward"));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setMinIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setMaxIdle(300);
        return dataSource;
    }
}