package education.cs.scu.service.impl;

import education.cs.scu.entity.TimeInfo;
import education.cs.scu.mapper.TimeMapper;
import education.cs.scu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/4/30.
 */
public class TimeServiceImpl implements TimeService {

    @Autowired
    TimeMapper timeMapper;

    @Override
    public int registTime(TimeInfo timeInfo) throws Exception {
        return  timeMapper.registTime(timeInfo);
    }

    @Override
    public TimeInfo getUptimeHistory(TimeInfo timeInfo) throws Exception {
        String timeList="";
        List<TimeInfo> timeInfos =  timeMapper.getUpTimeHstory(timeInfo);
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
}
