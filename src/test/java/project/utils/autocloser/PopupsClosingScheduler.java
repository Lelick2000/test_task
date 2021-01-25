package project.utils.autocloser;

import com.codeborne.selenide.WebDriverRunner;
import lombok.Getter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PopupsClosingScheduler {
    private static final long SCHEDULE_PERIOD = 4000;
    private static final long SCHEDULE_INITIAL_DELAY = 0;


    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> scheduledFuture;

    @Getter
    private PopupsClosingUtil popupsClosingUtil;

    public void startPopupsCloserSchedule() {
        popupsClosingUtil = new PopupsClosingUtil(WebDriverRunner.getWebDriver());
        scheduledFuture = scheduler.scheduleAtFixedRate(popupsClosingUtil,
                SCHEDULE_INITIAL_DELAY, SCHEDULE_PERIOD, TimeUnit.MILLISECONDS);
    }

    public void restartPopupsCloserSchedule() {
        cancelPopupsCloserSchedule();
        startPopupsCloserSchedule();
    }

    public void cancelPopupsCloserSchedule() {
        if (scheduledFuture == null) {
            return;
        }
        scheduledFuture.cancel(true);
    }

    public void shutdownPopupsCloserSchedule() {
        cancelPopupsCloserSchedule();
        scheduler.shutdown();
    }
}
