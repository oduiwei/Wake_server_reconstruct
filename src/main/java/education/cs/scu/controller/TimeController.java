package education.cs.scu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import education.cs.scu.entity.SleepInfo;
import education.cs.scu.entity.TimeInfo;
import education.cs.scu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class TimeController {
    @Autowired
    TimeService timeService;

    /**
     * 获取用户某段时间内的所有睡眠数据
     * @param username --> 用户名
     * @param start    --> 起始时间
     * @param end      --> 截止时间
     * @return         --> 返回SleepInfo:Bean构成的列表
     * @throws Exception
     */
    private List<SleepInfo> getUserSleepData(String username, String start, String end) throws Exception {

        return timeService.getUptimeHistory(username, start, end);
    }

    /**
     * 获取用户某段时间内的起床时间记录
     * @param username --> 用户名
     * @param start    --> 起始时间
     * @param end      --> 截至时间
     * @return         --> 返回数据JSON格式的字符串
     * @throws Exception
     */
    @RequestMapping(value = "/GetUpTimeHistory", produces = "application/json;charset=UTF-8")
    public String getUserUpTimeHistory(@RequestParam("username") String username,
                                       @RequestParam("start") String start,
                                       @RequestParam("end") String end) throws Exception {
        List<SleepInfo> sleepInfos = getUserSleepData(username, start, end);

        JsonArray jsonArray = new JsonArray();
        JsonObject jsonObject = new JsonObject();

        for (SleepInfo info: sleepInfos) {
            jsonArray.add(info.getUpTime());
        }

        jsonObject.add("data", jsonArray);

        return jsonObject.toString();
    }

    /**
     * 获取用户某段时间内的睡觉时间记录
     * @param username --> 用户名
     * @param start    --> 起始时间
     * @param end      --> 截至时间
     * @return         --> 返回数据JSON格式的字符串
     * @throws Exception
     */
    @RequestMapping(value = "/GetSleepTimeHistory", produces = "application/json;charset=UTF-8")
    public String getUserSleepTimeHistory(@RequestParam("username") String username,
                                          @RequestParam("start") String start,
                                          @RequestParam("end") String end) throws Exception {
        List<SleepInfo> sleepInfos = getUserSleepData(username, start, end);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (SleepInfo info : sleepInfos) {
            JSONObject tmp = new JSONObject();
            tmp.put(info.getSleepDate(), info.getSleepTime());
            jsonArray.add(tmp);
        }

        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     * 获取用户某段时间内睡眠时间记录
     * @param username --> 用户名
     * @param start    --> 起始时间
     * @param end      --> 截止时间
     * @return         --> 返回数据JSON格式的字符串
     * @throws Exception
     */
    @RequestMapping(value = "/GetSleepDurationHistory", produces = "application/json;charset=UTF-8")
    public String getUserSleepDurationHistory(@RequestParam("username") String username,
                                              @RequestParam("start") String start,
                                              @RequestParam("end") String end) throws Exception {
        List<SleepInfo> sleepInfos = getUserSleepData(username, start, end);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (SleepInfo info : sleepInfos) {
            JSONObject tmp = new JSONObject();
            JSONObject duration = new JSONObject();
            duration.put("totalsleep", info.getTotalSleep());
            duration.put("lightsleep", info.getLightSleep());
            duration.put("deepsleep", info.getDeepSleep());
            tmp.put(info.getSleepDate(), duration);
            jsonArray.add(tmp);
        }

        jsonObject.put("data", jsonArray);

        return jsonObject.toString();
    }

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
