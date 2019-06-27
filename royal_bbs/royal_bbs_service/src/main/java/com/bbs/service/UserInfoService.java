package com.bbs.service;

import com.bbs.domain.User;
import com.bbs.domain.ZoneApply;

public interface UserInfoService {
    void updateInfo(User user) throws Exception;

    void updatePassword(String newPassword, String userId);


    void zoneApply(ZoneApply zoneApply);
}
