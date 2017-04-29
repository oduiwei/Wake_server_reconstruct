package education.cs.scu.service;

import education.cs.scu.entity.AppUserInfo;

/**
 * Created by maicius on 2017/4/29.
 */
public interface FriendService {
    int addFriend(String userName, String friendName) throws Exception;
    int deleteFriend(String userName, String friendName) throws Exception;
    AppUserInfo getFriendList(AppUserInfo user) throws Exception;
    AppUserInfo searchFriend(AppUserInfo user) throws Exception;
}
