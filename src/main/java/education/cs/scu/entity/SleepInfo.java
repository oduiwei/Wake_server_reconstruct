package education.cs.scu.entity;

// 表sleep_info对应实体类
public class SleepInfo {
    private String username;    //用户ID
    private String sleepDate;   //某一天
    private String totalSleep;  //当天睡眠总时间
    private String lightSleep;  //当天浅睡总时间
    private String deepSleep;   //当天深睡总时间
    private String sleepTime;   //睡觉时间
    private String upTime;      //起床时间

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSleepDate() {
        return sleepDate;
    }

    public void setSleepDate(String sleepDate) {
        this.sleepDate = sleepDate;
    }

    public String getTotalSleep() {
        return totalSleep;
    }

    public void setTotalSleep(String totalSleep) {
        this.totalSleep = totalSleep;
    }

    public String getLightSleep() {
        return lightSleep;
    }

    public void setLightSleep(String lightSleep) {
        this.lightSleep = lightSleep;
    }

    public String getDeepSleep() {
        return deepSleep;
    }

    public void setDeepSleep(String deepSleep) {
        this.deepSleep = deepSleep;
    }

    public String getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(String sleepTime) {
        this.sleepTime = sleepTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }
}
