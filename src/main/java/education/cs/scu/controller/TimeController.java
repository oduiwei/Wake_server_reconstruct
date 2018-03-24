package education.cs.scu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import education.cs.scu.entity.RecentSleepInfo;
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

    /**
     * 获取用户与其好友的睡眠排名数据
     * @param username  --> 用户名
     * @return          --> 返回数据JSON格式的字符串
     * @throws Exception
     */
    @RequestMapping(value = "/GetFriendsSleepRank", produces = "application/json;charset=UTF-8")
    public String getFriendsSleepRank(@RequestParam("username") String username) throws Exception {
        List<RecentSleepInfo> rank = timeService.getFriendsSleepRank(username);

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        for (RecentSleepInfo info : rank) {
            JSONObject tmp = new JSONObject();
            JSONObject value = new JSONObject();
            value.put("avg_total", info.getAvgTotal());
            value.put("avg_sleep", info.getAvgSleep());
            value.put("avg_up", info.getAvgUp());
            value.put("qlty_bad", info.getQltyBad());
            value.put("qlty_ord", info.getQltyOrd());
            value.put("qlty_good", info.getQltyGood());
            value.put("score", info.getScore());
            value.put("nickname", info.getNickname());
            tmp.put(info.getUsername(), value);
            jsonArray.add(tmp);
        }

        jsonObject.put("data", jsonArray);
        return jsonObject.toString();
    }

    /**
     * 获取用户最近睡眠建议
     * @param username  --> 用户名
     * @return          --> 根据睡眠评分返回不同的建议
     * @throws Exception
     */
    @RequestMapping(value = "/GetSleepAdvice", produces = "application/json;charset=UTF-8")
    public String getRecentSleepAdvice(@RequestParam("username") String username) throws Exception {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        RecentSleepInfo info = timeService.getUserRecentSleepInfo(username);
        int score = info.getScore();
        float sleep = info.getAvgSleep();
        float up = info.getAvgUp();
        float total = info.getAvgTotal();

        boolean flag = false;
        if (score < 60) {
            jsonArray.add("您最近睡眠质量不是很好，请继续加油哦！");
        } else if (score < 80) {
            jsonArray.add("您最近睡眠质量一般，您还可以做得更好哦！");
        } else {
            jsonArray.add("您最近睡眠质量非常好，请继续保持哦！");
        }
        jsonArray.add("\n您的平均睡眠时长为：" + total);
        jsonArray.add("\n您的平均睡觉睡觉时间为：" + sleep);
        jsonArray.add("\n您的平均起床时间为：" + up);
        jsonArray.add("\n给您的建议：");
        if (total < 8) {
            jsonArray.add("\n-- 睡眠时长较短，不如多睡一会儿！");
            flag = true;
        }
        if (sleep >= 1) {
            jsonArray.add("\n-- 睡觉时间较晚，长时间熬夜可不好哦！");
            flag = true;
        }
        if (up < 6) {
            jsonArray.add("\n-- 起床时间较早，持续太早起可不好哦！");
            flag = true;
        }
        if (!flag) {
            jsonArray.add("\n-- 睡眠良好，请继续保持哦！");
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
