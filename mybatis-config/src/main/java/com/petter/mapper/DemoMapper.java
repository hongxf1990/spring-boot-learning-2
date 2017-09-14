package com.petter.mapper;

import com.petter.entity.Demo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 1、使用#{}意味着使用预编译语句，即jdbc的preparedStatement  (最好使用，防止sql注入)，传入的值默认为字符串
 * select * from demo where id = #{id} --> select * from demo where id = '2'
 * select * from #{tableName} order by #{id}  --> select * from 'table1' order by 'id' (报错)
 * 2、使用${}不会当做字符串处理，传入什么就是什么（最好用于传入一些固定名称）
 * select * from demo where id = ${id} --> select * from demo where id = 2
 * select * from ${tableName} order by ${id}  --> select * from table1 order by id
 *
 * @author Administrator
 * @since 2017-02-17 21:29
 */
@Repository
public interface DemoMapper {

    @Select("select * from demo where id = #{id}")
    Demo getById(Long id);

    /**
     * 同上方法，但是不推荐
     * @param id
     * @return
     */
    @Select("select * from demo where id = ${id}")
    @Results({
            @Result(column = "telephone_number", property = "telephoneNumber")
    })
    Demo getById2(@Param("id") Long id);

    /**
     * 这里的name传入格式是 '%hongxf%'
     * @param name
     * @return
     */
    @Select("select * from demo where name like #{name}")
    //@Results注解将指定列于指定JavaBean属性映射起来
    @Results({
            @Result(column = "telephone_number", property = "telephoneNumber")
    })
    List<Demo> likeName(String name);

    @Select("select name from demo where id = #{id}")
    String getNameById(String name);

    @Insert("insert into Demo(name,telephone_number) values(#{name},#{telephoneNumber})")
    //该注解可以获取插入后记录的主键id，useGeneratedKeys=true就是定义数据库返回主键ID的，保存完成以后，会把demo里面的id设置成数据库中真正id值
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void save(
            Demo demo
    );

    void saveDemo(Demo demo);
}
