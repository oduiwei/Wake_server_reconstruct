package education.cs.scu.service.impl;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.mapper.UserMapper;
import education.cs.scu.service.userService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * Created by maicius on 2017/3/31.
 */
public class userServiceImpl implements userService {
    @Autowired
    private UserMapper userMapper;

    public List<AppUserInfo> doUserLogin(AppUserInfo user) throws Exception{
        return userMapper.doUserLogin(user);
    }

    @Override
    public int doUserRegist(AppUserInfo user) throws Exception {
        return userMapper.doUserRegist(user);
    }

    @Override
    public AppUserInfo getUserInfo(AppUserInfo userInfo) throws Exception {
        userInfo = userMapper.getUserInfo(userInfo);
        String infos = userInfo.getUserName() + "#" + userInfo.getNickName() + "#" +userInfo.getBrief_intro();
        userInfo.setUserInfo(infos);
        return userInfo;
    }

    @Override
    public int setUserInfo(AppUserInfo userInfo) throws Exception {
        return userMapper.setUserInfo(userInfo);
    }
}
