package com.petter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author hongxf
 * @since 2017-08-31 9:00
 */
@ConfigurationProperties(prefix = "com.petter.blog")
public class BlogProperties {

    private String name;
    private String title;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BlogProperties{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
