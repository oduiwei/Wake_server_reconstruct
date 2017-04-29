package education.cs.scu.service;

import education.cs.scu.entity.AppUserInfo;

/**
 * Created by maicius on 2017/3/31.
 */
public interface LoginService {
    AppUserInfo doUserLogin(AppUserInfo user) throws Exception;
}
