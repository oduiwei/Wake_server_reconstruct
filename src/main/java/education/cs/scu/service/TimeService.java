package education.cs.scu.service;

import education.cs.scu.entity.RecentSleepInfo;
import education.cs.scu.entity.SleepInfo;
import education.cs.scu.entity.TimeInfo;

import java.util.List;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeService {
    TimeInfo getUptimeHistory(TimeInfo timeInfo) throws Exception;
    int registTime(TimeInfo timeInfo) throws Exception;
    int registSleepTime(TimeInfo timeInfo) throws Exception;

    TimeInfo getSleepTimeHistory(TimeInfo timeinfo) throws Exception;

    List<SleepInfo> getUptimeHistory(String username, String start, String end) throws Exception;

    int updRecentSleepInfo(RecentSleepInfo sleepInfo) throws Exception;
    List<RecentSleepInfo> getFriendsSleepRank(String username) throws Exception;
    RecentSleepInfo getUserRecentSleepInfo(String username) throws Exception;
}
