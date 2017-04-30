package education.cs.scu.service;

import education.cs.scu.entity.TimeInfo;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeService {
    TimeInfo getUptimeHistory(TimeInfo timeInfo) throws Exception;
    int registTime(TimeInfo timeInfo) throws Exception;
    int registSleepTime(TimeInfo timeInfo) throws Exception;
}
