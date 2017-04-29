package education.cs.scu.checkInformation.checkInformationImpl;

import education.cs.scu.checkInformation.CheckInformation;
import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.mapper.CheckInformationMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/4/29.
 */
public class CheckInformationImpl implements CheckInformation {
    @Autowired
    CheckInformationMapper checkInformationMapper;
    @Override
    public boolean whetherUser(String userName) throws Exception {
        List<AppUserInfo> userInfos =  checkInformationMapper.whetherUser(userName);
        if(userInfos == null)
            return false;
        else
            return true;
    }

    @Override
    public boolean whetherFriends(String userName, String friendName) throws Exception {
        List<Integer> info = checkInformationMapper.whetherFriends(userName, friendName);
        if(info == null)
            return true;
        else
            return false;
    }
}
