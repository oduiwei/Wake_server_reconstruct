package education.cs.scu.service.impl;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.mapper.FriendMapper;
import education.cs.scu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by maicius on 2017/4/29.
 */
public class FriendServiceImpl implements FriendService {
    @Autowired
    private FriendMapper friendMapper;
    @Override
    public int addFriend(String userName, String friendName) throws Exception {
        return friendMapper.addFriend(userName, friendName);
    }

    @Override
    public int deleteFriend(String userName, String friendName) throws Exception {
        return friendMapper.deleteFriend(userName, friendName);
    }

    @Override
    public AppUserInfo getFriendList(AppUserInfo user) throws Exception {
        String friendList = "";
        List<AppUserInfo> appUserInfos = friendMapper.getFriendList(user);
        for (AppUserInfo appUserInfo: appUserInfos
             ) {
            friendList += appUserInfo.getUserName() +"#" + appUserInfo.getNickName() + "#" + appUserInfo.getBrief_intro() + "#";
            //System.out.println(friendList);
        }
        user.setFriendsList(friendList);

        return user;
    }

    @Override
    public AppUserInfo searchFriend(AppUserInfo user) throws Exception {
        String friend="";
        List<AppUserInfo> appUserInfos = friendMapper.searchFriend(user);
        for (AppUserInfo appuserInfo: appUserInfos
             ) {
            friend += appuserInfo.getUserName() + "#" + appuserInfo.getNickName() + "#";
        }
        user.setSearchFriends(friend);
        return user;
    }
}
