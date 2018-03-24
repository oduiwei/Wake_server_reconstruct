package education.cs.scu.task;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.entity.RecentSleepInfo;
import education.cs.scu.entity.SleepInfo;
import education.cs.scu.service.TimeService;
import education.cs.scu.service.UserService;
import education.cs.scu.utils.DateUtil;
import education.cs.scu.utils.NumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自启动任务：负责计算所有用户最近的睡眠情况
 * 将数据保存到recent_sleep表
 */
@Component
public class RecentSleepTimerTask {
    @Autowired
    private UserService userService;

    @Autowired
    private TimeService timeService;

    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(cron = "0/10 * * * * *")
    public void execute() throws Exception {
        System.out.println("准备更新用户最近睡眠数据...");
        String start = DateUtil.getMondayOfWeek();

        List<AppUserInfo> users = userService.getAllUsers();
        // 计算每个用户最近的平均睡眠情况
        for (AppUserInfo user : users) {
            float avg_total = 0f, avg_sleep = 0f, avg_up = 0f;
            int qlty_bad = 0, qlty_ord = 0, qlty_good = 0;
            float bad_pct = 0f, ord_pct = 0f, good_pct = 0f;
            float sum_total = 0f, sum_sleep = 0f, sum_up = 0f;
            int score = 0;
            int count = 0;
            // 获取用户最近的睡眠数据
            List<SleepInfo> sleepInfos = timeService.getUptimeHistory(user.getUserName(), start, "");
            for (SleepInfo info : sleepInfos) {
                count++;
                float total = Float.parseFloat(info.getTotalSleep());
                float sleep = DateUtil.timeToHours(info.getSleepTime());
                float up = DateUtil.timeToHours(info.getUpTime());
                int quality = info.getQuality();
                sum_total += total;
                sum_sleep += sleep;
                sum_up += up;
                switch (quality) {
                    case 0:
                        qlty_bad += 1;
                        break;
                    case 1:
                        qlty_ord += 1;
                        break;
                    case 2:
                        qlty_good += 1;
                        break;
                }
            }
            avg_total = NumUtil.get1Decimal(sum_total / count);
            avg_sleep = NumUtil.get1Decimal(sum_sleep / count);
            avg_up = NumUtil.get1Decimal(sum_up / count);
            bad_pct = NumUtil.get1Decimal((float)qlty_bad / count);
            ord_pct = NumUtil.get1Decimal((float)qlty_ord / count);
            good_pct = NumUtil.get1Decimal((float)qlty_good / count);
            score = 2 * (int)(bad_pct * 20 + ord_pct * 30 + good_pct * 50);

            // 将计算的数据保存到数据库
            RecentSleepInfo slpInfo = new RecentSleepInfo();
            slpInfo.setUsername(user.getUserName());
            slpInfo.setAvgTotal(avg_total);
            slpInfo.setAvgSleep(avg_sleep);
            slpInfo.setAvgUp(avg_up);
            slpInfo.setQltyBad(bad_pct);
            slpInfo.setQltyOrd(ord_pct);
            slpInfo.setQltyGood(good_pct);
            slpInfo.setScore(score);
            timeService.updRecentSleepInfo(slpInfo);
        }
        System.out.println("最近睡眠数据更新完毕...");
    }
}
