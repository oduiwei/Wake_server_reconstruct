package education.cs.scu.controller;

import education.cs.scu.checkInformation.CheckInformation;
import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;

@RestController
public class FriendsController {

    @Autowired
    CheckInformation checkInformation;
    @Autowired
    FriendService friendService;

    @RequestMapping(value="/AddFriend", produces = "text/html;charset=UTF-8")
    public String AddFriend(@RequestParam("username") String userName,
                            @RequestParam("friendName") String friendName) throws Exception{
        boolean whetherUser = checkInformation.whetherUser(friendName);
        if(whetherUser){
            boolean whetherFriends = checkInformation.whetherFriends(userName, friendName);

            if(!whetherFriends){
                int addFriend = friendService.addFriend(userName, friendName);
                if(addFriend > 0)
                    return "success";
                else
                    return "failed";
            }else{
                System.out.println("already");
                return "already";
            }
        }else{
            return "notuser";
        }

    }

    @RequestMapping(value="/DeleteFriend", produces = "text/html;charset=UTF-8")
    public String DeleteFriend(@RequestParam("username") String userName,
                               @RequestParam("friendName") String friendName) throws Exception{
        int delete = friendService.deleteFriend(userName, friendName);
        if(delete > 0)
            return "success";
        else
            return "failed";
    }

    /**
     * 设置亲友
     * @param username   --> 用户名
     * @param friendName --> 好友用户名
     * @return           --> 成功返回success，失败返回failed
     * @throws Exception
     */
    @RequestMapping(value = "/SetRelation", produces = "text/html;charset=UTF-8")
    public String setIntimacyRelation(@RequestParam("username") String username,
                                      @RequestParam("friendName") String friendName) throws Exception {
        int num = friendService.setIntimacyRelation(username, friendName);
        if (num > 0)
            return "success";
        else
            return "failed";
    }

    @RequestMapping(value="/GetFriendsList", produces = "text/html;charset=UTF-8")
    public String getFriendList(@RequestParam("username") String userName) throws Exception{
            AppUserInfo user = new AppUserInfo();
            user.setUserName(userName);
            user = friendService.getFriendList(user);
            return user.getFriendsList();
    }

    @RequestMapping(value="/SearchFriend", produces = "text/html;charset=UTF-8")
    public String searchFriend(@RequestParam("username") String username,
                               @RequestParam("friendName") String friendNickName) throws Exception{
        //此处由于前端不正常对复用导致命名不正常，username应为nickName，friendName应为username
        String nickName = URLDecoder.decode(username, "UTF-8");
        friendNickName = URLDecoder.decode(friendNickName, "UTF-8");
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setNickName(nickName);
        appUserInfo.setUserName(friendNickName);
        //System.out.println(appUserInfo.getUserName()+" " +appUserInfo.getNickName());
        appUserInfo = friendService.searchFriend(appUserInfo);
        System.out.println(appUserInfo.getSearchFriends());
        return appUserInfo.getSearchFriends();
    }

    @RequestMapping(value="/SetGetUpTip", produces = "text/html;charset=UTF-8")
    public String SetGetUpTip(@RequestParam("username") String userName,
                              @RequestParam("friendname") String friendName,
                              @RequestParam("tip") String tip ) throws Exception{
        tip = URLDecoder.decode(tip, "UTF-8");
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setUserName(userName);
        appUserInfo.setFriendName(friendName);
        appUserInfo.setTip(tip);

        int info = friendService.setGetUpTip(appUserInfo);
        if(info > 0)
            return "success";
        else
            return "failed";
    }

    @RequestMapping(value="/GetGetUpTip",produces = "text/html;charset=UTF-8")
    public String GetWakeUpTip(@RequestParam("username") String userName) throws Exception{
        AppUserInfo user = new AppUserInfo();
        user.setUserName(userName);
        user = friendService.getWakeUpTip(user);
        return user.getGreetingInfo();
    }
}
