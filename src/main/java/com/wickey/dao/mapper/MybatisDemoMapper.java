package com.wickey.dao.mapper;

import com.wickey.bean.testbean.MybatisDemo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by fatboyliang on 2016/10/5.
 */
@Component
public interface MybatisDemoMapper {

    @Select("select * from mybatis_demo where name like #{name} ")
    public List<MybatisDemo> likeName(String name);

    @Select("select * from mybatis_demo where id = #{id}")
    public MybatisDemo getById(long id);

    @Select("select name from mybatis_demo where id = #{id}")
    public String getNameById(long id);

}
