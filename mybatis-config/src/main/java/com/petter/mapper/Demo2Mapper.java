package com.petter.mapper;

import com.petter.entity.Demo2;
import com.petter.provider.Demo2SqlProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hongxf
 * @since 2017-09-14 11:36
 */
@Repository
public interface Demo2Mapper {

    //@SelectProvider(type = Demo2SqlProvider.class, method = "selectByDemo")
    @SelectProvider(type = Demo2SqlProvider.class, method = "selectByDemo2")
    List<Demo2> selectByDemo(Demo2 demo);

    @InsertProvider(type=Demo2SqlProvider.class, method="save")
    @Options(keyColumn="id", useGeneratedKeys=true)
    void save(Demo2 demo);

    @UpdateProvider(type=Demo2SqlProvider.class,method="update")
    int update(Demo2 demo);

    @UpdateProvider(type=Demo2SqlProvider.class,method="delete")
    int delete(int id);
}
