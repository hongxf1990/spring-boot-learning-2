package com.petter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongxf
 * @since 2017-08-24 16:03
 */
@ConfigurationProperties(prefix = "petter")
public class PetterProperties {

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
