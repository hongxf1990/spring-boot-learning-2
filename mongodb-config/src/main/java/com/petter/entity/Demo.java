package com.petter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hongxf
 * @since 2017-09-06 15:42
 */
@Document(collection = "petter_collection") //如果不显式指定collection，Spring会根据实体类的名字去推测集合的名字
public class Demo {

    @Id
    private String id;
    private String name;
    private String telephoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }
}
