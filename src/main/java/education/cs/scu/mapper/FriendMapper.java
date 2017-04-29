package education.cs.scu.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * Created by maicius on 2017/4/29.
 */
public interface FriendMapper {
    int addFriend(@Param("userName") String userName, @Param("friendName") String friendName) throws Exception;
}
