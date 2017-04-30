package education.cs.scu.service.impl;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.entity.TimeInfo;
import education.cs.scu.mapper.TimeMapper;
import education.cs.scu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;

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
    public AppUserInfo getUptime(TimeInfo timeInfo) throws Exception {
        return null;
    }

    @Override
    public int registSleepTime(TimeInfo timeInfo) throws Exception {
        return timeMapper.registSleepTime(timeInfo);
    }
}
