package com.wickey.repository;

import com.wickey.bean.testbean.Demo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by fatboyliang on 2016/8/14.
 */
/*
 * 在CrudRepository自带常用的crud方法.
 * 这样一个基本dao就写完了.
 */
@Repository
@Transactional
public interface DemoRepository extends CrudRepository<Demo,Long>{


}
