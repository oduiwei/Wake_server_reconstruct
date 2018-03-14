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
    private CheckInformationMapper checkInformationMapper;
    @Override
    public boolean whetherUser(String userName) throws Exception {
        List<AppUserInfo> userInfos =  checkInformationMapper.whetherUser(userName);
        if(userInfos.size() == 0)
            return false;
        else
            return true;
    }

    @Override
    public boolean whetherFriends(String userName, String friendName) throws Exception {
        List<Integer> info = checkInformationMapper.whetherFriends(userName, friendName);
        if(info.size() != 0)
            return true;
        else
            return false;
    }

    @Override
    public boolean whetherIntimacy(String userName, String friendName) throws Exception {
        int relation = checkInformationMapper.whetherIntimacy(userName, friendName);
        if (relation == 1)
            return true;
        else
            return false;
    }
}
