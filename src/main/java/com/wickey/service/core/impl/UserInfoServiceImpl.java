package com.wickey.service.core.impl;

import com.wickey.bean.core.UserInfo;
import com.wickey.repository.core.UserInfoRepository;
import com.wickey.service.core.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by fatboyliang on 2016/10/12.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
