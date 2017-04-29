package education.cs.scu.mapper;

import education.cs.scu.entity.AppUserInfo;

/**
 * Created by maicius on 2017/4/30.
 */
public interface TimeMapper {
    int registTime(AppUserInfo userInfo) throws Exception;
}
