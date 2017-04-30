package education.cs.scu.entity;

/**
 * Created by maicius on 2017/4/30.
 */
public class TimeInfo {
    private String userName;
    private String hours;
    private String sleepDate;
    private String getUpTime;
    private String getUpTimeHistory;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getSleepDate() {
        return sleepDate;
    }

    public void setSleepdate(String sleepDate) {
        this.sleepDate = sleepDate;
    }

    public String getGetUpTime() {
        return getUpTime;
    }

    public void setGetUpTime(String getUpTime) {
        this.getUpTime = getUpTime;
    }

    public String getGetUpTimeHistory() {
        return getUpTimeHistory;
    }

    public void setGetUpTimeHistory(String getUpTimeHistory) {
        this.getUpTimeHistory = getUpTimeHistory;
    }
}
