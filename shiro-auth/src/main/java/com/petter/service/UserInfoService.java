package com.petter.service;

import com.petter.entity.UserInfo;
import com.petter.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author hongxf
 * @since 2017-02-17 14:04
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    public UserInfo findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }
}
