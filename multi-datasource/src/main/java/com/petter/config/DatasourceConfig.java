package com.petter.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author hongxf
 * @since 2017-08-29 9:40
 */
@Configuration
public class DatasourceConfig {

    /**
     * 1. 使用DataSourceBuilder
     * 2. 指定DruidDataSource
     * @return
     */
    @Bean(name = "primaryDS")
    //@Qualifier("primaryDS")
    @Primary
    @ConfigurationProperties(prefix="spring.primary.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean(name = "secondaryDS")
    //@Qualifier("secondaryDS")
    @ConfigurationProperties(prefix="spring.secondary.datasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
