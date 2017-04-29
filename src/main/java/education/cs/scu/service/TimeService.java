package education.cs.scu.service;

import education.cs.scu.entity.AppUserInfo;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeService {
    AppUserInfo getUptime(AppUserInfo userInfo) throws Exception;
    int registTime(AppUserInfo userInfo) throws Exception;
}
