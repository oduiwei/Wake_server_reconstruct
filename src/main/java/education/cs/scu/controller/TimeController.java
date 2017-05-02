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

    @RequestMapping(value="/GetUpTime", produces = "text/html;charset=UTF-8")
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

    @RequestMapping(value="/SleepTime", produces = "text/html;charset=UTF-8")
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
    @RequestMapping(value="/TimeHistory", produces = "text/html;charset=UTF-8")
    public String getUpTimeHistory(@RequestParam("username") String userName) throws Exception{
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setUserName(userName);
        timeInfo = timeService.getUptimeHistory(timeInfo);
        System.out.println(timeInfo.getGetUpTimeHistory());
        return timeInfo.getGetUpTimeHistory();
    }

    @RequestMapping(value="/GetSleepTime",produces = "text/html;charset=UTF-8")
    public String GetSleepTimeHistory(@RequestParam("username") String userName) throws Exception{
        TimeInfo timeInfo = new TimeInfo();
        timeInfo.setUserName(userName);
        timeInfo = timeService.getSleepTimeHistory(timeInfo);
        System.out.println(timeInfo.getSleepTimeHistory());
        return timeInfo.getSleepTimeHistory();
    }

}
