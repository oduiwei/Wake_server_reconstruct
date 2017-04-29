package education.cs.scu.entity;

/**
 * Created by maicius on 2017/3/31.
 */
public class AppUserInfo {
    private String userName;
    private String password;
    private String nickName;
    private String timeList;
    private String friendsList;
    private String sleepList;
    private String greetingInfo;
    private String userInfo;
    private String brief_intro;
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTimeList() {
        return timeList;
    }

    public void setTimeList(String timeList) {
        this.timeList = timeList;
    }

    public String getFriendsList() {
        return friendsList;
    }

    public void setFriendsList(String friendsList) {
        this.friendsList = friendsList;
    }

    public String getSleepList() {
        return sleepList;
    }

    public void setSleepList(String sleepList) {
        this.sleepList = sleepList;
    }

    public String getGreetingInfo() {
        return greetingInfo;
    }

    public void setGreetingInfo(String greetingInfo) {
        this.greetingInfo = greetingInfo;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getBrief_intro() {
        return brief_intro;
    }

    public void setBrief_intro(String brief_intro) {
        this.brief_intro = brief_intro;
    }
}
