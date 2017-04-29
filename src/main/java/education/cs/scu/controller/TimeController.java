package education.cs.scu.controller;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by maicius on 2017/4/30.
 */
@RestController
public class TimeController {
    @Autowired
    TimeService timeService;

    @RequestMapping("/GetUptime")
    public String registTime(@RequestParam("username") String userName,
                             @RequestParam("date") String getUpTime) throws Exception{
        AppUserInfo userInfo = new AppUserInfo();
        userInfo.setUserName(userName);
        userInfo.setGetUpTime(getUpTime);
        int registTime = timeService.registTime(userInfo);
        if(registTime > 0)
            return "success";
        else
            return "failed";
    }
}
