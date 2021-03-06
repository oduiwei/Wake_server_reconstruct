package education.cs.scu.service.impl;

import education.cs.scu.entity.RecentSleepInfo;
import education.cs.scu.entity.SleepInfo;
import education.cs.scu.entity.TimeInfo;
import education.cs.scu.mapper.SleepMapper;
import education.cs.scu.mapper.TimeMapper;
import education.cs.scu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maicius on 2017/4/30.
 */
public class TimeServiceImpl implements TimeService {

    @Autowired
    TimeMapper timeMapper;

    @Autowired
    SleepMapper sleepMapper;

    /**
     * 根据用户名和过滤选项查询某个时间段内用户的起床时间记录
     * @param username   --> 用户名
     * @param start      --> 起始时间
     * @param end        --> 截至时间
     * @return
     * @throws Exception
     */
    @Override
    public List<SleepInfo> getUptimeHistory(String username, String start, String end) throws Exception {
        return sleepMapper.getUserPeriodSleepData(username, start, end);
    }

    /**
     * 更新用户最近睡眠数据
     * @param sleepInfo  --> 用户最近睡眠数据
     * @return
     * @throws Exception
     */
    @Override
    public int updRecentSleepInfo(RecentSleepInfo sleepInfo) throws Exception {
        return timeMapper.updRecentSleepInfo(sleepInfo);
    }

    /**
     * 获取用户及其好友最近睡眠排民数据
     * @param username  --> 用户名
     * @return
     * @throws Exception
     */
    @Override
    public List<RecentSleepInfo> getFriendsSleepRank(String username) throws Exception {
        return timeMapper.getFriendsSleepRank(username);
    }

    /**
     * 获取用户最近睡眠数据
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public RecentSleepInfo getUserRecentSleepInfo(String username) throws Exception {
        return timeMapper.getUserRecentSleepInfo(username);
    }

    @Override
    public int registTime(TimeInfo timeInfo) throws Exception {
        return  timeMapper.registTime(timeInfo);
    }

    @Override
    public TimeInfo getUptimeHistory(TimeInfo timeInfo) throws Exception {
        String timeList="";
        List<TimeInfo> timeInfos =  timeMapper.getUpTimeHistory(timeInfo);
        for (TimeInfo Info: timeInfos
             ) {
            timeList += Info.getGetUpTime().substring(0, 19) + "#";
        }
        timeInfo.setGetUpTimeHistory(timeList);
        return timeInfo;
    }

    @Override
    public int registSleepTime(TimeInfo timeInfo) throws Exception {
        return timeMapper.registSleepTime(timeInfo);
    }

    @Override
    public TimeInfo getSleepTimeHistory(TimeInfo timeInfo) throws Exception {
        String SleepList="";
        List<TimeInfo> timeInfos = timeMapper.getSleepTimeHistory(timeInfo);
        for (TimeInfo info: timeInfos
             ) {
            SleepList += info.getSleepDate() +" "+ info.getHours() + "#";
        }
        timeInfo.setSleepTimeHistory(SleepList);

        return timeInfo;
    }
}
