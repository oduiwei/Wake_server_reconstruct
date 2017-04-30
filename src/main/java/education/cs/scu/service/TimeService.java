package education.cs.scu.service;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.entity.TimeInfo;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeService {
    AppUserInfo getUptime(TimeInfo timeInfo) throws Exception;
    int registTime(TimeInfo timeInfo) throws Exception;
    int registSleepTime(TimeInfo timeInfo) throws Exception;
}
