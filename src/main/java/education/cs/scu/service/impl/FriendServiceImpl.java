package education.cs.scu.service.impl;

import education.cs.scu.mapper.FriendMapper;
import education.cs.scu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
