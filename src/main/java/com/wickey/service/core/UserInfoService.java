package com.wickey.service.core;

import com.wickey.bean.core.UserInfo;

/**
 * Created by fatboyliang on 2016/10/12.
 */
public interface UserInfoService {

    public UserInfo findByUsername(String username);
}
