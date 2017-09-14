package com.petter.provider;

import com.petter.entity.Demo2;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author hongxf
 * @since 2017-09-14 13:39
 */
public class Demo2SqlProvider {

    public String selectByDemo(Demo2 demo2) {
        StringBuilder sb = new StringBuilder("select * from demo2 where 1=1 ");
        if (demo2.getName() != null) {
            sb.append(" and name = #{name}");
        }
        if (demo2.getEmail() != null) {
            sb.append(" and email = #{email}");
        }
        return sb.toString();
    }

    public String selectByDemo2(final Demo2 demo2) {
        return new SQL() {
            {
                SELECT("id,name,email");
                FROM("demo2");
                if (demo2.getName() != null) {
                    WHERE("name=#{name}");
                }
                if (demo2.getEmail() != null) {
                    WHERE("email=#{email}");
                }
                }
        }.toString();
    }

    public String save() {
        return new SQL(){
            {
                INSERT_INTO("demo2");
                INTO_COLUMNS("name", "email");
                INTO_VALUES("#{name}", "#{email}");
            }
        }.toString();
    }

    public String update(final Demo2 demo){
        return new SQL(){{
            UPDATE("demo2");

            //条件写法.
            if(demo.getName() != null){
                SET("name=#{name}");
            }
            if(demo.getEmail() != null){
                SET("email=#{email}");
            }
            WHERE("id=#{id}");
        }}.toString();
    }

    public String delete(){
        return new SQL(){{
            DELETE_FROM("demo2");
            WHERE("id=#{id}");
        }}.toString();
    }
}
