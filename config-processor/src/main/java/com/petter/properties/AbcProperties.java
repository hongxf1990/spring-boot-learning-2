package com.petter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 自定义配置文件使用
 * @author hongxf
 * @since 2017-08-31 10:09
 */
@Component //必须交给Spring进行管理，这里的必须使用
@ConfigurationProperties(prefix = "abc")
//因为ConfigurationProperties取消了locations属性，这里使用注解@PropertySource指定配置文件位置
@PropertySource("classpath:conf/abc.properties")
public class AbcProperties {

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
