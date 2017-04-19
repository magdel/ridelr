package com.ridelr.site.scheduler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class StatisticLogger {
    private static final Logger logger = LoggerFactory.getLogger(StatisticLogger.class);
    private static final long MIN_MILLIS = 1000L * 60;

    public StatisticLogger() {
    }

    @Scheduled(fixedDelay = MIN_MILLIS)
    public void checkStatistic() {
        logger.info("Writing some stat");
    }

}
