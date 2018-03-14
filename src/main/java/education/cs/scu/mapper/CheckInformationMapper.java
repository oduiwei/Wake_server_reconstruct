package education.cs.scu.mapper;

import education.cs.scu.entity.AppUserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by maicius on 2017/4/22.
 */
public interface CheckInformationMapper {

    List<AppUserInfo> whetherUser(String userName) throws Exception;
    List<Integer> whetherFriends(@Param("userName") String userName, @Param("friendName") String friendName) throws Exception;
    int whetherIntimacy(@Param("userName") String userName,
                        @Param("friendName") String friendName) throws Exception;
}
