package education.cs.scu.mapper;

import education.cs.scu.entity.AppUserInfo;

import java.util.List;

/**
 * Created by maicius on 2017/3/31.
 */
public interface UserMapper {
    List<AppUserInfo> doUserLogin(AppUserInfo user) throws Exception;
    int doUserRegist(AppUserInfo user) throws Exception;
    AppUserInfo getUserInfo(AppUserInfo userInfo) throws Exception;
    int setUserInfo(AppUserInfo user) throws Exception;
}
