package education.cs.scu.mapper;

import education.cs.scu.entity.SleepInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SleepMapper {

    List<SleepInfo> getUserPeriodSleepData(@Param("username") String username,
                                           @Param("start") String start,
                                           @Param("end") String end) throws Exception;
}
