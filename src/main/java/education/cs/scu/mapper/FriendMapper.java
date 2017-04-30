package education.cs.scu.mapper;

import education.cs.scu.entity.AppUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by maicius on 2017/4/29.
 */
public interface FriendMapper {
    int addFriend(@Param("userName") String userName, @Param("friendName") String friendName) throws Exception;
    int deleteFriend(@Param("userName") String userName, @Param("friendName") String friendName) throws Exception;
    List<AppUserInfo> getFriendList(AppUserInfo userInfo) throws Exception;
    List<AppUserInfo> searchFriend(AppUserInfo userInfo) throws Exception;
    int setGetUpTip(AppUserInfo user) throws Exception;
    List<AppUserInfo> getGetUpTip(AppUserInfo user) throws Exception;
}
