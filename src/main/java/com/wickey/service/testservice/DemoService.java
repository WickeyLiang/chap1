package com.wickey.service.testservice;

import com.wickey.bean.testbean.Demo;
import com.wickey.config.datasource.dynamic.TargetDataSource;
import com.wickey.dao.testdao.DemoDao;
import com.wickey.repository.DemoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by fatboyliang on 2016/8/14.
 */
@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Resource
    private DemoDao demoDao;


    public void save(Demo demo){
        demoRepository.save(demo);
    }


    public Demo getById(long id){
        return demoRepository.findOne(id);
        //return demoDao.getById(id);
    }

    public void delById(long id) {
        demoRepository.delete(id);
    }


    /**
     * 不指定数据源使用默认数据源
     * @return
     */
    public List<Demo> getList(){
        return demoDao.getList();
    }

    /**
     * 指定数据源
     * @return
     */
    @TargetDataSource("ds1")
    public List<Demo> getListByDs1(){
        return demoDao.getListByDs1();
    }


    public void addDemo(Demo demo){
        demoDao.addDemo(demo);
    }

}
