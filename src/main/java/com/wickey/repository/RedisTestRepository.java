package com.wickey.repository;

import com.wickey.bean.testbean.TestBean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fatboyliang on 2016/8/22.
 */
@Repository
public interface RedisTestRepository extends CrudRepository<TestBean,Long> {
}
