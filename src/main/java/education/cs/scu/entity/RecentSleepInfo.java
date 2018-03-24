package education.cs.scu.entity;

public class RecentSleepInfo {
    private String username;    // 用户名
    private float avgTotal;     // 平均睡眠时间
    private float avgSleep;     // 平均睡觉时间
    private float avgUp;        // 平均起床时间
    private float qltyBad;      // 睡眠质量差的天数
    private float qltyOrd;      // 睡眠质量一般的天数
    private float qltyGood;     // 睡眠质量良好的天数
    private int score;          // 睡眠质量评分
    private int count;          // 用来保存数据库中已有数据的条数
    private String nickname;    // 用户昵称

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getAvgTotal() {
        return avgTotal;
    }

    public void setAvgTotal(float avgTotal) {
        this.avgTotal = avgTotal;
    }

    public float getAvgSleep() {
        return avgSleep;
    }

    public void setAvgSleep(float avgSleep) {
        this.avgSleep = avgSleep;
    }

    public float getAvgUp() {
        return avgUp;
    }

    public void setAvgUp(float avgUp) {
        this.avgUp = avgUp;
    }

    public float getQltyBad() {
        return qltyBad;
    }

    public void setQltyBad(float qltyBad) {
        this.qltyBad = qltyBad;
    }

    public float getQltyOrd() {
        return qltyOrd;
    }

    public void setQltyOrd(float qltyOrd) {
        this.qltyOrd = qltyOrd;
    }

    public float getQltyGood() {
        return qltyGood;
    }

    public void setQltyGood(float qltyGood) {
        this.qltyGood = qltyGood;
    }
}
