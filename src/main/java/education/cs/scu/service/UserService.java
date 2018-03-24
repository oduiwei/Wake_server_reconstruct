package education.cs.scu.service;

import education.cs.scu.entity.AppUserInfo;

import java.util.List;

public interface UserService {
    List<AppUserInfo> doUserLogin(AppUserInfo user) throws Exception;
    int doUserRegist(AppUserInfo user) throws Exception;
    AppUserInfo getUserInfo(AppUserInfo userInfo) throws Exception;
    int setUserInfo(AppUserInfo userInfo) throws Exception;

    List<AppUserInfo> getAllUsers() throws Exception;
}
