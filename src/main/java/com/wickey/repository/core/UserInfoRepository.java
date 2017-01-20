package com.wickey.repository.core;

import com.wickey.bean.core.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fatboyliang on 2016/10/12.
 */
@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo,Long>{

    public UserInfo findByUsername(String username);

}
