package com.bbs.service.impl;

import com.bbs.dao.UserInfoDao;
import com.bbs.domain.User;
import com.bbs.domain.ZoneApply;
import com.bbs.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserInfoDao userInfoDao;


    @Override
    public void updateInfo(User user) throws Exception{

        userInfoDao.updateInfo(user);
    }

    @Override
    public void updatePassword(String newPassword, String userId) {
        userInfoDao.updatePassword(newPassword,userId);
    }

    @Override
    public void zoneApply(ZoneApply zoneApply) {
        userInfoDao.zoneApply(zoneApply);
    }
}
