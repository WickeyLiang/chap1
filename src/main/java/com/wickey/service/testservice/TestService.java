package com.wickey.service.testservice;

import com.wickey.bean.testbean.TestBean;
import javassist.NotFoundException;

/**
 * Created by fatboyliang on 2016/8/22.
 */
public interface TestService {

    public TestBean findById(long id);

    public void deleteFromCache(long id);

    void test();

    public TestBean save(TestBean rtb);

    public TestBean update(TestBean rtb) throws NotFoundException;

    public void delete(Long id);

}
