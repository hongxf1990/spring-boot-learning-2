package com.petter.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @null        验证对象是否为空
 * @notnull     验证对象是否为非空
 * @asserttrue  验证 boolean 对象是否为 true
 * @assertfalse 验证 boolean 对象是否为 false
 * @min         验证 number 和 string 对象是否大等于指定的值
 * @max         验证 number 和 string 对象是否小等于指定的值
 * @decimalmin  验证 number 和 string 对象是否大等于指定的值，小数存在精度
 * @decimalmax  验证 number 和 string 对象是否小等于指定的值，小数存在精度
 * @size        验证对象（array,collection,map,string）长度是否在给定的范围之内
 * @digits      验证 number 和 string 的构成是否合法
 * @past        验证 date 和 calendar 对象是否在当前时间之前
 * @future      验证 date 和 calendar 对象是否在当前时间之后
 * @pattern     验证 string 对象是否符合正则表达式的规则
 * @Email       验证邮箱
 *
 * @author hongxf
 * @since 2017-09-01 9:45
 */
public class Demo {

    private Long id;

    //使用国际化的配置文件中信息
    @NotEmpty(message = "{validation.name}")
    private String name;

    @NotEmpty(message="密码不能为空")
    @Length(min=6, message="密码长度不能小于6位")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
