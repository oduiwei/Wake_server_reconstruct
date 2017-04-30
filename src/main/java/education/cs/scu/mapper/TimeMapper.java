package education.cs.scu.mapper;

import education.cs.scu.entity.TimeInfo;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeMapper {
    int registTime(TimeInfo timeInfo) throws Exception;
    int registSleepTime(TimeInfo timeInfo) throws Exception;
}
