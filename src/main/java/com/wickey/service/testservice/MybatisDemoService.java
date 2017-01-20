package com.wickey.service.testservice;

import com.wickey.bean.testbean.MybatisDemo;
import com.wickey.dao.mapper.MybatisDemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fatboyliang on 2016/10/5.
 */
@Service
public class MybatisDemoService {

    @Autowired
    private MybatisDemoMapper mybatisDemoMapper;


    public List<MybatisDemo> likeName(String name){
        name = "%"+name+"%";
        return mybatisDemoMapper.likeName(name);
    }

}
