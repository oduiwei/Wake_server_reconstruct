package education.cs.scu.mapper;

import education.cs.scu.entity.AppUserInfo;

/**
 * Created by maicius on 2017/3/31.
 */
public interface UserMapper {
    AppUserInfo doUserLogin(AppUserInfo user) throws Exception;
}
