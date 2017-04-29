package education.cs.scu.controller;

import education.cs.scu.checkInformation.CheckInformation;
import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by maicius on 2017/4/22.
 */
@RestController
public class FriendsController {

    @Autowired
    CheckInformation checkInformation;
    @Autowired
    FriendService friendService;

    @RequestMapping(value="/addFriend")
    public String AddFriend(@RequestParam("userName") String userName,
                            @RequestParam("friendName") String friendName) throws Exception{
        AppUserInfo user = new AppUserInfo();
        user.setUserName(userName);
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


}
