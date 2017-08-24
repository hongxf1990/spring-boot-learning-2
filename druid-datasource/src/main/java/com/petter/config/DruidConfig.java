package com.petter.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.petter.properties.DatasourceProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 手动注册servlet和filter，不需要@ServletComponentScan 注解
 * @author Administrator
 * @since 2017-02-12 11:34
 */
@Configuration
public class DruidConfig {

    @Resource
    private DatasourceProperties datasourceProperties;
    /**
     * 注册一个StatViewServlet
     * @return
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(
                new StatViewServlet(),"/druid/*");

        //添加初始化参数：initParams
        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","admin");
        servletRegistrationBean.addInitParameter("loginPassword","123456");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");
        return servletRegistrationBean;
    }


    /**
     * 注册一个：filterRegistrationBean
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());

        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    /**
     * 使用配置文件形式进行属性配置
     * 如果同时进行了编程式的注入和配置的注入，配置的就无效了。
     * @return
     */
    @Bean
    public DataSource druidDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(datasourceProperties.getUrl());
        druidDataSource.setUsername(datasourceProperties.getUsername());
        druidDataSource.setPassword(datasourceProperties.getPassword());
        druidDataSource.setDriverClassName(datasourceProperties.getDriverClassName());
        druidDataSource.setInitialSize(datasourceProperties.getInitialSize());
        druidDataSource.setMinIdle(datasourceProperties.getMinIdle());
        druidDataSource.setMaxActive(datasourceProperties.getMaxActive());
        druidDataSource.setMaxWait(datasourceProperties.getMaxWait());
        druidDataSource.setTimeBetweenEvictionRunsMillis(datasourceProperties.getTimeBetweenEvictionRunsMillis());
        druidDataSource.setMinEvictableIdleTimeMillis(datasourceProperties.getMinEvictableIdleTimeMillis());
        druidDataSource.setValidationQuery(datasourceProperties.getValidationQuery());
        druidDataSource.setTestWhileIdle(datasourceProperties.isTestWhileIdle());
        druidDataSource.setTestOnBorrow(datasourceProperties.isTestOnBorrow());
        druidDataSource.setTestOnReturn(datasourceProperties.isTestOnReturn());
        try {
            druidDataSource.setFilters(datasourceProperties.getFilters());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return druidDataSource;
    }

}
