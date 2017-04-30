package education.cs.scu.controller;

import education.cs.scu.entity.TimeInfo;
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

    @RequestMapping("/GetUpTime")
    public String registTime(@RequestParam("username") String userName,
                             @RequestParam("date") String getUpTime) throws Exception{
        TimeInfo userInfo = new TimeInfo();
        userInfo.setUserName(userName);
        userInfo.setGetUpTime(getUpTime);
        int registTime = timeService.registTime(userInfo);
        if(registTime > 0)
            return "success";
        else
            return "failed";
    }

    @RequestMapping("/SleepTime")
    public String sleepTime(@RequestParam("username") String userName,
                                        @RequestParam("hour") String hours,
                                        @RequestParam("date") String sleepDate) throws Exception{
        TimeInfo info = new TimeInfo();
        info.setUserName(userName);
        info.setHours(hours);
        info.setSleepdate(sleepDate);

        int registTime = timeService.registSleepTime(info);
        if(registTime > 0){
            return "success";
        }else{
            return "failed";
        }
    }
    @RequestMapping("/TimeHistory")
    public String getUpTimeHistory(@RequestParam("username") String userName) throws Exception{
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setUserName(userName);
        timeInfo = timeService.getUptimeHistory(timeInfo);
        return timeInfo.getGetUpTimeHistory();
    }

}
